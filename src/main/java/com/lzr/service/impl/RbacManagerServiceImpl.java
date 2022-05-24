package com.lzr.service.impl;

import com.lzr.dao.RbacManagerDao;
import com.lzr.model.RbacManager;
import com.lzr.service.RbacManagerService;
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
    public Integer deleteById(Integer id) {
        return rbacManagerDao.deleteById(id);
    }

    @Override
    public Integer add(RbacManager rbacManager) {
        return rbacManagerDao.add(rbacManager);
    }

    @Override
    public Integer update(RbacManager rbacManager) {
        return rbacManagerDao.update(rbacManager);
    }
}
