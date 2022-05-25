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

    @PostMapping("/menu")
    public ResponseMap add(@RequestBody Menu menu){
        return menuService.add(menu);
    }

    @GetMapping("/menu")
    public ResponseMap getAll(){
        return menuService.getAll();
    }

    @GetMapping("/menu/father")
    public ResponseMap getAllFatherMenu(){
        return menuService.getAllFatherMenu();
    }

    @GetMapping("/menu/{menuId}")
    public ResponseMap getById(@PathVariable("menuId") Integer id){
        return menuService.getById(id);
    }

}
