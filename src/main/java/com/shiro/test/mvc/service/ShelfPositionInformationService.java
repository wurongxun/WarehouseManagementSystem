package com.shiro.test.mvc.service;/**
 * @Description ShelfPositionInformationService
 * @Author Rongxun.Wu
 * @Date 2020/2/9 22:46
 * @Version 1.0
 */

import com.shiro.test.mvc.base.Bean.PositionCommodityCountInformationBean;
import com.shiro.test.mvc.pojo.ShelfPositionInformation;

import java.util.List;
import java.util.Map;

public interface ShelfPositionInformationService {

    List<ShelfPositionInformation> QureyAllShelfPositionInformation();

    int  AddShelfPositionInformation_Batch(List<ShelfPositionInformation> list);
    List<ShelfPositionInformation> Find_LikeQureyAllShelfPositionInformation(ShelfPositionInformation shelfPositionInformation);

    Integer FindShelfPositionNumber(Map map);

    Integer updateIsItEmptyBatch(List<ShelfPositionInformation> shelfPositionInformations);

    List<ShelfPositionInformation>  Find_ShelfPositionInformation_SpecifiedQuantity(Map map);

    Integer updateIsItEmptyDetermineWarehousing(Map map);

    Integer  updateCancelApplicationFormStatusChange_Warehousing(Map map);

    List<PositionCommodityCountInformationBean> SelectPositionCommodityId();

    Integer SelectPositionCommodityIdCount(String CommodityId);

    Integer  updateCancelApplicationFormStatusChange_OutOfStock(Map map);

    /*按出库单ID查询*/
    List<ShelfPositionInformation>   selectShelfPositionDetailedListOutOfStockId(String DetailedListOutOfStockId);

    /*架位使用次数加1*/
    int updateShelfPositionUsageRecord(List<ShelfPositionInformation> shelfPositionInformations);

    /*查询状态数量*/
    Integer Find_ShelfPositionIsItEmpty(Integer isItEmpty);

}
