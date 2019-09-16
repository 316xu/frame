package com.example.demobackend.mapper;


import com.example.demobackend.model.WxUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface WxUserMapper {

    @Select("SELECT * FROM wx_user")
    WxUser get(@Param("uid") String openid);

    @Insert("INSERT INTO wx_user (uid) VALUE (#{uid})")
    void add(@Param("uid") String uid);

    void update(WxUser user);

    @Update("UPDATE wx_user SET last_login_time = CURRENT_TIMESTAMP WHERE uid = #{uid}")
    void login(@Param("uid") String uid);

}
