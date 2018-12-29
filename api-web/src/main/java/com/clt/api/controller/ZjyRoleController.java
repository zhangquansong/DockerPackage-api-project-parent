package com.clt.api.controller;

import com.clt.api.service.ZjyRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.clt.api.param.ZjyRoleParam;
import java.util.List;
import com.clt.api.entity.ZjyRole;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author zhangquansong
 * @since 2018-12-29
 */
@RestController
@RequestMapping("/zjyRole")
public class ZjyRoleController  {

    @Autowired
    private  ZjyRoleService zjyRoleService;

    /**
     * 新增
     *
     * @param zjyRoleParam
     */
    @PostMapping("/create")
    @ResponseBody
    public void create(@RequestBody ZjyRoleParam zjyRoleParam) {
        ZjyRole zjyRole=new ZjyRole();
        BeanUtils.copyProperties(zjyRoleParam, ZjyRole.class);

        zjyRoleService.create(zjyRole);
    }

    /**
     * 删除
     *
     * @param id 主键id
     */
    @PostMapping("/delete")
    @ResponseBody
    public void delete(Integer id) {
        zjyRoleService.delete(id);
    }

    /**
     * 修改
     *
     * @param zjyRoleParam
     */
    @PostMapping("/edit")
    @ResponseBody
    public void edit(@RequestBody ZjyRoleParam zjyRoleParam) {
        ZjyRole zjyRole=new ZjyRole();
        BeanUtils.copyProperties(zjyRoleParam, ZjyRole.class);
        zjyRoleService.edit(zjyRole);
    }

    /**
     * 查询列表
     *
     * @return
     */
    @PostMapping("/listAll")
    @ResponseBody
    public List<ZjyRole> listAll() {
        return zjyRoleService.listAll();
    }

    /**
    * 查询详情
    *
    * @param id 主键id
    * @return
    */
    @PostMapping("/findById")
    @ResponseBody
    public ZjyRole findById(Integer id) {
        return zjyRoleService.findById(id);
    }

}