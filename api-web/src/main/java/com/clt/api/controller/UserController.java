package com.clt.api.controller;

import com.clt.api.annotation.Login;
import com.clt.api.annotation.LoginUser;
import com.clt.api.annotation.Sign;
import com.clt.api.entity.User;
import com.clt.api.param.UserCreateParam;
import com.clt.api.param.UserEditParam;
import com.clt.api.service.UserService;
import com.clt.api.utils.PageInfo;
import com.clt.api.utils.RestResult;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName : UserController
 * @Author : zhangquansong
 * @Date : 2019/1/5 0005 下午 3:28
 * @Description :用户控制层
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 新增
     *
     * @param userCreateParam
     */
    @PostMapping("/create")
    @ResponseBody
    @Sign
    public RestResult create(@Valid @RequestBody UserCreateParam userCreateParam) {
        User user = new User();
        BeanUtils.copyProperties(userCreateParam, user);
        userService.create(user);
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
        userService.delete(id);
        return RestResult.successResponse();
    }

    /**
     * 修改
     *
     * @param userEditParam
     */
    @PostMapping("/edit")
    @ResponseBody
    public RestResult edit(@Valid @RequestBody UserEditParam userEditParam) {
        User user = new User();
        BeanUtils.copyProperties(userEditParam, user);
        userService.edit(user);
        return RestResult.successResponse();
    }

    /**
     * 查询列表
     *
     * @return
     */
    @PostMapping("/listAll")
    @ResponseBody
    public RestResult<List<User>> listAll() {
        return RestResult.successResponse(userService.listAll());
    }

    /**
     * 查询详情
     *
     * @param id 主键id
     * @return
     */
    @PostMapping("/findById")
    @ResponseBody
    public RestResult<User> findById(@Param("id") Integer id) {
        return RestResult.successResponse(userService.findById(id));
    }

    @GetMapping("/simple/listPage")
    public String list() {
        System.out.println("listPage");
        return "123";
    }

    @GetMapping("/listPage")
    @ResponseBody
    public PageInfo<User> listPage() {
        Page<User> users = userService.findByPage(1, 20);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Login
    @Sign
    @PostMapping("/getUser")
    public User getUser(@LoginUser User user) {
        return user;
    }

}