package com.lzr.service;

import com.lzr.model.RbacManager;
import com.lzr.util.ResponseMap;

public interface RbacManagerService {
    ResponseMap getAll(Integer currentPage,Integer pageSize);
    ResponseMap getByCondtion(RbacManager rbacManager);
    ResponseMap deleteById(Integer id);
    ResponseMap add(RbacManager rbacManager);
    ResponseMap update(RbacManager rbacManager);
    ResponseMap resetPassword(RbacManager rbacManager);
    ResponseMap setStatus(RbacManager rbacManager);
}
