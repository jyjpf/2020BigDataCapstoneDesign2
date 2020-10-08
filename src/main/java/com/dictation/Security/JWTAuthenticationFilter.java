package com.dictation.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private Logger logger = LogManager.getLogger(JWTAuthenticationFilter.class);

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Override 
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        logger.info("start authentication");
        String token = jwtTokenProvider.resolveToken(request);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            
            logger.info("validate token");

            // String url[] = request.getRequestURI().split("/");

            // for(int i = 0; i < url.length; i++) {
            //     if(url[i].equals())
            // }
            
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);

        } else {

            chain.doFilter(request, response);

        }

        logger.info("finish authentication");

    }
}