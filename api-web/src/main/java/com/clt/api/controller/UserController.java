package com.clt.api.controller;

import com.clt.api.annotation.LoginUser;
import com.clt.api.entity.User;
import com.clt.api.service.impl.UserServiceImpl;
import com.clt.api.utils.PageInfo;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/simple/listPage")
    public String list() {
        System.out.println("listPage");
        return "123";
    }

    @GetMapping("/user/listPage")
    @ResponseBody
    public PageInfo<User> listPage() {
        Page<User> users = userService.findByPage(1, 20);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @PostMapping("/user/getUser")
    public User getUser(@LoginUser User user) {
        return user;
    }
}

