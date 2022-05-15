package com.lzc.serviceRental.controller;

import com.google.gson.Gson;
import com.lzc.serviceRental.service.ProjectService;
import com.lzc.serviceRental.common.exception.JSONResponse;
import com.lzc.serviceRental.entity.dto.ProjectBaseDTO;
import com.lzc.serviceRental.entity.dto.ProjectDTO;
import com.lzc.serviceRental.entity.model.Project;
import com.lzc.serviceRental.entity.model.User;
import com.lzc.serviceRental.entity.param.ProjectParam;
import com.lzc.serviceRental.entity.view.LayUIAProjectView;
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
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/project/queryAllTypes")
    @ResponseBody
    public JSONResponse queryAllTypes(HttpServletRequest request){
        List<String> types = projectService.queryAllTypes();
        log.info("所有项目类型：{}",types);
        request.getSession().setAttribute("projectTypes",types);
        return new JSONResponse().success().setData(types);
    }

    @PostMapping("/project/queryProjectsByType")
    @ResponseBody
    public JSONResponse queryProjectsByType(@RequestBody Map<String, Object> models, HttpServletRequest request){

        String type = (String)models.get("type");
        List<ProjectBaseDTO> projectBaseDTOS = projectService.queryProjectsByType(type);
        log.info("该类型下的项目基础信息：{}",projectBaseDTOS);
        request.getSession().setAttribute("projectBaseDTOS",projectBaseDTOS);
        return new JSONResponse().success().setData(projectBaseDTOS);
    }

    @PostMapping("/project/queryProjectInfo")
    @ResponseBody
    public JSONResponse queryProjectInfo(@RequestBody Map<String, Object> models, HttpServletRequest request){

        Integer projectId = Integer.valueOf((String)models.get("projectId"));
        ProjectDTO projectDTO = projectService.queryProjectInfo(projectId);
        log.info("项目详细信息：{}",projectDTO);
        request.getSession().setAttribute("projectDTO",projectDTO);
        return new JSONResponse().success().setData(projectDTO);
    }

    @PostMapping("/project/insertProject")
    @ResponseBody
    public JSONResponse insertLecture(@RequestBody ProjectParam projectParam, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        return projectService.insertProject(projectParam, user.getUserId());
    }


    @PostMapping("/project/searchProject")
    @ResponseBody
    public JSONResponse searchProject(@RequestBody Map<String, Object> models, HttpServletRequest request){

        String str = (String)models.get("str");
        log.info("_________{}",str);
        List<ProjectBaseDTO> projectBaseDTOS = projectService.searchProject(str);
        log.info("模糊查询出的项目基础信息：{}",projectBaseDTOS);
        request.getSession().setAttribute("projectBaseDTOS",projectBaseDTOS);
        return new JSONResponse().success().setData(projectBaseDTOS);
    }

    @PostMapping("/project/selectAllProjects")
    @ResponseBody
    public String selectAllProjects(HttpServletRequest request){
        List<Project> projectList = projectService.selectAllProjects();
        LayUIAProjectView layUIAProjectView = new LayUIAProjectView();
        Gson gson = new Gson();
        layUIAProjectView.setCount(projectList.size());
        layUIAProjectView.setData(projectList);
        layUIAProjectView.setMsg("");
        layUIAProjectView.setCode(0);
        log.info("______{}",gson.toJson(projectList));
        return gson.toJson(layUIAProjectView);
    }

    @PostMapping("/project/deleteProject")
    @ResponseBody
    public JSONResponse deleteProject(@RequestBody Map<String, Object> models, HttpServletRequest request){
        Integer proId = (Integer)models.get("proId");
        return projectService.deleteProject(proId);
    }

    @PostMapping("/project/setEditProject")
    @ResponseBody
    public JSONResponse setEditProject(@RequestBody Project editProject, HttpServletRequest request){
        request.getSession().setAttribute("editProject",editProject);
        log.info("__________{}",editProject);
        return new JSONResponse().success();
    }

    @PostMapping("/project/updateProject")
    @ResponseBody
    public JSONResponse updateProject(@RequestBody Project project, HttpServletRequest request){
        return projectService.updateProject(project);
    }
}
