package com.shiro.test.mvc.pojo;

import java.util.List;

/**
 * @Description RolePermission
 * @Author Rongxun.Wu
 * @Date 2019/12/26 23:01
 * @Version 1.0
 */
public class MenuPermission {
    private String id;
    private  String url;
    private List<RoleInformation> roleIds;

   public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<RoleInformation> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<RoleInformation> roleIds) {
        this.roleIds = roleIds;
    }

   /* public List<Integer> getRoleMenus() {
        return roleMenus;
    }

    public void setRoleMenus(List<Integer> roleMenus) {
        this.roleMenus = roleMenus;
    }*/

    @Override
    public String toString() {
        return "MenuPermission{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", roleIds=" + roleIds +
                '}';
    }
}
