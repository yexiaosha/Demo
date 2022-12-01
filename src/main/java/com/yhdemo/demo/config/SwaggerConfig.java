package com.yhdemo.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger配置
 * @author wyh
 * @date 2022/12/01 17:52
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                .groupName("YH")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yhdemo.demo.controller"))
                .paths(PathSelectors.ant("/controller/**"))
                .build();
    }
}
