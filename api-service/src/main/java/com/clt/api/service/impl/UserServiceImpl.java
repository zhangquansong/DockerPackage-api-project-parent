package com.clt.api.service.impl;

import com.clt.api.dao.UserMapper;
import com.clt.api.entity.User;
import com.clt.api.entity.UserExample;
import com.clt.api.service.UserService;
import com.clt.api.utils.PageInfo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

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
    public PageInfo<User> findByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<User> userList = userMapper.selectByExample(new UserExample());
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

}
