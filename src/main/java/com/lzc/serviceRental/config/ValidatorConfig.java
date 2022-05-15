package com.lzc.serviceRental.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @Description Hibernate validator参数校验配置
 * @Author lzc
 * @Date 2022/5/7
 * @Version 1.0
 */
@Configuration
public class ValidatorConfig {

    //使@RequestParam注解的参数也可以使用Spring Validator进行参数校验
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        //设置validator模式为快速失败
        postProcessor.setValidator(validator());
        return postProcessor;
    }

    @Bean
    public Validator validator(){

        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .failFast(true) //快速失败模式，在第一次发生约束违规时立即从当前验证返回
                .buildValidatorFactory();

        return validatorFactory.getValidator();
    }

}
