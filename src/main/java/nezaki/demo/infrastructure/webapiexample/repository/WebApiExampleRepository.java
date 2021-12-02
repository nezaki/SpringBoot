package nezaki.demo.infrastructure.webapiexample.repository;

import nezaki.demo.infrastructure.webapiexample.entity.WebApiExampleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Repository
public class WebApiExampleRepository {

  @Autowired private RestTemplate restTemplate;

  public static final String URL = "http://localhost:8080";

  public ResponseEntity<WebApiExampleEntity> getExample(int id) {
    try {
      return this.restTemplate.getForEntity(URL + "/examples/" + id, WebApiExampleEntity.class);
    } catch (HttpClientErrorException ex) {
      return new ResponseEntity<>(ex.getStatusCode());
    }
  }

  public ResponseEntity<WebApiExampleEntity> postExample(WebApiExampleEntity webApiExampleEntity) {
    try {
      HttpHeaders headers = new HttpHeaders();
      HttpEntity<WebApiExampleEntity> request = new HttpEntity<>(webApiExampleEntity, headers);
      return this.restTemplate.postForEntity(URL + "/examples", request, WebApiExampleEntity.class);
    } catch (HttpClientErrorException ex) {
      return new ResponseEntity<>(ex.getStatusCode());
    }
  }
}
