package github.ezgikardes.loanapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    public OpenAPI customOpenApi(){
        SecurityScheme securityScheme = new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .in(SecurityScheme.In.HEADER)
                .description("JWT Authorization header using the Bearer scheme. Example: \\\"Authorization: Bearer {token}\\\"");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Authorization", List.of("read", "write"));

        return new OpenAPI()
                .info(new Info().title("Loan API").version("1.0.").description("Loan Management API with JWT Security"))
                .addSecurityItem(securityRequirement)
                .components(new Components().addSecuritySchemes("Authorization", securityScheme));
    }
}
