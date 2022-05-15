package com.lzc.serviceRental.common.exception;

import com.lzc.serviceRental.common.enums.CodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常捕获处理类，捕获抛出的异常，并返回错误信息
 * @Author lzc
 * @Date 2022/5/1
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public JSONResponse handlerBusinessException(BusinessException be) {
        log.debug("发生业务异常 ---> 错误码:{}, 错误信息: {}", be.getCode(), be.getMessage());
        return new JSONResponse().fail(be.getCode(), be.getMessage()).setError(be.getMessage()).setData(be.getData());
    }

    /**
     * 统一参数校验异常处理
     * 将错误信息拼接并返回给前端
     *
     * @param e
     * @return
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public JSONResponse handlerValidationException(MethodArgumentNotValidException e) {

        log.debug("发生参数校验异常 ---> 错误信息: {}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder("参数校验错误： ");
        if (bindingResult != null) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            if (!CollectionUtils.isEmpty(fieldErrors)) {
                for (FieldError fieldError : fieldErrors) {
                    sb.append(fieldError.getField())
                            .append("->")
                            .append(fieldError.getDefaultMessage())
                            .append("  ");
                }
            }
        }
        return new JSONResponse().fail(CodeEnum.PARAMETER_VALUE_INVALID).setError(sb.toString());
    }

    /**
     * 参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public JSONResponse paramException(IllegalArgumentException e) {
        return new JSONResponse().fail(CodeEnum.PARAMETER_VALUE_INVALID).setError(e.getMessage());
    }

}
