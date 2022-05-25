package com.lzr.dao;

import com.lzr.model.Menu;

import java.util.List;

public interface MenuDao {
    Integer add(Menu menu);
    List<Menu> getAll();
    Menu getById(Integer id);
    List<Menu> getByRoleId(Integer roleId);
    List<Menu> getAllFatherMenu();
}
