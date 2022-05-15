package com.lzc.serviceRental.entity.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description
 * @Author lzc
 * @Date 2022/5/11
 * @Version 1.0
 */
@Component
@Data
public class RegisterParam implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer userId;

    @NotBlank(message = "用户姓名不能为空")
    @Length(min = 3, max = 10, message = "用户名只能为3-10个字符")
    private String userName;

    @NotBlank(message = "用户密码不能为空")
    @Length(min = 3, max = 12, message = "密码只能为3-12个字符")
    private String password;

    @NotBlank(message = "用户确认密码不能为空")
    @Length(min = 3, max = 12, message = "确认密码只能为3-12个字符")
    private String password02;

    private Integer type;
}
