package com.api_cursos.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "API Cursos",
                description = "API REST para manejar curso y estudiantes",
                version = "1.0.0",
                contact = @Contact(
                        name = "Melbin243",
                        url = "https://github.com/Melbin243"
                )
        ),
        servers = {
                @Server(
                    description = "DEV SERVER",
                        url = "http://localhost:8080/api/v1"
                )
        }
)
public class SwaggerConfig {
}
