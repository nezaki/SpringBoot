package nezaki.demo.infrastructure.repository;

import java.util.List;
import nezaki.demo.infrastructure.entity.ExampleTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ExampleRepository {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public ExampleRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<ExampleTable> getAll() {
    String sql = "select * from example";
    RowMapper<ExampleTable> rowMapper = new BeanPropertyRowMapper<ExampleTable>(ExampleTable.class);
    return jdbcTemplate.query(sql, rowMapper);
  }
}
