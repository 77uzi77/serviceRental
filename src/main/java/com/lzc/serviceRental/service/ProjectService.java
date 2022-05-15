package com.lzc.serviceRental.service;

import com.lzc.serviceRental.common.exception.JSONResponse;
import com.lzc.serviceRental.entity.dto.ProjectBaseDTO;
import com.lzc.serviceRental.entity.dto.ProjectDTO;
import com.lzc.serviceRental.entity.model.Project;
import com.lzc.serviceRental.entity.param.ProjectParam;

import java.util.List;

/**
 * @Description
 * @Author lzc
 * @Date 2022/5/4
 * @Version 1.0
 */
public interface ProjectService {

    List<String> queryAllTypes();

    List<ProjectBaseDTO> queryProjectsByType(String type);

    ProjectDTO queryProjectInfo(Integer projectId);

    JSONResponse insertProject(ProjectParam projectParam, Integer userId);

    List<ProjectBaseDTO> searchProject(String str);

    List<Project> selectAllProjects();

    JSONResponse deleteProject(Integer proId);

    JSONResponse updateProject(Project project);
}
