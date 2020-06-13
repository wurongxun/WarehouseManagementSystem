package com.shiro.test.mvc.filter;

import com.shiro.test.mvc.pojo.ActiveUser;
import com.shiro.test.mvc.pojo.MenuPermission;
import com.shiro.test.mvc.pojo.RoleInformation;
import com.shiro.test.mvc.service.MenuInformationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class MyShiroFilterFactoryBean extends ShiroFilterFactoryBean {
    private static final String ROLE_STRING = "roles[{0}]";

    private  String filterChainDefinitions;//默认权限地址

    @Autowired
    MyShiroRealm myShiroRealm;

    @Autowired
    private MenuInformationService menuService;

    @Override
    public void setFilterChainDefinitions(String definitions) {


        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/login.html/** ", "anon");
        filterChainDefinitionMap.put("/pages/** ", "anon");
        filterChainDefinitionMap.put("/easyui/** ", "anon");
        filterChainDefinitionMap.put("/jquery-easyui-1.7.0/**", "anon");
        filterChainDefinitionMap.put("/js/**", "authc");
         filterChainDefinitionMap.put("/images/**", "anon");
         filterChainDefinitionMap.put("/css/**", "anon");
         filterChainDefinitionMap.put("/static/** ", "anon");
         filterChainDefinitionMap.put("/admin/auth/403", "anon");
/*





        System.out.println("默认地址:  "+definitions);
        filterChainDefinitions = definitions;*/
/*definitions 默认地址 <property name="filterChainDefinitions">*//*

        Ini ini = new Ini();
        ini.load(definitions);
        //did they explicitly state a 'urls' section?  Not necessary, but just in case:
        Ini.Section section = ini.getSection("urls");
        if (CollectionUtils.isEmpty(section)) {
            //no urls section.  Since this _is_ a urls chain definition property, just assume the
            //default section contains only the definitions:
            section = ini.getSection("");
        }

        //数据库中查询
        List<MenuPermission> menuPermissionList = menuService.getMenuPermission();
        if (menuPermissionList != null) {
            for (MenuPermission menuPermission : menuPermissionList) {
                List<RoleInformation> roleIds = menuPermission.getRoleIds();
               // System.out.println("roleIds  "+roleIds+"    roleIds."+roleIds.size());
             if (StringUtils.hasLength(menuPermission.getUrl()) && roleIds != null && roleIds.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    StringBuilder sb2 = new StringBuilder();
                    for (RoleInformation role : roleIds) {
                        sb.append(role.getRoleName()).append(",");//封装角色   拼接
                        sb2.append(role.getRemark()).append(",");
                    }
                    String str2 = sb2.substring(0, sb2.length() - 1);//去掉最后一个“ , ”
                    String str = sb.substring(0, sb.length() - 1);//去掉最后一个“ , ”
                 log.println( "类MyShiroFilterFactoryBean    地址：  "+menuPermission.getUrl()+ "    角色名： "+str+"  "+str2);
                    //加入URL地 址menuPermission.getUrl().substring(0, menuPermission.getUrl().indexOf("?")
                 log.println();
                 filterChainDefinitionMap.put(menuPermission.getUrl(), "roles["+str+"]");
                    section.put(menuPermission.getUrl(), MessageFormat.format(ROLE_STRING, str));
                }

            }
        }


        section.put("/**", "authc");//没有定义过的URL 需要登录后才能访问 最后添加*/
        filterChainDefinitionMap.put("/**", "authc");
        this.setFilterChainDefinitionMap(filterChainDefinitionMap);

    }

    /*
     * 权限更新
     * */
    public void update() {
        synchronized (this) {
            try {

                AbstractShiroFilter shiroFilter =(AbstractShiroFilter) this.getObject();
                PathMatchingFilterChainResolver resolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
                DefaultFilterChainManager manager = (DefaultFilterChainManager) resolver.getFilterChainManager();
                /*清除原来的权限验证*/
                manager.getFilterChains().clear();
                this.getFilterChainDefinitionMap().clear();

                //设置新权限
                System.out.println("更新权限数据");
                 /*List<MenuPermission> menuPermissionList = menuService.getMenuPermission();*/

               /* this.setFilterChainDefinitions("/dotest3.html=authc,roles[admin]");*/
                //开始




                //结束
                /*设置默认权限地址*/
                this.setFilterChainDefinitions(filterChainDefinitions);


                // 重新构建生成
                Map<String, String> chains = getFilterChainDefinitionMap();
                if (!CollectionUtils.isEmpty(chains)) {
                    for (Map.Entry<String, String> entry : chains.entrySet()) {
                        String url = entry.getKey();
                        String chainDefinition = entry.getValue();
                        manager.createChain(url, chainDefinition);
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");

    /*    try {
            //刷新权限
            reloadAuthorizing(subject.getPrincipals());
        } catch (Exception e) {
            e.printStackTrace();
        }
*/      /*  clearCache();*/
        /*reloadAuthorizing(myShiroRealm,activeUser.getEmail());*/
    }

    /**
     * 重新赋值权限(在比如:给一个角色临时添加一个权限,需要调用此方法刷新权限,否则还是没有刚赋值的权限)
     * @param myRealm 自定义的realm
     * @param username 用户名
     */
    public static void reloadAuthorizing(MyShiroRealm myRealm,String username){
        System.out.println("类MyShiroFilterFactoryBean  刷新权限");
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        //第一个参数为用户名,第二个参数为realmName,test想要操作权限的用户
        SimplePrincipalCollection principals = new SimplePrincipalCollection(username,realmName);
        subject.runAs(principals);
        myRealm.getAuthorizationCache().remove(subject.getPrincipals());
        subject.releaseRunAs();
    }

    /**
     * @title 刷新用户权限
     * @param principal
     * @desc principal为用户的认证信息
     */
    public static void  reloadAuthorizing(Object principal) throws Exception{
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm myShiroRealm = (MyShiroRealm) rsm.getRealms().iterator().next();

        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, realmName);
        subject.runAs(principals);
        if(myShiroRealm.isAuthenticationCachingEnabled()) {
            myShiroRealm.getAuthenticationCache().remove(principals);
        }
        if(myShiroRealm.isAuthorizationCachingEnabled()) {
            // 删除指定用户shiro权限
            myShiroRealm.getAuthorizationCache().remove(principals);
        }
        // 刷新权限
        subject.releaseRunAs();
    }

    //清空缓存
    public void clearCache() {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        //AccountAuthorizationRealm为在项目中定义的realm类
        MyShiroRealm shiroRealm = (MyShiroRealm) rsm.getRealms().iterator().next();
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        Object principal = subject.getPrincipal();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(subject.getPrincipal(), realmName);
        subject.runAs(principals);
        //用realm删除principle
        shiroRealm.getAuthorizationCache().remove(subject.getPrincipals());
        //切换身份也就是刷新了
        subject.releaseRunAs();

    }


}
