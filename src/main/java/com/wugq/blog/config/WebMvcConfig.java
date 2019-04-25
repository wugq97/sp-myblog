package com.wugq.blog.config;

import com.wugq.blog.config.interceptor.AdminLoginHandlerInterceptor;
import com.wugq.blog.config.interceptor.LoginHandleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandleInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/index.html","/user/login");
        registry.addInterceptor(new AdminLoginHandlerInterceptor()).addPathPatterns("/admin/**");
    }

}
