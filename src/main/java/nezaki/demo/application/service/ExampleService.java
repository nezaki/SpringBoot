package nezaki.demo.application.service;

import java.util.List;
import nezaki.demo.infrastructure.rdbexample.entity.ExampleTable;
import nezaki.demo.infrastructure.rdbexample.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ExampleService {

  @Autowired ExampleRepository exampleRepository;

  /**
   * publicのメソッドにアノテーションを付与した場合のみ、ロールバックが有効になる。
   * ただし、ロールバックの注意点として、非検査例外(RuntimeException及びそのサブクラス)が発生した場合はロールバックされるが
   * 検査例外(Exception及びそのサブクラスでRuntimeExceptionのサブクラスじゃないもの)が発生した場合はロールバックされずコミットされる。
   * 対応方法 @Transactional(rollbackFor = Exception.class)
   */
  @Transactional(rollbackFor = Exception.class)
  public List<ExampleTable> selectAll() {
    return exampleRepository.selectAll();
  }

  @Transactional(rollbackFor = Exception.class)
  public ExampleTable selectOne(int id) {
    return exampleRepository
        .selectOne(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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
