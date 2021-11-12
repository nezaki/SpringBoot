package nezaki.demo.application.service;

import java.util.Optional;
import nezaki.demo.infrastructure.entity.ExampleTable;
import nezaki.demo.infrastructure.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExampleService {

  @Autowired private ExampleRepository exampleRepository;

  @Transactional(rollbackFor = Exception.class)
  public Optional<ExampleTable> selectOne(int id) {
    return exampleRepository.selectOne(id);
  }

  @Transactional(rollbackFor = Exception.class)
  public ExampleTable insert(ExampleTable exampleTable) {
    return exampleRepository.insert(exampleTable);
  }
}
