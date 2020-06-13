package com.shiro.test.mvc.base.tool;

import java.util.HashMap;
import java.util.Map;

public class MessageUtil {
    public static Map<String,Object> geSuccessResult(String message ){
     Map<String,Object> result=new HashMap<>();
     result.put("status",true);
     result.put("message",message);
     return result;
    }
    public static Map<String,Object> geErrorResult(String message ){
        Map<String,Object> result=new HashMap<>();
        result.put("status",false);
        result.put("message",message);
        return result;
    }
}
