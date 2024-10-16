package com.javarnd.project.security.configg;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebSecurity
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securityContexts(securityContexts())
                .securitySchemes(Arrays.asList(apiKey()));
        
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Insurance Domain",
                "This is Insurance Domain Appliction",
                "1.0",
                "Terms of service",
                new Contact("Ravindra", "www.example.com", "abc@gmail.com"),
                "License of API", "www.url.com", Collections.emptyList());
    }
    
    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }
    
    private List<springfox.documentation.spi.service.contexts.SecurityContext> securityContexts(){
    	return Arrays.asList(springfox.documentation.spi.service.contexts.SecurityContext.builder().securityReferences(defaultAuth(  )).build());
    }
    
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
    }
}

