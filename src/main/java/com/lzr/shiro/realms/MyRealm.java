package com.lzr.shiro.realms;
import com.lzr.model.RbacManager;
import com.lzr.service.LoginAuthService;
import com.lzr.shiro.jwt.JsonWebToken;
import com.lzr.util.JWTUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import java.util.Set;
@Configuration
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private LoginAuthService loginAuthService;
    /**
     * 可选方案，基于复杂的token校验，可以选择的重写方法，因为自定义域不支持更多的令牌认证方案。
     * ChangeLog : 1. 创建 (22/05/23/0023 下午 12:11 [马宇航]);
     * @param token
     * @return boolean
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        //判断token是不是属于JsonWebToken的类型
        return token instanceof JsonWebToken;
    }
    /**
     * 自定义授权方法，通过用户名查出它的所有角色集合和权限集合，并封装到simpleAuthorizationInfo对象中
     * ChangeLog : 1. 创建 (22/05/23/0023 下午 4:07 [马宇航]);
     * @param principals 认证方法中，封装进入的 `校长`
     * @return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        String username = principals + "";
        //使用username查询出该用户的所有角色
        Set<String> roles = loginAuthService.getRolesByName(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        //使用username查询出该用户的所有权限
        Set<String> perms = loginAuthService.getPermsByName(username);
        simpleAuthorizationInfo.setStringPermissions(perms);
        //每次权限认证 都要查数据库，影响性能。
        return simpleAuthorizationInfo;
    }
    /**
     * 认证ok，使用自定义的jwt来进行认证操作
     * ChangeLog : 1. 创建 (22/05/23/0023 下午 3:11 [马宇航]);
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JsonWebToken token1 = (JsonWebToken) token;
        //jwt才是我们获取到的token
        String jwt = token1.getPrincipal()+"";
        //通过工具类，传入token即可返回我们的账号。可选方案，通过工具类，重新校验一下token！
        String userName = JWTUtils.getUserName(jwt);
        //通过userName判断数据库中有没有这个账号。
        RbacManager manager = loginAuthService.getByName(userName);
        if (manager == null){
            throw new AuthenticationException("账号密码错误！");
        }
        //为什么需要写JWT的校验？
        if (JWTUtils.verify(jwt,manager.getAccount(),manager.getPassword())){
            //token已经获取到了，下午要基于token写认证方法。 第一个参数，是封装给shiro的校长对象，jwt参数，用于底层的一个凭证校验
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName,jwt,"myRealm");
            return simpleAuthenticationInfo;
        }else {
            return new SimpleAuthenticationInfo();
        }
    }
}
