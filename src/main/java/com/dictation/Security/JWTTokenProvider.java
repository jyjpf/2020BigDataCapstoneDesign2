package com.dictation.Security;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.dictation.vo.UserVO;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenProvider {
    
    private String secretKey;

    @PostConstruct
    protected void init() {
        this.secretKey = Base64.getEncoder().encodeToString(JWTProperties.SECRET.getBytes());
    }

    public String createToken(String userPk, String code, String role) {
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("role", role);
        claims.put("code", code);
        Date now = new Date();
        return JWTProperties.TOKEN_PREFIX +
            Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + JWTProperties.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, this.secretKey)
                .compact();
    } 

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
        Collection<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority((String) claims.get("role")));

        UserVO user = new UserVO();
        user.setUser_id(claims.getSubject());
        user.setSchool_cd((String) claims.get("code"));

        return new UsernamePasswordAuthenticationToken(user, "", roles);
    }

    public String resolveToken(HttpServletRequest request) {
        String X_AUTH_TOKEN = request.getHeader(JWTProperties.HEADER_STRING);

        if(X_AUTH_TOKEN != null)
            if(X_AUTH_TOKEN.startsWith(JWTProperties.TOKEN_PREFIX))
                return X_AUTH_TOKEN.substring(7);

        return null;
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}