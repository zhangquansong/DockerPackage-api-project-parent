package com.clt.api.entity;

public class ZJYUser {

    private Long id;

   private String zjy_login_name;

   private  int zjy_company_id;

   private String zjy_depts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZjy_login_name() {
        return zjy_login_name;
    }

    public void setZjy_login_name(String zjy_login_name) {
        this.zjy_login_name = zjy_login_name;
    }

    public int getZjy_company_id() {
        return zjy_company_id;
    }

    public void setZjy_company_id(int zjy_company_id) {
        this.zjy_company_id = zjy_company_id;
    }

    public String getZjy_depts() {
        return zjy_depts;
    }

    public void setZjy_depts(String zjy_depts) {
        this.zjy_depts = zjy_depts;
    }
}
