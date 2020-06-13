package com.shiro.test.mvc.filter;

import com.shiro.test.mvc.base.tool.StringUtil;
import com.shiro.test.mvc.dao.MenuInformationMapper;
import com.shiro.test.mvc.dao.RoleInformationMapper;
import com.shiro.test.mvc.dao.RoleMenuInformationMapper;
import com.shiro.test.mvc.pojo.*;
import com.shiro.test.mvc.service.MenuInformationService;
import com.shiro.test.mvc.service.RoleInformationService;
import com.shiro.test.mvc.service.UserInformationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserInformationService userService;
    @Autowired
    private RoleInformationService roleService;

    @Autowired
    private MenuInformationService menuInformationService;

    //每次验证权限执行
    /**
     * 为当限前登录的用户授予角色和权
     */
   /* @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        // TODO Auto-generated method stub
        ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();
        //根据用户名查找拥有的角色
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (activeUser.getRole()!=null){
          RoleInformation r=  activeUser.getRole();
            System.out.println("用户拥有的角色名： "+r.getRoleName());
            info.addRole(r.getRoleName());
            return info;
        } else {
            return null;
        }

    }*/
    //登录执行
    /**
     * 为当前登录的用户授予角色和权
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /*拿到用户名*/
        String email= (String) token.getPrincipal();
        UserInformation user=userService.getUserByEmail(email);
        System.out.println("类AuthenticationInfo  UserInformation   "+user.toString());
        if (user!=null){
            //更具用户ID查询用户拥有的角色id
            String roleIds=userService.getUserRoleIds(user.getId());
            RoleInformation role=roleService.selectByPrimaryKey(roleIds);
           // log.println("用户拥有的角色 "+role);
            ActiveUser activeUser=new ActiveUser(user.getId(),user.getUserName(),user.getEmail(),role);

            //不等于空 返回给shiro SimpleAuthenticationInfo(Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName) {
            return new SimpleAuthenticationInfo(activeUser,user.getPassword(), ByteSource.Util.bytes(user.getEmail()),getName());//getName()  返回数据源的名字  user.getPassword()为数据库查询到的密码
        }
        return null;
    }

    public AuthorizationInfo getAuthorizationInfoC(){
        return getAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

    /**
     * 为当限前登录的用户授予角色和权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("认证成功进行授权中...");
        ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();

        System.out.println("角色名： "+activeUser.getRole().getRoleName());

        //SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> role = new HashSet<>();
        Set<String> Permissions = new HashSet<>();
        role.add(activeUser.getRole().getRoleName());
        List<MenuPermission> menuInformations = menuInformationService.getMenuPermission();
        for (MenuPermission m:menuInformations) {
            for (RoleInformation roleInformation:m.getRoleIds()) {
            /*  if (StringUtil.isEquals(roleInformation.getRoleName(),activeUser.getRole().getRoleName())) {*/
                  Permissions.add(m.getUrl());
            /*  }*/
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(role);
       // authorizationInfo.setRoles(role);
        //authorizationInfo.setStringPermissions(Permissions);
        return authorizationInfo;

    }

}
