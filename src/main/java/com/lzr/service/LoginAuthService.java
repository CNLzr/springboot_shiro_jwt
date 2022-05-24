package com.lzr.service;

import com.lzr.model.RbacManager;
import com.lzr.util.ResponseMap;

import java.util.Set;

public interface LoginAuthService {
    ResponseMap login(String username, String password);
    RbacManager getByName(String userName);
    Set<String> getRolesByName(String username);
    Set<String> getPermsByName(String username);
}
