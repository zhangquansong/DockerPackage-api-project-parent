package com.clt.api.dao;

import com.clt.api.entity.User;
import com.clt.api.entity.UserExample;
import com.github.pagehelper.Page;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    Page<User> selectUserByPage();
}