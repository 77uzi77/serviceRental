package com.lzc.serviceRental.service.impl;

import com.lzc.serviceRental.service.HireService;
import com.lzc.serviceRental.common.enums.CodeEnum;
import com.lzc.serviceRental.common.exception.BusinessException;
import com.lzc.serviceRental.common.exception.JSONResponse;
import com.lzc.serviceRental.entity.dto.HireDTO;
import com.lzc.serviceRental.entity.model.Hire;
import com.lzc.serviceRental.entity.model.Project;
import com.lzc.serviceRental.mapper.HireMapper;
import com.lzc.serviceRental.mapper.ProjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author lzc
 * @Date 2022/5/4
 * @Version 1.0
 */
@Service
@Slf4j
public class HireServiceImpl implements HireService {

    @Autowired
    private HireMapper hireMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public JSONResponse insertHire(Hire hire,Integer type) {
        try{
            // 登录用户为服务商
            if(type == 2){
                hire.setDate(new Date());
                hireMapper.insertHire(hire);
            }else{
                throw new BusinessException(CodeEnum.PARAMETER_VALUE_INVALID,"只有服务商可以接受服务项目");
            }
            return new JSONResponse().success();
        }catch (Exception e){
            log.error("数据库执行出错：{}",e.getMessage());
            throw new BusinessException(CodeEnum.DATABASE_ERROR,"数据库执行出错");
        }
    }

    @Override
    public List<HireDTO> queryUserHire(Integer userId, Integer type) {
        try{
            List<HireDTO> hireDTOList = new ArrayList<>();
            // 登录用户为服务商
            if(type == 2){
                hireDTOList = hireMapper.selectHire(userId);
            }else if(type == 1){    // 登录用户为客户
                List<Project> projectList = projectMapper.queryUserProjects(userId);
                List<HireDTO> finalHireDTOList = hireDTOList;
                projectList.forEach(project -> {
                    HireDTO hireDTO = new HireDTO();
                    hireDTO.setProId(project.getProId());
                    hireDTO.setProName(project.getProName());
                    hireDTO.setDate(project.getDate());
                    hireDTO.setHireId(project.getProId());
                    finalHireDTOList.add(hireDTO);
                });
                hireDTOList = finalHireDTOList;
            }
            if(hireDTOList.size() > 0){
                hireDTOList.forEach(hireDTO -> {
                    if(new Date().after(projectMapper.selectDate(hireDTO.getProId()))){
                        hireDTO.setLate(true);
                    }else {
                        hireDTO.setLate(false);
                    }
                });
            }
            return hireDTOList;
        }catch (Exception e){
            log.error("数据库执行出错：{}",e.getMessage());
            throw new BusinessException(CodeEnum.DATABASE_ERROR,"数据库执行出错");
        }
    }

    @Override
    public JSONResponse deleteHire(Integer hireId, Integer type) {
        try{
            // 登录用户为服务商
            if(type == 2){
                hireMapper.deleteHire(hireId);
            }else if(type == 1) {    // 登录用户为客户
                projectMapper.deleteProject(hireId);
            }
            return new JSONResponse().success();
        }catch (Exception e){
            log.error("数据库执行出错：{}",e.getMessage());
            throw new BusinessException(CodeEnum.DATABASE_ERROR,"数据库执行出错");
        }
    }
}
