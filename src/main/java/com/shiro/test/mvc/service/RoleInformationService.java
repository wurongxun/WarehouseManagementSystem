package com.shiro.test.mvc.service;

import com.shiro.test.mvc.base.application.web.json.WebJsonResult;

import com.shiro.test.mvc.pojo.RoleInformation;

import java.util.List;
import java.util.Map;

public interface RoleInformationService {
    List<RoleInformation> getRoleList();
    /*一个角色分配多个菜单*/
    WebJsonResult addRoleMenuInformation(String roleId, String[] menuIds);

    List<String> getRoleMenuIds(String roleId);
    int deleteByPrimaryKey(String id);

    List<RoleInformation> FindLikeRoleInformation(Map map);

    int insertSelective(RoleInformation record);

    int updateByPrimaryKey(RoleInformation record);

    int updateByPrimaryKeySelective(RoleInformation record);

    RoleInformation selectByPrimaryKey(String id);
}
