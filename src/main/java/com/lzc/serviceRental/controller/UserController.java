package com.lzc.serviceRental.controller;

import cn.hutool.core.util.ObjectUtil;
import com.google.gson.Gson;
import com.lzc.serviceRental.service.UserService;
import com.lzc.serviceRental.common.exception.JSONResponse;
import com.lzc.serviceRental.entity.model.User;
import com.lzc.serviceRental.entity.param.LoginParam;
import com.lzc.serviceRental.entity.param.RegisterParam;
import com.lzc.serviceRental.entity.view.LayUIAUserView;
import com.lzc.serviceRental.entity.view.LoginView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author lzc
 * @Date 2022/12/17
 * @Version 1.0
 */
@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"/","login.html"})
    public String loginHtml(){
        return "login";
    }

    @GetMapping("register.html")
    public String registerHtml(){
        return "register";
    }

    @GetMapping("head.html")
    public String headHtml(){
        return "head";
    }

    @GetMapping("index.html")
    public String indexHtml(HttpServletRequest request){
        // 未登录则设置为游客
        if(request.getSession().getAttribute("user") == null){
            User user = new User();
            user.setUserName("游客");
            request.getSession().setAttribute("user",user);
        }
        return "index";
    }

    @GetMapping("projectInType.html")
    public String projectInTypeHtml(){
        return "projectInType";
    }

    @GetMapping("projectAdd.html")
    public String projectAddHtml(){
        return "projectAdd";
    }

    @GetMapping("userCenter.html")
    public String userCenterHtml(){
        return "userCenter";
    }

    @GetMapping("userUpdate.html")
    public String userUpdateHtml(){
        return "userUpdate";
    }

    @GetMapping("hireUser.html")
    public String hireUserHtml(){
        return "hireUser";
    }

    @GetMapping("manage.html")
    public String manageHtml(){
        return "manage";
    }

    @GetMapping("pro_manage.html")
    public String proManageHtml(){
        return "pro_manage";
    }

    @GetMapping("projectUpdate.html")
    public String projectUpdateHtml(){
        return "projectUpdate";
    }

    @PostMapping("/user/login")
    @ResponseBody
    public JSONResponse login(@RequestBody LoginParam loginParam, HttpServletRequest request){
        JSONResponse jsonResponse = userService.login(loginParam);
        if(ObjectUtil.isNotNull(jsonResponse.getData())){
            LoginView loginView = (LoginView)jsonResponse.getData();
            log.info("登录用户信息：{}",loginView.getUser().toString());
            request.getSession().setAttribute("user",loginView.getUser());
            request.getSession().setAttribute("role",loginView.getRole());
        }
        return jsonResponse;
    }

    @PostMapping("/user/register")
    @ResponseBody
    public JSONResponse register(@RequestBody RegisterParam registerParam){
        return userService.register(registerParam);
    }

    @PostMapping("/user/deleteUser")
    @ResponseBody
    public JSONResponse deleteUser(@RequestBody Map<String, Object> models, HttpServletRequest request){
        Integer userId = (Integer) models.get("userId");
        User loginUser = (User)request.getSession().getAttribute("user");
        return userService.deleteUser(loginUser.getUserId(),userId);
    }

    @PostMapping("/user/updateUser")
    @ResponseBody
    public JSONResponse updateUser(@RequestBody User user, HttpServletRequest request){
        User loginUser = (User)request.getSession().getAttribute("user");
        user.setUserId(loginUser.getUserId());
        JSONResponse jsonResponse = userService.updateUser(user);
        if(jsonResponse.getCode() == 1000){
            user.setUserName(loginUser.getUserName());
            request.getSession().setAttribute("user",user);
        }
        return jsonResponse;
    }


    @PostMapping("/user/selectAllUser")
    @ResponseBody
    public String selectAllUser(HttpServletRequest request){
        List<User> userList = userService.selectAllUser();
        LayUIAUserView layUIAUserView = new LayUIAUserView();
        Gson gson = new Gson();
        layUIAUserView.setCount(userList.size());
        layUIAUserView.setCode(0);
        layUIAUserView.setData(userList);
        layUIAUserView.setMsg("");
        log.info("______{}",gson.toJson(userList));
        return gson.toJson(layUIAUserView);
    }

}
