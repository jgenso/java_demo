package com.challenge.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.Optional;

@Configuration
@EnableOpenApi
public class DemoConfiguration {

    /**
     * Group Demo contains operations related to sites, questions and question answers management
     */
    @Bean
    public Docket swaggerDemoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Site")
                .genericModelSubstitutes(Optional.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.challenge.demo.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Demo Challenge - REST APIs")
                .description("Demo Challenge App.").termsOfServiceUrl("")
                .contact(new Contact("Juan Jose Olivera", "", "juan@genso.com.bo"))
                .license("")
                .licenseUrl("")
                .version("")
                .build();
    }
}