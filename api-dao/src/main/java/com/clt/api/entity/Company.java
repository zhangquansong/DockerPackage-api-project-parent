package com.clt.api.entity;

import java.util.List;

public class Company {

   private int id;
   private String zjy_company_name;
   private  String zjy_address;
   private List<Dept> depts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZjy_company_name() {
        return zjy_company_name;
    }

    public void setZjy_company_name(String zjy_company_name) {
        this.zjy_company_name = zjy_company_name;
    }

    public String getZjy_address() {
        return zjy_address;
    }

    public void setZjy_address(String zjy_address) {
        this.zjy_address = zjy_address;
    }

    public List<Dept> getDepts() {
        return depts;
    }

    public void setDepts(List<Dept> depts) {
        this.depts = depts;
    }
}
