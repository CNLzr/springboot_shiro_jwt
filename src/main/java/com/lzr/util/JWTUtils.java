package com.lzr.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

public final class JWTUtils {
    //定义过期时间
    private static Long EXPIRATION = 60*60*1000L;
    private static String salt = "lzr";//盐，偏量值。增加复杂度
    /**
     * 用于使用动态参数，生成一个token字符串
     */
    public static String sign(String username,String password){
        //加密算法 增加解密难度 //签名算法，用来作为秘钥
        Algorithm algorithm = Algorithm.HMAC256(password+salt);
        long now = System.currentTimeMillis();
        // 建造者模式
        String token = JWT.create()
                .withClaim("username", username)//在jwt中封装一个用户账号名
                .withExpiresAt(new Date(now + EXPIRATION))//设置过期时间,现在时间加上自定义时间
                .sign(algorithm);//签名算法，比较复杂
        return token;
    }
    /**
     * 传入token返回封装的claim中的username
     */
    public static String getUserName(String token){
        System.out.println("token:"+token);
        DecodedJWT decode = JWT.decode(token);
        System.out.println("decodetoken:"+decode);
        System.out.println("decodetoken:"+decode.getClaim("username").asString());
        return decode.getClaim("username").asString();
    }

    /**
     * token的校验工具类
     */
    public static boolean verify(String token,String username,String password){
        Algorithm algorithm = Algorithm.HMAC256(password+salt);
        //使用 和sign类似的方式，重新进行加密，然后底层内部去比对 token是否还有效
        JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
        //这一步就是校验token是否还有效，如果无效则会直接抛出异常 就不可能返回true
        verifier.verify(token);
        return true;
    }
}
