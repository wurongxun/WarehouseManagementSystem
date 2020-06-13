package com.shiro.test.mvc.base.web.controller.BaseInformation;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiro.test.mvc.base.application.web.json.DatagridResult;
import com.shiro.test.mvc.base.application.web.json.WebJsonResult;
import com.shiro.test.mvc.base.tool.Excel.CommodityInformationListExcel;
import com.shiro.test.mvc.base.tool.NumberingGeneration;
import com.shiro.test.mvc.base.tool.StringUtil;
import com.shiro.test.mvc.base.tool.UID;
import com.shiro.test.mvc.pojo.ActiveUser;
import com.shiro.test.mvc.pojo.CommodityInformation;
import com.shiro.test.mvc.pojo.SupplierClassInformation;
import com.shiro.test.mvc.pojo.SuppliersInformation;
import com.shiro.test.mvc.service.CommodityInformationService;
import com.shiro.test.mvc.service.SupplierClassInformationService;
import com.shiro.test.mvc.service.SupplierInformationService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Description CommodityInformationController
 * @Author Rongxun.Wu
 * @Date 2020/2/4 15:16
 * @Version 1.0
 */
@Controller
@RequestMapping("commodityInformation.html")
public class CommodityInformationController {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(CommodityInformationController.class));


    @Autowired
    private SupplierInformationService supplierService;

    @Autowired
    private SupplierClassInformationService supplierClassInformationService;

    @Autowired
    CommodityInformationService commodityInformationService;

    @RequestMapping(params = "act=page")
    public String Page() {
        return "BaseInformation/CommodityInformation_list";
    }

    @RequestMapping(params = "act=list")
    public @ResponseBody
    WebJsonResult CommodityInformationList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "1") Integer rows, CommodityInformation commodityInformation) {
        System.out.println("act=list");
        PageHelper.startPage(page, rows);//int pageNum, int pageSize
        System.out.println("getSupplierName() " + commodityInformation.getSupplierName() + " getSupplierPhone() " + commodityInformation.getSupplierPhone() + "  commodityInformation.getCommodityName() " + commodityInformation.getCommodityName() + "  commodityInformation.getStatus() " + commodityInformation.getStatus());
        List<CommodityInformation> commodityInformationList;
        if (StringUtil.isEmpty(commodityInformation.getSupplierName()) &&
                StringUtil.isEmpty(commodityInformation.getSupplierPhone()) &&
                StringUtil.isEmpty(commodityInformation.getCommodityName()) &&
                StringUtil.isEmpty(commodityInformation.getCommodityMn()) &&
                commodityInformation.getStatus() == null
        ) {
            System.out.println("QureyAllCommodityInformation");
            commodityInformationList = commodityInformationService.QureyAllCommodityInformation();

        } else {
            commodityInformationList = commodityInformationService.findLikeCommodityInformationList(commodityInformation);
        }
        PageInfo<CommodityInformation> pageInfo = new PageInfo<>(commodityInformationList, 6);
        final DatagridResult creditDatagridResult = new DatagridResult();
        creditDatagridResult.setTotal(pageInfo.getTotal());
        creditDatagridResult.setRows(pageInfo.getList());
        for (CommodityInformation s : pageInfo.getList()
        ) {
            System.out.println(s.toString());
        }
        return WebJsonResult.newSuccess(creditDatagridResult);
    }

    /*查询 商品大类小类信息*/
    @RequestMapping(params = "act=Find_SupplierClassInformation")
    public @ResponseBody
    WebJsonResult Find_SupplierClassInformation(SupplierClassInformation supplierClassInformation) {
        if (StringUtil.isNotEmpty(supplierClassInformation.getParentClassCode())) {
            return WebJsonResult.newSuccess(supplierClassInformationService.queryAllSupplierClssList(supplierClassInformation));
        }
        return WebJsonResult.newFailure("父Code不能为空");
    }

    /*查询父类信息*/
    @RequestMapping(params = "act=Find_queryAllSupplierLargeClass")
    public @ResponseBody
    WebJsonResult Find_queryAllSupplierLargeClass() {
        return WebJsonResult.newSuccess(supplierClassInformationService.queryAllSupplierLargeClass());
    }

    /*加入商品信息 act=addCommodityInformation*/
    @RequestMapping(params = "act=AddCommodityInformation")
    public @ResponseBody
    WebJsonResult AddCommodityInformation(@RequestBody CommodityInformation commodityInformation) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        Date Create = new Date();
        System.out.println("getProductionDate() " + commodityInformation.getProductionDate());
        SuppliersInformation suppliersInformation = new SuppliersInformation();
        if (StringUtil.isNotEmpty(commodityInformation.getSupplierId())) {
            suppliersInformation.setSupplierId(commodityInformation.getSupplierId());

            System.out.println("isNotEmpty supplierId " + suppliersInformation.getSupplierId());
            SuppliersInformation Su = supplierService.selectByIfKey(suppliersInformation);
            commodityInformation.setCreateBy(activeUser.getUserName());
            commodityInformation.setCreateDate(Create);
            commodityInformation.setSupplierCode(Su.getSupplierCode());
            commodityInformation.setSupplierName(Su.getChineseFullName());
            commodityInformation.setSupplierPhone(Su.getSupplierPhone());
            commodityInformation.setId(UID.getUID32());
            commodityInformation.setCommodityId(UID.getUID32());
            commodityInformation.setCommodityCode(new NumberingGeneration("SPXX").toNumberingGeneration());
            int status = commodityInformationService.insertSelective(commodityInformation);
            if (status == 1) {
                return WebJsonResult.newSuccess();
            } else {
                return WebJsonResult.newFailure("act=addCommodityInformation提示：SupplierId()不為空 失敗");
            }
        } else {
            return WebJsonResult.newFailure("act=addCommodityInformation提示：SupplierId()為空 失敗");
        }
    }

    @RequestMapping(params = "act=go_edit")
    public String goEdit() {
        System.out.println("UpCommodityInformation act=go_edit");
        return "/BaseInformation/CommodityInformation_edit";
    }

    @RequestMapping(params = "act=SingleCommodityInformation")
    public @ResponseBody
    WebJsonResult SingleCommodityInformation(CommodityInformation commodityInformation) {
        CommodityInformation commo = commodityInformationService.selectByPrimaryKey(commodityInformation);
        return WebJsonResult.newSuccess(commo);
    }

    @RequestMapping(params = "act=UpCommodityInformation")
    public @ResponseBody
    WebJsonResult UpCommodityInformation(@RequestBody CommodityInformation commodityInformation) {

        System.out.println(commodityInformation.getId()+"   "+commodityInformation.getSupplierId()+"   "+commodityInformation.getCommodityId());
        if(StringUtil.isNotEmpty(commodityInformation.getId())&&StringUtil.isNotEmpty(commodityInformation.getCommodityId())&&StringUtil.isNotEmpty(commodityInformation.getSupplierId())){
            int stutas=commodityInformationService.updateByPrimaryKeySelective(commodityInformation);
            if (stutas==1){
                return  WebJsonResult.newSuccess("UpCommodityInformatio: 更新成功");
            }else {
                return WebJsonResult.newFailure("UpCommodityInformatio: 更新失败 ");
            }
        }else {
            return WebJsonResult.newFailure("UpCommodityInformatio: ID||commdityId||SupplierId 为空");
        }
        /*return WebJsonResult.newFailure("UpCommodityInformatio: ID||commdityId||SupplierId 为空");*/
    }

    @RequestMapping(params = "act=CommodityInformationDelete")
    public @ResponseBody
    WebJsonResult CommodityInformationDelete(String id, String commodityId) {
        CommodityInformation commodityInformation = new CommodityInformation();
        commodityInformation.setCommodityId(commodityId);
        commodityInformation.setId(id);
        if (StringUtil.isNotEmpty(commodityInformation.getId()) && StringUtil.isNotEmpty(commodityInformation.getCommodityId())) {
            int status = commodityInformationService.deleteByPrimaryKey(commodityInformation);
            if (status == 1) {
                return WebJsonResult.newSuccess("删除成功");
            } else {
                return WebJsonResult.newFailure("删除失败");
            }
        } else {
            return WebJsonResult.newFailure("删除失败: ID||CommdityID 为空");
        }
    }

    @RequestMapping(params = "act=exportCommodityInformation")
    public void ExportCommodityInformation(HttpServletResponse response, HttpServletRequest request) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        List<CommodityInformation> commodityInformationList = commodityInformationService.QureyAllCommodityInformation();
        CommodityInformationListExcel supplierInformationListExcel = new CommodityInformationListExcel();
        Date t = new Date();
        //生成错误的文档
        try {
            XSSFWorkbook workbook = supplierInformationListExcel.CreateCommodityInformationListExcel(commodityInformationList);
            OutputStream ouputStream = response.getOutputStream();
            String fileName = formatter.format(t);
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + "CommodityInformation" + ".xlsx");
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();

        } catch (Exception e) {
            System.out.println("导出错误的excel文件,exportSupplierInformation 异常:");
        }
    }
}
