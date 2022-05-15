package com.lzc.serviceRental.common.enums;


/**
 * @ClassName CodeEnum
 * @Description 错误码/错误信息枚举类
 * @Author lzc
 * @Date 2022/5/1
 * @Version 1.0
 */
public enum CodeEnum {

    SUCCESS(1000, "success"),
    FAIL(1001,"fail"),

    DATABASE_ERROR(1002,"数据库执行出错"),
    PARAMETER_VALUE_INVALID(1004,"参数的值不合法"),
    PARAMETER_MISSING(1005,"请求参数不全"),
    PARAMETER_ILLEGAL(1006,"请求参数不合法"),
    REAPT(1007,"已操作过"),
    NO_AUTHORIZATION(1008,"没有操作权限"),

    PASSWORD_ERROR(2001,"密码出错"),

    IO_ERROR(3002,"后台IO出错");

    private int code;   // 错误码
    private String message; //  错误信息

    CodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
