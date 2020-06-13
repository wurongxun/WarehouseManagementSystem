package com.shiro.test.mvc.service;/**
 * @Description CommodityInformationService
 * @Author Rongxun.Wu
 * @Date 2020/2/4 15:42
 * @Version 1.0
 */

import com.shiro.test.mvc.pojo.CommodityInformation;

import java.util.List;

/**
 *
 * @ClassName CommodityInformationService
 * @Description CommodityInformationService
 * @Author Rongxun.Wu
 * @Date 2020/2/4 15:42
 * @Version 1.0
 *
 */
public interface CommodityInformationService {

    List<CommodityInformation> QureyAllCommodityInformation();

    int insertSelective(CommodityInformation record);

    CommodityInformation selectByPrimaryKey(CommodityInformation key);

    int deleteByPrimaryKey(CommodityInformation key);

    List<CommodityInformation> findLikeCommodityInformationList(CommodityInformation commodityInformation);

    int updateByPrimaryKeySelective(CommodityInformation record);

    CommodityInformation selectByPrimaryCommodityId(String commodityId);


}
