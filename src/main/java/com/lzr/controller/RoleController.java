package com.lzr.controller;

import com.lzr.model.Role;
import com.lzr.service.RoleService;
import com.lzr.util.ResponseMap;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色请求
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/role/{currentPage}/{pageSize}")
    @RequiresRoles(value={"超级管理员","普通管理员"},logical = Logical.OR)
    public ResponseMap getAll(@PathVariable int currentPage, @PathVariable int pageSize){
        return roleService.getAll(currentPage,pageSize);
    }

    /**
     * 新增角色请求
     * @param role
     * @return
     */
    @PostMapping("/role")
    public ResponseMap add(@RequestBody Role role){
        return roleService.add(role);
    }

    /**
     * 修改角色请求
     * @param role
     * @return
     */
    @PutMapping("/role")
    public ResponseMap update(@RequestBody Role role){
        return roleService.update(role);
    }

    /**
     * 删除角色请求
     * @param id
     * @return
     */
    @DeleteMapping("/role/{id}")
//    @RequiresPermissions(value = {"role:del"})
    public ResponseMap delete(@PathVariable Integer id){
        return roleService.deleteById(id);
    }

    /**
     * 获取角色所有权限请求
     * @param roleId
     * @return
     */
    @GetMapping("/role/menus/{roleId}")
    public ResponseMap getRoleMenus(@PathVariable("roleId") Integer roleId){
        return roleService.getRoleMenus(roleId);
    }
}
