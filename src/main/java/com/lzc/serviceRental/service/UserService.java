package com.lzc.serviceRental.service;

import com.lzc.serviceRental.common.exception.JSONResponse;
import com.lzc.serviceRental.entity.model.User;
import com.lzc.serviceRental.entity.param.LoginParam;
import com.lzc.serviceRental.entity.param.RegisterParam;

import java.util.List;

/**
 * @Description
 * @Author lzc
 * @Date 2022/5/6
 * @Version 1.0
 */
public interface UserService {

    List<String> selectAllUserIds();

    JSONResponse login(LoginParam loginParam);

    JSONResponse register(RegisterParam registerParam);

    JSONResponse updateUser(User user);

    List<User> selectAllUser();

    JSONResponse deleteUser(Integer loginUserId, Integer userId);
}
