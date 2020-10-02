package com.canemreayar.foreignexchange.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(getRestApis())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().title("Foreign Exchange Api")
                .description("Finance Application Foreign Exchange Api")
                .termsOfServiceUrl("N/A")
                .contact(getContact())
                .version(getClass().getPackage().getImplementationVersion())
                .build();


    }

    private Predicate<RequestHandler> getRestApis(){
        return RequestHandlerSelectors.withClassAnnotation(RestController.class);
    }

    //TODO Fix Url
    private Contact getContact(){
        return new Contact("Can Emre Ayar","github.com/justayar","canemreayar@gmail.com");
    }

}
