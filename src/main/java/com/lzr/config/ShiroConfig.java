package com.lzr.config;
import com.lzr.filters.JWTFilter;
import com.lzr.shiro.realms.MyRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig  {
    /**
     * 配置SecurityManager，然后注入我们的自定义域
     * @param myRealm
     * @return org.apache.shiro.web.mgt.DefaultWebSecurityManager
     */
    // @Bean 类似与之前的xml配置  id=方法名  class 是返回的 对象
    // <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
    //    后面的参数myRealm，相当于自动注入，怎么配置，则在代码块中自己写
    //     securityManager(MyRealm myRealm)  括号中的变量，必须是Spring容器中可以找到的bean对象
    // </bean>
    @Bean
    public DefaultWebSecurityManager securityManager(MyRealm myRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);
        return  defaultWebSecurityManager;
    }
    /**
     * 注册我们自定义jwtFilter进入到shiro的过滤器链中
     * @param securityManager
     * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //直接设置登录用的过滤器
        Map<String, String> loginFilter = new HashMap<>();
        // 常用的配置手段，过滤器链

        /**
         * key ：访问连接
         * 支持通配符的形式
         * value：过滤器类型
         * shiro常用过滤器类型
         * anon ：匿名访问（表明此链接所有人可以访问）
         * authc ：认证后访问（表明此链接需登录认证成功之后可以访问）
         */
        loginFilter.put("/login","anon");//匿名访问，不用登录
        loginFilter.put("/401","anon");//匿名访问，不用登录
        loginFilter.put("/welcome","anon");
        //FilterChain 过滤器链 底层有一堆过滤器 挨个进行过滤判断，在SpringSecurity也是一样的。
        // 所有权限框架底层都是基于过滤器链来进行拦截。
        shiroFilterFactoryBean.setFilterChainDefinitionMap(loginFilter);
        //声明我们自定义的 jwtfilter
        Map<String, Filter> jwtFilter = new HashMap<>();
        //注册的key表示过滤器类型的名称
        jwtFilter.put("JWTfilter",new JWTFilter());
        //把我们自定义的过滤器，放入shiro的过滤器链中，让它生效
        shiroFilterFactoryBean.setFilters(jwtFilter);
        //继续配置shiro的过滤器 让所有请求都要经过，我们jwt的过滤,value就是 过滤器类型
        loginFilter.put("/**","JWTfilter");
        //直接配置登录过滤，但是底层发生什么我们不知道
//        shiroFilterFactoryBean.setLoginUrl("login");
        return shiroFilterFactoryBean;
    }
}
