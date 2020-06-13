package com.shiro.test.mvc.base.web.controller.BaseInformation;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiro.test.mvc.base.Status.shelfPositionisItEmptyStatus;
import com.shiro.test.mvc.base.application.web.json.DatagridResult;
import com.shiro.test.mvc.base.application.web.json.WebJsonResult;
import com.shiro.test.mvc.base.tool.Excel.OutOfStockInformationExcel;
import com.shiro.test.mvc.base.tool.Excel.WarehousingEntryInformationListExcel;
import com.shiro.test.mvc.base.tool.StringUtil;
import com.shiro.test.mvc.pojo.*;
import com.shiro.test.mvc.service.CommodityDetailedListBeanService;
import com.shiro.test.mvc.service.DetailedListInformationService;
import com.shiro.test.mvc.service.OutOfStockInformationService;
import com.shiro.test.mvc.service.ShelfPositionInformationService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description OutOfStockController
 * @Author Rongxun.Wu
 * @Date 2020/2/10 16:00
 * @Version 1.0
 */

@Controller
@RequestMapping("outOfStockInformation.html")
public class OutOfStockInformationController {

    @Autowired
    private  OutOfStockInformationService outOfStockInformationService;

   @Autowired
    private DetailedListInformationService detailedListInformationService;

    @Autowired
    private CommodityDetailedListBeanService commodityDetailedListBeanService;

    @Autowired
    private ShelfPositionInformationService shelfPositionInformationService;

    @Autowired
    private OutOfStockInformationService stockService;

    @RequestMapping(params="act=page")
    public String  Page(){
        return "BaseInformation/OutOfStockInformation_list";
    }

    @RequestMapping(params = "act=list")
    public  @ResponseBody
    WebJsonResult supplierList(@RequestParam(value = "page",defaultValue ="1") Integer page, @RequestParam(value = "rows",defaultValue ="1")Integer rows){
        PageHelper.startPage(page,rows);
        List<OutOfStock> outOfStockList=stockService.QureyAllOutOfStock();
        PageInfo<OutOfStock> pageInfo = new PageInfo<>(outOfStockList,6);
        final DatagridResult creditDatagridResult=new DatagridResult();
        creditDatagridResult.setTotal(pageInfo.getTotal());
        creditDatagridResult.setRows(pageInfo.getList());
        for (OutOfStock s:pageInfo.getList()
        ) {
            System.out.println(s.toString());
        }
        return  WebJsonResult.newSuccess(creditDatagridResult);
    }

    /*查询库存商品信息 以及数量*/
    @RequestMapping(params = "act=CommodityInformation")
    public  @ResponseBody
    WebJsonResult GetCommodityInformation(@RequestParam(value = "page",defaultValue ="1") Integer page, @RequestParam(value = "rows",defaultValue ="1")Integer rows){
        PageHelper.startPage(page,rows);
        List<CommodityDetailedListBean>  commodityDetailedListBeans1=commodityDetailedListBeanService.QueryCommodityDetailedListBeanGroupByCommodityId();
        List<CommodityDetailedListBean>  commodityDetailedListBeans2=new ArrayList<>();
        CommodityDetailedListBean commodityInformationBeans;
        for (CommodityDetailedListBean c:commodityDetailedListBeans1
             ) {
            commodityInformationBeans=c;
            int Count=shelfPositionInformationService.SelectPositionCommodityIdCount(commodityInformationBeans.getCommodityId());
            if (Count>0){
                commodityInformationBeans.setCount(Count);
                commodityDetailedListBeans2.add(commodityInformationBeans);
            }

        }
        PageInfo< CommodityDetailedListBean > pageInfo = new PageInfo<>(commodityDetailedListBeans2,6);
        final DatagridResult creditDatagridResult=new DatagridResult();
        creditDatagridResult.setTotal(pageInfo.getTotal());
        creditDatagridResult.setRows(pageInfo.getList());
        return  WebJsonResult.newSuccess(creditDatagridResult);


    }

    //取消出库申请单
    @RequestMapping(params = "act=CancelApplicationForm")
    public @ResponseBody
    WebJsonResult CancelApplicationForm(@RequestBody DetailedListInformation detailedListInformation) {
        if (detailedListInformation.getStatus() != null
                && StringUtil.isNotEmpty(detailedListInformation.getDetailedListId())
                && StringUtil.isNotEmpty(detailedListInformation.getId())) {

            //改变架位状态位为 改为已存储
            Map<String, Object> map = new HashMap<>();
            map.put("isItEmpty", shelfPositionisItEmptyStatus.AlreadyStored.getCode());//架位 改为已存储
            map.put("detailedListOutOfStockId", detailedListInformation.getDetailedListId());
            //改变 detailed_list_out_of_stock_id=NULL commodity_detaid_list_bean_out_of_stock_id=NULL

            if (shelfPositionInformationService.updateCancelApplicationFormStatusChange_OutOfStock(map) != 0) {
                //删除bean
                if (commodityDetailedListBeanService.deleteByDetailedListIdKey(detailedListInformation.getDetailedListId()) != 0) {
                    int status = detailedListInformationService.updateByPrimaryKeySelective(detailedListInformation);
                    if (status != 0) {
                        return WebJsonResult.newSuccess("取消出库成功");
                    } else {
                        return WebJsonResult.newFailure("取消出库出错");
                    }
                } else {
                    return WebJsonResult.newFailure("取消出库 删除Bean失败");
                }
            } else {
                return WebJsonResult.newFailure("服务器提示：修改架位状态出错");
            }
        } else {
            return WebJsonResult.newFailure("传入服务器数据错误");
        }
    }


    //确定出库
    @Transactional
    @RequestMapping(params = "act=determineOutOfStock")
    public @ResponseBody
    WebJsonResult DetermineOutOfStock(@RequestBody DetailedListInformation detailedListInformation) {


        return outOfStockInformationService.DetermineOutOfStock(detailedListInformation);
    }

    @RequestMapping(params = "act=exportOutOfStockInformation")
    public void ExportOutOfStockInformation(HttpServletResponse response, HttpServletRequest request, Integer detailedListType, DetailedListInformation detailedListInformation) {
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
        OutOfStockInformationExcel outOfStockInformationExcel = new OutOfStockInformationExcel();
        Date t = new Date();
        //生成错误的文档
        try {
            XSSFWorkbook workbook =outOfStockInformationExcel.CreateOutOfStockInformationListExcel(listInformations);
            OutputStream ouputStream = response.getOutputStream();
            String fileName = formatter.format(t);
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + "OutOfStockInformation" + ".xlsx");
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();

        } catch (Exception e) {
            System.out.println("导出错误的excel文件,exportOutOfStockInformation 异常:");
        }
    }
}
