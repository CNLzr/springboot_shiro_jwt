package com.lzr.service;

import com.lzr.model.RbacManager;

import java.util.List;

public interface RbacManagerService {
    List<RbacManager> getAll();
    List<RbacManager> getByCondtion(RbacManager rbacManager);
    Integer deleteById(Integer id);
    Integer add(RbacManager rbacManager);
    Integer update(RbacManager rbacManager);
}
