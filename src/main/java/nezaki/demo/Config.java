package nezaki.demo;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {

  @Bean
  public FilterRegistrationBean filter() {
    @SuppressWarnings("unchecked")
    FilterRegistrationBean bean = new FilterRegistrationBean(new RequestFilter());
    bean.addUrlPatterns("/*");
    bean.setOrder(1);
    return bean;
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public Intercepter intercepter() {
    return new Intercepter();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(intercepter());
  }

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Spring Boot Example API")
                .description("SpringBoot Example API")
                .version("v0"))
        .components(
            new Components()
                .addSecuritySchemes(
                    "bearer-key",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")));
  }
}
