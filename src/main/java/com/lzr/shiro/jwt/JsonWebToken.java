package com.lzr.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

public class JsonWebToken implements AuthenticationToken {
    private String token;

    public JsonWebToken(String token) {
        this.token = token;
    }
    /**
     * 校长
     *
     * @return java.lang.Object
     */
    @Override
    public Object getPrincipal() {
        return token;
    }
    /**
     * 凭证，它是用来做password认证校验，realm中认证方法中，需要存这个字段！
     *
     * @return java.lang.Object
     */
    @Override
    public Object getCredentials() {
        return token;
    }
}
