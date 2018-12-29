package com.clt.api.impl;

import com.clt.api.entity.ZjyRole;
import com.clt.api.mapper.ZjyRoleMapper;
import com.clt.api.service.ZjyRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.clt.api.param.ZjyRoleParam;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 *
 * @author zhangquansong
 * @since 2018-12-29
 */
@Service
@Transactional
public class ZjyRoleServiceImpl extends ServiceImpl<ZjyRoleMapper, ZjyRole> implements ZjyRoleService {


    @Autowired
    private ZjyRoleMapper zjyRoleMapper;


    /**
     * 新增
     *
     * @param zjyRole
     */
    @Override
    public void create(ZjyRole zjyRole) {
        this.insertOrUpdate(zjyRole);
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
     * @param zjyRole
     */
    @Override
    public void edit(ZjyRole zjyRole) {
        this.updateById(zjyRole);
    }

    /**
     * 查询列表
     *
     * @return
     */
    @Override
    public List<ZjyRole> listAll() {
        return this.selectList(null);
    }

    /**
    * 查询详情
    *
    * @param id 主键id
    * @return
    */
    @Override
    public ZjyRole findById(Integer id) {
        return this.selectById(id);
    }

}