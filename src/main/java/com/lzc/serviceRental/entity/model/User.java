package com.lzc.serviceRental.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Description 用户实体类
 * @Author lzc
 * @Date 2022/5/13
 * @Version 1.0
 */
@Component
@Data
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer userId;
    private String userName;
    @JsonIgnore
    private String password;
    private String sex;
    private String contactType;
    private String contactNum;
}
