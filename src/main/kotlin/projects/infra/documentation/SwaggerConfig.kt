package projects.infra.documentation

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Deploy Board API")
                    .description("API para gerenciamento de projetos e deployments")
                    .version("1.0.0")
                    .contact(
                        Contact()
                            .name("Deploy Board Team")
                            .email("support@deployboard.com")
                    )
                    .license(
                        License()
                            .name("Apache 2.0")
                            .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                    )
            )
            .servers(
                listOf(
                    Server()
                        .url("http://localhost:8080")
                        .description("Servidor Local de Desenvolvimento")
                )
            )
            .components(
                Components()
                    .addSecuritySchemes(
                        "bearer-jwt",
                        SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                            .description("Token JWT para autenticação. Use o endpoint /auth/login para obter o token.")
                    )
            )
            .addSecurityItem(SecurityRequirement().addList("bearer-jwt"))
    }
}
