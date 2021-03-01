package com.jxz.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // addPathPatterns("/admin/**)的意思是这个链接下的都要进入到SessionInterceptor里面去执行
        // excludePathPatterns("/admin")的意思是admin的url可以不用进入到SessionInterceptor中，直接放过执行。
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }
}

