package nezaki.demo;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("3.0.3") String appVersion) {
		return new OpenAPI()
//				.components(
//						new Components().addSecuritySchemes("basicScheme",
//						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
				.info(new Info()
						.title("Demo API title")
						.version(appVersion)
						.description("Demo API Description")
//						.termsOfService("http://swagger.io/terms/")
//						.license(new License().name("Apache 2.0").url("http://springdoc.org"))
				);
	}

}
