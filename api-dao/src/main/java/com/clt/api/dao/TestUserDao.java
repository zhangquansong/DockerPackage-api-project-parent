package com.clt.api.dao;

import com.clt.api.entity.User;
import com.github.pagehelper.Page;

import java.util.List;

public interface TestUserDao {
    int deleteByPrimaryKey(Long id);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);

    /**
     * 获取所有数据
     *
     * @return
     */
    List<User> findAll();

    /**
     * 分页查询数据
     *
     * @return
     */
    Page<User> findByPage();


}
