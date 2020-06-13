package com.shiro.test.mvc.base.web.controller.BaseInformation;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.shiro.test.mvc.base.application.web.json.DatagridResult;
import com.shiro.test.mvc.base.application.web.json.WebJsonResult;
import com.shiro.test.mvc.base.tool.Excel.GoodsShelvesInformationListExcel;
import com.shiro.test.mvc.base.tool.UID;
import com.shiro.test.mvc.pojo.ActiveUser;
import com.shiro.test.mvc.pojo.GoodsShelvesInformation;
import com.shiro.test.mvc.pojo.WarehouseInformation;
import com.shiro.test.mvc.service.GoodsShelvesInformationService;
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
 * @Description GoodsShelvesInformation
 * @Author Rongxun.Wu
 * @Date 2020/2/10 15:19
 * @Version 1.0
 *货架信息
 */
@Controller
@RequestMapping("goodsShelvesInformation.html")
public class GoodsShelvesInformationController {

    @Autowired
    WarehouseInformationService warehouseInformationService;

    @Autowired
    GoodsShelvesInformationService goodsShelvesInformationService;

    @RequestMapping(params = "act=page")
    public String Page() {
        return "BaseInformation/GoodsShelvesInformation_list";
    }

    @RequestMapping(params = "act=list")
    public @ResponseBody
    WebJsonResult GoodsShelvesInformationList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "1") Integer rows,GoodsShelvesInformation goodsShelvesInformation) {
        PageHelper.startPage(page, rows);
        List<GoodsShelvesInformation> supplierList =  new ArrayList<>();
        if (StringUtil.isNotEmpty(goodsShelvesInformation.getWarehouseName())||
                StringUtil.isNotEmpty(goodsShelvesInformation.getGoodsShelvesName())||
                StringUtil.isNotEmpty(goodsShelvesInformation.getGoodsShelvesCode())||
                goodsShelvesInformation.getGoodsShelvesNo()!=null||
                 goodsShelvesInformation.getStatus()!=null
        ){
            supplierList=goodsShelvesInformationService.find_likeGoodsShelvesInformationList(goodsShelvesInformation);
        }else {
            supplierList=goodsShelvesInformationService.QureyAllGoodsShelvesInformation();
        }
        PageInfo<GoodsShelvesInformation> pageInfo = new PageInfo<>(supplierList, 6);
        final DatagridResult creditDatagridResult = new DatagridResult();
        creditDatagridResult.setTotal(pageInfo.getTotal());
        creditDatagridResult.setRows(pageInfo.getList());
        for (GoodsShelvesInformation s : pageInfo.getList()
        ) {
            System.out.println(s.toString());
        }
        return WebJsonResult.newSuccess(creditDatagridResult);
    }

    @RequestMapping(params = "act=AddGoodsShelvesInformation")
    public @ResponseBody
    WebJsonResult AddGoodsShelvesInformation(@RequestBody GoodsShelvesInformation goodsShelvesInformation) {

        System.out.println("goodsShelvesInformation: "+goodsShelvesInformation.toString());
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        Date Create = new Date();
        if (StringUtil.isNotEmpty(goodsShelvesInformation.getWarehouseId())&&
                goodsShelvesInformation.getGoodsShelvesLayerNo()!=null&&
                goodsShelvesInformation.getGoodsShelvesNo()!=null&&
                goodsShelvesInformation.getGoodsShelvesRowNo()!=null&&
                goodsShelvesInformation.getStatus()!=null
        ) {
            WarehouseInformation warehouseInformation = warehouseInformationService.selectByWarehouseId(goodsShelvesInformation.getWarehouseId());
            goodsShelvesInformation.setId(UID.getUID32());
            goodsShelvesInformation.setCreateBy(activeUser.getUserName());
            goodsShelvesInformation.setCreateDate(Create);
            goodsShelvesInformation.setGoodsShelvesId(UID.getUID32());
            goodsShelvesInformation.setWarehouseCode(warehouseInformation.getWarehouseCode());
            goodsShelvesInformation.setWarehouseName(warehouseInformation.getWarehouseName());
            goodsShelvesInformation.setGoodsShelvesCode("HJXX" +
                            "-"+String.format("%04d", warehouseInformation.getWarehouseNumber())+
                            "-"+String.format("%04d", goodsShelvesInformation.getGoodsShelvesNo())+
                            "-"+String.format("%04d", goodsShelvesInformation.getGoodsShelvesLayerNo())+
                            "-"+String.format("%04d", goodsShelvesInformation.getGoodsShelvesRowNo()));/*仓库号-货架号-货架层数总数-货架排总数*/
            int status=goodsShelvesInformationService.insertSelective(goodsShelvesInformation);
            if (status==1){
                return WebJsonResult.newSuccess("添加成功");
            }else {
                return WebJsonResult.newFailure("添加失败：持久层");
            }
        }else {
            return WebJsonResult.newFailure("仓库ID(warehouseId) || 货架号 || 货架层数 ||货架排数 为空");
        }
    }

    @RequestMapping(params = "act=go_edit")
    public String GoEdit(){ return "BaseInformation/GoodsShelvesInformation_edit"; }


    /*查询货架*/
    @RequestMapping(params = "act=SingleGoodsShelvesInformation")
    public @ResponseBody WebJsonResult SingleGoodsShelvesInformation(@RequestBody GoodsShelvesInformation goodsShelvesInformation){

        if(StringUtil.isNotEmpty(goodsShelvesInformation.getId())){
            GoodsShelvesInformation goodsShelves=goodsShelvesInformationService.selectByPrimaryKey(goodsShelvesInformation.getId());
            System.out.println("Single:   "+goodsShelves.toString());
            return WebJsonResult.newSuccess("SingleGoodsShelvesInformation 成功",goodsShelves);
        }else {
            return WebJsonResult.newFailure("货架ID为空");
        }
    }
    /*删除货架信息*/
    @RequestMapping(params = "act=GoodsShelvesInformationDelete")
    public @ResponseBody
    WebJsonResult GoodsShelvesInformationDelete(String id) {
        System.out.println("GoodsShelvesInformationDelete:   "+id);
        GoodsShelvesInformation goodsShelvesInformation = new GoodsShelvesInformation();
        goodsShelvesInformation.setId(id);
        if (StringUtil.isNotEmpty(goodsShelvesInformation.getId())) {
            int status =goodsShelvesInformationService.deleteByPrimaryKey(goodsShelvesInformation.getId());
            if (status == 1) {
                return WebJsonResult.newSuccess("删除成功");
            } else {
                return WebJsonResult.newFailure("删除失败");
            }
        } else {
            return WebJsonResult.newFailure("删除失败: ID 为空");
        }
    }

    /*更新货架 初版*/
    @RequestMapping(params = "act=UpGoodsShelvesInformation")
    public @ResponseBody WebJsonResult UpGoodsShelvesInformation(@RequestBody GoodsShelvesInformation goodsShelvesInformation){
        System.out.println("UpGoodsShelvesInformation"+goodsShelvesInformation.toString());
        if(StringUtil.isNotEmpty(goodsShelvesInformation.getId())){
            int status=goodsShelvesInformationService.updateByPrimaryKeySelective(goodsShelvesInformation);
            if (status==1){
                return WebJsonResult.newSuccess("跟新成功");
            }else if(status==0) {
                return WebJsonResult.newSuccess("数据未变化");
            }else {
                return WebJsonResult.newFailure("持久层出错");
            }
        }else {
            return WebJsonResult.newFailure("更新失败：数据接收未成功");
        }
    }

    /*按仓库ID查询货架*/
    @RequestMapping(params = "act=QueryGoodsShelvesInformationWarehouseId")
    public  @ResponseBody WebJsonResult QueryGoodsShelvesInformationWarehouseId(String warehouseId ){
        System.out.println("act=QueryGoodsShelvesInformationWarehouseId");
        List<GoodsShelvesInformation> goodsShelvesInformations=goodsShelvesInformationService.selectByPrimaryWarehouseId(warehouseId);
        if (goodsShelvesInformations.size()>0){
            return  WebJsonResult.newSuccess("查询成功",goodsShelvesInformations);
        }else {
            return WebJsonResult.newFailure("未查询到货架请添加");
        }

    }

    /*按仓库ID查询货架*/
    @RequestMapping(params = "act=SingleGoodsShelvesInformationByGoodsShelvesId")
    public  @ResponseBody WebJsonResult QueryGoodsShelvesInformationByGoodsShelvesId(String goodsShelvesId ){
        System.out.println("act=SingleGoodsShelvesInformationByGoodsShelvesId");
        GoodsShelvesInformation goodsShelvesInformations=goodsShelvesInformationService.selectByPrimaryByGoodsShelvesId(goodsShelvesId);
        if (goodsShelvesInformations.getId()!=null){
            return  WebJsonResult.newSuccess("查询成功",goodsShelvesInformations);
        }else {
            return WebJsonResult.newFailure("未查询到货架请添加");
        }

    }

    @RequestMapping(params = "act=exportGoodsShelvesInformation")
    public void ExportCommodityInformation(HttpServletResponse response, HttpServletRequest request) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        List<GoodsShelvesInformation> goodsShelvesInformations = goodsShelvesInformationService.QureyAllGoodsShelvesInformation();
        GoodsShelvesInformationListExcel goodsShelvesInformationListExcel = new GoodsShelvesInformationListExcel();
        Date t = new Date();
        //生成错误的文档
        try {
            XSSFWorkbook workbook = goodsShelvesInformationListExcel.CreateGoodsShelvesInformationListExcel(goodsShelvesInformations);
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
