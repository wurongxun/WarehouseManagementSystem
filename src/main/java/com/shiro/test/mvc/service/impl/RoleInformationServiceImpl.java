package com.shiro.test.mvc.service.impl;

import com.shiro.test.mvc.base.application.web.json.WebJsonResult;
import com.shiro.test.mvc.base.tool.UID;
import com.shiro.test.mvc.dao.RoleInformationMapper;
import com.shiro.test.mvc.dao.RoleMenuInformationMapper;
import com.shiro.test.mvc.filter.MyShiroFilterFactoryBean;
import com.shiro.test.mvc.pojo.ActiveUser;
import com.shiro.test.mvc.pojo.RoleInformation;
import com.shiro.test.mvc.pojo.RoleMenuInformation;
import com.shiro.test.mvc.service.RoleInformationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class RoleInformationServiceImpl implements RoleInformationService {
/*
    @Autowired
    RoleMapper roleDao;
*/

    @Autowired
    RoleMenuInformationMapper roleMenuInformationMapper;

    @Autowired
    RoleInformationMapper roleInformationMapper;

    @Autowired
    private MyShiroFilterFactoryBean myShiroFilterFactoryBean;

    @Override
    public List<RoleInformation> getRoleList() {
        List<RoleInformation> re = roleInformationMapper.getRoleList();
        System.out.println(re.toString());
        for (RoleInformation role :
                re) {
            System.out.println(role.getRoleName());
        }
        return re;
    }

    @Override
    public WebJsonResult addRoleMenuInformation(String roleId, String[] menuIds) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        Date Create = new Date();
        //①将旧菜单删除
        /*先查询是否否分配过菜单*/
        int number = roleMenuInformationMapper.FindCountByRoleID(roleId);
        if (number > 0) {
            int status = roleMenuInformationMapper.deleteRoleMenuInformation(roleId);
            if (status == 0) return WebJsonResult.newFailure("删除旧菜单错误");

        }
        //②封装参数
        List<RoleMenuInformation> roleMenuInformationList = new ArrayList<>();

        for (String menuId : menuIds) {
            RoleMenuInformation rm = new RoleMenuInformation();
            rm.setRoleId(roleId);
            rm.setMenuId(menuId);
            rm.setCreateBy(activeUser.getUserName());
            rm.setId(UID.getUID32());
            rm.setCreateDate(Create);
            roleMenuInformationList.add(rm);
        }
        /*批量插入*/
        if (roleMenuInformationMapper.AddRoleMenuInformation_Batch(roleMenuInformationList) == menuIds.length) {
            /*角色更新  需要手动更新所有的权限*/
            myShiroFilterFactoryBean.update();
            return WebJsonResult.newSuccess("分配成功");
        } else {
            return WebJsonResult.newFailure("分配失败");
        }
    }

    @Override
    public List<String> getRoleMenuIds(String roleId) {
        return roleMenuInformationMapper.getMenuIdByRoleId(roleId);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        /*删除角色的同时将角色拥有的权限一并删除*/

        return roleInformationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<RoleInformation> FindLikeRoleInformation(Map map) {
        return roleInformationMapper.FindLikeRoleInformation(map);
    }

    @Override
    public int insertSelective(RoleInformation record) {
        return roleInformationMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKey(RoleInformation record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(RoleInformation record) {
        return roleInformationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public RoleInformation selectByPrimaryKey(String id) {
        return roleInformationMapper.selectByPrimaryKey(id);
    }

}
