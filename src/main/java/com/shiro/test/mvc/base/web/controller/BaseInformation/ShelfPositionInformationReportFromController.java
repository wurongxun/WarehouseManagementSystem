package com.shiro.test.mvc.base.web.controller.BaseInformation;

import com.shiro.test.mvc.base.application.web.json.WebJsonResult;
import com.shiro.test.mvc.service.ShelfPositionInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description ShelfPositionInformationReportFromController
 * @Author Rongxun.Wu
 * @Date 2020/4/8 15:57
 * @Version 1.0
 */


@Controller
@RequestMapping("shelfPositionInformationReportFrom.html")
public class ShelfPositionInformationReportFromController {

    @Autowired
    ShelfPositionInformationService shelfPositionInformationService;

    @RequestMapping(params = "act=page")
    public String Page() {
        return "BaseInformation/ShelfPositionInformation_ReportForm";
    }

    @RequestMapping(params = "act=json")
    public @ResponseBody
    WebJsonResult CategoryReportFrom() {
        Object[][] a = new Object[5][4];
        a[0][0] = "product";
        a[0][1] = "已存储";
        a[0][2] = "未存储";
        a[0][3] = "已被选中";
        a[1][0] = "仓库架位存储信息";
        a[1][1] = shelfPositionInformationService.Find_ShelfPositionIsItEmpty(1);
        a[1][2] = shelfPositionInformationService.Find_ShelfPositionIsItEmpty(2);
        a[1][3] = shelfPositionInformationService.Find_ShelfPositionIsItEmpty(3)+shelfPositionInformationService.Find_ShelfPositionIsItEmpty(4);

        return WebJsonResult.newSuccess("成功", a);
    }


}

