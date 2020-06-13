package com.shiro.test.mvc.service;

import com.shiro.test.mvc.pojo.RoleInformation;
import com.shiro.test.mvc.pojo.UserInformation;

import java.util.List;
import java.util.Map;

/**
 * @Description UserInformationService
 * @Author Rongxun.Wu
 * @Date 2020/3/1 11:18
 * @Version 1.0
 */
public interface UserInformationService {

    UserInformation getUserByEmail(String email);

    UserInformation doLogin(String email, String password);

    List<UserInformation> getUserList();

    String getUserRoleIds(String userId);

    Integer addUserRole(String userId,String roleId);

    List<RoleInformation> getUserRole(List<String> roleId);

    UserInformation getUser(Map map);

    Integer updataPassword(String password,String id);
    Integer  addUser(UserInformation user);

    UserInformation selectByPrimaryKey(String id);

    List<UserInformation> findLikeUserInformation(UserInformation userInformation);

    int insertSelective(UserInformation record);

    int deleteByPrimaryKey(String id);
}
