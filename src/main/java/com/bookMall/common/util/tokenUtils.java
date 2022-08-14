package com.bookMall.common.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class tokenUtils {
    //过期时间
    private static final long EXPIRE_TIME=15*60*1000;
    //基础密钥
    private static final String TOKEN_SECRET="jhh0-o8slhy5**^-npcs%^*0-wjmly8-anasy";
//生成token
    public static String sign(Long userId){
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            HashMap<String, Object> headers = new HashMap<>(2);
            headers.put("typ","JWT");
            headers.put("alg","hmac256");
            return JWT.create()
                    .withHeader(headers)
                    .withClaim("userId",userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //验证token
    public static boolean verify(String token){
        token=getRealToken(token);
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e)  {
            e.printStackTrace();
            return false;
        }
    }
    //解析token
    public static Long getId(String token){
        token=getRealToken(token);
        try {
            DecodedJWT decode = JWT.decode(token);
            return decode.getClaim("userId").asLong();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getRealToken(String token){
        return token.replace("Bearer ","");
    }
}
