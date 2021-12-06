package nezaki.demo.security;

import java.util.Arrays;
import java.util.Collections;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Profile("local")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated();
    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.cors().configurationSource(buildCorsConfigurationSource());
    http.addFilter(buildJsonAuthenticationFilter());
    http.addFilterBefore(new JWTFilter("secret"), UsernamePasswordAuthenticationFilter.class);
  }

  private JsonAuthenticationFilter buildJsonAuthenticationFilter() throws Exception {
    JsonAuthenticationFilter filter = new JsonAuthenticationFilter();
    filter.setAuthenticationManager(authenticationManager());
    filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    return filter;
  }

  public CorsConfigurationSource buildCorsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(Collections.singletonList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));
    configuration.setAllowedOrigins(Collections.singletonList("*"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
