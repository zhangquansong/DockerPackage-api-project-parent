package com.clt.api.controller;

import com.clt.api.annotation.SameUrlData;
import com.clt.api.param.UserLoginParam;
import com.clt.api.result.UserLoginVO;
import com.clt.api.service.UserExtendService;
import com.clt.api.service.UserService;
import com.clt.api.utils.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @ClassName : LoginController
 * @Author : zhangquansong
 * @Date : 2019/1/5 0005 下午 3:27
 * @Description :登录控制层
 **/
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserExtendService userExtendService;

    /**
     * @param userLoginParam
     * @return com.clt.api.utils.RestResult<com.clt.api.result.UserLoginVO>
     * @Author zhangquansong
     * @Date 2019/1/5 0005 下午 3:27
     * @Description : 用户登录
     **/
    @PostMapping("/login")
    @ResponseBody
    @SameUrlData
    public RestResult<UserLoginVO> login(@RequestBody @Valid UserLoginParam userLoginParam) {
        return userExtendService.userLogin(userLoginParam.getUserLoginName(), userLoginParam.getUserPassword());
    }
}