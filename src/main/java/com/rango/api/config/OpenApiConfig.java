package com.rango.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Rango API",
                version = "v1",
                description = "API para gerenciamento de itens, receitas e itens de receita.",
                contact = @Contact(name = "Rango API")
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local")
        }
)
public class OpenApiConfig {
}
