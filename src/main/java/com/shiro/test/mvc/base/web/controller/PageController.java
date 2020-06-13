package com.shiro.test.mvc.base.web.controller;


import com.shiro.test.mvc.filter.MyShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @Autowired
    private MyShiroFilterFactoryBean shiroFilterFactoryBean;
    @RequestMapping("updata.html")
    public String updata() {

       shiroFilterFactoryBean.update();
        return "index";
    }

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("error.html")
    public String error() {
        return "error";
    }

    @RequestMapping("do{path}.html")
    public String page(@PathVariable String path, Model model) {

        model.addAttribute("path",path);
        return "test";
    }
}
