package com.shiro.test.mvc.base.web.controller.BaseInformation;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.shiro.test.mvc.base.application.web.json.DatagridResult;
import com.shiro.test.mvc.base.application.web.json.WebJsonResult;
import com.shiro.test.mvc.base.tool.Excel.WarehouseInformationListExcel;
import com.shiro.test.mvc.base.tool.NumberingGeneration;
import com.shiro.test.mvc.base.tool.RandomNumber;
import com.shiro.test.mvc.base.tool.UID;
import com.shiro.test.mvc.pojo.ActiveUser;
import com.shiro.test.mvc.pojo.UserInformation;
import com.shiro.test.mvc.pojo.WarehouseInformation;
import com.shiro.test.mvc.service.GoodsShelvesInformationService;
import com.shiro.test.mvc.service.UserInformationService;
import com.shiro.test.mvc.service.WarehouseInformationService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description WarehouseInformationController
 * @Author Rongxun.Wu
 * @Date 2020/2/9 20:20
 * @Version 1.0
 */
@Controller
@RequestMapping("warehouseInformation.html")
public class WarehouseInformationController {

    @Autowired
    public GoodsShelvesInformationService goodsShelvesInformationService;

    @Autowired
    private UserInformationService userService;

    @Autowired
    WarehouseInformationService warehouseInformationService;

    @RequestMapping(params = "act=page")
    public String Page() {
        return "BaseInformation/WarehouseInformation_list";
    }

    @RequestMapping(params = "act=go_edit")
    public String GoEdit() {
        return "BaseInformation/WarehouseInformation_edit";
    }

    @RequestMapping(params = "act=list")
    public @ResponseBody
    WebJsonResult WarehouseInformationList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "1") Integer rows, WarehouseInformation warehouseInformation) {
        System.out.println("WarehouseInformationList");
        PageHelper.startPage(page, rows);//int pageNum, int pageSize
        List<WarehouseInformation> warehouseInformationList = new ArrayList<>();
        if (StringUtil.isNotEmpty(warehouseInformation.getWarehouseName()) ||
                StringUtil.isNotEmpty(warehouseInformation.getWarehousePersonInChargeName()) ||
                StringUtil.isNotEmpty(warehouseInformation.getWarehousePersonInChargePhone()) ||
                StringUtil.isNotEmpty(warehouseInformation.getCommodityCategory()) ||
                warehouseInformation.getWarehouseType() != null ||
                warehouseInformation.getWarehouseNumber()!=null||
                warehouseInformation.getStatus() != null
        ) {
            warehouseInformationList = warehouseInformationService.find_likeWarehouseInformationList(warehouseInformation);
        } else {
            warehouseInformationList = warehouseInformationService.QureyAllWarehouseInformation();
        }
        PageInfo<WarehouseInformation> pageInfo = new PageInfo<>(warehouseInformationList, 6);
        final DatagridResult creditDatagridResult = new DatagridResult();
        creditDatagridResult.setTotal(pageInfo.getTotal());
        creditDatagridResult.setRows(pageInfo.getList());
        for (WarehouseInformation s : pageInfo.getList()
        ) {
            System.out.println(s.toString());
        }
        return WebJsonResult.newSuccess(creditDatagridResult);
    }

    @RequestMapping(params = "act=AddWarehouseInformation")
    public @ResponseBody
    WebJsonResult AddWarehouseInformation(@RequestBody WarehouseInformation warehouseInformation) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        Date Create = new Date();
        System.out.println("warehouseInformation::  " + warehouseInformation.toString());
        if(warehouseInformation.getWarehouseNumber()!=null&&warehouseInformationService.selectByWarehouseNumber(warehouseInformation.getWarehouseNumber())!=null){

            return WebJsonResult.newFailure("添加失败 已有仓库号 ");
        }else {
            if (StringUtil.isNotEmpty(warehouseInformation.getWarehousePersonInChargeId()) && StringUtil.isNotEmpty(warehouseInformation.getCommodityCategory())&&warehouseInformation.getWarehouseNumber()!=null) {
                UserInformation userInformation = userService.selectByPrimaryKey(warehouseInformation.getWarehousePersonInChargeId());
                warehouseInformation.setId(UID.getUID32());
                warehouseInformation.setCreateBy(activeUser.getUserName());
                warehouseInformation.setCreateDate(Create);
                warehouseInformation.setWarehouseId(UID.getUID32());
                /*warehouseInformation.setWarehouseCode(new NumberingGeneration("CKXX").toNumberingGeneration());*/
                warehouseInformation.setWarehouseCode("CKXX-"+String.format("%04d",warehouseInformation.getWarehouseNumber())+"-"
                        +RandomNumber.GetRandom2());
                warehouseInformation.setWarehousePersonInChargeCode(userInformation.getUserCode());
                warehouseInformation.setWarehousePersonInChargeName(userInformation.getUserName());
                warehouseInformation.setWarehousePersonInChargePhone(userInformation.getPhone());
                int status = warehouseInformationService.insertSelective(warehouseInformation);
                if (status == 1) {
                    return WebJsonResult.newSuccess("添加成功");
                } else {
                    return WebJsonResult.newFailure("添加失败");
                }
            }
            return WebJsonResult.newFailure("AddWarehouseInformation：未获负责人ID | 商品大类");
        }

    }

    @RequestMapping(params = "act=SingleWarehouseInformation")
    public @ResponseBody
    WebJsonResult SingleWarehouseInformation(@RequestBody WarehouseInformation warehouseInformation) {
        System.out.println("SingleWarehouseInformation" + warehouseInformation.getId());
        if (StringUtil.isNotEmpty(warehouseInformation.getId())) {
            WarehouseInformation wa = warehouseInformationService.selectByPrimaryKey(warehouseInformation.getId());
            return WebJsonResult.newSuccess(wa);
        } else {
            return WebJsonResult.newFailure("SingleWarehouseInformation: ID为空");
        }
    }

    @RequestMapping(params = "act=UpWarehouseInformation")
    public @ResponseBody
    WebJsonResult UpWarehouseInformation(@RequestBody WarehouseInformation warehouseInformation) {

        System.out.println("act=UpWarehouseInformation");
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        Date update = new Date();
        if (StringUtil.isNotEmpty(warehouseInformation.getId())) {
            warehouseInformation.setUpdateBy(activeUser.getUserName());
            warehouseInformation.setUpdateDate(update);
            System.out.println(warehouseInformation.toString());
            int status = warehouseInformationService.updateByPrimaryKeySelective(warehouseInformation);
            if (status == 1) {
                return WebJsonResult.newSuccess("仓库信息更新成功");
            } else {
                return WebJsonResult.newFailure("仓库信息更新失败");
            }

        } else {
            return WebJsonResult.newFailure("更新失败: ID为空");
        }
    }

    @RequestMapping(params = "act=DeleteWarehouseInformation")
    public @ResponseBody WebJsonResult DeleteWarehouseInformation(@RequestBody WarehouseInformation warehouseInformation){

        if (StringUtil.isNotEmpty(warehouseInformation.getId())){

            /*查询是否还有货架*/
            WarehouseInformation CountWarehouseInformation=warehouseInformationService.selectByPrimaryKey(warehouseInformation.getId());

            if (goodsShelvesInformationService.CountWarehouseId(CountWarehouseInformation.getWarehouseId())>0){
                return WebJsonResult.newFailure("仓库不为空 无法删除");
            }

            int status=warehouseInformationService.deleteByPrimaryKey(warehouseInformation.getId());
            if (status==1){
                return WebJsonResult.newSuccess("仓库信息删除成功");
            }else {
                return WebJsonResult.newSuccess("仓库信息删除失败: 数据库层出错");
            }

        }else {
            return WebJsonResult.newSuccess("仓库信息删除失败: 未接收到数据ID");
        }
    }
    @RequestMapping(params = "act=warehouseInformationExcel")
    public void WarehouseInformationExcel(HttpServletResponse response, HttpServletRequest request) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        List<WarehouseInformation> warehouseInformationList = warehouseInformationService.QureyAllWarehouseInformation();
        WarehouseInformationListExcel warehouseInformationListExcel=new WarehouseInformationListExcel();
        Date t = new Date();
        //生成错误的文档
        try {
            XSSFWorkbook workbook = warehouseInformationListExcel.CreateWarehouseInformationListExcel(warehouseInformationList);
            OutputStream ouputStream = response.getOutputStream();
            String fileName = formatter.format(t);
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + "WarehouseInformationList" + ".xlsx");
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();

        } catch (Exception e) {
            System.out.println("导出错误的excel文件,WarehouseInformationList 异常:");
        }
    }

    /*无条件获取全部仓库信息*/
    @RequestMapping(params = "act=listAll")
    public @ResponseBody
    WebJsonResult WarehouseInformationListAll() {
        List<WarehouseInformation> warehouseInformationList = warehouseInformationService.QureyAllWarehouseInformation();
        return WebJsonResult.newSuccess(warehouseInformationList);
    }

}
