package com.clt.api.service;

import com.clt.api.entity.User;
import com.github.pagehelper.Page;

import java.util.List;


public interface UserService {

    List<User> findAll();

    /**
     * 分页查询
     *
     * @param pageNo   页号
     * @param pageSize 每页显示记录数
     * @return
     */
    Page<User> findByPage(int pageNo, int pageSize);

    User selectByPrimaryKey(Long id);

    void insert(User person);
}
