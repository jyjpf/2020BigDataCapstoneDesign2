package com.dictation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer {
  
  @Autowired
  InterceptorConfig interceptorConfig;

	// public void addCorsMappings(CorsRegistry registry) {

	// 	registry.addMapping("/**") 
	// 			.allowedMethods("GET", "POST", "HEAD", "PUT", "DELETE", "OPTIONS");
        
	// }
  
  @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(interceptorConfig);
   }
}
