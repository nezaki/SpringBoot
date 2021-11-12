package nezaki.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ApiControllerAdvice {

  private static final Logger logger = LoggerFactory.getLogger(ApiControllerAdvice.class);

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
    logger.error("error", ex);
    return new ResponseEntity<Object>("", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<Object> handle(ResponseStatusException ex, WebRequest request) {
    return new ResponseEntity<Object>("", new HttpHeaders(), ex.getStatus());
  }
}
