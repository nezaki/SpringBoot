package nezaki.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JWTFilter extends GenericFilterBean {

  private final Algorithm algorithm;

  public JWTFilter(String secretKey) {
    this.algorithm = Algorithm.HMAC512(secretKey);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    String token = resolveToken(request);
    if (token == null) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      JWTVerifier verifier = JWT.require(algorithm).build();
      DecodedJWT decodedJWT = verifier.verify(token);
      UsernamePasswordAuthenticationToken authentication =
          new UsernamePasswordAuthenticationToken(decodedJWT.getSubject(), null, null);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      filterChain.doFilter(request, response);
    } catch (JWTVerificationException e) {
      SecurityContextHolder.clearContext();
      ((HttpServletResponse) response)
          .sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }
  }

  private String resolveToken(ServletRequest request) {
    String token = ((HttpServletRequest) request).getHeader("Authorization");
    if (token == null || !token.startsWith("Bearer ")) {
      return null;
    }
    return token.substring(7);
  }
}
