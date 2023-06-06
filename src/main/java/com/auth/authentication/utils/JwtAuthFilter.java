package com.auth.authentication.utils;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";

    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String authHeader = request.getHeader(AUTH_HEADER);

        if (authHeader != null && authHeader.startsWith("Bearer")) {
            // extract token and add it to security context
            String token = authHeader.substring(7);

            // verify if it is from admin
            DecodedJWT decodedJWT = JsonWebToken.verifyToken(token, secretKey);

            List<String> claims = JsonWebToken.getClaims(decodedJWT);

            String username = claims.get(1);
            String roles = claims.get(claims.size() - 1);

            // add user details to security context holder
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority(roles))));
        }

        // apply filter
        filterChain.doFilter(request, response);
    }
}
