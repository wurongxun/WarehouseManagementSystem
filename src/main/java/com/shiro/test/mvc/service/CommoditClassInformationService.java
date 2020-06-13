package com.shiro.test.mvc.service;

import com.shiro.test.mvc.pojo.CommodityClassInformation;

/**
 * @Description CommoditClassInformationService
 * @Author Rongxun.Wu
 * @Date 2020/3/3 0:09
 * @Version 1.0
 */
public interface CommoditClassInformationService {

    CommodityClassInformation selectByPrimaryCode(CommodityClassInformation commodityClassInformation);
}
