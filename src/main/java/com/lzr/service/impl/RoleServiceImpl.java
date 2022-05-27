package com.lzr.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzr.dao.RoleDao;
import com.lzr.model.Role;
import com.lzr.model.RoleMenus;
import com.lzr.service.RoleService;
import com.lzr.util.ResponseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * 查询所有角色业务
     * @return
     */
    @Override
    public ResponseMap getAll(Integer currentPage,Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Role> list = roleDao.getAll();
        PageInfo<Role> rbacManagerPageInfo = new PageInfo<>(list);
        return new ResponseMap(rbacManagerPageInfo);
    }

    /**
     * 删除角色业务
     * @param id
     * @return
     */
    @Override
    public ResponseMap deleteById(Integer id) {
        Integer i = roleDao.deleteById(id);
        if(i > 0){
            return ResponseMap.SUCCESS;
        }
        return ResponseMap.ERROR;
    }

    /**
     * 新增角色业务
     * @param role
     * @return
     */
    @Override
    public ResponseMap add(Role role) {
        Integer i = roleDao.add(role);
        if(i > 0){
            return ResponseMap.SUCCESS;
        }
        return ResponseMap.ERROR;
    }

    @Override
    public ResponseMap update(Role role) {
        Integer i = roleDao.update(role);
        if(i > 0){
            return ResponseMap.SUCCESS;
        }
        return ResponseMap.ERROR;
    }

    @Override
    public ResponseMap getRoleMenus(Integer roleId) {
        RoleMenus roleMenus = roleDao.getRoleMenus(roleId);
        return new ResponseMap(roleMenus);
    }
}
