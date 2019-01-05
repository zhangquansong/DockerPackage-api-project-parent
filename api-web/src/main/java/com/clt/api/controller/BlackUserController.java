package com.clt.api.controller;

import com.clt.api.entity.BlackUser;
import com.clt.api.param.BlackUserCreateParam;
import com.clt.api.param.BlackUserEditParam;
import com.clt.api.service.BlackUserService;
import com.clt.api.utils.RestResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName : BlackUserController
 * @Author : zhangquansong
 * @Date : 2019/1/5 0005 下午 5:50
 * @Description :黑名单用户控制层
 **/
@RestController
@RequestMapping("/blackUser")
public class BlackUserController {

    @Autowired
    private BlackUserService blackUserService;

    /**
     * 新增
     *
     * @param blackUserCreateParam
     */
    @PostMapping("/create")
    @ResponseBody
    public RestResult create(@Valid @RequestBody BlackUserCreateParam blackUserCreateParam) {
        BlackUser blackUser = new BlackUser();
        BeanUtils.copyProperties(blackUserCreateParam, blackUser);
        blackUserService.create(blackUser);
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
        blackUserService.delete(id);
        return RestResult.successResponse();
    }

    /**
     * 修改
     *
     * @param blackUserEditParam
     */
    @PostMapping("/edit")
    @ResponseBody
    public RestResult edit(@Valid @RequestBody BlackUserEditParam blackUserEditParam) {
        BlackUser blackUser = new BlackUser();
        BeanUtils.copyProperties(blackUserEditParam, blackUser);
        blackUserService.edit(blackUser);
        return RestResult.successResponse();
    }

    /**
     * 查询列表
     *
     * @return
     */
    @PostMapping("/listAll")
    @ResponseBody
    public RestResult
            <List<BlackUser>> listAll() {
        return RestResult.successResponse(blackUserService.listAll());
    }

    /**
     * 查询详情
     *
     * @param id 主键id
     * @return
     */
    @PostMapping("/findById")
    @ResponseBody
    public RestResult<BlackUser> findById(@Param("id") Integer id) {
        return RestResult.successResponse(blackUserService.findById(id));
    }

}