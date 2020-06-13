package com.shiro.test.mvc.dao;

import com.shiro.test.mvc.pojo.SupplierClassInformation;

import java.util.List;

public interface SupplierClassInformationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_supplier_class_information
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_supplier_class_information
     *
     * @mbggenerated
     */
    int insert(SupplierClassInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_supplier_class_information
     *
     * @mbggenerated
     */
    int insertSelective(SupplierClassInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_supplier_class_information
     *
     * @mbggenerated
     */
    SupplierClassInformation selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_supplier_class_information
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SupplierClassInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_supplier_class_information
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SupplierClassInformation record);

    List<SupplierClassInformation> queryAllSupplierClssList(SupplierClassInformation supplierClassInformation);

    List<SupplierClassInformation> queryAllSupplierLargeClass();


}