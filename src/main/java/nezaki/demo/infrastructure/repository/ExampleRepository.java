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

  @Autowired private JdbcTemplate jdbc;

  public ExampleTable selectOne(int id) {
    String sql = "select * from example where id = ?";
    RowMapper<ExampleTable> rowMapper = new BeanPropertyRowMapper<ExampleTable>(ExampleTable.class);
    return jdbc.queryForObject(sql, rowMapper, id);
  }

  public List<ExampleTable> selectAll() {
    String sql = "select * from example";
    RowMapper<ExampleTable> rowMapper = new BeanPropertyRowMapper<ExampleTable>(ExampleTable.class);
    return jdbc.query(sql, rowMapper);
  }

  public int insertOne(ExampleTable exampleTable) {
    String sql =
        "INSERT INTO example(example_string,"
            + " example_number,"
            + " example_boolean,"
            + "example_datetime )"
            + " VALUES(?, ?, ?, ?)";
    int rowNumber =
        jdbc.update(
            sql,
            exampleTable.getExampleString(),
            1,
            exampleTable.isExampleBoolean(),
            exampleTable.getExampleDatetime());
    return rowNumber;
  }

  public int updateOne(ExampleTable exampleTable) {
    String sql =
        "UPDATE  example SET "
            + " example_string = ? ,"
            + " example_number = ? ,"
            + " example_boolean = ? ,"
            + " example_datetime = ? WHERE id = ? ";
    int rowNumber =
        jdbc.update(
            sql,
            exampleTable.getExampleString(),
            exampleTable.getExampleNumber(),
            exampleTable.isExampleBoolean(),
            exampleTable.getExampleDatetime(),
            exampleTable.getId());
    return rowNumber;
  }

  public int deleteOne(String id) {
    String sql = "DELETE FROM example WHERE id = ?";
    int rowNumber = jdbc.update(sql, id);
    return rowNumber;
  }
}
