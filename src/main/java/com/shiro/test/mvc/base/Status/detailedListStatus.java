package com.shiro.test.mvc.base.Status;

/**
 * @Description detailedWarehousingEntryListStatus
 * @Author Rongxun.Wu
 * @Date 2020/3/20 10:39
 * @Version 1.0
 */

/*清单状态*/
public enum detailedListStatus {
    SuccessfulWarehousing("入库成功",1),
    ToBeAudited("等待审核", 2),
    AuditSuccess("审核成功", 3),
    WaitingWarehousingEntry("等待入库", 4),
    AuditFailed("审核不通过", 5),
    CancelApplicationForm("申请单已取消",6),
    SuccessfulOutOfStock("出库成功",7),
    WaitingOutOfStock("等待出库", 8);
    String status;
    Integer code;
    detailedListStatus(String status, Integer code) {

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

    public static String getStatusByCode(Integer code){
        for(shelfPositionisItEmptyStatus warehouseType:shelfPositionisItEmptyStatus.values()){
            if(code.equals(warehouseType.getCode())){
                return warehouseType.status;
            }
        }
        return  null;
    }
}
