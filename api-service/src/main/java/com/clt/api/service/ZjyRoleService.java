package com.clt.api.service;

import com.clt.api.entity.ZjyRole;
import com.baomidou.mybatisplus.service.IService;
import com.clt.api.param.ZjyRoleParam;

import java.util.List;

/**
 * @author zhangquansong
 * @since 2018-12-29
 */
public interface ZjyRoleService extends IService<ZjyRole> {

    /**
     * 新增
     *
     * @param zjyRole
     */
    void create(ZjyRole zjyRole);

    /**
     * 删除
     *
     * @param id 主键id
     */
    void delete(Integer id);

    /**
     * 修改
     *
     * @param zjyRole
     */
    void edit(ZjyRole zjyRole);


    /**
     * 列表(全部)
     *
     * @return
     */
    List<ZjyRole> listAll();

    /**
    * 通过id获取数据
    *
    * @param id 主键id
    * @return
    */
    ZjyRole findById(Integer id);
}