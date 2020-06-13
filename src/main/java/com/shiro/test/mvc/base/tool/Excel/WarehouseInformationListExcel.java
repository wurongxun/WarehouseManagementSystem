package com.shiro.test.mvc.base.tool.Excel;

import com.shiro.test.mvc.pojo.CommodityClassInformation;
import com.shiro.test.mvc.pojo.WarehouseInformation;
import com.shiro.test.mvc.service.CommoditClassInformationService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Description WarehouseInformationListExcel
 * @Author Rongxun.Wu
 * @Date 2020/3/2 23:48
 * @Version 1.0
 * 仓库信息
 */
public class WarehouseInformationListExcel {

    @Autowired
    private CommoditClassInformationService supplierClassInformationService;

    public XSSFWorkbook CreateWarehouseInformationListExcel(List<WarehouseInformation> listresult){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 1.创建HSSFWorkbook，一个HSSFWorkbook对应一个Excel文件
        XSSFWorkbook wb = new XSSFWorkbook();
        // 2.在workbook中添加一个sheet,对应Excel文件中的工作表
        XSSFSheet sheet = wb.createSheet("sheet1");
        // 3.设置表头，即每个列的列名
        String[] titel = {"仓库名称","创建时间","仓库负责人名","仓库负责人电话","启用日期","仓库类型","仓库存储大类"};
        // 3.1创建第一行
        XSSFRow row = sheet.createRow(0);
        // 此处创建一个序号列
        row.createCell(0).setCellValue("序号");
        // 将列名写入
        for (int i = 0; i < titel.length; i++) {
            // 给列写入数据,创建单元格，写入数据
            row.createCell(i+1).setCellValue(titel[i]);
        }
        // 写入正式数据
        for (int i = 0; i < listresult.size(); i++) {
            // 创建行
            row = sheet.createRow(i+1);
            // 序号
            row.createCell(0).setCellValue(i+1);
            row.createCell(1).setCellValue(listresult.get(i).getWarehouseName());

            sheet.autoSizeColumn(1, true);

            row.createCell(2).setCellValue(date.format(listresult.get(i).getCreateDate()));

            row.createCell(3).setCellValue(listresult.get(i).getWarehousePersonInChargeName());

            row.createCell(4).setCellValue(listresult.get(i).getWarehousePersonInChargePhone());

            //助记码
            row.createCell(5).setCellValue(date.format(listresult.get(i).getOpeningDate()));
            row.createCell(6).setCellValue(listresult.get(i).getWarehouseType());
            row.createCell(7).setCellValue(listresult.get(i).getCommodityCategory());
        }
        /**
         * 上面的操作已经是生成一个完整的文件了，只需要将生成的流转换成文件即可；
         * 下面的设置宽度可有可无，对整体影响不大
         */
        // 设置单元格宽度
        int curColWidth = 0;
        for (int i = 0; i <= titel.length; i++) {
            // 列自适应宽度，对于中文半角不友好，如果列内包含中文需要对包含中文的重新设置。
            sheet.autoSizeColumn(i, true);
            // 为每一列设置一个最小值，方便中文显示
            curColWidth = sheet.getColumnWidth(i);
            if(curColWidth<2500){
                sheet.setColumnWidth(i, 2500);
            }
            // 第3列文字较多，设置较大点。
            sheet.setColumnWidth(3, 8000);
        }
        return wb;
    }

    public String WarehouseType(int type){
        if (type==1){
            return "冷冻库";
        }else {
            return "常温库";
        }
    }

    public String ToCommodityCategory(String ToCommodityCategory){
     CommodityClassInformation commodityClassInformation1=new CommodityClassInformation();
        commodityClassInformation1.setParentClassCode(ToCommodityCategory);
        CommodityClassInformation   commodityClassInformation = supplierClassInformationService.selectByPrimaryCode(commodityClassInformation1);
        return commodityClassInformation.getClassificationName();

    }

}
