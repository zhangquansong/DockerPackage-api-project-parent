package com.clt.api.dao;

import com.clt.api.entity.User;
import com.clt.api.entity.UserExample;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserExtendMapper {

    @Select("select * from user")
    Page<User> selectUserByPage();
}