package com.lzc.serviceRental.entity.dto;

import com.lzc.serviceRental.entity.model.Hire;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author lzc
 * @Date 2022/5/4
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Component
@Data
public class HireDTO extends Hire {

    private String proName;
    private boolean isLate;
}
