package com.shiro.test.mvc.service;

import com.shiro.test.mvc.pojo.GoodsShelvesInformation;

import java.util.List;

/**
 * @Description GoodsShelvesInformationService
 * @Author Rongxun.Wu
 * @Date 2020/2/10 15:35
 * @Version 1.0
 */
public interface GoodsShelvesInformationService {

    List<GoodsShelvesInformation> QureyAllGoodsShelvesInformation();

    int insertSelective(GoodsShelvesInformation record);

    GoodsShelvesInformation selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsShelvesInformation record);

    List<GoodsShelvesInformation> find_likeGoodsShelvesInformationList(GoodsShelvesInformation record);

    Integer CountWarehouseId(String warehouseId);

    List<GoodsShelvesInformation> selectByPrimaryWarehouseId(String warehouseId);

    GoodsShelvesInformation selectByPrimaryByGoodsShelvesId(String goodsShelvesId);
}
