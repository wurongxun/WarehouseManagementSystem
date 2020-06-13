package com.shiro.test.mvc.base.tool.Excel;

import com.shiro.test.mvc.base.Status.WarehouseType;
import com.shiro.test.mvc.base.Status.shelfPositionisItEmptyStatus;
import com.shiro.test.mvc.pojo.ShelfPositionInformation;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description ShelfPositionInformationExcel
 * @Author Rongxun.Wu
 * @Date 2020/4/19 14:20
 * @Version 1.0
 */
public class ShelfPositionInformationExcel {

    public XSSFWorkbook CreateGoodsShelfPositionInformationListExcel(List<ShelfPositionInformation> listresult){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 1.创建HSSFWorkbook，一个HSSFWorkbook对应一个Excel文件
        XSSFWorkbook wb = new XSSFWorkbook();
        // 2.在workbook中添加一个sheet,对应Excel文件中的工作表
        XSSFSheet sheet = wb.createSheet("sheet1");
        // 3.设置表头，即每个列的列名
        String[] titel = {"架位编号","创建时间","所属货架号","仓库名称", "仓库编码",
                "长","宽","高","负责人","仓库类型", "存取次数","存储状态","状态"};
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
            row.createCell(1).setCellValue(listresult.get(i).getGoodsShelvesNo());
            sheet.autoSizeColumn(1, true);
            row.createCell(2).setCellValue(ReturnDate(listresult.get(i).getCreateDate()));
            row.createCell(3).setCellValue(listresult.get(i).getGoodsShelvesNo());
            row.createCell(4).setCellValue(listresult.get(i).getWarehouseName());
            row.createCell(5).setCellValue(listresult.get(i).getWarehouseCode());
            row.createCell(6).setCellValue(listresult.get(i).getLength());
            row.createCell(7).setCellValue(listresult.get(i).getWide());
            row.createCell(8).setCellValue(listresult.get(i).getHeight());
            row.createCell(9).setCellValue(listresult.get(i).getWarehousePersonInCharge());
            row.createCell(10).setCellValue(WarehouseType.getValueByCode(listresult.get(i).getWarehouseType()));
            row.createCell(11).setCellValue(listresult.get(i).getUsageRecord());
            row.createCell(12).setCellValue(shelfPositionisItEmptyStatus.getValueByCode(listresult.get(i).getIsItEmpty()));
            row.createCell(13).setCellValue(Status(listresult.get(i).getStatus()));
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
