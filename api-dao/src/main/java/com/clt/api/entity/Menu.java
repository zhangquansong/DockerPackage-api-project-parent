package com.clt.api.entity;

import java.util.Comparator;
import java.util.List;

/**
 * 菜单实体类
 */
public class Menu {

    private int id;//主键id
    private String zjy_menu_name;//菜单名称
    private int zjy_parent_id;//父菜单id
    private int zjy_menu_desc;//菜单排序
    private boolean check;//是否选中
    private List<Menu> menus;//子菜单列表

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZjy_menu_name() {
        return zjy_menu_name;
    }

    public void setZjy_menu_name(String zjy_menu_name) {
        this.zjy_menu_name = zjy_menu_name;
    }

    public int getZjy_parent_id() {
        return zjy_parent_id;
    }

    public void setZjy_parent_id(int zjy_parent_id) {
        this.zjy_parent_id = zjy_parent_id;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public int getZjy_menu_desc() {
        return zjy_menu_desc;
    }

    public void setZjy_menu_desc(int zjy_menu_desc) {
        this.zjy_menu_desc = zjy_menu_desc;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }


    /*
     * 菜单排序,根据desc排序
     */
    public Comparator<Menu> desc() {
        Comparator<Menu> comparator = new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                if (o1.getZjy_menu_desc() != o2.getZjy_menu_desc()) {
                    return o1.getZjy_menu_desc() - o2.getZjy_menu_desc();
                }
                return 0;
            }
        };
        return comparator;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", zjy_menu_name='" + zjy_menu_name + '\'' +
                ", zjy_parent_id=" + zjy_parent_id +
                ", zjy_menu_desc=" + zjy_menu_desc +
                ", check=" + check +
                ", menus=" + menus +
                '}';
    }
}
