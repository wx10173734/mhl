package com.lzc.mhl.service;

import com.lzc.mhl.dao.MenuDao;
import com.lzc.mhl.domain.Menu;

import java.util.List;

/**
 * @title: MenuService
 * @Author luozouchen
 * @Date: 2022/10/26 20:45
 * @Version 1.0
 */
public class MenuService {
    private MenuDao menuDao = new MenuDao();

    //显示所有菜品
    public List<Menu> list() {
        List<Menu> menuList = menuDao.queryMulti("select * from menu", Menu.class);
        return menuList;
    }

    //获取菜品单价
    public Menu getMenuById(int id) {
        Menu menu = menuDao.querySingle("select * from menu where id=?", Menu.class, id);
        return menu;
    }


}
