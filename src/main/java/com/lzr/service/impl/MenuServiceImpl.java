package com.lzr.service.impl;

import com.lzr.dao.MenuDao;
import com.lzr.model.Menu;
import com.lzr.service.MenuService;
import com.lzr.util.ResponseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    /**
     * 新增菜单权限业务
     * @param menu
     * @return
     */
    @Override
    public ResponseMap add(Menu menu) {
        Integer i = menuDao.add(menu);
        if(i > 0){
            return ResponseMap.SUCCESS;
        }
        return ResponseMap.ERROR;
    }

    /**
     * 获取所有菜单权限业务
     * @return
     */
    @Override
    public ResponseMap getAll() {
        List<Menu> list = menuDao.getAll();
        return new ResponseMap(list);
    }

    /**
     * 根据Id获取菜单权限业务
     * @param id
     * @return
     */
    @Override
    public ResponseMap getById(Integer id) {
        Menu menu = menuDao.getById(id);
        return new ResponseMap(menu);
    }

    /**
     * 获取所有一级菜单权限业务
     * @return
     */
    @Override
    public ResponseMap getAllFatherMenu() {
        List<Menu> list = menuDao.getAllFatherMenu();
        return new ResponseMap(list);
    }
}
