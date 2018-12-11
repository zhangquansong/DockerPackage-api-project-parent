package com.clt.api.service.impl;

import com.clt.api.dao.UserExtendMapper;
import com.clt.api.entity.User;
import com.clt.api.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserExtendMapper userExtendMapper;

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
        Page<User> userList = userExtendMapper.selectUserByPage();
        return userList;
    }

}
