package com.clt.api.entity;

import java.util.List;

public class RoleGroup {

    private int id;
    private String zjy_role_group_name;
    private List<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZjy_role_group_name() {
        return zjy_role_group_name;
    }

    public void setZjy_role_group_name(String zjy_role_group_name) {
        this.zjy_role_group_name = zjy_role_group_name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "RoleGroup{" +
                "id=" + id +
                ", zjy_role_group_name='" + zjy_role_group_name + '\'' +
                ", roles=" + roles +
                '}';
    }
}
