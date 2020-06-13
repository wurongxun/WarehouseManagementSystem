package com.shiro.test.mvc.service.impl;

import com.shiro.test.mvc.dao.CommodityDetailedListBeanMapper;
import com.shiro.test.mvc.pojo.CommodityDetailedListBean;
import com.shiro.test.mvc.service.CommodityDetailedListBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description CommodityDetailedListBeanServiceImpl
 * @Author Rongxun.Wu
 * @Date 2020/3/25 13:07
 * @Version 1.0
 */

@Service
public class CommodityDetailedListBeanServiceImpl implements CommodityDetailedListBeanService {

    @Autowired
    CommodityDetailedListBeanMapper commodityDetailedListBeanMapper;

    @Override
    public int AddCommodityDetailedListBean_Batch(List<CommodityDetailedListBean> listBeans) {
        return commodityDetailedListBeanMapper.AddCommodityDetailedListBean_Batch(listBeans);
    }

    @Override
    public int deleteByDetailedListIdKey(String DetailedListId) {
        return commodityDetailedListBeanMapper.deleteByDetailedListIdKey(DetailedListId);
    }

    @Override
    public List<CommodityDetailedListBean> QueryCommodityDetailedListBeanGroupByCommodityId() {
        return commodityDetailedListBeanMapper.QueryCommodityDetailedListBeanGroupByCommodityId();
    }
}
