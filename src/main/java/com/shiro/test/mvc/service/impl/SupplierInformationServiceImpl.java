package com.shiro.test.mvc.service.impl;

import com.shiro.test.mvc.dao.SuppliersInformationMapper;
import com.shiro.test.mvc.pojo.SuppliersInformation;
import com.shiro.test.mvc.pojo.SuppliersInformationKey;
import com.shiro.test.mvc.service.SupplierInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class SupplierInformationServiceImpl implements SupplierInformationService {
    @Autowired
    SuppliersInformationMapper suppliersInformationMapper;

    @Override
    public List<SuppliersInformation> queryAllSupplier() {
        return suppliersInformationMapper.queryAllSupplier();
    }

    @Override
    public int insertSelective(SuppliersInformation record) {
        return suppliersInformationMapper.insertSelective(record);
    }

    @Override
    public int insert(SuppliersInformation record) {
        return suppliersInformationMapper.insert(record);
    }

    @Override
    public SuppliersInformation selectByPrimaryKey(SuppliersInformationKey key) {
        return suppliersInformationMapper.selectByPrimaryKey(key);
    }

    @Override
    public int updateByPrimaryKeySelective(SuppliersInformation record) {
        return suppliersInformationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(SuppliersInformationKey key) {
        return suppliersInformationMapper.deleteByPrimaryKey(key);
    }

    @Override
    public List<SuppliersInformation> findLikeSupplier(SuppliersInformation suppliersInformation) {
        return suppliersInformationMapper.findLikeSupplier(suppliersInformation);
    }

    @Override
    public SuppliersInformation selectByIfKey(SuppliersInformationKey key) {
        return suppliersInformationMapper.selectByIfKey(key);
    }
}
