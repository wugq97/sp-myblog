package com.wugq.blog.config;

import com.wugq.blog.config.interceptor.AdminLoginHandlerInterceptor;
import com.wugq.blog.config.interceptor.LoginHandleInterceptor;
import com.wugq.blog.config.websocket.WebSocketServer;
import com.wugq.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//    @Bean
//    public AdminLoginHandlerInterceptor adminHandlerInterceptor(){
//        return new AdminLoginHandlerInterceptor();
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandleInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/user/**","/websocket","/download");
        registry.addInterceptor(new AdminLoginHandlerInterceptor()).addPathPatterns("/admin/**");
    }


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig());
        return new CorsFilter(source);
    }
    private CorsConfiguration corsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedOrigin("http://localhost:9528");
        corsConfiguration.addAllowedOrigin("http://localhost:8085");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //和页面有关的静态目录都放在项目的static目录下
        //registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //上传的图片在D盘下的OTA目录下，访问路径如：http://localhost:8081/OTA/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
        //其中OTA表示访问的前缀。"file:D:/OTA/"是文件真实的存储路径
        registry.addResourceHandler("/image/**").addResourceLocations("file:C:/Users/wgq/Desktop/img/");
    }

    //websocket
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    //向websocket实例中注入Bean
    @Autowired
    public void setMessageService(ArticleService articleService) {
        WebSocketServer.articleService = articleService;
    }
}
