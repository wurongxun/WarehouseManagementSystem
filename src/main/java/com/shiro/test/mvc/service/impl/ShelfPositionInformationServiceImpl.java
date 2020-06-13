package com.shiro.test.mvc.service.impl;


import com.shiro.test.mvc.base.Bean.PositionCommodityCountInformationBean;
import com.shiro.test.mvc.dao.ShelfPositionInformationMapper;
import com.shiro.test.mvc.pojo.ShelfPositionInformation;
import com.shiro.test.mvc.service.ShelfPositionInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description ShelfPositionInformationServiceImpl
 * @Author Rongxun.Wu
 * @Date 2020/2/9 22:46
 * @Version 1.0
 */

@Transactional
@Service
public class ShelfPositionInformationServiceImpl implements ShelfPositionInformationService {

    @Autowired
    ShelfPositionInformationMapper shelfPositionInformationMapper;

    @Override
    public List<ShelfPositionInformation> QureyAllShelfPositionInformation() {
        return shelfPositionInformationMapper.QureyAllShelfPositionInformation();
    }

    @Override
    public int AddShelfPositionInformation_Batch(List<ShelfPositionInformation> list) {
        return shelfPositionInformationMapper.AddShelfPositionInformation_Batch(list);
    }

    @Override
    public List<ShelfPositionInformation> Find_LikeQureyAllShelfPositionInformation(ShelfPositionInformation shelfPositionInformation) {
        return shelfPositionInformationMapper.Find_LikeQureyAllShelfPositionInformation(shelfPositionInformation);
    }

    @Override
    public Integer FindShelfPositionNumber(Map map) {
        return shelfPositionInformationMapper.FindShelfPositionNumber(map);
    }

    @Override
    public Integer updateIsItEmptyBatch(List<ShelfPositionInformation> shelfPositionInformations) {
        return shelfPositionInformationMapper.updateIsItEmptyBatch(shelfPositionInformations);
    }

    @Override
    public List<ShelfPositionInformation> Find_ShelfPositionInformation_SpecifiedQuantity(Map map) {
        return shelfPositionInformationMapper.Find_ShelfPositionInformation_SpecifiedQuantity(map);
    }

    @Override
    public Integer updateIsItEmptyDetermineWarehousing(Map map) {
        return shelfPositionInformationMapper.updateIsItEmptyDetermineWarehousing(map);
    }

    @Override
    public Integer updateCancelApplicationFormStatusChange_Warehousing(Map map) {
        return shelfPositionInformationMapper.updateCancelApplicationFormStatusChange_Warehousing(map);
    }

    @Override
    public List<PositionCommodityCountInformationBean> SelectPositionCommodityId() {
        return shelfPositionInformationMapper.SelectPositionCommodityId();
    }

    @Override
    public Integer SelectPositionCommodityIdCount(String CommodityId) {
        return shelfPositionInformationMapper.SelectPositionCommodityIdCount(CommodityId);
    }

    @Override
    public Integer updateCancelApplicationFormStatusChange_OutOfStock(Map map) {
        return shelfPositionInformationMapper.updateCancelApplicationFormStatusChange_OutOfStock(map);
    }

    @Override
    public List<ShelfPositionInformation> selectShelfPositionDetailedListOutOfStockId(String detailedListOutOfStockId) {
        return shelfPositionInformationMapper.selectShelfPositionDetailedListOutOfStockId(detailedListOutOfStockId);
    }

    @Override
    public int updateShelfPositionUsageRecord(List<ShelfPositionInformation> shelfPositionInformations) {
        return shelfPositionInformationMapper.updateShelfPositionUsageRecord(shelfPositionInformations);
    }

    @Override
    public Integer Find_ShelfPositionIsItEmpty(Integer isItEmpty) {
        return shelfPositionInformationMapper.Find_ShelfPositionIsItEmpty(isItEmpty);
    }


}
