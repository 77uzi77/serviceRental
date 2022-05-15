package com.lzc.serviceRental.service.impl;

import cn.hutool.core.util.StrUtil;
import com.lzc.serviceRental.service.ProjectService;
import com.lzc.serviceRental.common.enums.CodeEnum;
import com.lzc.serviceRental.common.exception.BusinessException;
import com.lzc.serviceRental.common.exception.JSONResponse;
import com.lzc.serviceRental.entity.dto.ProjectBaseDTO;
import com.lzc.serviceRental.entity.dto.ProjectDTO;
import com.lzc.serviceRental.entity.model.Project;
import com.lzc.serviceRental.entity.param.ProjectParam;
import com.lzc.serviceRental.mapper.ProjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<String> queryAllTypes() {
        return projectMapper.selectAllTypes();
    }

    @Override
    public List<ProjectBaseDTO> queryProjectsByType(String type) {
        return projectMapper.queryProjectsByType(type);
    }

    @Override
    public ProjectDTO queryProjectInfo(Integer projectId) {
        return projectMapper.queryProjectInfo(projectId);
    }

    @Override
    public JSONResponse insertProject(ProjectParam projectParam, Integer userId) {
        try {
            projectParam.setUserId(userId);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = simpleDateFormat.parse(projectParam.getStrDate());
            projectParam.setDate(date);
            projectMapper.insertProject(projectParam);
            return new JSONResponse().success();
        }catch (Exception e){
            log.error("数据库执行出错：{}",e.getMessage());
            throw new BusinessException(CodeEnum.DATABASE_ERROR,"数据库执行出错");
        }
    }

    @Override
    public List<ProjectBaseDTO> searchProject(String str) {
        if(StrUtil.isEmpty(str))
            return new ArrayList<>();
        return projectMapper.searchProject(str);
    }

    @Override
    public List<Project> selectAllProjects() {
        return projectMapper.selectAllProjects();
    }

    @Override
    public JSONResponse deleteProject(Integer proId) {
        projectMapper.deleteProject(proId);
        return new JSONResponse().success();
    }

    @Override
    public JSONResponse updateProject(Project project) {
        projectMapper.updateProject(project);
        return new JSONResponse().success();
    }
}
