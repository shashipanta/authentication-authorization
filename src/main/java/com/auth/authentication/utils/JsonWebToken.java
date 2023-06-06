package com.auth.authentication.utils;

import com.auth.authentication.entity.Account;
import com.auth.authentication.entity.Role;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;

import java.util.List;
import java.util.Set;

public class JsonWebToken {

    private final static Integer EXPIRATION_TIME = 50 * 60 * 60 * 1000;


    public static String generateAccessToken(Account account, String secretKey) {

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

//        Date issuedAt = new Date(System.currentTimeMillis());
//        Date expiresAt = new Date(issuedAt.getTime() + EXPIRATION_TIME);
        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plusMillis(EXPIRATION_TIME);

        Set<Role> rolesSet = account.getRoles();

        Role role  = rolesSet.stream()
                .findFirst()
                .get();




        String jwt = JWT.create()
                .withSubject(account.getUserName())
                .withClaim("userName", account.getUserName())
                .withClaim("roles", role.getName())
                .withClaim("iat", issuedAt)
                .withClaim("exp", expiresAt)
                .sign(algorithm);


        return jwt;
    }

    static DecodedJWT verifyToken(String token, String secretKey) {

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier jwtAdminVerifier = JWT.require(algorithm)
                .withClaim("roles", "ROLE_ADMIN")
                .build();

        JWTVerifier jwtExpirationVerifier = JWT.require(algorithm)
                .acceptNotBefore(System.currentTimeMillis())
                .build();


        DecodedJWT decodedJWT = jwtAdminVerifier.verify(token);

        String body = decodedJWT.getPayload();
        String header = decodedJWT.getHeader();

        return decodedJWT;
    }

    public static List<String> getClaims(DecodedJWT decodedJWT){
        String subject = decodedJWT.getSubject();
        String username = decodedJWT.getClaim("userName").asString();
        String roles = decodedJWT.getClaim("roles").asString();
        return List.of(subject,username,roles);
    }

    public static String getUserName(DecodedJWT jwtBody){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof UsernamePasswordAuthenticationToken authenticationToken){
            return String.valueOf(authenticationToken.getPrincipal());
        }
        return "No username";
    }


}
