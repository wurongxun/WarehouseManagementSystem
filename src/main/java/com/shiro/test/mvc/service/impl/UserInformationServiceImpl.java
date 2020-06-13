package com.shiro.test.mvc.service.impl;

import com.shiro.test.mvc.base.tool.Constants;
import com.shiro.test.mvc.base.tool.MD5Utils;
import com.shiro.test.mvc.dao.UserInformationMapper;
import com.shiro.test.mvc.dao.UserRoleInformationMapper;
import com.shiro.test.mvc.pojo.ActiveUser;
import com.shiro.test.mvc.pojo.RoleInformation;
import com.shiro.test.mvc.pojo.UserInformation;
import com.shiro.test.mvc.pojo.UserRoleInformation;
import com.shiro.test.mvc.service.RoleInformationService;
import com.shiro.test.mvc.service.UserInformationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description UserInformationServiceImpl
 * @Author Rongxun.Wu
 * @Date 2020/3/1 11:20
 * @Version 1.0
 */
@Service
public class UserInformationServiceImpl implements UserInformationService {

    @Autowired
    UserRoleInformationMapper userRoleInformationMapper;

    @Autowired
    private UserInformationService userService;
    @Autowired
    private RoleInformationService roleService;
    @Autowired
    private UserInformationMapper userDao;


    @Override
    public UserInformation doLogin(String email, String password) {
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token= new UsernamePasswordToken(email,password); //转到MyShiroRealm  password输入的密码
        try {
            subject.login(token);//如果没有抛出异常证明登录成功
            Session session=subject.getSession();
            UserInformation user=getUserByEmail(email);
            String roleIds=userService.getUserRoleIds(user.getId());
            RoleInformation role= roleService.selectByPrimaryKey(roleIds);
            ActiveUser activeUser=new ActiveUser(user.getId(),user.getUserName(),user.getEmail(),role);
            session.setAttribute("activeUser",activeUser);
            session.setAttribute(Constants.SESSION_USER,user);
            return user;
        }catch (AuthenticationException e){
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public List<UserInformation> getUserList() {
        return userDao.getUserList();
    }

    @Override
    public UserInformation getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public String getUserRoleIds(String userId) {
        return userRoleInformationMapper.getUserRoleIds(userId);
    }

    @Override
    public Integer addUserRole(String userId, String roleId) {
        //①将旧角色删除
        userRoleInformationMapper.deleteUserRoleByUserId(userId);

        //②封装参数
        Map<String,String> param=new HashMap<>();
        param.put("userId",userId);
        param.put("roleId",roleId);
        UserRoleInformation userRoleInformation=new UserRoleInformation();
        userRoleInformation.setRoleId(roleId);
        userRoleInformation.setUserId(userId);
      return  userRoleInformationMapper.insertSelective(userRoleInformation);

    }

    @Override
    public List<RoleInformation> getUserRole(List<String> userrole) {
        RoleInformation role=null;
        List<RoleInformation> roleList=new ArrayList<>();
        for (String in:userrole
        ) {
            role= userDao.getRoleByRoleID(in);
            roleList.add(role);
        }
        return roleList;
    }

    @Override
    public UserInformation getUser(Map map) {
        return userDao.getUser(map);
    }

    @Override
    public Integer updataPassword(String password, String id) {
        Map<String,String> param=new HashMap<>();
        Map<String, Object> param2=new HashMap<>();
        param.put("id",id);
        UserInformation user=userDao.getUser(param);
        String pas = MD5Utils.encodePassword(password, user.getEmail());
        param2.put("password",pas);
        param2.put("id",id);
        return userDao.updataPassword(param2);
    }

    @Override
    public Integer addUser(UserInformation user) {
        return userDao.addUser(user);
    }

    @Override
    public UserInformation selectByPrimaryKey(String id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public List<UserInformation> findLikeUserInformation(UserInformation userInformation) {
        return userDao.findLikeUserInformation(userInformation);
    }

    @Override
    public int insertSelective(UserInformation record) {
        return userDao.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return userDao.deleteByPrimaryKey(id);
    }

}
