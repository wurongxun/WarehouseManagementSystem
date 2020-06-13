package com.shiro.test.mvc.base.tool;

import java.util.UUID;

public class UID {
    public static String getUID32(){
       return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
