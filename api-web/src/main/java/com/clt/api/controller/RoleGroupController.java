package com.clt.api.controller;

import com.clt.api.entity.RoleGroup;
import com.clt.api.service.impl.RoleGroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleGroupController {

    @Autowired
    private RoleGroupServiceImpl roleGroupService;


    @GetMapping("/roleGroup/tree")
    @ResponseBody
    public List<RoleGroup> roleGroupTree() {
        List<RoleGroup> roleGroups = roleGroupService.getRoleAndRoleGroup();
        return roleGroups;
    }
}

