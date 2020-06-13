package com.shiro.test.mvc.base.web.controller.BaseInformation;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiro.test.mvc.base.Status.shelfPositionisItEmptyStatus;
import com.shiro.test.mvc.base.application.web.json.DatagridResult;
import com.shiro.test.mvc.base.application.web.json.WebJsonResult;
import com.shiro.test.mvc.base.tool.Excel.WarehousingEntryInformationListExcel;
import com.shiro.test.mvc.base.tool.StringUtil;
import com.shiro.test.mvc.dao.CommodityDetailedListBeanMapper;
import com.shiro.test.mvc.pojo.ActiveUser;
import com.shiro.test.mvc.pojo.DetailedListInformation;
import com.shiro.test.mvc.pojo.WarehousingEntryInformation;
import com.shiro.test.mvc.service.DetailedListInformationService;
import com.shiro.test.mvc.service.ShelfPositionInformationService;
import com.shiro.test.mvc.service.WarehousingEntryInformationService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description WarehousingEntryInformatin
 * @Author Rongxun.Wu
 * @Date 2020/2/10 13:35
 * @Version 1.0
 */

@Controller
@RequestMapping("warehousingEntryInformation.html")
public class WarehousingEntryInformatinController {


    @Autowired
    CommodityDetailedListBeanMapper commodityDetailedListBeanMapper;

    @Autowired
    DetailedListInformationService detailedListInformationService;

    @Autowired
    ShelfPositionInformationService shelfPositionInformationService;

    @Autowired
    WarehousingEntryInformationService warehousingEntryService;

    @RequestMapping(params = "act=page")
    public String Page() {
        return "BaseInformation/warehousingEntryInformatin_list";
    }



    @RequestMapping(params = "act=list")
    public @ResponseBody
    WebJsonResult WarehouseInformationList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "1") Integer rows) {
        System.out.println("WarehouseInformationList");
        PageHelper.startPage(page, rows);//int pageNum, int pageSize
        List<WarehousingEntryInformation> warehousingEntryList = warehousingEntryService.QureyAllWarehousingEntryInformation();
        PageInfo<WarehousingEntryInformation> pageInfo = new PageInfo<>(warehousingEntryList, 6);
        final DatagridResult creditDatagridResult = new DatagridResult();
        creditDatagridResult.setTotal(pageInfo.getTotal());
        creditDatagridResult.setRows(pageInfo.getList());
        for (WarehousingEntryInformation s : pageInfo.getList()
        ) {
            System.out.println(s.toString());
        }
        return WebJsonResult.newSuccess(creditDatagridResult);
    }

    //确定入库
    @RequestMapping(params = "act=determineWarehousing")
    public @ResponseBody
    WebJsonResult DetermineWarehousing(@RequestBody DetailedListInformation detailedListInformation) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        Date Create = new Date();
        if (detailedListInformation.getStatus() != null
                && StringUtil.isNotEmpty(detailedListInformation.getDetailedListId())
                && StringUtil.isNotEmpty(detailedListInformation.getId())) {
            //确定入库人信息
            detailedListInformation.setStaffId(activeUser.getId());
            /*detailedListInformation.setStaffCode(activeUser.get);*/
            detailedListInformation.setStaffName(activeUser.getUserName());

            //改变架位状态位为 已存储
            Map<String, Object> map = new HashMap<>();
            map.put("isItEmpty", shelfPositionisItEmptyStatus.AlreadyStored.getCode());
            map.put("detailedListWarehousingId", detailedListInformation.getDetailedListId());
            if (shelfPositionInformationService.updateIsItEmptyDetermineWarehousing(map) != 0) {
                int status = detailedListInformationService.updateByPrimaryKeySelective(detailedListInformation);
                if (status != 0) {
                    return WebJsonResult.newSuccess("确定入库成功");
                } else {
                    return WebJsonResult.newFailure("确定入库成出错");
                }
            } else {
                return WebJsonResult.newFailure("服务器提示：修改架位状态出错");
            }

            //修改入库清单状态

        } else {
            return WebJsonResult.newFailure("传入服务器数据错误");
        }
    }

    //取消申请单
    @RequestMapping(params = "act=CancelApplicationForm")
    public @ResponseBody
    WebJsonResult CancelApplicationForm(@RequestBody DetailedListInformation detailedListInformation) {
        if (detailedListInformation.getStatus() != null
                && StringUtil.isNotEmpty(detailedListInformation.getDetailedListId())
                && StringUtil.isNotEmpty(detailedListInformation.getId())) {

            //改变架位状态位为 未存储
            Map<String, Object> map = new HashMap<>();
            map.put("isItEmpty", shelfPositionisItEmptyStatus.Unsaved.getCode());
            map.put("detailedListWarehousingId", detailedListInformation.getDetailedListId());
            if (shelfPositionInformationService.updateCancelApplicationFormStatusChange_Warehousing(map) != 0) {
                //删除bean
                if (commodityDetailedListBeanMapper.deleteByDetailedListIdKey(detailedListInformation.getDetailedListId()) != 0) {
                    int status = detailedListInformationService.updateByPrimaryKeySelective(detailedListInformation);
                    if (status != 0) {
                        return WebJsonResult.newSuccess("取消入库成功");
                    } else {
                        return WebJsonResult.newFailure("取消入库成出错");
                    }
                } else {
                    return WebJsonResult.newFailure("取消入库 删除Bean失败");
                }
            } else {
                return WebJsonResult.newFailure("服务器提示：修改架位状态出错");
            }
        } else {
            return WebJsonResult.newFailure("传入服务器数据错误");
        }
    }

    @RequestMapping(params = "act=exportWarehousingEntryInformation")
    public void ExportWarehousingEntryInformation(HttpServletResponse response, HttpServletRequest request,Integer detailedListType,DetailedListInformation detailedListInformation) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
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

        List<DetailedListInformation> listInformations = detailedListInformationService.QureyAllDetailedListInformation(map);
        WarehousingEntryInformationListExcel goodsShelvesInformationListExcel = new WarehousingEntryInformationListExcel();
        Date t = new Date();
        //生成错误的文档
        try {
            XSSFWorkbook workbook =goodsShelvesInformationListExcel.CreateWarehousingEntryInformationListExcel(listInformations);
            OutputStream ouputStream = response.getOutputStream();
            String fileName = formatter.format(t);
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + "WarehousingEntryInformation" + ".xlsx");
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();

        } catch (Exception e) {
            System.out.println("导出错误的excel文件,exportGoodsShelvesInformationListExcel 异常:");
        }
    }
}
