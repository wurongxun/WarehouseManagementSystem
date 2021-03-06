package com.shiro.test.mvc.dao;

import com.shiro.test.mvc.pojo.RoleInformation;

import java.util.List;
import java.util.Map;

public interface RoleInformationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_shiro_role_information
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_shiro_role_information
     *
     * @mbggenerated
     */
    int insert(RoleInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_shiro_role_information
     *
     * @mbggenerated
     */
    int insertSelective(RoleInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_shiro_role_information
     *
     * @mbggenerated
     */
    RoleInformation selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_shiro_role_information
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RoleInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_shiro_role_information
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RoleInformation record);

    List<RoleInformation> getRoleList();

    List<RoleInformation> FindLikeRoleInformation(Map map);
}