package com.lzc.serviceRental.mapper;

import com.lzc.serviceRental.entity.dto.HireDTO;
import com.lzc.serviceRental.entity.model.Hire;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author lzc
 * @Date 2022/5/4
 * @Version 1.0
 */
@Mapper
@Repository
public interface HireMapper {

    @Insert("insert into hire(pro_id,user_id,date) " +
            "values(#{proId},#{userId},#{date})")
    @Options(useGeneratedKeys = true,keyProperty = "hireId",keyColumn = "hire_id")
    void insertHire(Hire hire);

    @Select("select h.*,p.pro_name from hire h, project p where h.pro_id = p.pro_id and h.user_id = #{userId}")
    List<HireDTO> selectHire(@Param("userId")Integer userId);

    @Delete("delete from hire where hire_id = #{hireId}")
    void deleteHire(@Param("hireId")Integer hireId);

    @Delete("delete from hire where user_id = #{userId}")
    void deleteUserHire(@Param("userId")Integer userId);
}
