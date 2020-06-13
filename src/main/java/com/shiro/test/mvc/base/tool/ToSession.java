package com.shiro.test.mvc.base.tool;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @Description
 * @auther Eason
 * @create 2020-03-02 10:52
 */

public class ToSession {
    public  static   Subject subject = SecurityUtils.getSubject();
    public  static  Session session = subject.getSession();
}