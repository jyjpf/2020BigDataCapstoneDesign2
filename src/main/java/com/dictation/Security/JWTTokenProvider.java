// package com.dictation.Security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jws;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import lombok.RequiredArgsConstructor;

// import org.apache.logging.log4j.Logger;
// import org.apache.logging.log4j.LogManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.stereotype.Component;

// import javax.annotation.PostConstruct;
// import javax.servlet.http.HttpServletRequest;
// import java.util.Base64;
// import java.util.Date;
// import java.util.List;

// @RequiredArgsConstructor
// @Component
// public class JWTTokenProvider {

//     private Logger logger = LogManager.getLogger(JWTTokenProvider.class);

//     private final UserDetailsService userDetailsService;

//     private String secretKey;

//     @PostConstruct
//     protected void init() {
//         this.secretKey = Base64.getEncoder().encodeToString(JWTProperties.SECRET.getBytes());
//     }


//     public String createToken(String userPk, List<String> roles) {
//         logger.info("SECRET KEY : " + this.secretKey);
//         Claims claims = Jwts.claims().setSubject(userPk);
//         claims.put("roles", roles);
//         Date now = new Date();
//         return Jwts.builder().setClaims(claims).setIssuedAt(now)
//                 .setExpiration(new Date(now.getTime() + JWTProperties.EXPIRATION_TIME))
//                 .signWith(SignatureAlgorithm.HS256, this.secretKey).compact();
//     }

//     public Authentication getAuthentication(String token) {
//         UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
//         return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//     }

//     public String getUserPk(String token) {
//         return Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody().getSubject();
//     }

//     public String resolveToken(HttpServletRequest request) {
//         logger.info(request.getHeader("X-AUTH-TOKEN"));
//         return request.getHeader("X-AUTH-TOKEN");
//     }

//     public boolean validateToken(String jwtToken) {
//         try {
//             Jws<Claims> claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(jwtToken);
//             return !claims.getBody().getExpiration().before(new Date());
//         } catch (Exception e) {
//             return false;
//         }
//     }
// }