package nezaki.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(security = {@SecurityRequirement(name = "bearer-key")})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
