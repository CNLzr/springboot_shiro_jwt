package com.lzr.service.impl;

import com.lzr.dao.RbacManagerDao;
import com.lzr.model.RbacManager;
import com.lzr.service.RbacManagerService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RbacManagerServiceImpl implements RbacManagerService {
    @Autowired
    private RbacManagerDao rbacManagerDao;

    @Override
    public List<RbacManager> getAll() {
        return rbacManagerDao.getAll();
    }

    @Override
    public List<RbacManager> getByCondtion(RbacManager rbacManager) {
        return rbacManagerDao.getByCondition(rbacManager);
    }

    @Override
    public Integer deleteById(Integer id) {
        return rbacManagerDao.deleteById(id);
    }

    @Override
    public Integer add(RbacManager rbacManager) {
        RbacManager rbacManager1 = rbacManagerDao.getByName(rbacManager.getAccount());
        System.out.println(rbacManager1);
        if(rbacManager1 != null) return 0;
        String password = rbacManager.getPassword();
        SimpleHash simpleHash = new SimpleHash("MD5",password,rbacManager.getAccount());
        String newPassword = simpleHash.toBase64();
        rbacManager.setPassword(newPassword);
        return rbacManagerDao.add(rbacManager);
    }

    @Override
    public Integer update(RbacManager rbacManager) {
        String password = rbacManager.getPassword();
        SimpleHash simpleHash = new SimpleHash("MD5",password,rbacManager.getAccount());
        String newPassword = simpleHash.toBase64();
        rbacManager.setPassword(newPassword);
        return rbacManagerDao.update(rbacManager);
    }
}
