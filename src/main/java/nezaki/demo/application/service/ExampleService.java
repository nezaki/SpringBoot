package nezaki.demo.application.service;

import java.util.List;
import java.util.Optional;
import nezaki.demo.infrastructure.dbexample.entity.ExampleTable;
import nezaki.demo.infrastructure.dbexample.repository.ExampleRepository;
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
  public List<ExampleTable> selectAll() {
    return exampleRepository.selectAll();
  }

  @Transactional(rollbackFor = Exception.class)
  public ExampleTable insert(ExampleTable exampleTable) {
    return exampleRepository.insert(exampleTable);
  }

  @Transactional(rollbackFor = Exception.class)
  public void update(ExampleTable exampleTable, int id) {
    exampleRepository.update(exampleTable, id);
  }

  @Transactional(rollbackFor = Exception.class)
  public void delete(int id) {
    exampleRepository.delete(id);
  }
}
