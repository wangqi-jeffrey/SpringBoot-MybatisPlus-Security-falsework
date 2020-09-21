package com.jeffrey.utils;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * Description: jwt 工具类
 *
 * @author WQ
 * @date 2020/8/20 4:23 PM
 */
public class JwtTokenUtil {

    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;

    public static void setup(String aliasKey, char[] password, String keystorePath) {
        // 将证书文件里边的私钥公钥拿出来
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(keystorePath);
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS"); // java key store 固定常量
            keyStore.load(inputStream, password);
            privateKey = (PrivateKey) keyStore.getKey(aliasKey, password); // jwt 为 命令生成整数文件时的别名
            publicKey = keyStore.getCertificate(aliasKey).getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成token
     * <p>
     * iss: jwt签发者
     * sub: jwt所面向的用户
     * aud: 接收jwt的一方
     * exp: jwt的过期时间，这个过期时间必须要大于签发时间
     * nbf: 定义在什么时间之前，该jwt都是不可用的.
     * iat: jwt的签发时间
     * jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
     * </p>
     *
     * @param subject 主体信息
     * @param claims  自定义身份信息
     * @param exp     过期时间（秒）
     * @return token
     */
    public static String generateToken(String subject, Map<String, Object> claims, long exp) {
        LocalDateTime expDate = LocalDateTime.now().plusSeconds(exp);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(Date.from(expDate.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 解析token, 获得subject中的信息
     */
    public static String getSubject(String token) {
        String subject = "UNKNOWN";
        try {
            subject = getTokenBody(token).getSubject();
        } catch (ClaimJwtException e) {
            subject = e.getClaims().getSubject();
        } catch (Exception ignored) {
        }
        return subject;
    }

    /**
     * 获取token自定义属性
     */
    public static Map<String, Object> getClaims(String token) {
        return getTokenBody(token);
    }

    /**
     * 获取过期时间
     *
     * @param token 令牌
     * @return 时间
     * @throws ExpiredJwtException 令牌过期异常
     */
    public static long getExpirationDate(String token) throws ExpiredJwtException {
        Claims claims = getTokenBody(token);
        return claims.getExpiration().toInstant().getEpochSecond();
    }

    /**
     * 是否已过期
     */
    public static boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    public static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token)
                .getBody();
    }

}