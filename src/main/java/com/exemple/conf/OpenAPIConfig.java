package com.exemple.conf;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info().title("Relacionamento API")
                .contact(new Contact().name("Alvaro Pires Santos").email("alvaropires.eng@hotmail.com").url("http://github.com/alvaropires"))
                .description("Spring Boot REST API for products and categories")
                .version("1.0.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
