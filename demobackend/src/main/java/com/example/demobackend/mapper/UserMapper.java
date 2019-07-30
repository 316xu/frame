package com.example.demobackend.mapper;

import com.example.demobackend.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user where name=#{name}")
    User getByName(@Param("name") String name);

    @Insert("INSERT INTO user (name, password) value (#{name}, #{password})")
    void insertUser(User user);

    @Insert("INSERT INTO user_privileges (name, privilege) value (#{name}, #{privilege})")
    void insertPrivilege(@Param("name") String name, @Param("privilege") String privilege);


    @Select("SELECT privilege FROM user_privileges where name=#{name}")
    List<String> privileges(@Param("name") String name);

    @Select("SELECT * FROM user LIMIT #{start}, #{size}")
    List<User> list(@Param("start") long start, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM user")
    long count();
}
