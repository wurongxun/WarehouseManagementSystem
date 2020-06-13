package com.shiro.test.mvc.service.impl;

import com.shiro.test.mvc.base.Status.shelfPositionisItEmptyStatus;
import com.shiro.test.mvc.base.application.web.json.WebJsonResult;
import com.shiro.test.mvc.base.tool.StringUtil;
import com.shiro.test.mvc.dao.OutOfStockMapper;
import com.shiro.test.mvc.pojo.ActiveUser;
import com.shiro.test.mvc.pojo.DetailedListInformation;
import com.shiro.test.mvc.pojo.OutOfStock;
import com.shiro.test.mvc.pojo.ShelfPositionInformation;
import com.shiro.test.mvc.service.CommodityDetailedListBeanService;
import com.shiro.test.mvc.service.DetailedListInformationService;
import com.shiro.test.mvc.service.OutOfStockInformationService;
import com.shiro.test.mvc.service.ShelfPositionInformationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Description OutOfStockServiceImpl
 * @Author Rongxun.Wu
 * @Date 2020/2/10 15:57
 * @Version 1.0
 */

@Transactional
@Service
public class OutOfStockInformationServiceImpl implements OutOfStockInformationService {

    @Autowired
    private DetailedListInformationService detailedListInformationService;

    @Autowired
    private CommodityDetailedListBeanService commodityDetailedListBeanService;

    @Autowired
    private ShelfPositionInformationService shelfPositionInformationService;

    @Autowired
    private OutOfStockInformationService stockService;

    @Autowired
    OutOfStockMapper outOfStockMapper;

    @Override
    public List<OutOfStock> QureyAllOutOfStock() {
        return outOfStockMapper.QureyAllOutOfStock();
    }

    @Override
    public WebJsonResult DetermineOutOfStock(DetailedListInformation detailedListInformation) {

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        Date Create = new Date();
        if (detailedListInformation.getStatus() != null
                && StringUtil.isNotEmpty(detailedListInformation.getDetailedListId())
                && StringUtil.isNotEmpty(detailedListInformation.getId())) {
            //确定出库人信息
            detailedListInformation.setStaffId(activeUser.getId());
            /*detailedListInformation.setStaffCode(activeUser.get);*/
            detailedListInformation.setStaffName(activeUser.getUserName());

            /*获取该申请单下 使用架位的信息 ID*/
            List<ShelfPositionInformation> positionInformations=shelfPositionInformationService.selectShelfPositionDetailedListOutOfStockId(detailedListInformation.getDetailedListId());
            List<ShelfPositionInformation> positionInformations2=new ArrayList<>();
            ShelfPositionInformation shelfPositionInformation=new ShelfPositionInformation();
            for (ShelfPositionInformation s:positionInformations
                 ) {
                shelfPositionInformation=s;
                shelfPositionInformation.setUsageRecord(s.getUsageRecord()+1);
                positionInformations2.add(shelfPositionInformation);
            }
            //改变架位状态位为 未存储
            Map<String, Object> map = new HashMap<>();
            map.put("isItEmpty", shelfPositionisItEmptyStatus.Unsaved.getCode());
            map.put("detailedListOutOfStockId", detailedListInformation.getDetailedListId());
            if (shelfPositionInformationService.updateCancelApplicationFormStatusChange_OutOfStock(map) != 0&&positionInformations.size()>0) {
                int status = detailedListInformationService.updateByPrimaryKeySelective(detailedListInformation);
                /*架位使用次数加1*/
                int status2=shelfPositionInformationService.updateShelfPositionUsageRecord(positionInformations2);
                System.out.println("status2:  "+status2+"  status:  "+status);
                if (status != 0&&status2>0) {
                    return WebJsonResult.newSuccess("确定出库成功");
                } else {
                    return WebJsonResult.newFailure("确定出库成出错");
                }
            } else {
                return WebJsonResult.newFailure("服务器提示：修改架位状态出错");
            }

            //修改入库清单状态

        } else {
            return WebJsonResult.newFailure("传入服务器数据错误");
        }


    }
}
