package com.shiro.test.mvc.base.web.controller.BaseInformation;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiro.test.mvc.base.Status.DetailedListType;
import com.shiro.test.mvc.base.Status.detailedListStatus;
import com.shiro.test.mvc.base.Status.shelfPositionisItEmptyStatus;
import com.shiro.test.mvc.base.application.web.json.DatagridResult;
import com.shiro.test.mvc.base.application.web.json.WebJsonResult;
import com.shiro.test.mvc.base.tool.StringUtil;
import com.shiro.test.mvc.pojo.ActiveUser;
import com.shiro.test.mvc.pojo.DetailedListInformation;
import com.shiro.test.mvc.service.CommodityDetailedListBeanService;
import com.shiro.test.mvc.service.DetailedListInformationService;
import com.shiro.test.mvc.service.ShelfPositionInformationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description detailedListApprovalController
 * @Author Rongxun.Wu
 * @Date 2020/3/21 21:36
 * @Version 1.0
 */
@Controller
@RequestMapping("detailedListApprovalController.html")
public class detailedListApprovalController {

    @Autowired
    ShelfPositionInformationService shelfPositionInformationService;

    @Autowired
    CommodityDetailedListBeanService commodityDetailedListBeanService;

    @Autowired
    DetailedListInformationService detailedListInformationService;


    @RequestMapping(params = "act=page")
    public String Page(Integer detailedListType) {
        if (detailedListType == DetailedListType.Warehousing.getCode()) {
            return "BaseInformation/DetailedListApproval_Warehousing_list";
        } else if (detailedListType == DetailedListType.OutOfStock.getCode()) {
            return "BaseInformation/DetailedListApproval_OutOfStock_list";
        } else {
            return "";
        }

    }

    @RequestMapping(params = "act=list")
    public @ResponseBody
    WebJsonResult DetailedListApprovalList(@RequestParam(value = "page",defaultValue ="1") Integer page, @RequestParam(value = "rows",defaultValue ="1")Integer rows,Integer detailedListType, Integer status,DetailedListInformation detailedListInformation) {
        PageHelper.startPage(page,rows);
        Map<String,Object> map = new HashMap<>();
        map.put("detailedListType", detailedListType);
        map.put("status", status);
        map.put("detailedListCode",detailedListInformation.getDetailedListCode());
        map.put("detailedListName",detailedListInformation.getDetailedListName());
        map.put("staffName",detailedListInformation.getStaffName());
        map.put("receivingDelivererPersonName",detailedListInformation.getReceivingDelivererPersonName());
        map.put("receivingDelivererPersonPhone",detailedListInformation.getReceivingDelivererPersonPhone());
        map.put("receivingDelivererPersonAddress",detailedListInformation.getReceivingDelivererPersonAddress());
        List<DetailedListInformation> listInformations = detailedListInformationService.QureyAllDetailedListInformation(map);
        PageInfo<DetailedListInformation> pageInfo = new PageInfo<>(listInformations,6);
        final DatagridResult creditDatagridResult=new DatagridResult();
        creditDatagridResult.setTotal(pageInfo.getTotal());
        creditDatagridResult.setRows(pageInfo.getList());
        for (DetailedListInformation s:pageInfo.getList()
        ) {
            System.out.println(s.toString());
        }
        return  WebJsonResult.newSuccess(creditDatagridResult);
    }

    //清单审核
    @RequestMapping(params = "act=detailedListApproval")
    public @ResponseBody WebJsonResult DetailedListApproval_Warehousing(@RequestBody DetailedListInformation detailedListInformation){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        Date Create = new Date();
        //判断是否是自己给自己审核
        if (StringUtil.isEquals(detailedListInformation.getApplicantId(),activeUser.getId())){
            return WebJsonResult.newFailure("不能给自己的申请单审核");
        }
        if (detailedListInformation.getStatus()!=null
                &&StringUtil.isNotEmpty(detailedListInformation.getDetailedListId())
                &&StringUtil.isNotEmpty(detailedListInformation.getId())){


            detailedListInformation.setAuditById(activeUser.getId());
            detailedListInformation.setAuditByName(activeUser.getUserName());
            //审核不通过
            if (detailedListInformation.getStatus()== detailedListStatus.AuditFailed.getCode()){
                Map<String, Object> map = new HashMap<>();
                map.put("isItEmpty", shelfPositionisItEmptyStatus.Unsaved.getCode());
                map.put("detailedListWarehousingId", detailedListInformation.getDetailedListId());
                if (shelfPositionInformationService.updateCancelApplicationFormStatusChange_Warehousing(map) != 0) {
                    //删除bean
                    if (commodityDetailedListBeanService.deleteByDetailedListIdKey(detailedListInformation.getDetailedListId()) != 0) {
                    }else {
                        return WebJsonResult.newFailure("取消入库 删除Bean失败");
                    }
                }else {
                    return WebJsonResult.newFailure("服务器提示：修改架位状态出错");
                }
            }

           int status= detailedListInformationService.updateByPrimaryKeySelective(detailedListInformation);
           if (status!=0){
               return WebJsonResult.newSuccess("操作成功");
           }else {
               return WebJsonResult.newFailure("服务器出错");
           }
        }else {
            return WebJsonResult.newFailure("传入服务器数据错误");
        }
    }

    //清单审核
    @RequestMapping(params = "act=detailedListApproval_OutOfStock")
    public @ResponseBody WebJsonResult DetailedListApproval_OutOfStock(@RequestBody DetailedListInformation detailedListInformation){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        Date Create = new Date();
        //判断是否是自己给自己审核
        if (StringUtil.isEquals(detailedListInformation.getApplicantId(),activeUser.getId())){
            return WebJsonResult.newFailure("不能给自己的申请单审核");
        }
        if (detailedListInformation.getStatus()!=null
                &&StringUtil.isNotEmpty(detailedListInformation.getDetailedListId())
                &&StringUtil.isNotEmpty(detailedListInformation.getId())){


            detailedListInformation.setAuditById(activeUser.getId());
            detailedListInformation.setAuditByName(activeUser.getUserName());
            //审核不通过
            if (detailedListInformation.getStatus()== detailedListStatus.AuditFailed.getCode()){
                Map<String, Object> map = new HashMap<>();
                map.put("isItEmpty", shelfPositionisItEmptyStatus.Unsaved.getCode());
                map.put("detailedListOutOfStockId", detailedListInformation.getDetailedListId());
                if (shelfPositionInformationService.updateCancelApplicationFormStatusChange_OutOfStock(map) != 0) {
                    //删除bean
                    if (commodityDetailedListBeanService.deleteByDetailedListIdKey(detailedListInformation.getDetailedListId()) != 0) {
                    }else {
                        return WebJsonResult.newFailure("取消入库 删除Bean失败");
                    }
                }else {
                    return WebJsonResult.newFailure("服务器提示：修改架位状态出错");
                }
            }

            int status= detailedListInformationService.updateByPrimaryKeySelective(detailedListInformation);
            if (status!=0){
                return WebJsonResult.newSuccess("操作成功");
            }else {
                return WebJsonResult.newFailure("服务器出错");
            }
        }else {
            return WebJsonResult.newFailure("传入服务器数据错误");
        }
    }
}
