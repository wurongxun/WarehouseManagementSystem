package com.shiro.test.mvc.base.Status;

/**
 * @Description SEX
 * @Author Rongxun.Wu
 * @Date 2020/4/19 20:57
 * @Version 1.0
 */
public enum SEX {
    MAN("男",1),
    WOMAN("女",2);
    String sex;
    Integer code;
    SEX(String sex,Integer code){
        this.sex = sex;
        this.code = code;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static String getSexByCode(Integer code){
        for(SEX sex:SEX.values()){
            if(code.equals(sex.getCode())){
                return sex.getSex();
            }
        }
        return  null;
    }
}
