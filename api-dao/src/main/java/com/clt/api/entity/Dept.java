package com.clt.api.entity;

import java.util.List;

public class Dept {

    private int id;
    private String zjy_dept_name;
    private int zjy_company_id;
    private int zjy_parent_id;
    List<ZJYUser> zjyUsers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZjy_dept_name() {
        return zjy_dept_name;
    }

    public void setZjy_dept_name(String zjy_dept_name) {
        this.zjy_dept_name = zjy_dept_name;
    }

    public int getZjy_company_id() {
        return zjy_company_id;
    }

    public void setZjy_company_id(int zjy_company_id) {
        this.zjy_company_id = zjy_company_id;
    }

    public int getZjy_parent_id() {
        return zjy_parent_id;
    }

    public void setZjy_parent_id(int zjy_parent_id) {
        this.zjy_parent_id = zjy_parent_id;
    }

    public List<ZJYUser> getZjyUsers() {
        return zjyUsers;
    }

    public void setZjyUsers(List<ZJYUser> zjyUsers) {
        this.zjyUsers = zjyUsers;
    }
}
