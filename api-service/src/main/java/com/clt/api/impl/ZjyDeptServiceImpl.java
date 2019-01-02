package com.clt.api.impl;

import com.clt.api.entity.ZjyDept;
import com.clt.api.mapper.ZjyDeptMapper;
import com.clt.api.service.ZjyDeptService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.clt.api.param.ZjyDeptParam;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 *
 * @author zhangquansong
 * @since 2019-01-02
 */
@Service
@Transactional
public class ZjyDeptServiceImpl extends ServiceImpl<ZjyDeptMapper, ZjyDept> implements ZjyDeptService {


    @Autowired
    private ZjyDeptMapper zjyDeptMapper;


    /**
     * 新增
     *
     * @param zjyDept
     */
    @Override
    public void create(ZjyDept zjyDept) {
        this.insertOrUpdate(zjyDept);
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
     * @param zjyDept
     */
    @Override
    public void edit(ZjyDept zjyDept) {
        this.updateById(zjyDept);
    }

    /**
     * 查询列表
     *
     * @return
     */
    @Override
    public List<ZjyDept> listAll() {
        return this.selectList(null);
    }

    /**
    * 查询详情
    *
    * @param id 主键id
    * @return
    */
    @Override
    public ZjyDept findById(Integer id) {
        return this.selectById(id);
    }

}