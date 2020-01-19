package com.example.domain.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @auther: 高希阳
 * @Date: 2019/3/5 10:28
 * @Description:springboot 2 整合swagger2
 * https://blog.csdn.net/ysk_xh_521/article/details/80633141
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                        .apiInfo(apiInfo())
                        .select()
                        //自行修改为自己的包路径
                        .apis(RequestHandlerSelectors.basePackage("com.example"))
                        .paths(PathSelectors.any())
                        .build();
        }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                    .title("swagger-api文档")
                    .description("swagger接入教程")
                    //服务条款网址
                    .termsOfServiceUrl("https://blog.csdn.net/ysk_xh_521")
                    .version("1.0")
                    .build();
    }
}
