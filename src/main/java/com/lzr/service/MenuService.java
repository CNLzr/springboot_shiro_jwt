package com.lzr.service;

import com.lzr.model.Menu;
import com.lzr.util.ResponseMap;

public interface MenuService {
    ResponseMap add(Menu menu);

    ResponseMap getAll();

    ResponseMap getById(Integer id);

    ResponseMap getAllFatherMenu();
}
