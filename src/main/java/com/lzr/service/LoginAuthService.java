package com.lzr.service;

import com.lzr.model.RbacManager;
import com.lzr.util.ResponseMap;

import java.util.Set;

public interface LoginAuthService {
    ResponseMap login(String username, String password);
    /**
     * 通过用户名，查询出当前的用户信息
     * ChangeLog : 1. 创建 (22/05/23/0023 下午 4:09 [马宇航]);
     * @param userName
     * @return com.woniuxy.entity.RbacManager
     */
    RbacManager getByName(String userName);
    /**
     * 通过用户名查询出所有的角色
     * ChangeLog : 1. 创建 (22/05/23/0023 下午 4:10 [马宇航]);
     * @param username
     * @return java.util.Set<java.lang.String>
     */
    Set<String> getRolesByName(String username);
    /**
     * 通过用户名查询出所有的权限集合
     * ChangeLog : 1. 创建 (22/05/23/0023 下午 4:10 [马宇航]);
     * @param username
     * @return java.util.Set<java.lang.String>
     */
    Set<String> getPermsByName(String username);
}
