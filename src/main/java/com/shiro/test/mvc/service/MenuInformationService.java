package com.shiro.test.mvc.service;

import com.shiro.test.mvc.pojo.MenuInformationBean;
import com.shiro.test.mvc.pojo.MenuPermission;

import java.util.List;

/**
 * @Description MenuService
 * @Author Rongxun.Wu
 * @Date 2019/12/25 22:27
 * @Version 1.0
 */
public interface MenuInformationService {
    /*获得树形菜单*/
    List<MenuInformationBean> getTreeMenu();

    List<MenuPermission> getMenuPermission();
}
