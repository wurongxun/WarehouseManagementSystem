package com.shiro.test.mvc.base.tool.Excel;

import com.shiro.test.mvc.pojo.CommodityInformation;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Description
 * @auther Eason
 * @create 2020-02-28 15:15
 * 商品信息
 */

public class CommodityInformationListExcel {

    public XSSFWorkbook CreateCommodityInformationListExcel(List<CommodityInformation> listresult){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 1.创建HSSFWorkbook，一个HSSFWorkbook对应一个Excel文件
        XSSFWorkbook wb = new XSSFWorkbook();
        // 2.在workbook中添加一个sheet,对应Excel文件中的工作表
        XSSFSheet sheet = wb.createSheet("sheet1");
        // 3.设置表头，即每个列的列名
        String[] titel = {"商品名称","创建时间","供应商编号","所属供应商","供应商电话"};
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
            // 医院名称
            row.createCell(1).setCellValue(listresult.get(i).getCommodityName());
            sheet.autoSizeColumn(1, true);
            // 业务类型
            row.createCell(2).setCellValue(date.format(listresult.get(i).getCreateDate()));
            // 异常信息
            row.createCell(3).setCellValue(listresult.get(i).getSupplierCode());
            // 数量
            row.createCell(4).setCellValue(listresult.get(i).getSupplierName());
            row.createCell(5).setCellValue(listresult.get(i).getSupplierPhone());
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
}