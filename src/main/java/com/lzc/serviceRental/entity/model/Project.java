package com.lzc.serviceRental.entity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author lzc
 * @Date 2022/5/4
 * @Version 1.0
 */
@Component
@Data
public class Project implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer proId;
    private Integer userId;
    private String proName;
    private String content;
    private String type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date date;
}
