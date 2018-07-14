package com.sfy.mapper;

import com.sfy.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    // 查询语句
    @Select("SELECT * FROM USER WHERE NAME=#{name}")
    User findByName(@Param("name") String name);

    //添加
    @Insert("INSERT INTO USER(NAME, AGE) values(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") int age);

    @Select("SELECT * FROM USER")
    List<User> findUserList();
}
