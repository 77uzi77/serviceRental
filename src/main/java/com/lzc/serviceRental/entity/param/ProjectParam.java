package com.lzc.serviceRental.entity.param;

import com.lzc.serviceRental.entity.model.Project;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author lzc
 * @Date 2022/5/4
 * @Version 1.0
 */
@Component
@Data
public class ProjectParam extends Project {

    private String strDate;
}
