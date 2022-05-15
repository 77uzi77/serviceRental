package com.lzc.serviceRental.entity.view;

import com.lzc.serviceRental.entity.model.Project;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description
 * @Author lzc
 * @Date 2022/5/5
 * @Version 1.0
 */
@Component
@Data
public class LayUIAProjectView {

    private Integer count; //总数（分页）
    private Integer code; //成功0，失败其他
    private String msg;
    private List<Project> data;
}
