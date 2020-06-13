package com.shiro.test.mvc.service;


import com.shiro.test.mvc.pojo.CommodityDetailedListBean;

import java.util.List;

/**
 * @Description CommodityDetailedListBeanService
 * @Author Rongxun.Wu
 * @Date 2020/3/25 13:05
 * @Version 1.0
 */
public interface CommodityDetailedListBeanService {

    int AddCommodityDetailedListBean_Batch(List<CommodityDetailedListBean> listBeans);

    int deleteByDetailedListIdKey(String DetailedListId);

    List<CommodityDetailedListBean> QueryCommodityDetailedListBeanGroupByCommodityId();
}
