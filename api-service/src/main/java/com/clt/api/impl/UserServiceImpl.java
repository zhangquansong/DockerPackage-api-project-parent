package com.clt.api.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.clt.api.entity.User;
import com.clt.api.mapper.UserMapper;
import com.clt.api.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 分页查询
     *
     * @param pageNo   页号
     * @param pageSize 每页显示记录数
     * @return
     */
    @Override
    public Page<User> findByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Page<User> userList = userMapper.selectUserByPage();
        return userList;
    }

}
