// package com.dictation.config;

// import com.dictation.Security.JWTAuthenticationFilter;
// import com.dictation.Security.JWTTokenProvider;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import lombok.RequiredArgsConstructor;

// @Configuration
// @EnableWebSecurity 
// @RequiredArgsConstructor
// public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//   private final JWTTokenProvider jwtTokenProvider;

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
        
//       http
//       .httpBasic().disable() 
//       .csrf().disable()
//       .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
//       .and()
//       .authorizeRequests()
//       .antMatchers("/api/user/**").hasRole("ADMIN")
//       .antMatchers("/api/manager/**").hasRole("USER")
//       .anyRequest().permitAll()
//       .and()
//       .addFilterBefore(new JWTAuthenticationFilter(jwtTokenProvider),
//               UsernamePasswordAuthenticationFilter.class);        
//     }

//     // @Override
//     // public void configure(AuthenticationManagerBuilder auth) throws Exception {
//     //     auth.userDetailsService(userDetailsService);
//     // }

//     @Bean
//     @Override
//     public AuthenticationManager authenticationManagerBean() throws Exception {
//         return super.authenticationManagerBean();
//     }

// }