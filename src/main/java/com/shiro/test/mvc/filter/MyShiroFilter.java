package com.shiro.test.mvc.filter;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyShiroFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        /**
         * 拿到当前会话的Subject
         */
        Subject subject=getSubject(request,response);
        //定义角色拦截器 得到角色数组
        String[] roles= (String[]) mappedValue;
        if(roles==null||roles.length==0){//没有角色
            return true;
        }
        for (String role:roles){//有角色
            if (subject.hasRole(role))return true;
        }
        return false;
    }
}
