package com.demo.Common.KeyHelper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.Common.UserContext;
import org.apache.commons.codec.binary.Hex;
import sun.misc.BASE64Encoder;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class KeyHelper {
    private static String SECRET = "#~13@#1233";

    public static String generateUUID() {
        UUID id = UUID.randomUUID();
        return id.toString();
    }


    //JWT加密

    public static String createJWT(UserContext context) {
        Date iatDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 999999);
        Date expiresDate = nowTime.getTime();
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create()
                .withClaim("UserId", context.getUserId())
                .withClaim("UserName", context.getUserName())
                .withClaim("DepartmentId", context.getDepartmentId())
                .withClaim("DepartmentName", context.getDepartmentName())
                .withExpiresAt(expiresDate)//过期时间
                .withIssuedAt(iatDate)//签发时间
                .sign(Algorithm.HMAC256(SECRET));
    }

    //解密
    public static Map<String, Claim> verifyJWT(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt;
        jwt = verifier.verify(token);
        return jwt.getClaims();
    }

    //Double MD5加密
    public static String EncoderMD5(String str) {
        //确定计算方法
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newString = null;
        try {
            newString = base64en.encode(md5.digest(str.getBytes("utf-8")));
            String salt = getSaltMD5(newString);//加盐
            newString = base64en.encode(md5.digest((newString+salt).getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newString;
    }

    private static String getSaltMD5(String password) {
        // 生成一个16位的随机数
        Random random = new Random();
        StringBuilder sBuilder = new StringBuilder(16);
        sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = sBuilder.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sBuilder.append("0");
            }
        }
        // 生成最终的加密盐
        String Salt = sBuilder.toString();
        password = md5Hex(password + Salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = Salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return String.valueOf(cs);

    }

    private static String md5Hex(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes());
            return new String(new Hex().encode(digest));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return "";
        }
    }
}
