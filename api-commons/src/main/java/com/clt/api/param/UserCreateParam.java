package com.clt.api.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zhangquansong
 * @since 2019-01-03
 */
@Data
public class UserCreateParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "登录名称不能为空")
    private String userLoginName;
    private String userName;
    private String userPhone;
    private Integer userSex;
    private String userIdCard;
    private String userFace;
    private String userCode;
    @NotNull(message = "用户类型不能为空")
    private Integer userType;
    private String userPassword;
    private String userAddress;

}
