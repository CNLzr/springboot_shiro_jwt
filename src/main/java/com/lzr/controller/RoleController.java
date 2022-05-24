package com.lzr.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzr.model.RbacManager;
import com.lzr.model.Role;
import com.lzr.service.RbacManagerService;
import com.lzr.service.RoleService;
import com.lzr.util.ResponseMap;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/role/{currentPage}/{pageSize}")
    @RequiresRoles(value={"超级管理员","普通管理员"},logical = Logical.OR)
    public ResponseMap findAll(@PathVariable int currentPage, @PathVariable int pageSize){
        PageHelper.startPage(currentPage,pageSize);
        List<Role> all = roleService.getAll();
        //包含了分页信息的
        PageInfo<Role> rbacManagerPageInfo = new PageInfo<>(all);
        return new ResponseMap(rbacManagerPageInfo);
    }

    @PostMapping("/role")
    public ResponseMap add(@RequestBody Role role){
        Integer i = roleService.add(role);
        if(i>0) return ResponseMap.SUCCESS;
        return ResponseMap.ERROR;
    }

    @PutMapping("/role")
    public ResponseMap update(@RequestBody Role role){
        Integer i = roleService.update(role);
        if(i>0) return ResponseMap.SUCCESS;
        return ResponseMap.ERROR;
    }

    @DeleteMapping("/role/{id}")
//    @RequiresPermissions(value = {"role:del"})
    public ResponseMap delete(@PathVariable Integer id){
        Integer i = roleService.deleteById(id);
        if(i>0) return ResponseMap.SUCCESS;
        return ResponseMap.ERROR;
    }
}
