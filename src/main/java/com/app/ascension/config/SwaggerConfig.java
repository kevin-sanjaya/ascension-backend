package com.app.ascension.config;

import org.springframework.context.annotation.Configuration;
import org.joda.time.LocalDate;
import org.springframework.context.annotation.*;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.collect.Lists.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig{

    ApiInfo apiInfo() {
      return new ApiInfoBuilder()
        .title("API Reference")
        .version("1.0.0")
        .build();
    }

    @Bean
    public Docket customImplementation(){
		return new Docket(DocumentationType.SWAGGER_2)
      .apiInfo(apiInfo())
      .securitySchemes(newArrayList(apiKey()))
      .select().paths(PathSelectors.any())
          .apis(RequestHandlerSelectors.basePackage("com.app.ascension.api"))
          .build()
      .pathMapping("/")
      .useDefaultResponseMessages(false)
      .directModelSubstitute(LocalDate.class, String.class)
      .genericModelSubstitutes(ResponseEntity.class)
      ;
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "", "header");
    }

    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration("emailSecurity_client", "secret", "Spring", "emailSecurity", "", ApiKeyVehicle.HEADER, "", ",");
    }
}
