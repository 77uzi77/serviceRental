package com.lzc.serviceRental.entity.view;

import com.lzc.serviceRental.entity.model.User;
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
public class LayUIAUserView {

    private Integer code; //成功0，失败其他
    private Integer count; //总数（分页）
    private String msg;
    private List<User> data;
}
