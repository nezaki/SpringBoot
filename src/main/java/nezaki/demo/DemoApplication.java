package nezaki.demo;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
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
  public OpenAPI customOpenApi(@Value("3.0.3") String appVersion) {
    return new OpenAPI()
        .info(new Info()
            .title("Demo API title")
            .version(appVersion)
            .description("Demo API Description")
        );
  }

}
