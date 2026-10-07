package com.smartticket.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI smartTicketOpenApi() {
        return new OpenAPI().info(new Info()
                .title("SmartTicket API")
                .version("1.0.0")
                .description("API de venta de entradas y control de acceso")
                .contact(new Contact().name("Equipo SmartTicket")));
    }
}