package nezaki.demo.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nezaki.demo.infrastructure.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ExampleRepository {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public ExampleRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Example> getAll() {
    String sql = "select id, example_string from example";
    List<Map<String, Object>>exampleList = jdbcTemplate.queryForList(sql);
    List<Example> list = new ArrayList<>();
    for(Map<String,Object> str1 : exampleList) {
      Example examplle = new Example();
      examplle.setId((long)str1.get("id"));
      examplle.setExampleString((String)str1.get("example_string"));
      list.add(examplle);
    }
    return list;
  }
}