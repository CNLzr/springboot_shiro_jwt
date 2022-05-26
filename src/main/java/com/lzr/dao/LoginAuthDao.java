package com.lzr.dao;
import com.lzr.model.RbacManager;
import org.apache.ibatis.annotations.Param;
import java.util.Set;
public interface LoginAuthDao {
    RbacManager login(@Param("account") String account,@Param("password") String password);
    RbacManager getByName(String username);
    Set<String> getRoles(String account);
    Set<String> getPermsByName(String account);
}
