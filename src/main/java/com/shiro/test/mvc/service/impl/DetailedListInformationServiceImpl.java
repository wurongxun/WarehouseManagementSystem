package com.shiro.test.mvc.service.impl;

import com.shiro.test.mvc.base.Bean.DetailedListCommodityInformationBean;
import com.shiro.test.mvc.base.Status.DetailedListType;
import com.shiro.test.mvc.base.Status.detailedListStatus;
import com.shiro.test.mvc.base.Status.shelfPositionisItEmptyStatus;
import com.shiro.test.mvc.base.tool.RandomNumber;
import com.shiro.test.mvc.base.tool.UID;
import com.shiro.test.mvc.dao.CommodityDetailedListBeanMapper;
import com.shiro.test.mvc.dao.DetailedListInformationMapper;
import com.shiro.test.mvc.dao.ShelfPositionInformationMapper;
import com.shiro.test.mvc.pojo.*;
import com.shiro.test.mvc.service.CommodityInformationService;
import com.shiro.test.mvc.service.DetailedListInformationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Description DetailedListInformationServiceImpl
 * @Author Rongxun.Wu
 * @Date 2020/2/10 16:16
 * @Version 1.0
 */
@Transactional
@Service
public class DetailedListInformationServiceImpl implements DetailedListInformationService {

    @Autowired
    CommodityInformationService commodityInformationService;

    @Autowired
    ShelfPositionInformationMapper shelfPositionInformationMapper;

    @Autowired
    CommodityDetailedListBeanMapper commodityDetailedListBeanMapper;
    @Autowired
    DetailedListInformationMapper detailedListInformationMapper;

    @Override
    public List<DetailedListInformation> QureyAllDetailedListInformation(Map map) {
        return detailedListInformationMapper.QureyAllDetailedListInformation(map);
    }

    /*申请入库*/
    @Override
    public int AddDetailedList(DetailedListCommodityInformationBean detailedListCommodityInformation) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        Date Create = new Date();

        //添加 入库清单
        DetailedListInformation detailedListInformation = new DetailedListInformation();
        detailedListInformation.setId(UID.getUID32());
        detailedListInformation.setApplicantName(activeUser.getUserName());
        /*  detailedListInformation.setApplicantCode(activeUser.);*/
        detailedListInformation.setApplicantId(activeUser.getId());
        detailedListInformation.setCreateBy(activeUser.getUserName());
        detailedListInformation.setCreateDate(Create);
        detailedListInformation.setDetailedListId(UID.getUID32());
        detailedListInformation.setDetailedListCode("RKXX" +
                "-" + RandomNumber.GetRandom2() +
                "-" + String.format("%04d", detailedListCommodityInformation.getTotalQuantity()));//RKXX-XXXX-XXXX-XXXX—XXXX-数量
        detailedListInformation.setDetailedListName(detailedListCommodityInformation.getDetailedListName());
        detailedListInformation.setCommodityNumber(detailedListCommodityInformation.getTotalQuantity());
        /*detailedListInformation.setStaffCode();*/
        detailedListInformation.setDetailedListType(DetailedListType.Warehousing.getCode());
        detailedListInformation.setReceivingDelivererPersonName(detailedListCommodityInformation.getReceivingDelivererPersonName());
        detailedListInformation.setReceivingDelivererPersonPhone(detailedListCommodityInformation.getReceivingDelivererPersonPhone());
        detailedListInformation.setReceivingDelivererPersonAddress(detailedListCommodityInformation.getReceivingDelivererPersonAddress());
        detailedListInformation.setTotalSum(detailedListCommodityInformation.getTotalSum());
        detailedListInformation.setStatus(detailedListStatus.ToBeAudited.getCode());
        int status = detailedListInformationMapper.insertSelective(detailedListInformation);
        if (status != 0) {//如果清单添加成功
            /*
             * 修改架位状态开始 状态位3  shelfPositionisItEmptyStatus.HasBeenSelected.getCode() 同时往数据库中添加清单表内
             *添加商品的具体信息   当前清单状态为  待审核
             * */
            CommodityDetailedListBean commodityDetailedListBean;
            List<CommodityDetailedListBean> commodityDetaidLikeBeanList = new ArrayList<>();
            for (int i = 0; i < detailedListCommodityInformation.getCommodityDetaidLikeBeanList().size(); i++) {
                commodityDetailedListBean = detailedListCommodityInformation.getCommodityDetaidLikeBeanList().get(i);
                CommodityInformation commodityInformation=commodityInformationService.selectByPrimaryCommodityId(commodityDetailedListBean.getCommodityId());
                commodityDetailedListBean.setId(UID.getUID32());
                commodityDetailedListBean.setCreateDate(detailedListInformation.getCreateDate());
                commodityDetailedListBean.setCreateBy(detailedListInformation.getCreateBy());
                commodityDetailedListBean.setCommodityDetaidListBeanId(UID.getUID32());
                commodityDetailedListBean.setDetailedListId(detailedListInformation.getDetailedListId());
                commodityDetailedListBean.setDetailedListCode(detailedListInformation.getDetailedListCode());
                commodityDetailedListBean.setCommodityDetaidListBeanId(UID.getUID32());
                commodityDetailedListBean.setSupplierCode(commodityInformation.getSupplierCode());
                commodityDetailedListBean.setSupplierName(commodityInformation.getSupplierName());
                commodityDetailedListBean.setSupplierPhone(commodityInformation.getSupplierPhone());
                commodityDetailedListBean.setCommodityMn(commodityInformation.getCommodityMn());
                //1. 获取架位信息
                Map<String, Object> map = new HashMap<>();
                map.put("warehouseType", commodityDetailedListBean.getCommodityStorageType());
                map.put("isItEmpty", shelfPositionisItEmptyStatus.Unsaved.getCode());
                map.put("count", commodityDetailedListBean.getCount());
                //得到架位信息
                List<ShelfPositionInformation> shelfPositionInformation = shelfPositionInformationMapper.Find_ShelfPositionInformation_SpecifiedQuantity(map);
                //改变状态
                List<ShelfPositionInformation> shelfPositionIsItEmptyEmptyBatch = new ArrayList<>();//更新接收
                for (int j = 0; j < shelfPositionInformation.size(); j++) {
                    ShelfPositionInformation shelfPositionInformation_1 = shelfPositionInformation.get(j);//更新
                    shelfPositionInformation_1.setIsItEmpty(shelfPositionisItEmptyStatus.HasBeenSelected.getCode());
                    shelfPositionInformation_1.setCommodityDetaidListBeanWarehousingId(commodityDetailedListBean.getCommodityDetaidListBeanId());//所属ID
                    shelfPositionInformation_1.setDetailedListWarehousingId(detailedListInformation.getDetailedListId());
                    shelfPositionInformation_1.setCommodityId(commodityDetailedListBean.getCommodityId());
                    shelfPositionIsItEmptyEmptyBatch.add(shelfPositionInformation_1);
                }
                //修改架位状态 并。。。
                for (ShelfPositionInformation s : shelfPositionIsItEmptyEmptyBatch
                ) {
                    System.out.println(s.toString());
                }
                int state = shelfPositionInformationMapper.updateIsItEmptyBatch(shelfPositionIsItEmptyEmptyBatch);

                //更新成功 添加到bean
                if (state != 0) {
                    commodityDetaidLikeBeanList.add(commodityDetailedListBean);
                }
            }
            //往数据库中添加清单表内添加商品的具体信息
            int state1 = commodityDetailedListBeanMapper.AddCommodityDetailedListBean_Batch(commodityDetaidLikeBeanList);
            if (state1 != 0) {
                return state1;
            }
        }
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(DetailedListInformation record) {
        return detailedListInformationMapper.updateByPrimaryKeySelective(record);
    }

    /*申请出库*/
    @Override
    public int ApplicantOutOfStockEntryInformation(DetailedListCommodityInformationBean detailedListCommodityInformation) {

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        Date Create = new Date();

        //添加 出库清单
        DetailedListInformation detailedListInformation = new DetailedListInformation();
        detailedListInformation.setId(UID.getUID32());
        detailedListInformation.setApplicantName(activeUser.getUserName());
        /*  detailedListInformation.setApplicantCode(activeUser.);*/
        detailedListInformation.setApplicantId(activeUser.getId());
        detailedListInformation.setCreateBy(activeUser.getUserName());
        detailedListInformation.setCreateDate(Create);
        detailedListInformation.setDetailedListId(UID.getUID32());
        detailedListInformation.setDetailedListCode("CKXX" +
                "-" + RandomNumber.GetRandom2() +
                "-" + String.format("%04d", detailedListCommodityInformation.getTotalQuantity()));//RKXX-XXXX-XXXX-XXXX—XXXX-数量
        detailedListInformation.setDetailedListName(detailedListCommodityInformation.getDetailedListName());
        detailedListInformation.setCommodityNumber(detailedListCommodityInformation.getTotalQuantity());
        /*detailedListInformation.setStaffCode();*/
        detailedListInformation.setDetailedListType(DetailedListType.OutOfStock.getCode());
        detailedListInformation.setReceivingDelivererPersonName(detailedListCommodityInformation.getReceivingDelivererPersonName());
        detailedListInformation.setReceivingDelivererPersonPhone(detailedListCommodityInformation.getReceivingDelivererPersonPhone());
        detailedListInformation.setReceivingDelivererPersonAddress(detailedListCommodityInformation.getReceivingDelivererPersonAddress());
        detailedListInformation.setTotalSum(detailedListCommodityInformation.getTotalSum());
        detailedListInformation.setStatus(detailedListStatus.ToBeAudited.getCode());
        int status = detailedListInformationMapper.insertSelective(detailedListInformation);
        if (status != 0) {//如果入库清单添加成功
            /*
             * 修改架位状态开始 状态位3  detailedListStatus.HasBeenSelected.getCode() 同时往数据库中添加清单表内
             *添加商品的具体信息   当前清单状态为  待审核
             * */
            CommodityDetailedListBean commodityDetailedListBean;
            List<CommodityDetailedListBean> commodityDetaidLikeBeanList = new ArrayList<>();
            for (int i = 0; i < detailedListCommodityInformation.getCommodityDetaidLikeBeanList().size(); i++) {
                commodityDetailedListBean = detailedListCommodityInformation.getCommodityDetaidLikeBeanList().get(i);
                //获取商品信息
                CommodityInformation commodityInformation=commodityInformationService.selectByPrimaryCommodityId(commodityDetailedListBean.getCommodityId());

                commodityDetailedListBean.setId(UID.getUID32());
                commodityDetailedListBean.setCreateDate(detailedListInformation.getCreateDate());
                commodityDetailedListBean.setCreateBy(detailedListInformation.getCreateBy());
                commodityDetailedListBean.setCommodityDetaidListBeanId(UID.getUID32());
                commodityDetailedListBean.setDetailedListId(detailedListInformation.getDetailedListId());
                commodityDetailedListBean.setDetailedListCode(detailedListInformation.getDetailedListCode());
                commodityDetailedListBean.setCommodityDetaidListBeanId(UID.getUID32());
                commodityDetailedListBean.setSupplierCode(commodityInformation.getSupplierCode());
                commodityDetailedListBean.setSupplierName(commodityInformation.getSupplierName());
                commodityDetailedListBean.setSupplierPhone(commodityInformation.getSupplierPhone());
                commodityDetailedListBean.setCommodityMn(commodityInformation.getCommodityMn());
                commodityDetailedListBean.setCommodityUnitPrice(commodityInformation.getCommodityUnitPrice());

                //1. 获取已存储架位以及对应存储的商品信息
                Map<String, Object> map = new HashMap<>();
                map.put("CommodityId",commodityDetailedListBean.getCommodityId());//价位存储的商品
                map.put("warehouseType", commodityDetailedListBean.getCommodityStorageType());
                map.put("isItEmpty", shelfPositionisItEmptyStatus.AlreadyStored.getCode());//获取已存储价位
                map.put("count", commodityDetailedListBean.getCount());//需要的数量
                //得到架位信息
                List<ShelfPositionInformation> shelfPositionInformation = shelfPositionInformationMapper.Find_ShelfPositionInformation_SpecifiedQuantity(map);
                //改变状态 状态4(已被出库单选中）
                List<ShelfPositionInformation> shelfPositionIsItEmptyEmptyBatch = new ArrayList<>();//用来接收更新后的List
                for (int j = 0; j < shelfPositionInformation.size(); j++) {
                    ShelfPositionInformation shelfPositionInformation_1 = shelfPositionInformation.get(j);//更新
                    shelfPositionInformation_1.setIsItEmpty(shelfPositionisItEmptyStatus.OutOfStockSelected.getCode());//状态修改 改为4
                    shelfPositionInformation_1.setCommodityDetaidListBeanOutOfStockId(commodityDetailedListBean.getCommodityDetaidListBeanId());//所属Bean  ID
                    shelfPositionInformation_1.setDetailedListOutOfStockId(detailedListInformation.getDetailedListId());
                    /*shelfPositionInformation_1.setCommodityId(commodityDetailedListBean.getCommodityId());*/
                    shelfPositionIsItEmptyEmptyBatch.add(shelfPositionInformation_1);
                }
                //修改架位状态 并。。。
                for (ShelfPositionInformation s : shelfPositionIsItEmptyEmptyBatch
                ) {
                    System.out.println(s.toString());
                }
                int state = shelfPositionInformationMapper.updateIsItEmptyBatch(shelfPositionIsItEmptyEmptyBatch);

                //更新成功 添加到bean
                if (state != 0) {
                    commodityDetaidLikeBeanList.add(commodityDetailedListBean);
                }
            }
            //往数据库中添加清单表内添加商品的具体信息
            int state1 = commodityDetailedListBeanMapper.AddCommodityDetailedListBean_Batch(commodityDetaidLikeBeanList);
            if (state1 != 0) {
                return state1;
            }
        }
        return 0;

    }

    @Override
    public Integer SelectWarehousingOutOfStackCount(Map map) {
        return detailedListInformationMapper.SelectWarehousingOutOfStackCount(map);
    }

    @Override
    public Integer SelectDetailedListTypeCount(Map map) {
        return detailedListInformationMapper.SelectDetailedListTypeCount(map);
    }

    @Override
    public Integer SelectDetailedListApplicantIdCount(Map map) {
        return detailedListInformationMapper.SelectDetailedListApplicantIdCount(map);
    }


}
