package cn.wangbowen.webchat.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 */
public class JwtUtils {
    /**
     * 7天过期
     */
    private static final long EXPPIRE_TIME = 7 * 24 * 60 * 60 * 1000;
    /**
     * token密钥
     */
    private static final String TOKEN_SECRET = "j465huisfdg5gsf58gfs86g568sfgfs58h";

    /**
     * 生成签名
     * @param username
     * @param uid
     * @return
     */
    public static String sign(String username, String uid) {

        try {
            // 过期时间
            Date date = new Date(System.currentTimeMillis() + EXPPIRE_TIME);
            // 加密
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            // 附加信息，生成签名
            return JWT.create()
                    .withHeader(header)
                    .withClaim("username", username)
                    .withClaim("uid", uid)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验token（解码没有异常就说明没有过期或者无效）
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取用户名信息，无需解密
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取user ID
     * @param token
     * @return
     */
    public static String getUserid(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("uid").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
