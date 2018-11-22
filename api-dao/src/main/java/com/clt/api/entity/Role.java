package com.clt.api.entity;

public class Role {

    private int id;
    private String zjy_role_name;
    private String zjy_right_detail;
    private int zjy_group_id;
    private int zjy_company_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZjy_role_name() {
        return zjy_role_name;
    }

    public void setZjy_role_name(String zjy_role_name) {
        this.zjy_role_name = zjy_role_name;
    }

    public String getZjy_right_detail() {
        return zjy_right_detail;
    }

    public void setZjy_right_detail(String zjy_right_detail) {
        this.zjy_right_detail = zjy_right_detail;
    }

    public int getZjy_group_id() {
        return zjy_group_id;
    }

    public void setZjy_group_id(int zjy_group_id) {
        this.zjy_group_id = zjy_group_id;
    }

    public int getZjy_company_id() {
        return zjy_company_id;
    }

    public void setZjy_company_id(int zjy_company_id) {
        this.zjy_company_id = zjy_company_id;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", zjy_role_name='" + zjy_role_name + '\'' +
                ", zjy_right_detail='" + zjy_right_detail + '\'' +
                ", zjy_group_id=" + zjy_group_id +
                ", zjy_company_id=" + zjy_company_id +
                '}';
    }
}
