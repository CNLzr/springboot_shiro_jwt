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
     * ChangeLog : 1. 创建 (22/05/20/0020 下午 5:09 [马宇航]);
     * @param username 存放在claim中，我们后续要解析出来
     * @param password 用来增加token 生成的复杂度
     * @return java.lang.String
     */
    public static String sign(String username,String password){
        //加密算法 增加解密难度 //签名算法，用来作为秘钥
        //                .signWith(SignatureAlgorithm.HS256,"woniuxy");
        Algorithm algorithm = Algorithm.HMAC256(password+salt);
        long now = System.currentTimeMillis();
        String token = JWT.create()
                .withClaim("username", username)//在jwt中封装一个用户账号名
                .withExpiresAt(new Date(now + EXPIRATION))//设置一个过期时间为30分钟的时间
                .sign(algorithm);//签名算法，比较复杂
        return token;
    }
    /**
     * 传入token返回封装的claim中的username
     * ChangeLog : 1. 创建 (22/05/20/0020 下午 5:20 [马宇航]);
     * @param token
     * @return java.lang.String
     */
    public static String getUserName(String token){
        DecodedJWT decode = JWT.decode(token);
        return decode.getClaim("username").asString();
    }

    public static void main(String[] args) {
        String token = JWTUtils.sign("admin", "123456");
        System.out.println(token);
        String userName = JWTUtils.getUserName(token);
        System.out.println(userName);
    }
    /**
     * token的校验工具类
     * ChangeLog : 1. 创建 (22/05/23/0023 下午 2:44 [马宇航]);
     * @param token
     * @param username
     * @param password
     * @return boolean
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
