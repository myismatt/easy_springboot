package com.easy.facade.framework.knife4j;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * knife4j接口文档
 * </p>
 *
 * @Author Matt
 * @Date 2022/6/14 14:33
 */
@Configuration
@EnableSwagger2
public class Knife4jConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.easy"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("")
                .license(null).licenseUrl(null)
                .termsOfServiceUrl(null)
                .contact(new Contact("Matt", "https://www.mattjia.cn", "myismatt@foxmail.com"))
                .version("1.0.0")
                .build();
    }
}
