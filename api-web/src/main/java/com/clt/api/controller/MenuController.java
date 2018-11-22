package com.clt.api.controller;

import com.clt.api.entity.Menu;
import com.clt.api.service.impl.MenuExtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {

    @Autowired
    private MenuExtServiceImpl menuService;


    @GetMapping("/menu/tree")
    @ResponseBody
    public List<Menu> roleGroupTree() {
        List<Menu> menus = menuService.getMenuTree();
        return menus;
    }
}

