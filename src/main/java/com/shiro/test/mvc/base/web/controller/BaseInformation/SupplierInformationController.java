package com.shiro.test.mvc.base.web.controller.BaseInformation;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiro.test.mvc.base.application.web.json.DatagridResult;
import com.shiro.test.mvc.base.application.web.json.WebJsonResult;
import com.shiro.test.mvc.base.tool.Excel.SupplierInformationListExcel;
import com.shiro.test.mvc.base.tool.NumberingGeneration;
import com.shiro.test.mvc.base.tool.StringUtil;
import com.shiro.test.mvc.base.tool.UID;
import com.shiro.test.mvc.pojo.ActiveUser;
import com.shiro.test.mvc.pojo.SuppliersInformation;
import com.shiro.test.mvc.pojo.SuppliersInformationKey;
import com.shiro.test.mvc.pojo.testTime;
import com.shiro.test.mvc.service.SupplierInformationService;

import com.shiro.test.mvc.service.TestService;
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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("supplier.html")
public class SupplierInformationController {



    @Autowired
    TestService testService;

    @Autowired
    private SupplierInformationService supplierService;

    @RequestMapping(params="act=page")
    public String  Page(){
        return "BaseInformation/SuppliersInformation_list";
    }

    @RequestMapping(params = "act=list")
    public  @ResponseBody
    WebJsonResult supplierList(@RequestParam(value = "page",defaultValue ="1") Integer page, @RequestParam(value = "rows",defaultValue ="1")Integer rows,SuppliersInformation suppliersInformation){
        PageHelper.startPage(page,rows);
        /*List<SuppliersInformation> supplierList=supplierService.queryAllSupplier();
        for (SuppliersInformation s:
             supplierList) {
        System.out.println(s.getChineseFullName()+"  sdsdsd  "+s.getId()+"  "+s.getKaleidoscope()+"  "+s.getCreateDate());
        }*/
        List<SuppliersInformation> supplierList;
        System.out.println("suppliersInformation.toString()   "+ StringUtil.isEmpty(suppliersInformation.getChineseFullName())+"  SupplierStatus()  "  +(suppliersInformation.getSupplierStatus()!=null)+"    " +suppliersInformation.toString());
        if (StringUtil.isEmpty(suppliersInformation.getChineseFullName())&&
                StringUtil.isEmpty(suppliersInformation.getSupplierCode())&&
                StringUtil.isEmpty(suppliersInformation.getSupplierAbbreviation())&&
                StringUtil.isEmpty(suppliersInformation.getMnemonicCode())&&
                StringUtil.isEmpty(suppliersInformation.getSupplierPhone())&&
                StringUtil.isEmpty(suppliersInformation.getMainContact())&&
                StringUtil.isEmpty(suppliersInformation.getEmail())&&
                suppliersInformation.getSupplierStatus()==null&&
                suppliersInformation.getSupplierLevel()==null

        ){
            supplierList=supplierService.queryAllSupplier();
        }else {
            supplierList=supplierService.findLikeSupplier(suppliersInformation);
        }
        PageInfo<SuppliersInformation> pageInfo = new PageInfo<>(supplierList,6);
        final DatagridResult creditDatagridResult=new DatagridResult();
        creditDatagridResult.setTotal(pageInfo.getTotal());
        creditDatagridResult.setRows(pageInfo.getList());
        for (SuppliersInformation s:pageInfo.getList()
             ) {
            System.out.println(s.toString());
        }
        return  WebJsonResult.newSuccess(creditDatagridResult);
    }

    @RequestMapping(params = "act=test")
    public  @ResponseBody List<testTime> test(){
        List<testTime> list = testService.queryTime();
        System.out.println(list.listIterator());
        for (testTime t:list
             ) {
            System.out.println(t.getCreateTime());
        }
        return list;
    }

    @RequestMapping(params = "act=addSupplier")
    public @ResponseBody
    WebJsonResult addSupplier(@RequestBody SuppliersInformation supplierInformation){
        Subject subject= SecurityUtils.getSubject();
        Session session=subject.getSession();
        ActiveUser activeUser = (ActiveUser)session.getAttribute("activeUser");
        Date t = new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("act=addSupplier");
        supplierInformation.setCreateBy(activeUser.getUserName());
        supplierInformation.setSupplierId(UID.getUID32());
        supplierInformation.setId(UID.getUID32());
        supplierInformation.setCreateDate(t);
        supplierInformation.setSupplierCode(new NumberingGeneration("GYSX").toNumberingGeneration());
        System.out.println(supplierInformation.toString());
        int c=supplierService.insert(supplierInformation);
        if (c==0){
            WebJsonResult.newFailure();
        }
       return WebJsonResult.newSuccess();
    }

    @RequestMapping(params = "act=go_edit")
    public String goEdit(){
        System.out.println("uppliersInformation act=go_edit");

        return "/BaseInformation/SuppliersInformation_edit";
    }

    @RequestMapping(params = "act=SingleInformation")
    public @ResponseBody
    WebJsonResult SingleInformation(String id,String supplierId){
        System.out.println("suppliersInformationKey哈哈哈  "+id+"  supplierId  "+supplierId);
        SuppliersInformationKey suppliersInformationKey=new SuppliersInformationKey();
        suppliersInformationKey.setId(id);
        suppliersInformationKey.setSupplierId(supplierId);
        SuppliersInformation suppliersInformation=supplierService.selectByPrimaryKey(suppliersInformationKey);
        return WebJsonResult.newSuccess(suppliersInformation);
    }



    @RequestMapping(params = "act=UpSupplierInformation")
    public @ResponseBody
    WebJsonResult updataSupplierInformation(@RequestBody SuppliersInformation supplierInformation){

        Subject subject= SecurityUtils.getSubject();
        Session session=subject.getSession();
        ActiveUser activeUser = (ActiveUser)session.getAttribute("activeUser");
        Date t = new Date();
        System.out.println("supplierInformation.getId() "+supplierInformation.getId()+"  supplierInformation.getSupplierId()  "+supplierInformation.getSupplierId());
        if (supplierInformation.getId()!=null&&supplierInformation.getSupplierId()!=null){

            supplierInformation.setUpdateBy(activeUser.getUserName());
            supplierInformation.setUpdateDate(t);
            System.out.println(supplierInformation.toString());
            int status= supplierService.updateByPrimaryKeySelective(supplierInformation);

            if (status==1){
                return   WebJsonResult.newSuccess();
            }else {
                return WebJsonResult.newFailure();
            }
        }
        System.out.printf("提交为空");
        return WebJsonResult.newFailure("提交为空");
    }


    @RequestMapping(params = "act=SuppliersDelete")
    public @ResponseBody
    WebJsonResult SuppliersDelete(String id,String supplierId){
        System.out.println("删除供应商信息 "+id+"  supplierId  "+supplierId);
        SuppliersInformationKey suppliersInformationKey=new SuppliersInformationKey();
        suppliersInformationKey.setId(id);
        suppliersInformationKey.setSupplierId(supplierId);

        if (suppliersInformationKey.getId()!=null&&suppliersInformationKey.getSupplierId()!=null){

            int status=supplierService.deleteByPrimaryKey(suppliersInformationKey);

            if (status==1){
                return   WebJsonResult.newSuccess();
            }else {
                return WebJsonResult.newFailure();
            }
        }
        System.out.printf("SuppliersDelete提交为空");
        return WebJsonResult.newFailure("SuppliersDelete提交为空");
    }

    @RequestMapping(params ="act=exportSupplierInformation")
    public void exportSupplierInformation(/*@RequestParam(value = "paramUrl") String paramUrl,*/ HttpServletResponse response, HttpServletRequest request) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        List<SuppliersInformation> supplierList=supplierService.queryAllSupplier();
        SupplierInformationListExcel supplierInformationListExcel=new SupplierInformationListExcel();
        Date t = new Date();
        //生成错误的文档
        try {
            XSSFWorkbook workbook =supplierInformationListExcel.CreateSupplierInformationListExcel(supplierList);
            OutputStream ouputStream = response.getOutputStream();
            String fileName = formatter.format(t);
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();

        } catch (Exception e) {
            System.out.println("导出错误的excel文件,exportSupplierInformation 异常:");
        }
    }

    @RequestMapping(params = "act=Find_SupplierInformationList")
    public  @ResponseBody
    WebJsonResult Find_SupplierInformationList(){
        PageInfo<SuppliersInformation> pageInfo = new PageInfo<>(supplierService.queryAllSupplier(),6);
        final DatagridResult creditDatagridResult=new DatagridResult();
        creditDatagridResult.setTotal(pageInfo.getTotal());
        creditDatagridResult.setRows(pageInfo.getList());
        for (SuppliersInformation s:pageInfo.getList()
        ) {
            System.out.println(s.toString());
        }
        return  WebJsonResult.newSuccess(creditDatagridResult);
    }

}
