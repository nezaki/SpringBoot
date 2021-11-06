package nezaki.demo.infrastructure.repository;

import nezaki.demo.infrastructure.entity.ExampleTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ExampleRepository {

  @Autowired private JdbcTemplate jdbc;

  public ExampleTable selectOne(int id) {
    String sql = "select * from example where id = ?";
    RowMapper<ExampleTable> rowMapper = new BeanPropertyRowMapper<ExampleTable>(ExampleTable.class);
    return jdbc.queryForObject(sql, rowMapper, id);
  }
}
