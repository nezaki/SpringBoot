package nezaki.demo.application.service;

import nezaki.demo.infrastructure.entity.ExampleTable;
import nezaki.demo.infrastructure.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

  @Autowired
  private ExampleRepository exampleRepository;

  public ExampleTable selectOne(int exampleId) {
    return exampleRepository.selectOne(exampleId);
  }

}
