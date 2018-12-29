package com.clt.api.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.clt.api.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user")
    Page<User> selectUserByPage();
}