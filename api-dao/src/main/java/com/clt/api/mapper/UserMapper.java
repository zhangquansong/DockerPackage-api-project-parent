package com.clt.api.mapper;

import com.clt.api.base.SuperMapper;
import com.clt.api.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhangquansong
 * @since 2019-01-03
 */
public interface UserMapper extends SuperMapper<User> {

    @Select("select * from user")
    Page<User> findByPage();
}