package com.shiro.test.mvc.service;/**
 * @Description SupplierClassInformationService
 * @Author Rongxun.Wu
 * @Date 2020/2/25 23:20
 * @Version 1.0
 */

import com.shiro.test.mvc.pojo.SupplierClassInformation;

import java.util.List;

/**
 *
 * @ClassName SupplierClassInformationService
 * @Description SupplierClassInformationService
 * @Author Rongxun.Wu
 * @Date 2020/2/25 23:20
 * @Version 1.0
 *
 */
public interface SupplierClassInformationService {

    List<SupplierClassInformation> queryAllSupplierClssList( SupplierClassInformation supplierClassInformation );

    List<SupplierClassInformation> queryAllSupplierLargeClass();
}
