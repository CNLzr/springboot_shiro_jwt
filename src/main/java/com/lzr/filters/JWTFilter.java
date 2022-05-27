package com.lzr.filters;

import com.lzr.shiro.jwt.JsonWebToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends BasicHttpAuthenticationFilter {
    // 全局的跨域处理
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            System.out.println("发送option请求");
            return false;
        }
        return super.preHandle(request, response);
    }
    // isAccessAllowed 请求拦截判断是否允许访问，内部调用isLoginAttempt来判断请求头中是否有token
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //判断请求头中是否有token
        if (isLoginAttempt(request,response)) {
            //有token则执行登录
            try {
                executeLogin(request,response);
            } catch (Exception e) {
                //认证失败，认证失败返回401
                this.response401(request,response);
            }
        }
        // 没有token返回true放行
        return true;
    }

    private void response401(ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        try {
            httpServletResponse.sendRedirect("/401");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 尝试进行登录，就需要判断，请求头中是否有token
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //获取请求头中的token
        String token = httpServletRequest.getHeader("Authorization");
        return token!=null;
    }
    // 执行登录操作，等于 SecurityUtils.getSubject() subject.login
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        JsonWebToken jsonWebToken = new JsonWebToken(token);
        //会调用realm中的认证方法
        getSubject(request,response).login(jsonWebToken);
        //上一步认证失败，会直接抛出异常，不会进入return
        return true;
    }
}
