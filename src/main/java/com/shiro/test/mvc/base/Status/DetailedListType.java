package com.shiro.test.mvc.base.Status;

/**
 * @Description DetailedListType
 * @Author Rongxun.Wu
 * @Date 2020/3/20 15:42
 * @Version 1.0
 */
public enum DetailedListType {
    Warehousing("入库清单", 1),
    OutOfStock("出库清单",2);
    String status;
    Integer code;
    DetailedListType(String status, Integer code) {

        this.status = status;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
