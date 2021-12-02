package nezaki.demo;

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
}
