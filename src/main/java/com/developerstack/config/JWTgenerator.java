package com.developerstack.config;

import io.jsonwebtoken.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JWTgenerator {

    public String createJWT(String issuer, long ttlMillis, String signingKey) throws UnsupportedEncodingException {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject("auth")
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey.getBytes("UTF-8"));

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    public Claims parseJWT(String jwt, String signingKey) {

        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(signingKey.getBytes("UTF-8"))
                    .parseClaimsJws(jwt).getBody();


        } catch (SignatureException e) {
            return null;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        return claims;

    }

    public boolean checkExpire(Claims claims) {
        long nowMillis = System.currentTimeMillis();
        if (nowMillis > claims.getExpiration().getTime()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {
        JWTgenerator jwt = new JWTgenerator();
        String token = jwt.createJWT("kumar", 1000, "123");
        Claims claim = jwt.parseJWT(token, "123");
        System.out.println(token);
        System.out.println(claim.getExpiration().getTime());
        TimeUnit.SECONDS.sleep(20);
        if (jwt.checkExpire(claim)) {
            System.out.println("Not expired");
        }
    }


}
