package com.shiro.test.mvc.Test;

import org.junit.Test;

/**
 * @Description Test2
 * @Author Rongxun.Wu
 * @Date 2020/6/2 21:01
 * @Version 1.0
 */
public class Test2 extends Base {
    public static int a=2;
    public static void main(String[] args) {
        Test2 t=new Test2();
        Base b=new Test2();
        System.out.println(t.a);
        System.out.println(b.a);
    }
}
