package com.shiro.test.mvc.service;

import com.shiro.test.mvc.pojo.SuppliersInformation;
import com.shiro.test.mvc.pojo.SuppliersInformationKey;

import java.util.List;


public interface SupplierInformationService {
    List<SuppliersInformation> queryAllSupplier();

    int insertSelective(SuppliersInformation record);

    int insert(SuppliersInformation record);

    SuppliersInformation selectByPrimaryKey(SuppliersInformationKey key);

    int updateByPrimaryKeySelective(SuppliersInformation record);

    int deleteByPrimaryKey(SuppliersInformationKey key);

   List<SuppliersInformation> findLikeSupplier( SuppliersInformation suppliersInformation);

    SuppliersInformation selectByIfKey(SuppliersInformationKey key);
}
