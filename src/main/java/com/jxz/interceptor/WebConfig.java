package com.jxz.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 拦截器在Bean实例化之前执行，注入的redisTemplate为空
    // 需要让拦截器执行的时候实例化拦截器Bean，
    @Bean
    public LoginInterceptor getLoginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // addPathPatterns("/admin/**)的意思是这个链接下的都要进入到SessionInterceptor里面去执行
        // excludePathPatterns("/admin")的意思是admin的url可以不用进入到SessionInterceptor中，直接放过执行。
        registry.addInterceptor(getLoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .maxAge(3600);
    }
}

