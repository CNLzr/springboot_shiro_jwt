package com.lzr.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzr.dao.RbacManagerDao;
import com.lzr.model.RbacManager;
import com.lzr.service.RbacManagerService;
import com.lzr.util.ResponseMap;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RbacManagerServiceImpl implements RbacManagerService {
    @Autowired
    private RbacManagerDao rbacManagerDao;

    /**
     * 查询所有用户业务
     * @return
     */
    @Override
    public ResponseMap getAll(Integer currentPage,Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<RbacManager> list = rbacManagerDao.getAll();
        PageInfo<RbacManager> rbacManagerPageInfo = new PageInfo<>(list);
        return new ResponseMap(rbacManagerPageInfo);
    }

    /**
     * 根据条件查询用户业务
     * @param rbacManager
     * @return
     */
    @Override
    public ResponseMap getByCondtion(RbacManager rbacManager) {
        List<RbacManager> list = rbacManagerDao.getByCondition(rbacManager);
        return new ResponseMap(list);
    }

    /**
     * 删除用户业务
     * @param id
     * @return
     */
    @Override
    public ResponseMap deleteById(Integer id) {
        Integer i = rbacManagerDao.deleteById(id);
        if(i > 0){
            return ResponseMap.SUCCESS;
        }
        return ResponseMap.ERROR;
    }

    /**
     * 新增用户业务
     * @param rbacManager
     * @return
     */
    @Override
    public ResponseMap add(RbacManager rbacManager) {
        RbacManager rbacManager1 = rbacManagerDao.getByName(rbacManager.getAccount());
        System.out.println(rbacManager1);
        // 如果该用户名查出有数据，则表示已存在相同用户名
        if(rbacManager1 != null) return ResponseMap.ERROR;
        String password = rbacManager.getPassword();
        // 对密码字符串进行加密操作
        SimpleHash simpleHash = new SimpleHash("MD5",password,rbacManager.getAccount());
        String newPassword = simpleHash.toBase64();
        rbacManager.setPassword(newPassword);
        Integer i = rbacManagerDao.add(rbacManager);
        if(i>0){
            return ResponseMap.SUCCESS;
        }
        return ResponseMap.ERROR;
    }

    /**
     * 修改用户业务
     * @param rbacManager
     * @return
     */
    @Override
    public ResponseMap update(RbacManager rbacManager) {
        // 对密码字符串进行加密操作
        String password = rbacManager.getPassword();
        SimpleHash simpleHash = new SimpleHash("MD5",password,rbacManager.getAccount());
        String newPassword = simpleHash.toBase64();
        rbacManager.setPassword(newPassword);
        Integer i = rbacManagerDao.update(rbacManager);
        if(i > 0){
            return ResponseMap.SUCCESS;
        }
        return ResponseMap.ERROR;
    }

    /**
     * 重置密码业务，默认重置为 123456
     * @param rbacManager
     * @return
     */
    @Override
    public ResponseMap resetPassword(RbacManager rbacManager) {
        SimpleHash simpleHash = new SimpleHash("MD5","123456",rbacManager.getAccount());
        String newPassword = simpleHash.toBase64();
        rbacManager.setPassword(newPassword);
        Integer i = rbacManagerDao.update(rbacManager);
        if(i > 0){
            return ResponseMap.SUCCESS;
        }
        return ResponseMap.ERROR;
    }

    /**
     * 修改用户状态业务
     * @param rbacManager
     * @return
     */
    @Override
    public ResponseMap setStatus(RbacManager rbacManager) {
        rbacManager.setPassword(null);
        rbacManager.setAccount(null);
        Integer i = rbacManagerDao.update(rbacManager);
        if(i > 0){
            return ResponseMap.SUCCESS;
        }
        return ResponseMap.ERROR;
    }
}
