package com.shiro.test.mvc.base.Status;

/**
 * @Description shelfPositionisItEmptyStatus
 * @Author Rongxun.Wu
 * @Date 2020/3/20 13:08
 * @Version 1.0
 */
public enum shelfPositionisItEmptyStatus {
    AlreadyStored("已存储",1),
    Unsaved("未存储", 2),
    HasBeenSelected("已被入库单选中", 3),
    OutOfStockSelected("已被出库单选中", 4);
    String status;
    Integer code;
    shelfPositionisItEmptyStatus(String status, Integer code) {

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

    public static String getValueByCode(Integer code){
        for(shelfPositionisItEmptyStatus warehouseType:shelfPositionisItEmptyStatus.values()){
            if(code.equals(warehouseType.getCode())){
                return warehouseType.status;
            }
        }
        return  null;
    }
}
