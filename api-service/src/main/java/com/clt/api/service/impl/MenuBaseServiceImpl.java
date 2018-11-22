package com.clt.api.service.impl;

import com.clt.api.dao.MenuDao;
import com.clt.api.entity.Menu;
import com.clt.api.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuBaseServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> getMenus() {
        List<Menu> menus = menuDao.getMenus();
        return menus;
    }
}
