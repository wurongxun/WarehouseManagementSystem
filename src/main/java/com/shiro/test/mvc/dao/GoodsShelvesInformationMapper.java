package com.shiro.test.mvc.dao;

import com.shiro.test.mvc.pojo.GoodsShelvesInformation;

import java.util.List;

public interface GoodsShelvesInformationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_shelves_information
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_shelves_information
     *
     * @mbggenerated
     */
    int insert(GoodsShelvesInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_shelves_information
     *
     * @mbggenerated
     */
    int insertSelective(GoodsShelvesInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_shelves_information
     *
     * @mbggenerated
     */
    GoodsShelvesInformation selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_shelves_information
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(GoodsShelvesInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_goods_shelves_information
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(GoodsShelvesInformation record);

    List<GoodsShelvesInformation> QureyAllGoodsShelvesInformation();

    List<GoodsShelvesInformation> find_likeGoodsShelvesInformationList(GoodsShelvesInformation record);

    Integer CountWarehouseId(String warehouseId);

    List<GoodsShelvesInformation> selectByPrimaryWarehouseId(String warehouseId);

    GoodsShelvesInformation selectByPrimaryByGoodsShelvesId(String goodsShelvesId);
}