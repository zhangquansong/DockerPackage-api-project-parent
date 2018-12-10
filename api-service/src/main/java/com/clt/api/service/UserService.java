package com.clt.api.service;

import com.clt.api.entity.User;
import com.clt.api.utils.PageInfo;


public interface UserService {

    /**
     * 分页查询
     *
     * @param pageNo   页号
     * @param pageSize 每页显示记录数
     * @return
     */
    PageInfo<User> findByPage(int pageNo, int pageSize);

}
