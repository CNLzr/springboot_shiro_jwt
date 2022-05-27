package com.lzr.service;

import com.lzr.model.Role;
import com.lzr.util.ResponseMap;

public interface RoleService {
    ResponseMap getAll(Integer currentPage,Integer pageSize);
    ResponseMap deleteById(Integer id);
    ResponseMap add(Role role);
    ResponseMap update(Role role);
    ResponseMap getRoleMenus(Integer roleId);
}
