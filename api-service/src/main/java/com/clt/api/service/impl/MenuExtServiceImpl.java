package com.clt.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.clt.api.entity.Menu;
import com.clt.api.utils.GetMenuTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuExtServiceImpl {

    @Autowired
    private MenuBaseServiceImpl menuBaseService;

    public List<Menu> getMenuTree() {
        List<Menu> menus = menuBaseService.getMenus();
        String a = "[{\"id\":1,\"menu_name\":\"菜单1\"},{\"id\":2,\"menu_name\":\"菜单2\"},{\"id\":5,\"zjy_menu_name\":\"菜单1-2-2\"}]";
        List<Menu> list = JSONObject.parseArray(a, Menu.class);
        menus = GetMenuTreeUtil.getTree(menus, list);
        return menus;
    }
}
