package com.lzr.dao;

import com.lzr.model.RbacManager;

import java.util.List;

public interface RbacManagerDao {
    List<RbacManager> getAll();
    Integer deleteById(Integer id);
    Integer add(RbacManager rbacManager);
    Integer update(RbacManager rbacManager);
}
