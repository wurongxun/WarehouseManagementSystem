package com.shiro.test.mvc.base.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiro.test.mvc.base.application.web.json.DatagridResult;
import com.shiro.test.mvc.base.application.web.json.WebJsonResult;
import com.shiro.test.mvc.base.tool.StringUtil;
import com.shiro.test.mvc.base.tool.UID;
import com.shiro.test.mvc.filter.MyShiroRealm;
import com.shiro.test.mvc.pojo.*;
import com.shiro.test.mvc.service.MenuInformationService;
import com.shiro.test.mvc.service.RoleInformationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"role.html","RoleInformation.html"})
public class RoleController {

    @Autowired
    MyShiroRealm myShiroRealm;

    @Autowired
    private RoleInformationService roleService;

    @Autowired
    private MenuInformationService menuService;

    @RequestMapping(params = "act=page")
    public String page() {

        System.out.println("/Role/role_list");
        return "BaseInformation/RoleInformation_list";
    }

    @RequestMapping(params = "act=list")
    @ResponseBody
    public WebJsonResult list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "1") Integer rows,RoleInformation roleInformation,String find) {

        PageHelper.startPage(page, rows);
        List<UserInformation> userInformations;
        List<RoleInformation> roleInformations;
        System.out.println("find :" + find +" status "+roleInformation.getStatus());
        if (StringUtil.isNotEmpty(find)) {
            HashMap<String,Object> map=new HashMap<>();
            map.put("roleName",roleInformation.getRoleName());
            map.put("status",roleInformation.getStatus());
            roleInformations =roleService.FindLikeRoleInformation(map);
        } else {

            roleInformations = roleService.getRoleList();
        }
        PageInfo<RoleInformation> pageInfo = new PageInfo<>(roleInformations, 6);
        final DatagridResult creditDatagridResult = new DatagridResult();
        creditDatagridResult.setTotal(pageInfo.getTotal());
        creditDatagridResult.setRows(pageInfo.getList());
        return WebJsonResult.newSuccess(creditDatagridResult);
    }

    @RequestMapping(params = "act=go_edit")
    public String goEdit() {
        System.out.println("act=go_edit");

        return "BaseInformation/role_edit";
    }


    @RequestMapping(params = "act=edit")
    @ResponseBody
    public WebJsonResult edit(RoleInformation role) {
        System.out.println("收到请回答" + role.toString());
        if (role.getId() == null) {
            role.setId(UID.getUID32());
            Integer status = roleService.insertSelective(role);
            if (status>0){
             return WebJsonResult.newSuccess("添加成功");
            }
            return WebJsonResult.newFailure("添加失败");
        } else {
            Integer status = roleService.updateByPrimaryKeySelective(role);
            if (status>0){
                return WebJsonResult.newSuccess("更新成功");
            }
            return WebJsonResult.newFailure("更新失败");
        }
    }

    /*返回菜单*/
    @RequestMapping(params = "act=menu_list")
    @ResponseBody
    public List<MenuInformationBean> menuList() {
        return menuService.getTreeMenu();
    }

    /*分配角色和菜单*/
    @RequestMapping(params = "act=assign_menu")
    @ResponseBody
    public WebJsonResult assignMenu(String roleId, String[] menuIds) {
        System.out.println("类RoleController  roleId：" + roleId + "  menuId：  " + menuIds.length);
        if (StringUtil.isEmpty(roleId)||menuIds.length==0) {
            return WebJsonResult.newFailure("有空值");
        }else {
            return roleService.addRoleMenuInformation(roleId, menuIds);
        }
    }
    @RequestMapping(params = "act=role_menu")
    @ResponseBody
    public List<String> roleMenu(String roleId) {
        System.out.println("roleId  " + roleId);
        List<String> r = roleService.getRoleMenuIds(roleId);
        for (String str : r
        ) {
            System.out.println("  " + str);
        }
        return roleService.getRoleMenuIds(roleId);
    }

    //role.html?act=MenuP
    @RequestMapping(params = "act=MenuP")
    public @ResponseBody
    List<MenuPermission> roleMenus() {
        System.out.println("哈哈  查不到");
        return menuService.getMenuPermission();
    }

    //role.html?act=Role_judge
    @RequestMapping(params = "act=Role_judge")
    public @ResponseBody
    WebJsonResult RoleJudge() {
        System.out.println("返回当前角色Role:  ");
        WebJsonResult webJsonResult = new WebJsonResult();
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        webJsonResult.setData(activeUser);
        return webJsonResult;
    }
    @RequestMapping(params = "act=deleteRole")
    public @ResponseBody WebJsonResult deleteRole(RoleInformation role){

        if (roleService.deleteByPrimaryKey(role.getId())!=0){
            return WebJsonResult.newSuccess("操作成功");
        }

        return WebJsonResult.newFailure("操作失败");
    }

    @RequestMapping(params = "act=menuPermissionList")
            public @ResponseBody List<MenuPermission>   menuPermissionList(){

        return menuService.getMenuPermission();
    }

    @RequestMapping(params = "act=myShiroRealm")
    public @ResponseBody
    AuthorizationInfo GetAuthorizationInfo(){
       return  myShiroRealm.getAuthorizationInfoC();
    }
}
