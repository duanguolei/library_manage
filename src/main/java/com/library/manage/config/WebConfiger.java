package com.library.manage.config;


import com.library.manage.intercepeter.Loginintercerpetr;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiger implements WebMvcConfigurer {


//    注册拦截器
    @Bean
    public Loginintercerpetr loginintercerpetr(){
        return new Loginintercerpetr();
    };
//
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.loginintercerpetr()).addPathPatterns("/**");
    }


//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {

//        registry.addResourceHandler(
//                "/static/images/**"
//        ).addResourceLocations(
//                "file:D:\\javaproject\\manage\\src\\main\\resources\\static\\images"
//        );
//    }


}
