package com.shiro.test.mvc.base.web.controller.BaseInformation;

import com.shiro.test.mvc.base.Bean.WarehousingOutOfStockReportFormSunburst;
import com.shiro.test.mvc.base.application.web.json.WebJsonResult;
import com.shiro.test.mvc.base.tool.StringUtil;
import com.shiro.test.mvc.pojo.ActiveUser;
import com.shiro.test.mvc.service.DetailedListInformationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description WarehousingOutOfStockReportForm
 * @Author Rongxun.Wu
 * @Date 2020/4/2 13:36
 * @Version 1.0
 */

@Controller
@RequestMapping("warehousingOutOfStockReportForm.html")
public class WarehousingOutOfStockReportFormController {

    @Autowired
    DetailedListInformationService detailedListInformationService;

    @RequestMapping(params = "act=page")
    public String Page() {
        return "BaseInformation/WarehousingOutOfStock_ReportForm";
    }

    /*旭日图 出出入库情况*/
    @RequestMapping(params = "act=WarehousingOutOfStockReportFormSunburst_json")
    public @ResponseBody WebJsonResult  Sunburst(){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");

        System.out.println("activeUser.getRole() :"+activeUser.getRole().getRoleName());
        Map<String,Object> map= new HashMap();
        List<WarehousingOutOfStockReportFormSunburst> json=new ArrayList<>();

        /*入库*/

        WarehousingOutOfStockReportFormSunburst warehousing=new WarehousingOutOfStockReportFormSunburst();
        map.clear();
        map.put("detailedListType",1);
        warehousing.setName("入库单");
        if (StringUtil.isNotEquals("admin",activeUser.getRole().getRoleName())){
            map.put("applicantId",activeUser.getId());
        }
        warehousing.setValue(detailedListInformationService.SelectDetailedListTypeCount(map));

        //入库成功
        WarehousingOutOfStockReportFormSunburst warehousingSuccess=new WarehousingOutOfStockReportFormSunburst();
        map.clear();
        map.put("status",1);
        map.put("detailedListType",1);
        if (StringUtil.isNotEquals("admin",activeUser.getRole().getRoleName())){
            map.put("applicantId",activeUser.getId());
        }
        warehousingSuccess.setName("入库成功");
        warehousingSuccess.setValue(detailedListInformationService.SelectWarehousingOutOfStackCount(map));

        //入库失败
        WarehousingOutOfStockReportFormSunburst warehousingFail=new WarehousingOutOfStockReportFormSunburst();
        map.clear();
        map.put("status",5);
        map.put("detailedListType",1);
        if (StringUtil.isNotEquals("admin",activeUser.getRole().getRoleName())){
            map.put("applicantId",activeUser.getId());
        }
        warehousingFail.setName("入库失败");//审核不通过
        warehousingFail.setValue(detailedListInformationService.SelectWarehousingOutOfStackCount(map));

        //入库取消
        WarehousingOutOfStockReportFormSunburst warehousingCancel=new WarehousingOutOfStockReportFormSunburst();
        map.clear();
        map.put("status",6);
        map.put("detailedListType",1);
        if (StringUtil.isNotEquals("admin",activeUser.getRole().getRoleName())){
            map.put("applicantId",activeUser.getId());
        }
        warehousingCancel.setName("取消入库");
        warehousingCancel.setValue(detailedListInformationService.SelectWarehousingOutOfStackCount(map));

        //等待入库
        WarehousingOutOfStockReportFormSunburst warehousingWaiting=new WarehousingOutOfStockReportFormSunburst();
        map.clear();
        map.put("status",4);
        map.put("detailedListType",1);
        if (StringUtil.isNotEquals("admin",activeUser.getRole().getRoleName())){
            map.put("applicantId",activeUser.getId());
        }
        warehousingWaiting.setName("等待入库");
        warehousingWaiting.setValue(detailedListInformationService.SelectWarehousingOutOfStackCount(map));


        //入库等待审核
        WarehousingOutOfStockReportFormSunburst warehousingToBeAudited=new WarehousingOutOfStockReportFormSunburst();
        map.clear();
        map.put("status",2);
        map.put("detailedListType",1);
        if (StringUtil.isNotEquals("admin",activeUser.getRole().getRoleName())){
            map.put("applicantId",activeUser.getId());
        }
        warehousingWaiting.setName("等待审核");
        warehousingWaiting.setValue(detailedListInformationService.SelectWarehousingOutOfStackCount(map));

        //入库审核成功
        WarehousingOutOfStockReportFormSunburst warehousingToBeAuditedSuccess=new WarehousingOutOfStockReportFormSunburst();
        map.clear();
        map.put("status",3);
        map.put("detailedListType",1);
        if (StringUtil.isNotEquals("admin",activeUser.getRole().getRoleName())){
            map.put("applicantId",activeUser.getId());
        }
        warehousingToBeAuditedSuccess.setName("审核成功");
        warehousingToBeAuditedSuccess.setValue(detailedListInformationService.SelectWarehousingOutOfStackCount(map));

        List<WarehousingOutOfStockReportFormSunburst>  childrenWarehousing=new ArrayList<>();

        childrenWarehousing.add(warehousingSuccess);
        childrenWarehousing.add(warehousingCancel);
        childrenWarehousing.add(warehousingWaiting);
        childrenWarehousing.add(warehousingFail);
        childrenWarehousing.add(warehousingToBeAudited);
        childrenWarehousing.add(warehousingToBeAuditedSuccess);

        warehousing.setChildren(childrenWarehousing);


        /*入库结束  出库开始*/

        WarehousingOutOfStockReportFormSunburst outOfStock=new WarehousingOutOfStockReportFormSunburst();
        map.clear();
        map.put("detailedListType",2);
        if (StringUtil.isNotEquals("admin",activeUser.getRole().getRoleName())){
            map.put("applicantId",activeUser.getId());
        }
        outOfStock.setName("出库单");
        outOfStock.setValue(detailedListInformationService.SelectDetailedListTypeCount(map));



        //出库成功
        WarehousingOutOfStockReportFormSunburst outOfStockSuccess=new WarehousingOutOfStockReportFormSunburst();
        map.clear();
        map.put("status",7);
        map.put("detailedListType",2);
        if (StringUtil.isNotEquals("admin",activeUser.getRole().getRoleName())){
            map.put("applicantId",activeUser.getId());
        }
        outOfStockSuccess.setName("出库成功");
        outOfStockSuccess.setValue(detailedListInformationService.SelectWarehousingOutOfStackCount(map));

        //出库失败
        WarehousingOutOfStockReportFormSunburst  outOfStockFail=new WarehousingOutOfStockReportFormSunburst();
        map.clear();
        map.put("status",5);
        map.put("detailedListType",2);
        if (StringUtil.isNotEquals("admin",activeUser.getRole().getRoleName())){
            map.put("applicantId",activeUser.getId());
        }
        outOfStockFail.setName("出库失败");//审核不通过
        outOfStockFail.setValue(detailedListInformationService.SelectWarehousingOutOfStackCount(map));

        //出库取消
        WarehousingOutOfStockReportFormSunburst outOfStockCancel=new WarehousingOutOfStockReportFormSunburst();
        map.clear();
        map.put("status",6);
        map.put("detailedListType",2);
        if (StringUtil.isNotEquals("admin",activeUser.getRole().getRoleName())){
            map.put("applicantId",activeUser.getId());
        }
        outOfStockCancel.setName("取消出库");
        outOfStockCancel.setValue(detailedListInformationService.SelectWarehousingOutOfStackCount(map));

        //等待出库
        WarehousingOutOfStockReportFormSunburst outOfStockWaiting=new WarehousingOutOfStockReportFormSunburst();
        map.clear();
        map.put("status",8);
        map.put("detailedListType",2);
        if (StringUtil.isNotEquals("admin",activeUser.getRole().getRoleName())){
            map.put("applicantId",activeUser.getId());
        }
        outOfStockWaiting.setName("等待出库");
        outOfStockWaiting.setValue(detailedListInformationService.SelectWarehousingOutOfStackCount(map));


        //等待审核
        WarehousingOutOfStockReportFormSunburst outOfStockToBeAudited=new WarehousingOutOfStockReportFormSunburst();
        map.clear();
        map.put("status",2);
        map.put("detailedListType",2);
        if (StringUtil.isNotEquals("admin",activeUser.getRole().getRoleName())){
            map.put("applicantId",activeUser.getId());
        }
        outOfStockToBeAudited.setName("等待审核");
        outOfStockToBeAudited.setValue(detailedListInformationService.SelectWarehousingOutOfStackCount(map));


        //出库审核成功
        WarehousingOutOfStockReportFormSunburst outOfStockToBeAuditedSuccess=new WarehousingOutOfStockReportFormSunburst();
        map.clear();
        map.put("status",3);
        map.put("detailedListType",2);
        if (StringUtil.isNotEquals("admin",activeUser.getRole().getRoleName())){
            map.put("applicantId",activeUser.getId());
        }
        outOfStockToBeAuditedSuccess.setName("审核成功");
        outOfStockToBeAuditedSuccess.setValue(detailedListInformationService.SelectWarehousingOutOfStackCount(map));

        List<WarehousingOutOfStockReportFormSunburst>  childrenWaitingOutOfStock=new ArrayList<>();
        childrenWaitingOutOfStock.add(outOfStockSuccess);
        childrenWaitingOutOfStock.add(outOfStockFail);
        childrenWaitingOutOfStock.add(outOfStockCancel);
        childrenWaitingOutOfStock.add(outOfStockWaiting);
        childrenWaitingOutOfStock.add(outOfStockToBeAudited);
        childrenWaitingOutOfStock.add(outOfStockToBeAuditedSuccess);

        outOfStock.setChildren(childrenWaitingOutOfStock);


        json.add(warehousing);
        json.add(outOfStock);

        if (warehousing.getValue()==0&&outOfStock.getValue()==0){
            return WebJsonResult.newFailure("无记录");
        }else {
            return WebJsonResult.newSuccess("成功",json);
        }

    }
}
