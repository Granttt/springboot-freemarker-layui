package com.example.domain.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @auther: 高希阳
 * @Date: 2019/3/5 11:40
 * @Description:Spring Boot自动配置本身不会自动把/swagger-ui.html这个路径
 * 映射到对应的目录META-INF/resources/下面。我们加上这个映射即可
 * https://www.jianshu.com/p/840320d431a1
 * 想要启用MVC Java config，只需要将@EnableWebMvc添加到你的一个@Configuration class即可
 */
@Configuration
//@EnableWebMvc //2020.1.14 注 @EnableWebMvc会使配置文件中的spring.mvc.view.*失效，无法访问到static下的静态资源，故先去掉该注解
public class WebMvcConfigurerConfig extends WebMvcConfigurerAdapter {

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");

        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}
