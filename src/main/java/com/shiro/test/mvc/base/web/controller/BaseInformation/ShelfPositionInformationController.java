package com.shiro.test.mvc.base.web.controller.BaseInformation;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiro.test.mvc.base.application.web.json.DatagridResult;
import com.shiro.test.mvc.base.application.web.json.WebJsonResult;
import com.shiro.test.mvc.base.tool.Excel.ShelfPositionInformationExcel;
import com.shiro.test.mvc.base.tool.StringUtil;
import com.shiro.test.mvc.base.tool.UID;
import com.shiro.test.mvc.pojo.ActiveUser;
import com.shiro.test.mvc.pojo.GoodsShelvesInformation;
import com.shiro.test.mvc.pojo.ShelfPositionInformation;
import com.shiro.test.mvc.pojo.WarehouseInformation;
import com.shiro.test.mvc.service.GoodsShelvesInformationService;
import com.shiro.test.mvc.service.ShelfPositionInformationService;
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
import java.util.*;

/**
 * @Description ShelfPositionInformationController
 * @Author Rongxun.Wu
 * @Date 2020/2/9 22:48
 * @Version 1.0
 *
 * 架位信息
 */
@Controller
@RequestMapping("shelfPositionInformation.html")
public class ShelfPositionInformationController {

    @Autowired
    GoodsShelvesInformationService goodsShelvesInformationService;

    @Autowired
    ShelfPositionInformationService shelfPositionInformationService;

    @Autowired
    WarehouseInformationService warehouseInformationService;

    @RequestMapping(params = "act=page")
    public String Page() {
        return "BaseInformation/ShelfPositionInformation_list";
    }

    @RequestMapping(params = "act=list")
    public @ResponseBody
    WebJsonResult ShelfPositionInformationList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "1") Integer rows,ShelfPositionInformation shelfPositionInformation) {
        System.out.println("ShelfPositionInformation");
        PageHelper.startPage(page, rows);//int pageNum, int pageSize
        List<ShelfPositionInformation> warehouseInformationList;

        if(StringUtil.isNotEmpty(shelfPositionInformation.getShelfPositionCode())||
                StringUtil.isNotEmpty(shelfPositionInformation.getWarehousePersonInCharge())||
                StringUtil.isNotEmpty(shelfPositionInformation.getWarehouseCode())||
                StringUtil.isNotEmpty(shelfPositionInformation.getWarehouseName())||
                shelfPositionInformation.getShelfPositionRowNo()!=null||
                shelfPositionInformation.getGoodsShelvesNo()!=null||
                shelfPositionInformation.getShelfPositionLayerNo()!=null||
                shelfPositionInformation.getStatus()!=null||
                shelfPositionInformation.getWarehouseType()!=null

        ){
            warehouseInformationList = shelfPositionInformationService.Find_LikeQureyAllShelfPositionInformation(shelfPositionInformation);
        }else {
            warehouseInformationList = shelfPositionInformationService.QureyAllShelfPositionInformation();
        }

        PageInfo<ShelfPositionInformation> pageInfo = new PageInfo<>(warehouseInformationList, 6);
        final DatagridResult creditDatagridResult = new DatagridResult();
        creditDatagridResult.setTotal(pageInfo.getTotal());
        creditDatagridResult.setRows(pageInfo.getList());
        for (ShelfPositionInformation s : pageInfo.getList()
        ) {
            System.out.println(s.toString());
        }
        return WebJsonResult.newSuccess(creditDatagridResult);
    }


    /*批量添加架位*/
    @RequestMapping(params = "act=AddShelfPositionInformation_Batch")
    public @ResponseBody
    WebJsonResult AddShelfPositionInformation_Batch(@RequestBody ShelfPositionInformation shelfPositionInformation, Integer goodsShelvesLayerNo, Integer goodsShelvesRowNo) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        Date Create = new Date();

        System.out.println("shelfPositionInformation "+shelfPositionInformation.toString() );
        System.out.println("goodsShelvesLayerNo "+goodsShelvesLayerNo+"   goodsShelvesRowNo "+goodsShelvesRowNo);
        if(StringUtil.isEmpty(shelfPositionInformation.getGoodsShelvesId())||
                StringUtil.isEmpty(shelfPositionInformation.getWarehouseId())||
                StringUtil.isEmpty(shelfPositionInformation.getDimensionUnit())||
                StringUtil.isEmpty(shelfPositionInformation.getMassUnit())||
                shelfPositionInformation.getLength()==null||
                shelfPositionInformation.getWide()==null||
                shelfPositionInformation.getHeight()==null||
                shelfPositionInformation.getStatus()==null
        ){
            return WebJsonResult.newFailure("后台消息:  批量添加失败，含有未填项");
        }


        /*货架信息*/
        GoodsShelvesInformation goodsShelvesInformation = new GoodsShelvesInformation();
        goodsShelvesInformation = goodsShelvesInformationService.selectByPrimaryByGoodsShelvesId(shelfPositionInformation.getGoodsShelvesId());

        /*仓库信息*/
        WarehouseInformation warehouseInformation = warehouseInformationService.selectByWarehouseId(shelfPositionInformation.getWarehouseId());

        System.out.println("getWarehouseId() " + shelfPositionInformation.toString() + "  getGoodsShelvesId  " + shelfPositionInformation.getGoodsShelvesId() + "    " + goodsShelvesLayerNo + "   " + goodsShelvesRowNo);
        List<ShelfPositionInformation> addshelfPositionInformationList = new ArrayList<>();
        ShelfPositionInformation shelfPositionInformations;
        for (int i = 1; i <= goodsShelvesLayerNo; i++) {
            for (int j = 1; j <= goodsShelvesRowNo; j++) {
                System.out.print("shelfPositionInformation " + "  " + i + "   " + j);
                shelfPositionInformations = new ShelfPositionInformation();
                shelfPositionInformations.setId(UID.getUID32());
                shelfPositionInformations.setCreateBy(activeUser.getUserName());
                shelfPositionInformations.setCreateDate(Create);
                shelfPositionInformations.setShelfPositionId(UID.getUID32());
                shelfPositionInformations.setGoodsShelvesNo(goodsShelvesInformation.getGoodsShelvesNo());
                shelfPositionInformations.setShelfPositionLayerNo(i);//架位层号
                shelfPositionInformations.setShelfPositionRowNo(j);//架位排号
                shelfPositionInformations.setShelfPositionCode("JWXX" +
                        "-" + String.format("%04d", warehouseInformation.getWarehouseNumber()) +
                        "-" + String.format("%04d", goodsShelvesInformation.getGoodsShelvesNo()) +
                        "-" + String.format("%04d", i) +
                        "-" + String.format("%04d", j));/*仓库号-货架号-层数-货架-排数*/
                shelfPositionInformations.setGoodsShelvesId(shelfPositionInformation.getGoodsShelvesId());
                shelfPositionInformations.setGoodsShelvesCode(goodsShelvesInformation.getGoodsShelvesCode());
                shelfPositionInformations.setWarehouseId(warehouseInformation.getWarehouseId());
                shelfPositionInformations.setWarehouseCode(warehouseInformation.getWarehouseCode());
                shelfPositionInformations.setWarehouseName(warehouseInformation.getWarehouseName());
                shelfPositionInformations.setWarehousePersonInCharge(warehouseInformation.getWarehousePersonInCharge());
                shelfPositionInformations.setWarehouseType(warehouseInformation.getWarehouseType());
                if (shelfPositionInformation.getStatus() == 1) {
                    shelfPositionInformations.setOpeningDate(Create);
                }
                shelfPositionInformations.setDimensionUnit(shelfPositionInformation.getDimensionUnit());
                shelfPositionInformations.setMassUnit(shelfPositionInformation.getMassUnit());
                shelfPositionInformations.setVolume(shelfPositionInformation.getHeight() * shelfPositionInformation.getLength() * shelfPositionInformation.getWide());
                shelfPositionInformations.setArea(shelfPositionInformation.getLength() * shelfPositionInformation.getWide());
                shelfPositionInformations.setLength(shelfPositionInformation.getLength());
                shelfPositionInformations.setHeight(shelfPositionInformation.getHeight());
                shelfPositionInformations.setWide(shelfPositionInformation.getWide());
                shelfPositionInformations.setStatus(shelfPositionInformation.getStatus());
                shelfPositionInformations.setUsageRecord(0);
                shelfPositionInformations.setIsItEmpty(2);/*1 在存放东西 2 还没存放东西*/
                addshelfPositionInformationList.add(shelfPositionInformations);
            }
        }
        int status = shelfPositionInformationService.AddShelfPositionInformation_Batch(addshelfPositionInformationList);
        if (status!=0) {
            return  WebJsonResult.newSuccess("批量添加成功");
        } else {
            return WebJsonResult.newFailure("添加失败");
        }

    }

    /*查询符合商品存储架位的数量*/
    @RequestMapping(params = "act=find_ShelfPosition_Matching_Number")
    public @ResponseBody WebJsonResult findShelfPositionMatchingNumber(Integer commodityStorageType){
        System.out.println("commodityStorageType  "+commodityStorageType);
        Map<String, Object> param=new HashMap<>();
        param.put("warehouseType",commodityStorageType);
        param.put("isItEmpty",2);
        Integer number=shelfPositionInformationService.FindShelfPositionNumber(param);
        return WebJsonResult.newSuccess(number);
    }

    @RequestMapping(params = "act=exportShelfPositionInformation")
    public void ExportShelfPositionInformation(HttpServletResponse response, HttpServletRequest request,ShelfPositionInformation shelfPositionInformation) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        List<ShelfPositionInformation> shelfPositionInformationList = shelfPositionInformationService.Find_LikeQureyAllShelfPositionInformation(shelfPositionInformation);
        ShelfPositionInformationExcel shelfPositionInformationExcel=new ShelfPositionInformationExcel();
        Date t = new Date();
        //生成错误的文档
        try {
            XSSFWorkbook workbook = shelfPositionInformationExcel.CreateGoodsShelfPositionInformationListExcel(shelfPositionInformationList);
            OutputStream ouputStream = response.getOutputStream();
            String fileName = formatter.format(t);
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + "GoodsShelvesInformationListExcel" + ".xlsx");
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (Exception e) {
            System.out.println("导出错误的excel文件,exportGoodsShelvesInformationListExcel 异常:");
        }
    }
}
