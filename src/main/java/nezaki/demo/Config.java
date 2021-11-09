package nezaki.demo;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

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
}
