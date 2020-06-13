package com.shiro.test.mvc.Test;

import com.shiro.test.mvc.service.RoleInformationService;
import com.shiro.test.mvc.service.SupplierClassInformationService;
import com.shiro.test.mvc.service.TestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Description test
 * @Author Rongxun.Wu
 * @Date 2019/12/29 17:58
 * @Version 1.0
 */
public class test {

    @Autowired
    TestService testService;

    @Autowired
    RoleInformationService roleService;

    @Autowired
    private SupplierClassInformationService supplierClassInformationService;

    @Test
    public  void Test() {

        //System.out.println("dfdf  "+RandomNumber.GetRandom());

       /* String pas = MD5Utils.encodePassword("122","123@qq.com");
        System.out.println(pas);
        System.out.println(UID.getUID32());
        System.out.println();*/


       /* testService.queryTime();*/
      /*  List<Role> list = roleService.getRoleList();
        System.out.println(list.size());*/
        //System.out.println(OrderStatus.Successful.getCode());
        /*SupplierClassInformation s = new SupplierClassInformation();
        s.setCode("2323");
        s.setClassificationName("海鲜");
        List<SupplierClassInformation> supplierClassInformations=supplierClassInformationService.queryAllSupplierClssList(s);
        System.out.println(supplierClassInformations.size());*/

        Object[][] a=new Object[4][5];
        a[0][0]="product";
        a[0][1]="2011";
        a[0][2]="2012";
        a[0][3]="2013";

        System.out.println(a.toString());

    }
    @Test
    public void Test1(){
        String a="Java程序员";
        System.out.println(a.getBytes().length);
    }
}
