package com.shiro.test.mvc.service.impl;

import com.shiro.test.mvc.dao.CommodityClassInformationMapper;
import com.shiro.test.mvc.pojo.CommodityClassInformation;
import com.shiro.test.mvc.service.CommoditClassInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description CommoditClassInformationServiceImpl
 * @Author Rongxun.Wu
 * @Date 2020/3/3 0:19
 * @Version 1.0
 */
@Service
public class CommoditClassInformationServiceImpl implements CommoditClassInformationService {

    @Autowired
    private CommodityClassInformationMapper commodityClassInformationMapper;

    @Override
    public CommodityClassInformation selectByPrimaryCode(CommodityClassInformation commodityClassInformation) {
        return commodityClassInformationMapper.selectByPrimaryCode(commodityClassInformation);
    }
}
