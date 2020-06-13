package com.shiro.test.mvc.base.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description NumberingGeneration
 * @Author Rongxun.Wu
 * @Date 2020/2/23 14:22
 * @Version 1.0
 */
public class NumberingGeneration {

    private String u;
    public NumberingGeneration(String u){
        this.u=u;
    }
   public String  toNumberingGeneration(){
       return u+"-"+RandomNumber.GetRandom();
    }
}
