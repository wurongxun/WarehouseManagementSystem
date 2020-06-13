package com.shiro.test.mvc.service.impl;

import com.shiro.test.mvc.dao.WarehouseInformationMapper;
import com.shiro.test.mvc.pojo.WarehouseInformation;
import com.shiro.test.mvc.service.WarehouseInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description WarehouseInformationServiceImpl
 * @Author Rongxun.Wu
 * @Date 2020/2/9 22:17
 * @Version 1.0
 */

@Service
public class WarehouseInformationServiceImpl implements WarehouseInformationService {

    @Autowired
    WarehouseInformationMapper warehouseInformationMapper;

    @Override
    public List<WarehouseInformation> QureyAllWarehouseInformation() {
        return warehouseInformationMapper.QureyAllWarehouseInformation();
    }

    @Override
    public int insertSelective(WarehouseInformation record) {
        return warehouseInformationMapper.insertSelective(record);
    }

    @Override
    public WarehouseInformation selectByPrimaryKey(String id) {
        return warehouseInformationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WarehouseInformation record) {
        return warehouseInformationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<WarehouseInformation> find_likeWarehouseInformationList(WarehouseInformation record) {
        return warehouseInformationMapper.find_likeWarehouseInformationList(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return warehouseInformationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WarehouseInformation selectByWarehouseId(String warehouseId) {
        return warehouseInformationMapper.selectByWarehouseId(warehouseId);
    }

    @Override
    public WarehouseInformation selectByWarehouseNumber(Integer warehouseNumber) {
        return warehouseInformationMapper.selectByWarehouseNumber(warehouseNumber);
    }
}
