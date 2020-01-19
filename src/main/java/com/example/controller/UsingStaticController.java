//package com.example.controller;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
//
///**
// * @Author: gxy
// * @CreateDate: 2020/1/9 17:10
// * @Description:
// */
//@Configuration
//@EnableWebMvc
//public class UsingStaticController extends WebMvcConfigurerAdapter {
//
//    @Bean
//    public FreeMarkerViewResolver freeMarkerViewResolver() {
//
//        System.out.println("MvcConfig.freeMarkerViewResolver()");
//
//        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
//
//        resolver.setPrefix("");
//
//        resolver.setSuffix(".ftl");
//
//        resolver.setContentType("text/html; charset=UTF-8");
//
//        resolver.setRequestContextAttribute("request");
//
//        return resolver;
//
//    }
//}