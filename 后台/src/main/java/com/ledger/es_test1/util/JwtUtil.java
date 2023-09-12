package com.ledger.es_test1.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    /**
     * 创建一个 JSON Web Token (JWT)。
     *
     * @param claims        包含要添加到JWT中的声明（claims）的映射。
     *                      这些声明可以包括自定义的键值对，以提供额外的令牌信息。
     * @param secret        用于签名JWT的密钥，确保令牌的完整性和真实性。
     * @param subject       用于标识JWT主题的字符串，通常是用户的唯一标识符。
     * @param expireMinutes 令牌的过期时间（以分钟为单位）。
     *                      该值用于计算JWT的过期日期，令牌将在过期后不再有效。
     * @return 生成的JWT字符串。
     */
    public String createJwt(Map<String, Object> claims, String secret, String subject, int expireMinutes) {

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setSubject(subject) //用户的唯一标识符
                .setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate()) //设置过期时间
                .signWith(SignatureAlgorithm.HS512, secret); // 加密签名

        return builder.compact();
    }

    public boolean validateJwt(String jwtToken, String secret) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwtToken)
                    .getBody();
            // 在此处添加任何其他的验证逻辑，例如过期时间等
            Date expiration = claims.getExpiration();
            Date now = new Date();
            // JWT已过期
            return expiration == null || !expiration.before(now);
        } catch (Exception e) {
            return false;
        }
    }




}
