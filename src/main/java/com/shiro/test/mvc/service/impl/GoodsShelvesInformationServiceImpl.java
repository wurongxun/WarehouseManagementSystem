package com.shiro.test.mvc.service.impl;

import com.shiro.test.mvc.dao.GoodsShelvesInformationMapper;
import com.shiro.test.mvc.pojo.GoodsShelvesInformation;
import com.shiro.test.mvc.service.GoodsShelvesInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description GoodsShelvesInformationServiceImpl
 * @Author Rongxun.Wu
 * @Date 2020/2/10 15:36
 * @Version 1.0
 */

@Service
public class GoodsShelvesInformationServiceImpl implements GoodsShelvesInformationService {
    @Autowired
    GoodsShelvesInformationMapper goodsShelvesInformationMapper;

    @Override
    public List<GoodsShelvesInformation> QureyAllGoodsShelvesInformation() {
        return goodsShelvesInformationMapper.QureyAllGoodsShelvesInformation();
    }

    @Transactional
    @Override
    public int insertSelective(GoodsShelvesInformation record) {
        return goodsShelvesInformationMapper.insertSelective(record);
    }

    @Override
    public GoodsShelvesInformation selectByPrimaryKey(String id) {
        return goodsShelvesInformationMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(String id) {
        return goodsShelvesInformationMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(GoodsShelvesInformation record) {
        return goodsShelvesInformationMapper.updateByPrimaryKeySelective(record);
    }

    public List<GoodsShelvesInformation> find_likeGoodsShelvesInformationList(GoodsShelvesInformation record){
        return goodsShelvesInformationMapper.find_likeGoodsShelvesInformationList(record);
    }

    @Override
    public Integer CountWarehouseId(String warehouseId) {
        return goodsShelvesInformationMapper.CountWarehouseId(warehouseId);
    }

    @Override
    public List<GoodsShelvesInformation> selectByPrimaryWarehouseId(String warehouseId) {
        return goodsShelvesInformationMapper.selectByPrimaryWarehouseId(warehouseId);
    }

    @Override
    public GoodsShelvesInformation selectByPrimaryByGoodsShelvesId(String goodsShelvesId) {
        return goodsShelvesInformationMapper.selectByPrimaryByGoodsShelvesId(goodsShelvesId);
    }
}
