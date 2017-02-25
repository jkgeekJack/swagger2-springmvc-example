package com.jk.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Swagger描述
 * 下面两个注解可以让其被spring扫描到
 */
@Configuration
@EnableSwagger2
public class ApplicationSwaggerConfig {

    @Bean
    public Docket configSpringfoxDocketForAll() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json", "application/xml"))
                .consumes(Sets.newHashSet("application/json", "application/xml"))
                .protocols(Sets.newHashSet("http"/*, "https"*/))
                .forCodeGeneration(true)
                .select().paths(regex(".*"))
                .build()
                .apiInfo(apiInfo());
    }

    //页面头部api信息配置
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "SpringMVC REST API文档",
                "使用Swagger UI构建SpringMVC REST风格的可视化文档",
                "1.0.0",
                "http://localhost:8080/v2/api-docs",
                "jkgeekjack@163.com",
                "Apache License",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );
        return apiInfo;
    }

}
