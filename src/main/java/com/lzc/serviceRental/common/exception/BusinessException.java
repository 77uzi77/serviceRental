package com.lzc.serviceRental.common.exception;

import com.lzc.serviceRental.common.enums.CodeEnum;

/**
 * @ClassName BusinessException
 * @Description 自定义业务异常类 当有业务异常发生时，如参数校验错误，
 *              抛出此异常，由GlobalExceptionHandler捕获处理
 * @Author lzc
 * @Date 2022/5/1
 * @Version 1.0
 */
public class BusinessException extends RuntimeException{
    private int code;
    private String message;
    private Object data;

    /**
     * 错误信息由传入的枚举类默认设定
     * @param exceptionEnum
     */
    public BusinessException(CodeEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

    /**
     * 如果需要自定义并覆盖默认的错误信息，使用此构造器
     * @param exceptionEnum
     * @param message   自定义的错误信息
     */
    public BusinessException(CodeEnum exceptionEnum, String message) {
        this.code = exceptionEnum.getCode();
        this.message = message;
    }

    /**
     * 如果需要自定义并覆盖默认的错误信息，并且携带数据域，使用此构造器
     * @param exceptionEnum
     * @param message   自定义的错误信息
     */
    public BusinessException(CodeEnum exceptionEnum, String message, Object data) {
        this.code = exceptionEnum.getCode();
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
