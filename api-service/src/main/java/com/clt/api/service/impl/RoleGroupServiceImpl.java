package com.clt.api.service.impl;

import com.clt.api.dao.RoleGroupDao;
import com.clt.api.entity.RoleGroup;
import com.clt.api.service.RoleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleGroupServiceImpl implements RoleGroupService {

    @Autowired
    private RoleGroupDao roleGroupDao;

    @Override
    public List<RoleGroup> getRoleAndRoleGroup() {
        return roleGroupDao.getRoleAndRoleGroup();
    }


}
