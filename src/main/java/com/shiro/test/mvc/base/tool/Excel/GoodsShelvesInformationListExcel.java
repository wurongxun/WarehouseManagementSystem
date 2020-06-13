package com.shiro.test.mvc.base.tool.Excel;

import com.shiro.test.mvc.pojo.GoodsShelvesInformation;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description GoodsShelvesInformationListExcel
 * @Author Rongxun.Wu
 * @Date 2020/4/19 12:35
 * @Version 1.0
 */
public class GoodsShelvesInformationListExcel {
    public XSSFWorkbook CreateGoodsShelvesInformationListExcel(List<GoodsShelvesInformation> listresult){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 1.创建HSSFWorkbook，一个HSSFWorkbook对应一个Excel文件
        XSSFWorkbook wb = new XSSFWorkbook();
        // 2.在workbook中添加一个sheet,对应Excel文件中的工作表
        XSSFSheet sheet = wb.createSheet("sheet1");
        // 3.设置表头，即每个列的列名
        String[] titel = {"货架名称","创建时间","仓库编号","货架编号","仓库名称","启用时间","货架号","货架层数","货架排数","状态"};
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
            // 货架名
            row.createCell(1).setCellValue(listresult.get(i).getGoodsShelvesName());
            sheet.autoSizeColumn(1, true);
            row.createCell(2).setCellValue(date.format(listresult.get(i).getCreateDate()));
            row.createCell(3).setCellValue(listresult.get(i).getWarehouseCode());
            row.createCell(4).setCellValue(listresult.get(i).getGoodsShelvesCode());
            row.createCell(5).setCellValue(listresult.get(i).getWarehouseName());
            row.createCell(6).setCellValue(ReturnDate(listresult.get(i).getOpeningDate()));
            row.createCell(7).setCellValue(listresult.get(i).getGoodsShelvesNo());
            row.createCell(8).setCellValue(listresult.get(i).getGoodsShelvesLayerNo());
            row.createCell(9).setCellValue(listresult.get(i).getGoodsShelvesRowNo());
            row.createCell(10).setCellValue(Status(listresult.get(i).getStatus()));
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

    public String Status(int type){
        if (type==1){
            return "启用";
        }else {
            return "停用";
        }
    }
    public String ReturnDate(Date dat){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(null==dat){
            return " ";
        }else {
            return date.format(dat);
        }
    }
}
