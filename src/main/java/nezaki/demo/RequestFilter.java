package nezaki.demo;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RequestFilter implements Filter {

  private static final Log log = LogFactory.getLog(RequestFilter.class);

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    log.debug("RequestFilter started.");
    chain.doFilter(request, response);
    log.debug("RequestFilter ended.");
  }
}
