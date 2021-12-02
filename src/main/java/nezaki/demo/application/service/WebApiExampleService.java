package nezaki.demo.application.service;

import nezaki.demo.infrastructure.webapiexample.entity.WebApiExampleEntity;
import nezaki.demo.infrastructure.webapiexample.repository.WebApiExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WebApiExampleService {

  @Autowired WebApiExampleRepository webApiExampleRepository;

  public ResponseEntity<WebApiExampleEntity> getExample(int id) {
    ResponseEntity<WebApiExampleEntity> responseEntity =
        this.webApiExampleRepository.getExample(id);
    if (responseEntity.getStatusCodeValue() != 200) {
      throw new ResponseStatusException(responseEntity.getStatusCode());
    } else {
      return responseEntity;
    }
  }

  public ResponseEntity<WebApiExampleEntity> postExample(WebApiExampleEntity webApiExampleEntity) {
    ResponseEntity<WebApiExampleEntity> responseEntity =
        this.webApiExampleRepository.postExample(webApiExampleEntity);
    if (responseEntity.getStatusCodeValue() != 200) {
      throw new ResponseStatusException(responseEntity.getStatusCode());
    } else {
      return responseEntity;
    }
  }
}
