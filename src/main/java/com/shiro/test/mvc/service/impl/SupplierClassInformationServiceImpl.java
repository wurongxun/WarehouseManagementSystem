package com.shiro.test.mvc.service.impl;

import com.shiro.test.mvc.dao.SupplierClassInformationMapper;
import com.shiro.test.mvc.dao.SuppliersInformationMapper;
import com.shiro.test.mvc.pojo.SupplierClassInformation;
import com.shiro.test.mvc.service.SupplierClassInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description SupplierClassInformationServiceImpl
 * @Author Rongxun.Wu
 * @Date 2020/2/25 23:21
 * @Version 1.0
 */
@Service
public class SupplierClassInformationServiceImpl implements SupplierClassInformationService {

    @Autowired
    private SupplierClassInformationMapper supplierClassInformationMapper;
    @Override
    public List<SupplierClassInformation> queryAllSupplierClssList( SupplierClassInformation supplierClassInformation ) {
        return supplierClassInformationMapper.queryAllSupplierClssList(supplierClassInformation);
    }

    @Override
    public List<SupplierClassInformation> queryAllSupplierLargeClass() {
        return supplierClassInformationMapper.queryAllSupplierLargeClass();
    }
}
