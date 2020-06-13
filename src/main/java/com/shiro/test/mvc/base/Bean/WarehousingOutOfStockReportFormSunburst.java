package com.shiro.test.mvc.base.Bean;

import java.util.List;

/**
 * @Description Json1
 * @Author Rongxun.Wu
 * @Date 2020/4/8 8:25
 * @Version 1.0
 */
public class WarehousingOutOfStockReportFormSunburst {
    Integer value;
    String name;
    List<WarehousingOutOfStockReportFormSunburst>  children;

    public List<WarehousingOutOfStockReportFormSunburst> getChildren() {
        return children;
    }

    public void setChildren(List<WarehousingOutOfStockReportFormSunburst> children) {
        this.children = children;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
