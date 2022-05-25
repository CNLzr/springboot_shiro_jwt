package com.lzr.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    @Override
    public ResponseMap add(Menu menu) {
        Integer i = menuDao.add(menu);
        if(i > 0){
            return ResponseMap.SUCCESS;
        }
        return ResponseMap.ERROR;
    }

    @Override
    public ResponseMap getAll() {
        List<Menu> list = menuDao.getAll();
        return new ResponseMap(list);
    }

    @Override
    public ResponseMap getById(Integer id) {
        Menu menu = menuDao.getById(id);
        return new ResponseMap(menu);
    }

    @Override
    public ResponseMap getAllFatherMenu() {
        List<Menu> list = menuDao.getAllFatherMenu();
        return new ResponseMap(list);
    }
}
