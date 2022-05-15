package com.lzc.serviceRental.service.impl;

import com.lzc.serviceRental.service.UserService;
import com.lzc.serviceRental.common.enums.CodeEnum;
import com.lzc.serviceRental.common.exception.BusinessException;
import com.lzc.serviceRental.common.exception.JSONResponse;
import com.lzc.serviceRental.entity.model.Role;
import com.lzc.serviceRental.entity.model.User;
import com.lzc.serviceRental.entity.param.LoginParam;
import com.lzc.serviceRental.entity.param.RegisterParam;
import com.lzc.serviceRental.entity.view.LoginView;
import com.lzc.serviceRental.mapper.HireMapper;
import com.lzc.serviceRental.mapper.ProjectMapper;
import com.lzc.serviceRental.mapper.RoleMapper;
import com.lzc.serviceRental.mapper.UserMapper;
import com.lzc.serviceRental.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author lzc
 * @Date 2022/5/8
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private HireMapper hireMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<String> selectAllUserIds() {
        return userMapper.selectAllUserIds();
    }

    @Override
    public JSONResponse login(LoginParam loginParam) {
        log.info("_________开始登录__________");
        log.info("用户信息：{}",loginParam.toString());
        User user;
        Role role;
        try{
            user = userMapper.selectUser(loginParam);
            role = roleMapper.selectRole(user.getUserId());
        }catch (Exception e){
            log.error("查询用户信息失败：{}",e.getMessage());
            throw new BusinessException(CodeEnum.DATABASE_ERROR,"查询用户信息失败");
        }
        if(MD5Util.verify(loginParam.getPassword(),user.getPassword())){
            LoginView loginView = new LoginView();
            loginView.setUser(user);
            loginView.setRole(role);
            return new JSONResponse().success().setData(loginView);
        }else{
            return new JSONResponse().fail(CodeEnum.PASSWORD_ERROR);
        }
    }

    @Override
    public JSONResponse register(RegisterParam registerParam) {

        log.info("_________开始注册__________");
        log.info("用户信息：{}",registerParam.toString());

        if(!registerParam.getPassword().equals(registerParam.getPassword02())){
            return new JSONResponse().fail(CodeEnum.PARAMETER_ILLEGAL,"两次输入的密码不一致");
        }
        //密码MD5加盐加密
        registerParam.setPassword(MD5Util.generate(registerParam.getPassword()));
        if(userMapper.insertUser(registerParam) > 0){
            Role role = new Role();
            role.setUserId(registerParam.getUserId());
            role.setType(registerParam.getType());
            roleMapper.insertRole(role);
            return new JSONResponse().success();
        }else{
            return new JSONResponse().fail(CodeEnum.DATABASE_ERROR);
        }
    }

    @Override
    public JSONResponse updateUser(User user) {
        if(userMapper.updateUser(user) > 0){
            return new JSONResponse().success();
        }else{
            return new JSONResponse().fail(CodeEnum.DATABASE_ERROR);
        }
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public JSONResponse deleteUser(Integer loginUserId, Integer userId) {
        if(loginUserId.equals(userId))
            throw new BusinessException(CodeEnum.PARAMETER_VALUE_INVALID,"不能删除自己的账号");
        userMapper.deleteUser(userId);
        projectMapper.deleteUserProject(userId);
        hireMapper.deleteUserHire(userId);
        return new JSONResponse().success();
    }
}
