package com.lzc.serviceRental.mapper;

import com.lzc.serviceRental.entity.model.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author lzc
 * @Date 2022/5/4
 * @Version 1.0
 */
@Mapper
@Repository
public interface RoleMapper {

    @Insert("insert into role(user_id,type) " +
            "   values(#{userId},#{type})")
    Integer insertRole(Role role);

    @Select("select * from role where user_id = #{userId}")
    Role selectRole(@Param("userId") Integer userId);
}
