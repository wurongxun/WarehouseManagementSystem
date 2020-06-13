package com.shiro.test.mvc.base.web.controller;

import com.shiro.test.mvc.pojo.UserInformation;
import com.shiro.test.mvc.service.UserInformationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

    //2019/12/16
    @Autowired
    private UserInformationService userService;
    @RequestMapping("gologin.html")
    public String goLogin(){
        return "login";
    }
    @RequestMapping("/")
    public String index(){
        return "login";
    }
    @RequestMapping("logout.html")
    public String logout(){
        Subject subject= SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
    @RequestMapping("login.html")
    public  String login(String email, String password, HttpServletRequest request) {
        System.out.println("email"+email+"   password"+password);
        UserInformation user = userService.doLogin(email, password);
        if (user == null) {
            System.out.println("登录失败");
            request.setAttribute("error", "用户名或密码错误");
            return "login";
        }
        return "index";

    }
}
