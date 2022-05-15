package com.lzc.serviceRental.controller;

import com.lzc.serviceRental.service.HireService;
import com.lzc.serviceRental.common.exception.JSONResponse;
import com.lzc.serviceRental.entity.dto.HireDTO;
import com.lzc.serviceRental.entity.model.Hire;
import com.lzc.serviceRental.entity.model.Role;
import com.lzc.serviceRental.entity.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author lzc
 * @Date 2022/5/4
 * @Version 1.0
 */
@Controller
@Slf4j
public class HireController {

    @Autowired
    private HireService hireService;

    @PostMapping("/hire/insertHire")
    @ResponseBody
    public JSONResponse insertHire(@RequestBody Hire hire, HttpServletRequest request){
        Role role = (Role)request.getSession().getAttribute("role");
        return hireService.insertHire(hire,role.getType());
    }

    @PostMapping("/hire/queryUserHire")
    @ResponseBody
    public JSONResponse queryUserHire(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        Role role = (Role)request.getSession().getAttribute("role");
        List<HireDTO> hireDTOList = hireService.queryUserHire(user.getUserId(),role.getType());
        request.getSession().setAttribute("hireDTOList",hireDTOList);
        return new JSONResponse().success().setData(hireDTOList);
    }

    @PostMapping("/hire/deleteHire")
    @ResponseBody
    public JSONResponse deleteHire(@RequestBody Map<String, Object> models, HttpServletRequest request){
        Integer hireId = Integer.valueOf((String)models.get("hireId"));
        Role role = (Role)request.getSession().getAttribute("role");
        return hireService.deleteHire(hireId,role.getType());
    }
}

