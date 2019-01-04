package com.clt.api.controller;

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
 * 登录
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserExtendService userExtendService;

    /**
     * 用户登录
     *
     * @param userLoginParam
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public RestResult<UserLoginVO> login(@RequestBody @Valid UserLoginParam userLoginParam) {
        return userExtendService.userLogin(userLoginParam.getUserLoginName(), userLoginParam.getUserPassword());
    }
}