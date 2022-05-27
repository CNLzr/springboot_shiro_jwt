package com.lzr.controller;

import com.lzr.model.Menu;
import com.lzr.service.MenuService;
import com.lzr.util.ResponseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MenuController {
    @Autowired
    MenuService menuService;

    /**
     * 新增菜单权限请求
     * @param menu
     * @return
     */
    @PostMapping("/menu")
    public ResponseMap add(@RequestBody Menu menu){
        return menuService.add(menu);
    }

    /**
     * 获取所有菜单权限信息请求
     * @return
     */
    @GetMapping("/menu")
    public ResponseMap getAll(){
        return menuService.getAll();
    }

    /**
     * 获取一级权限信息请求
     * @return
     */
    @GetMapping("/menu/father")
    public ResponseMap getAllFatherMenu(){
        return menuService.getAllFatherMenu();
    }

    /**
     * 根据Id获取菜单权限信息
     * @param id
     * @return
     */
    @GetMapping("/menu/{menuId}")
    public ResponseMap getById(@PathVariable("menuId") Integer id){
        return menuService.getById(id);
    }

}
