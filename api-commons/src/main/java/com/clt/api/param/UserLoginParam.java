package com.clt.api.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author zhangquansong
 * @since 2019-01-03
 */
@Data
public class UserLoginParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "登录名称不能为空")
    private String userLoginName;
    private String userFace;
    @NotBlank(message = "密码不能为空")
    private String userPassword;

}
