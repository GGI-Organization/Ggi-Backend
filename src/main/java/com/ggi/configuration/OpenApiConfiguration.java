package com.ggi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OpenApiConfiguration  implements WebMvcConfigurer {
    @Bean(name = "ggiOpenApi")
    public OpenAPI ggiOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("GGI Application API")
                        .description("GGI API implemented with Spring Boot RESTful service and documented using springdoc-openapi and OpenAPI 3.1"));
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*");
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:images\\");
    }

}
