package com.lzr.service.impl;

import com.lzr.dao.RoleDao;
import com.lzr.model.Role;
import com.lzr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> getAll() {
        return roleDao.getAll();
    }

    @Override
    public Integer deleteById(Integer id) {
        return roleDao.deleteById(id);
    }

    @Override
    public Integer add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public Integer update(Role role) {
        return roleDao.update(role);
    }
}
