package com.lzc.serviceRental.entity.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Description 用户角色实体类
 * @Author lzc
 * @Date 2022/5/4
 * @Version 1.0
 */
@Component
@Data
public class Role implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer roleId;
    private Integer userId;
    private Integer type;
}
