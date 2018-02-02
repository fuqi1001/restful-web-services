package com.jim.rest.webservices.restfulwebservices.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/*
而项目中添加了@EnableWebMvc注解，
加了这个人注解之后会自动覆盖了官方给出的/static,
/public, META-INF/resources, /resources等存放静态资源的目录。
而将静态资源定位于src/main/webapp。

所以访问不到swagger-ui
 */
//@EnableWebMvc
//@Configuration
//@ComponentScan
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void configureContentNegotiation (ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }
}
