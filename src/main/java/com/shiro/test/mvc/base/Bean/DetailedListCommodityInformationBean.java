package com.shiro.test.mvc.base.Bean;
import com.shiro.test.mvc.pojo.CommodityDetailedListBean;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @auther Rongxun.Wu
 * @create 2020-02-27 10:51
 */

public class DetailedListCommodityInformationBean {

    List<CommodityDetailedListBean> commodityDetaidLikeBeanList;
    String detailedListName;
    String receivingDelivererPersonName;
    String receivingDelivererPersonPhone;
    String receivingDelivererPersonAddress;
    //总数
    Integer totalQuantity;
    //总价
    BigDecimal totalSum;

    public List<CommodityDetailedListBean> getCommodityDetaidLikeBeanList() {
        return commodityDetaidLikeBeanList;
    }

    public void setCommodityDetaidLikeBeanList(List<CommodityDetailedListBean> commodityDetaidLikeBeanList) {
        this.commodityDetaidLikeBeanList = commodityDetaidLikeBeanList;
    }

    public String getDetailedListName() {
        return detailedListName;
    }

    public void setDetailedListName(String detailedListName) {
        this.detailedListName = detailedListName;
    }

    public String getReceivingDelivererPersonName() {
        return receivingDelivererPersonName;
    }

    public void setReceivingDelivererPersonName(String receivingDelivererPersonName) {
        this.receivingDelivererPersonName = receivingDelivererPersonName;
    }

    public String getReceivingDelivererPersonPhone() {
        return receivingDelivererPersonPhone;
    }

    public void setReceivingDelivererPersonPhone(String receivingDelivererPersonPhone) {
        this.receivingDelivererPersonPhone = receivingDelivererPersonPhone;
    }

    public String getReceivingDelivererPersonAddress() {
        return receivingDelivererPersonAddress;
    }

    public void setReceivingDelivererPersonAddress(String receivingDelivererPersonAddress) {
        this.receivingDelivererPersonAddress = receivingDelivererPersonAddress;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }
}