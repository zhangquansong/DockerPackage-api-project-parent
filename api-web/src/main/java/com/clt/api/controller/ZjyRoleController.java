package com.clt.api.controller;

import com.clt.api.service.ZjyRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.clt.api.param.ZjyRoleParam;
import com.clt.api.utils.RestResult;
import java.util.List;
import com.clt.api.entity.ZjyRole;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author zhangquansong
 * @since 2019-01-02
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
    public RestResult create(@RequestBody ZjyRoleParam zjyRoleParam) {
        ZjyRole zjyRole=new ZjyRole();
        BeanUtils.copyProperties(zjyRoleParam, ZjyRole.class);
        zjyRoleService.create(zjyRole);
        return RestResult.successResponse();
    }

    /**
     * 删除
     *
     * @param id 主键id
     */
    @PostMapping("/delete")
    @ResponseBody
    public RestResult delete(Integer id) {
        zjyRoleService.delete(id);
        return RestResult.successResponse();
    }

    /**
     * 修改
     *
     * @param zjyRoleParam
     */
    @PostMapping("/edit")
    @ResponseBody
    public RestResult edit(@RequestBody ZjyRoleParam zjyRoleParam) {
        ZjyRole zjyRole=new ZjyRole();
        BeanUtils.copyProperties(zjyRoleParam, ZjyRole.class);
        zjyRoleService.edit(zjyRole);
        return RestResult.successResponse();
    }

    /**
     * 查询列表
     *
     * @return
     */
    @PostMapping("/listAll")
    @ResponseBody
    public RestResult<List<ZjyRole>> listAll() {
       return RestResult.successResponse(zjyRoleService.listAll());
    }

    /**
    * 查询详情
    *
    * @param id 主键id
    * @return
    */
    @PostMapping("/findById")
    @ResponseBody
    public RestResult<ZjyRole> findById(Integer id) {
        return RestResult.successResponse(zjyRoleService.findById(id));
    }

}