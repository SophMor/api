package med.voll.api.infra.security.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components(
                        ).addSecuritySchemes("bearer-key",
                        new SecurityScheme().
                                type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                )
                .info(new Info()
                        .title("VOLL.Med API")
                        .description("API Rest de aplicacion Voll.med" +
                                        "contiene listar medicos, pacientes, crear medicos pacientes y consultas"
                                )
                        .contact(new Contact()
                                .name("Sofia Morales - practica Allura")
                                .email("sofiamh568@gmail.com")
                                )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://voll.med/api/licencia")));

    }
}
