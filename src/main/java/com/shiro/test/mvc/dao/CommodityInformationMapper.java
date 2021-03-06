package com.shiro.test.mvc.dao;

import com.shiro.test.mvc.pojo.CommodityInformation;
import com.shiro.test.mvc.pojo.CommodityInformationKey;

import java.util.List;

public interface CommodityInformationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_commodity_information
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(CommodityInformation key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_commodity_information
     *
     * @mbggenerated
     */
    int insert(CommodityInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_commodity_information
     *
     * @mbggenerated
     */
    int insertSelective(CommodityInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_commodity_information
     *
     * @mbggenerated
     */
    CommodityInformation selectByPrimaryKey(CommodityInformation key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_commodity_information
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CommodityInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_commodity_information
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CommodityInformation record);

    List<CommodityInformation> QureyAllCommodityInformation();

    List<CommodityInformation> findLikeCommodityInformationList(CommodityInformation commodityInformation);

    CommodityInformation selectByPrimaryCommodityId(String commodityId);
}