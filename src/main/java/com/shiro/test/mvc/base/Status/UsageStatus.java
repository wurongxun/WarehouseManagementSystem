package com.shiro.test.mvc.base.Status;

/**
 * @Description UsageStatus
 * @Author Rongxun.Wu
 * @Date 2020/3/20 13:16
 * @Version 1.0
 */
public enum UsageStatus {
    Enable("启用",1),
    DiscontinueUse("停用", 2);
    String status;
    Integer code;
    UsageStatus(String status, Integer code) {

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
