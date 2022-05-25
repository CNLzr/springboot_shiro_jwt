package com.lzr.service;

import com.lzr.model.Menu;
import com.lzr.model.RbacManager;
import com.lzr.model.Role;
import com.lzr.model.RoleMenus;

import java.util.List;

public interface RoleService {
    List<Role> getAll();
    Integer deleteById(Integer id);
    Integer add(Role role);
    Integer update(Role role);

    RoleMenus getRoleMenus(Integer roleId);
}
