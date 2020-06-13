package com.shiro.test.mvc.base.tool.Excel;

import com.shiro.test.mvc.base.Status.detailedListStatus;
import com.shiro.test.mvc.pojo.DetailedListInformation;
import com.shiro.test.mvc.pojo.WarehousingEntryInformation;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description WarehousingEntryInformationListExcel
 * @Author Rongxun.Wu
 * @Date 2020/4/19 15:34
 * @Version 1.0
 */
public class WarehousingEntryInformationListExcel {
    public XSSFWorkbook CreateWarehousingEntryInformationListExcel(List<DetailedListInformation> listresult) {
        // 1.创建HSSFWorkbook，一个HSSFWorkbook对应一个Excel文件
        XSSFWorkbook wb = new XSSFWorkbook();
        // 2.在workbook中添加一个sheet,对应Excel文件中的工作表
        XSSFSheet sheet = wb.createSheet("sheet1");
        // 3.设置表头，即每个列的列名
        String[] titel = {"入库单编号", "申请时间", "申请入库人", "审批人","审批时间",
                "入库单名称", "入库时间", "送货人名", "送货人电话",
                "送货人地址", "总金额/元", "数量", "状态", "是否已入库",};
        // 3.1创建第一行
        XSSFRow row = sheet.createRow(0);
        // 此处创建一个序号列
        row.createCell(0).setCellValue("序号");
        // 将列名写入
        for (int i = 0; i < titel.length; i++) {
            // 给列写入数据,创建单元格，写入数据
            row.createCell(i + 1).setCellValue(titel[i]);
        }
        // 写入正式数据
        for (int i = 0; i < listresult.size(); i++) {
            // 创建行
            row = sheet.createRow(i + 1);
            // 序号
            row.createCell(0).setCellValue(i + 1);
            // 货架名
            row.createCell(1).setCellValue(listresult.get(i).getDetailedListCode());
            sheet.autoSizeColumn(1, true);
            row.createCell(2).setCellValue(ReturnDate(listresult.get(i).getCreateDate()));
            row.createCell(3).setCellValue(listresult.get(i).getApplicantName());
            row.createCell(4).setCellValue(listresult.get(i).getAuditByName());
            row.createCell(5).setCellValue(ReturnDate(listresult.get(i).getAuditDate()));
            row.createCell(6).setCellValue(listresult.get(i).getDetailedListName());
            row.createCell(7).setCellValue(ReturnDate(listresult.get(i).getWarehousingOutOfStockDate()));
            row.createCell(8).setCellValue(listresult.get(i).getReceivingDelivererPersonName());
            row.createCell(9).setCellValue(listresult.get(i).getReceivingDelivererPersonPhone());
            row.createCell(10).setCellValue(listresult.get(i).getReceivingDelivererPersonAddress());

            row.createCell(11).setCellValue(listresult.get(i).getTotalSum().toString());
            row.createCell(12).setCellValue(listresult.get(i).getCommodityNumber());
            row.createCell(13).setCellValue(detailedListStatus.getStatusByCode(listresult.get(i).getStatus()));
            row.createCell(14).setCellValue(Status(listresult.get(i).getOutStorageStatus()));
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
            if (curColWidth < 2500) {
                sheet.setColumnWidth(i, 2500);
            }
            // 第3列文字较多，设置较大点。
            sheet.setColumnWidth(3, 8000);
        }
        return wb;
    }

    public String Status(Integer Status) {
        if (Status!=null&&Status==1) {
            return "是";
        } else {
            return "否";
        }
    }

    public String ReturnDate(Date dat) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (null == dat) {
            return " ";
        } else {
            return date.format(dat);
        }
    }


}
