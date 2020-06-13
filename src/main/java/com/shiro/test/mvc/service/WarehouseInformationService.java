package com.shiro.test.mvc.service;/**
 * @Description WarehouseInformationService
 * @Author Rongxun.Wu
 * @Date 2020/2/9 22:14
 * @Version 1.0
 */

import com.shiro.test.mvc.pojo.WarehouseInformation;

import java.util.List;

/**
 *
 * @ClassName WarehouseInformationService
 * @Description WarehouseInformationService
 * @Author Rongxun.Wu
 * @Date 2020/2/9 22:14
 * @Version 1.0
 *
 */
public interface WarehouseInformationService {

    List<WarehouseInformation> QureyAllWarehouseInformation();

    int insertSelective(WarehouseInformation record);

    WarehouseInformation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WarehouseInformation record);

    List<WarehouseInformation> find_likeWarehouseInformationList(WarehouseInformation record);

    int deleteByPrimaryKey(String id);

    WarehouseInformation selectByWarehouseId(String warehouseId);

    WarehouseInformation selectByWarehouseNumber(Integer warehouseNumber);

}
