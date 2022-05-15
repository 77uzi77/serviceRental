package com.lzc.serviceRental.entity.view;

import com.lzc.serviceRental.entity.model.Role;
import com.lzc.serviceRental.entity.model.User;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Description
 * @Author lzc
 * @Date 2022/5/5
 * @Version 1.0
 */
@Component
@Data
public class LoginView implements Serializable {

    private User user;
    private Role role;
}
