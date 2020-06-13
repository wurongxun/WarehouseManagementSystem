package com.shiro.test.mvc.service;/**
 * @Description OutOfStockService
 * @Author Rongxun.Wu
 * @Date 2020/2/10 14:14
 * @Version 1.0
 */

import com.shiro.test.mvc.base.application.web.json.WebJsonResult;
import com.shiro.test.mvc.pojo.DetailedListInformation;
import com.shiro.test.mvc.pojo.OutOfStock;

import java.util.List;

/**
 *
 * @ClassName OutOfStockService
 * @Description OutOfStockService
 * @Author Rongxun.Wu
 * @Date 2020/2/10 14:14
 * @Version 1.0
 *
 */
public interface OutOfStockInformationService {

    List<OutOfStock> QureyAllOutOfStock();

    WebJsonResult DetermineOutOfStock(DetailedListInformation detailedListInformation);
}
