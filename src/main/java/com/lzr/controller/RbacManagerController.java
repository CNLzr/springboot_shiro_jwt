package com.lzr.controller;

import com.lzr.model.RbacManager;
import com.lzr.service.RbacManagerService;
import com.lzr.util.ResponseMap;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class RbacManagerController {
    @Autowired
    private RbacManagerService rbacManagerService;

    /**
     * 查询所有用户请求
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/manager/{currentPage}/{pageSize}")
    @RequiresRoles(value={"超级管理员","普通管理员"},logical = Logical.OR)
    public ResponseMap findAll(@PathVariable Integer currentPage, @PathVariable Integer pageSize){
        return rbacManagerService.getAll(currentPage,pageSize);
    }

    /**
     * 新增用户请求
     * @param rbacManager
     * @return
     */
    @PostMapping("/manager")
    public ResponseMap add(@RequestBody RbacManager rbacManager){
        return rbacManagerService.add(rbacManager);
    }

    /**
     * 修改用户请求
     * @param rbacManager
     * @return
     */
    @PutMapping("/manager")
    public ResponseMap update(@RequestBody RbacManager rbacManager){
        return rbacManagerService.update(rbacManager);
    }

    /**
     * 删除用户请求
     * @param id
     * @return
     */
    @DeleteMapping("/manager/{id}")
//    @RequiresPermissions(value = {"role:del"})
    public ResponseMap delete(@PathVariable Integer id){
        return rbacManagerService.deleteById(id);
    }


    /**
     * 根据条件查询用户请求
     * @param rbacManager
     * @return
     */
    @PostMapping("/manager/search")
    public ResponseMap getByCondition(@RequestBody RbacManager rbacManager){
        return rbacManagerService.getByCondtion(rbacManager);
    }

    /**
     * 重置用户密码请求
     * @param rbacManager
     * @return
     */
    @PostMapping("/manager/reset")
    public ResponseMap resetPassword(@RequestBody RbacManager rbacManager){
        return rbacManagerService.resetPassword(rbacManager);
    }

    /**
     * 修改用户状态请求
     * @param rbacManager
     * @return
     */
    @PostMapping("/manager/status")
    public ResponseMap setStatus(@RequestBody RbacManager rbacManager){
        return rbacManagerService.setStatus(rbacManager);
    }
}
