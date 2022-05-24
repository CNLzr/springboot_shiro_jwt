package com.lzr.service.impl;

import com.lzr.dao.LoginAuthDao;
import com.lzr.model.RbacManager;
import com.lzr.service.LoginAuthService;
import com.lzr.util.JWTUtils;
import com.lzr.util.ResponseMap;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LoginAuthServiceImpl implements LoginAuthService {
    @Autowired
    private LoginAuthDao loginAuthDao;

    @Override
    public ResponseMap login(String username, String password) {
        SimpleHash simpleHash = new SimpleHash("MD5",password,username);
        String newPassword = simpleHash.toBase64();
        RbacManager rbacManager = loginAuthDao.login(username,newPassword);
        String account = rbacManager.getAccount();
        String password1 = rbacManager.getPassword();
        //调用jwt工具类生成token
        String token = JWTUtils.sign(account, password1);
        ResponseMap responseMap = new ResponseMap(rbacManager);
        responseMap.putKey("token",token);
        return responseMap;
    }

    @Override
    public RbacManager getByName(String userName) {
        RbacManager rbacManager = loginAuthDao.getByName(userName);
        return rbacManager;
    }

    @Override
    public Set<String> getRolesByName(String username) {
        return loginAuthDao.getRoles(username);
    }

    @Override
    public Set<String> getPermsByName(String username) {
        return loginAuthDao.getPermsByName(username);
    }
}
