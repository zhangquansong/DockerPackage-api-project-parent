package com.clt.api.utils;

import com.clt.api.entity.Menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 得到菜单树状信息
 */
public class GetMenuTreeUtil {

    public static List<Menu> getTree(List<Menu> menus, List<Menu> checkMenuList) {
        //根节点
        List<Menu> rootMenu = new ArrayList<Menu>();
        for (Menu nav : menus) {
            if (nav.getZjy_parent_id() == 0) {//父节点是0的，为根节点。
                for (Menu check : checkMenuList) {
                    if (nav.getId() == check.getId()) {
                        nav.setCheck(true);
                    }
                }
                rootMenu.add(nav);
            }
        }
        /* 根据Menu类的desc排序 */
        Collections.sort(rootMenu, new Menu().desc());
        //为根菜单设置子菜单，getClild是递归调用的
        for (Menu nav : rootMenu) {
            /* 获取根节点下的所有子节点 使用getChild方法*/
            List<Menu> childList = getChild(nav.getId(), menus, checkMenuList);
            nav.setMenus(childList);//给根节点设置子节点
        }
        return rootMenu;
    }

    /**
     * 获取子节点
     *
     * @param id      父节点id
     * @param allMenu 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    public static List<Menu> getChild(int id, List<Menu> allMenu, List<Menu> checkMenuList) {
        //子菜单
        List<Menu> childList = new ArrayList<Menu>();
        for (Menu nav : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if (nav.getZjy_parent_id() == id) {
                for (Menu check : checkMenuList) {
                    if (nav.getId() == check.getId()) {
                        nav.setCheck(true);
                    }
                }
                childList.add(nav);
            }
        }
        //递归
        for (Menu nav : childList) {
            nav.setMenus(getChild(nav.getId(), allMenu, checkMenuList));
        }
        Collections.sort(childList, new Menu().desc());//排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0) {
            return new ArrayList<Menu>();
        }
        return childList;
    }
}
