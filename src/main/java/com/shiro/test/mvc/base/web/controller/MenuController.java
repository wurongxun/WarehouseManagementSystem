package com.shiro.test.mvc.base.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @RequestMapping("list.html")
    public String list() {
        return "menu_list";
    }

    @RequestMapping("go_edit.html")
    @RequiresPermissions("menu:edit")
    public String goEdit() {
        return "/menu_edit";
    }

    @RequestMapping("home.html")
    public String goHome(){return "/menu_edit"; }


}
