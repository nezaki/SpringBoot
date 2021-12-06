package nezaki.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) {
    try {
      Map<String, String> params =
          new ObjectMapper()
              .readValue(request.getInputStream(), new TypeReference<Map<String, String>>() {});
      UsernamePasswordAuthenticationToken authRequest =
          new UsernamePasswordAuthenticationToken(params.get("userId"), params.get("password"));
      return getAuthenticationManager().authenticate(authRequest);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth)
      throws IOException, ServletException {
    Date issuedAt = new Date();
    Date notBefore = new Date(issuedAt.getTime());
    Date expiresAt = new Date(issuedAt.getTime() + TimeUnit.MINUTES.toMillis(60L));

    String token =
        JWT.create()
            .withIssuedAt(issuedAt)
            .withNotBefore(notBefore)
            .withExpiresAt(expiresAt)
            .withSubject(auth.getName())
            .sign(Algorithm.HMAC512("secret"));
    res.addHeader("Authorization", "Bearer " + token);
  }
}
