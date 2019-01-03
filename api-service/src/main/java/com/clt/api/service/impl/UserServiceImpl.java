package com.clt.api.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.clt.api.entity.User;
import com.clt.api.mapper.UserMapper;
import com.clt.api.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangquansong
 * @since 2019-01-03
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private UserMapper userMapper;


    /**
     * 新增
     *
     * @param user
     */
    @Override
    public void create(User user) {
        this.insertOrUpdate(user);
    }

    /**
     * 删除
     *
     * @param id 主键id
     */
    @Override
    public void delete(Integer id) {
        this.deleteById(id);
    }

    /**
     * 修改
     *
     * @param user
     */
    @Override
    public void edit(User user) {
        this.updateById(user);
    }

    /**
     * 查询列表
     *
     * @return
     */
    @Override
    public List<User> listAll() {
        return this.selectList(null);
    }

    /**
     * 查询详情
     *
     * @param id 主键id
     * @return
     */
    @Override
    public User findById(Integer id) {
        return this.selectById(id);
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
        Page<User> userList = userMapper.findByPage();
        return userList;
    }
}