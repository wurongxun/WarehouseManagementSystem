package com.shiro.test.mvc.service.impl;

import com.shiro.test.mvc.dao.CommodityInformationMapper;
import com.shiro.test.mvc.pojo.CommodityInformation;
import com.shiro.test.mvc.service.CommodityInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description CommodityInformationServiceImpl
 * @Author Rongxun.Wu
 * @Date 2020/2/4 15:46
 * @Version 1.0
 */
@Service
public class CommodityInformationServiceImpl implements CommodityInformationService {

    @Autowired
    CommodityInformationMapper commodityInformationMapper;

    @Override
    public List<CommodityInformation> QureyAllCommodityInformation() {
        return commodityInformationMapper.QureyAllCommodityInformation();
    }

    @Override
    public int insertSelective(CommodityInformation record) {
        return commodityInformationMapper.insertSelective(record);
    }

    @Override
    public CommodityInformation selectByPrimaryKey(CommodityInformation key) {
        return commodityInformationMapper.selectByPrimaryKey(key);
    }

    @Override
    public int deleteByPrimaryKey(CommodityInformation key) {
       return commodityInformationMapper.deleteByPrimaryKey(key);
    }

    @Override
    public List<CommodityInformation> findLikeCommodityInformationList(CommodityInformation commodityInformation) {
        return commodityInformationMapper.findLikeCommodityInformationList(commodityInformation);
    }

    @Override
    public int updateByPrimaryKeySelective(CommodityInformation record) {
        return commodityInformationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public CommodityInformation selectByPrimaryCommodityId(String commodityId) {
        return commodityInformationMapper.selectByPrimaryCommodityId(commodityId);
    }
}
