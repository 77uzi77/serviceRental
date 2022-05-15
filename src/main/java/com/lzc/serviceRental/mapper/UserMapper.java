package com.lzc.serviceRental.mapper;

import com.lzc.serviceRental.entity.param.LoginParam;
import com.lzc.serviceRental.entity.param.RegisterParam;
import com.lzc.serviceRental.entity.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author lzc
 * @Date 2022/5/9
 * @Version 1.0
 */
@Mapper
@Repository
public interface UserMapper{

    @Select("select user_id from user")
    List<String> selectAllUserIds();

    @Select("select * from user where user_name = #{userName}")
    User selectUser(LoginParam loginParam);

    @Insert("insert into user(user_name,password,sex) " +
            "   values(#{userName},#{password},'未知')")
    @Options(useGeneratedKeys = true,keyProperty = "userId",keyColumn = "user_id")
    Integer insertUser(RegisterParam registerParam);

    @Update("update user set sex = #{sex}, contact_type = #{contactType} , contact_num = #{contactNum} " +
            "where user_id = #{userId}")
    Integer updateUser(User user);

    @Delete("delete from user where user_id = #{userId}")
    void deleteUser(@Param("userId") Integer userId);

    @Select("select * from user")
    List<User> selectAllUser();
}
