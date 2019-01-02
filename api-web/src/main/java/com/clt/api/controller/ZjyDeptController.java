package com.clt.api.controller;

import com.clt.api.service.ZjyDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.clt.api.param.ZjyDeptParam;
import com.clt.api.utils.RestResult;
import java.util.List;
import com.clt.api.entity.ZjyDept;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author zhangquansong
 * @since 2019-01-02
 */
@RestController
@RequestMapping("/zjyDept")
public class ZjyDeptController  {

    @Autowired
    private  ZjyDeptService zjyDeptService;

    /**
     * 新增
     *
     * @param zjyDeptParam
     */
    @PostMapping("/create")
    @ResponseBody
    public RestResult create(@RequestBody ZjyDeptParam zjyDeptParam) {
        ZjyDept zjyDept=new ZjyDept();
        BeanUtils.copyProperties(zjyDeptParam, ZjyDept.class);
        zjyDeptService.create(zjyDept);
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
        zjyDeptService.delete(id);
        return RestResult.successResponse();
    }

    /**
     * 修改
     *
     * @param zjyDeptParam
     */
    @PostMapping("/edit")
    @ResponseBody
    public RestResult edit(@RequestBody ZjyDeptParam zjyDeptParam) {
        ZjyDept zjyDept=new ZjyDept();
        BeanUtils.copyProperties(zjyDeptParam, ZjyDept.class);
        zjyDeptService.edit(zjyDept);
        return RestResult.successResponse();
    }

    /**
     * 查询列表
     *
     * @return
     */
    @PostMapping("/listAll")
    @ResponseBody
    public RestResult<List<ZjyDept>> listAll() {
       return RestResult.successResponse(zjyDeptService.listAll());
    }

    /**
    * 查询详情
    *
    * @param id 主键id
    * @return
    */
    @PostMapping("/findById")
    @ResponseBody
    public RestResult<ZjyDept> findById(Integer id) {
        return RestResult.successResponse(zjyDeptService.findById(id));
    }

}