package com.shiro.test.mvc.base.Status;

/**
 * @Description OrdeStatus
 * @Author Rongxun.Wu
 * @Date 2020/2/11 14:52
 * @Version 1.0
 */
public  enum OrderStatus {
    Successful("成功",1),Not_finished("未完成", 0), Pending_approval("待审核", 2);

    String status;
    int code;
    OrderStatus(String status, int code) {

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
