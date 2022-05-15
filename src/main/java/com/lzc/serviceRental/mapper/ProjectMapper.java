package com.lzc.serviceRental.mapper;

import com.lzc.serviceRental.entity.dto.ProjectBaseDTO;
import com.lzc.serviceRental.entity.dto.ProjectDTO;
import com.lzc.serviceRental.entity.model.Project;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author lzc
 * @Date 2022/5/4
 * @Version 1.0
 */
@Mapper
@Repository
public interface ProjectMapper {

    @Select("select distinct type from project")
    List<String> selectAllTypes();

    @Select("select p.pro_id,p.pro_name,p.date,u.user_name from project p, user u " +
            "where p.user_id = u.user_id and p.type = #{type} order by date desc")
    List<ProjectBaseDTO> queryProjectsByType(String type);

    @Select("select p.*,u.user_name from project p, user u where p.user_id = u.user_id and pro_id = #{projectId}")
    ProjectDTO queryProjectInfo(@Param("projectId")Integer projectId);

    @Select("select * from project where user_id = #{userId}")
    List<Project> queryUserProjects(@Param("userId")Integer userId);

    @Insert("insert into project(pro_name,type,content,user_id,date) " +
            "values(#{proName},#{type},#{content},#{userId},#{date})")
    @Options(useGeneratedKeys = true,keyProperty = "proId",keyColumn = "pro_id")
    void insertProject(Project project);

    @Select("select p.pro_id,p.pro_name,p.date,u.user_name from project p, user u " +
            "where p.user_id = u.user_id and " +
            "p.pro_name like concat('%',#{str},'%') " +
            "order by date desc")
    List<ProjectBaseDTO> searchProject(@Param("str")String str);

    @Select("select date from project where pro_id = #{proId}")
    Date selectDate(@Param("proId")Integer proId);

    @Delete("delete from project where pro_id = #{proId}")
    void deleteProject(@Param("proId")Integer proId);

    @Delete("delete from project where user_id = #{userId}")
    void deleteUserProject(@Param("userId")Integer userId);

    @Select("select * from project")
    List<Project> selectAllProjects();

    @Update("update project set pro_name = #{proName}, content = #{content}, type = #{type} " +
            "where pro_id = #{proId}")
    void updateProject(Project project);

}
