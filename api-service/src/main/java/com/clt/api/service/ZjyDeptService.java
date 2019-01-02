package com.clt.api.service;

import com.clt.api.entity.ZjyDept;
import com.baomidou.mybatisplus.service.IService;
import com.clt.api.param.ZjyDeptParam;

import java.util.List;

/**
 * @author zhangquansong
 * @since 2019-01-02
 */
public interface ZjyDeptService extends IService<ZjyDept> {

    /**
     * 新增
     *
     * @param zjyDept
     */
    void create(ZjyDept zjyDept);

    /**
     * 删除
     *
     * @param id 主键id
     */
    void delete(Integer id);

    /**
     * 修改
     *
     * @param zjyDept
     */
    void edit(ZjyDept zjyDept);


    /**
     * 列表(全部)
     *
     * @return
     */
    List<ZjyDept> listAll();

    /**
    * 通过id获取数据
    *
    * @param id 主键id
    * @return
    */
    ZjyDept findById(Integer id);
}