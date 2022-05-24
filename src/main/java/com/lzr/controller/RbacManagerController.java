package com.lzr.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzr.model.RbacManager;
import com.lzr.service.RbacManagerService;
import com.lzr.util.ResponseMap;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RbacManagerController {
    @Autowired
    private RbacManagerService rbacManagerService;

    @GetMapping("/manager/{currentPage}/{pageSize}")
    //超级管理员或者普通管理员可以放心
    @RequiresRoles(value={"超级管理员","普通管理员"},logical = Logical.OR)
    public ResponseMap findAll(@PathVariable int currentPage, @PathVariable int pageSize){
        PageHelper.startPage(currentPage,pageSize);
        List<RbacManager> all = rbacManagerService.getAll();
        //包含了分页信息的
        PageInfo<RbacManager> rbacManagerPageInfo = new PageInfo<>(all);
        return new ResponseMap(rbacManagerPageInfo);
    }

    @PostMapping("/manager")
    public ResponseMap add(@RequestBody RbacManager rbacManager){
        Integer i = rbacManagerService.add(rbacManager);
        if(i>0) return ResponseMap.SUCCESS;
        return ResponseMap.ERROR;
    }

    @PutMapping("/manager")
    public ResponseMap update(@RequestBody RbacManager rbacManager){
        System.out.println(rbacManager);
        Integer i = rbacManagerService.update(rbacManager);
        if(i>0) return ResponseMap.SUCCESS;
        return ResponseMap.ERROR;
    }

    @DeleteMapping("/manager/{id}")
//    @RequiresPermissions(value = {"role:del"})
    public ResponseMap delete(@PathVariable Integer id){
        Integer i = rbacManagerService.deleteById(id);
        if(i>0) return ResponseMap.SUCCESS;
        return ResponseMap.ERROR;
    }
}
