package com.shiro.test.mvc.service.impl;


import com.shiro.test.mvc.base.tool.StringUtil;
import com.shiro.test.mvc.dao.*;
import com.shiro.test.mvc.pojo.*;
import com.shiro.test.mvc.service.MenuInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @Description MenuServicelmpl
 * @Author Rongxun.Wu
 * @Date 2019/12/25 22:34
 * @Version 1.0
 */
@Service
public class MenuInformationServicelmpl implements MenuInformationService {

    @Autowired
    MenuInformationMapper menuInformationMapper;

    @Autowired
    RoleInformationMapper roleInformationMapper;

    @Autowired
    RoleMenuInformationMapper menuRoleInformationMapper;

    /*@Autowired
    RoleMapper roleDao;
*/
    /*封装 满足Easy UI Tree 树 JSON格式*/
    @Override
    public List<MenuInformationBean> getTreeMenu() {
        List<MenuInformation> menus = menuInformationMapper.getAllMenuInformation();//全部菜单信息
        /* HashMap<String, MenuInformation> menuMap = new HashMap<>(menus.size());*/

        /*一级菜单  parentId=1 为一级菜单 parentId=2 为二级菜单*/
        final List<MenuInformationBean> parentMenu = new ArrayList<>();
        for (MenuInformation menu : menus) {//遍历的到菜单
            /*menuMap.put(menu.getId(), menu);*/
            MenuInformationBean fatherMenuPermission = new MenuInformationBean();
            if (StringUtil.isEquals(menu.getParentId(),"father")) {/*说明为父菜单*/
                fatherMenuPermission.setId(menu.getId());
                fatherMenuPermission.setText(menu.getMenuName());
                //找到此根的子树
                List<MenuInformationBean> parentMenuSun = new ArrayList<>();
                for (MenuInformation menu2 : menus) {
                    if (StringUtil.isEquals(menu2.getParentId(), fatherMenuPermission.getId())&&StringUtil.isNotEquals("father",menu2.getParentId())) {
                        MenuInformationBean SunMenuPermission = new MenuInformationBean();
                        SunMenuPermission.setId(menu2.getId());
                        SunMenuPermission.setText(menu2.getMenuName());
                        parentMenuSun.add(SunMenuPermission);
                    }
                }
                fatherMenuPermission.setChildren(parentMenuSun);
                parentMenu.add(fatherMenuPermission);
            }

        }
        return parentMenu;
    }

    @Override
    public List<MenuPermission> getMenuPermission() {
        log.println("日志" + "menuDao.getAllMenu()&&roleDao.getRoleList()&&menuDao.getMenuRoleIdList() 返回 List<MenuPermission>");
        List<MenuInformation> m = menuInformationMapper.getAllMenuInformation();//全部菜单信息
        List<RoleInformation> r = roleInformationMapper.getRoleList();//获得角色
        List<RoleMenuInformation> mr = menuRoleInformationMapper.FindRoleMenuList(); //菜单角色
        final List<MenuPermission> menuPermissionList = new ArrayList<>();

        if (m != null && m.size() > 0) {
            for (MenuInformation menu : m) {//遍历菜单
                MenuPermission menuPermission = new MenuPermission();//
                List<RoleInformation> mpRole = new ArrayList<>();
                //加入菜单的id和url
                menuPermission.setUrl(menu.getUrl());
                menuPermission.setId(menu.getId());

                if (mr != null && mr.size() > 0) {//菜单和角色
                    for (RoleMenuInformation menuRole : mr) {//遍历菜单和角色集合
                        if (r != null && r.size() > 0) {
                            for (RoleInformation role : r) {//遍历角色信息
                                if (menuRole.getMenuId().equals(menu.getId())//如果菜单ID和（菜单角色）id相等
                                        && menuRole.getRoleId().equals(role.getId())) {
                                    mpRole.add(role);//将角色加入角色链表
                                }
                            }
                        }
                    }
                    menuPermission.setRoleIds(mpRole);
                    menuPermissionList.add(menuPermission);
                }
            }
        }
        return menuPermissionList;
    }
}
