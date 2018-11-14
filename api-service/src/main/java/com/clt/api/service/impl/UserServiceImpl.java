package com.clt.api.service.impl;

import com.clt.api.dao.TestUserDao;
import com.clt.api.entity.User;
import com.clt.api.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TestUserDao testUserDao;

    @Override
    public List<User> findAll() {
        return testUserDao.findAll();
    }

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
        return testUserDao.findByPage();
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        return testUserDao.selectByPrimaryKey(id);
    }

    @Override
    public void insert(User user) {
        testUserDao.insert(user);
    }
}
