// package com.dictation.Security;

// import lombok.RequiredArgsConstructor;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.filter.GenericFilterBean;

// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.ServletRequest;
// import javax.servlet.ServletResponse;
// import javax.servlet.http.HttpServletRequest;
// import java.io.IOException;


// @RequiredArgsConstructor
// public class JWTAuthenticationFilter extends GenericFilterBean {

//     private Logger logger = LogManager.getLogger(JWTAuthenticationFilter.class);

//     private final JWTTokenProvider jwtTokenProvider;

//     @Override
//     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//         logger.info("start authentication");
//         String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
//         if (token != null && jwtTokenProvider.validateToken(token)) {
//             logger.info("validate token");
//             Authentication authentication = jwtTokenProvider.getAuthentication(token);
//             SecurityContextHolder.getContext().setAuthentication(authentication);
//         }
//         logger.info("finish authentication");
//         chain.doFilter(request, response);
//     }
// }