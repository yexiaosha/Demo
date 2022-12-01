package com.yhdemo.demo.config;

import com.yhdemo.demo.handler.UserLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 * @author wyh
 * @date 2022/11/21 15:04
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {



    @Bean
    public HandlerInterceptor getUserLoginInterceptor(){
        return new UserLoginInterceptor();
    }

    /**
     * 拦截器配置
     * @param registry 注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(getUserLoginInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(
                "/login",
                "/register",
                "/swagger**/**",
                "/webjars/**",
                "/v3/**",
                "/doc.html");
    }
}
