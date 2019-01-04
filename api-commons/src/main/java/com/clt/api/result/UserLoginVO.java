package com.clt.api.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录返回vo
 */
@Data
@JsonIgnoreProperties(value = {"id", "expireTime"})
public class UserLoginVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String userLoginName;
    private String userName;
    private String userPhone;
    private Integer userSex;
    private String userIdCard;
    private String userFace;
    private String userCode;
    private Integer userType;
    private String userAddress;
    private String token;
    private Date expireTime;

}
