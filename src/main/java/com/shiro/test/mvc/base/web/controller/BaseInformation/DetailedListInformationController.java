package com.shiro.test.mvc.base.web.controller.BaseInformation;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiro.test.mvc.base.Bean.DetailedListCommodityInformationBean;
import com.shiro.test.mvc.base.application.web.json.DatagridResult;
import com.shiro.test.mvc.base.application.web.json.WebJsonResult;
import com.shiro.test.mvc.base.tool.StringUtil;
import com.shiro.test.mvc.pojo.ActiveUser;
import com.shiro.test.mvc.pojo.DetailedListInformation;
import com.shiro.test.mvc.service.DetailedListInformationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description DetailedListInformationController
 * @Author Rongxun.Wu
 * @Date 2020/2/10 15:56
 * @Version 1.0
 */

@Controller
@RequestMapping("DetailedListInformation.html")
public class DetailedListInformationController {

    @Autowired
    DetailedListInformationService detailedListInformationService;

    @RequestMapping(params = "act=page")
    public String Page() {
        return "BaseInformation/DetailedListInformation_list";
    }

    @RequestMapping(params = "act=list")
    public @ResponseBody
    WebJsonResult supplierList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "1") Integer rows, Integer detailedListType,DetailedListInformation detailedListInformation) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        PageHelper.startPage(page, rows);
        System.out.println(" setDetailedListType    "+detailedListInformation.getDetailedListType());
        Map<String, Object> map = new HashMap<>();
        map.put("detailedListType", detailedListType);
        if (StringUtil.isNotEquals(activeUser.getRole().getRoleName(),"admin")){
            map.put("applicantId", activeUser.getId());
        }
        map.put("detailedListCode",detailedListInformation.getDetailedListCode());
        map.put("detailedListName",detailedListInformation.getDetailedListName());
        map.put("staffName",detailedListInformation.getStaffName());
        map.put("receivingDelivererPersonName",detailedListInformation.getReceivingDelivererPersonName());
        map.put("receivingDelivererPersonPhone",detailedListInformation.getReceivingDelivererPersonPhone());
        map.put("receivingDelivererPersonAddress",detailedListInformation.getReceivingDelivererPersonAddress());
        map.put("status",detailedListInformation.getStatus());
        List<DetailedListInformation> detailedListInformationList = detailedListInformationService.QureyAllDetailedListInformation(map);
        PageInfo<DetailedListInformation> pageInfo = new PageInfo<>(detailedListInformationList, 6);
        final DatagridResult creditDatagridResult = new DatagridResult();
        creditDatagridResult.setTotal(pageInfo.getTotal());
        creditDatagridResult.setRows(pageInfo.getList());
        for (DetailedListInformation s : pageInfo.getList()
        ) {
            System.out.println(s.toString());
        }
        return WebJsonResult.newSuccess(creditDatagridResult);
    }

    /*申请入库*/
    @RequestMapping(params = "act=ApplicantWarehousingEntryInformation")
    public @ResponseBody
    WebJsonResult AddDetailedListCommodityInformation(@RequestBody DetailedListCommodityInformationBean detailedListCommodityInformationBean) {
        System.out.println("总价：   " + detailedListCommodityInformationBean.getTotalSum());
        int state = detailedListInformationService.AddDetailedList(detailedListCommodityInformationBean);
        if (state != 0) {
            return WebJsonResult.newSuccess("恭喜您申请入库成功，请等待审核");
        }
        return WebJsonResult.newFailure("申请入库失败");
    }

    /*申请出库*/
    @RequestMapping(params = "act=ApplicantOutOfStockEntryInformation")
    public @ResponseBody
    WebJsonResult ApplicantDetailedListCommodityInformation(@RequestBody DetailedListCommodityInformationBean detailedListCommodityInformationBean) {
        System.out.println("总价：   " + detailedListCommodityInformationBean.getTotalSum());
        int state = detailedListInformationService.ApplicantOutOfStockEntryInformation(detailedListCommodityInformationBean);
        if (state != 0) {
            return WebJsonResult.newSuccess("恭喜您申请入库成功，请等待审核");
        }
        return WebJsonResult.newFailure("申请入库失败");
    }
}
