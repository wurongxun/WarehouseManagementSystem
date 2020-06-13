package com.shiro.test.mvc.base.Status;

/**
 * @Description WarehouseType
 * @Author Rongxun.Wu
 * @Date 2020/4/19 14:36
 * @Version 1.0
 */
public enum WarehouseType {
    GeneralWarehouse("普通仓库",2),
    Freezer("冷冻库",1);
    String value;
    Integer code;
    WarehouseType(String value, Integer code) {

        this.value = value;
        this.code = code;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    /**
     * 根据code获取去value
     * @param code
     * @return
     */
    public static String getValueByCode(Integer code){
        for(WarehouseType warehouseType:WarehouseType.values()){
            if(code.equals(warehouseType.getCode())){
                return warehouseType.getValue();
            }
        }
        return  null;
    }
}
