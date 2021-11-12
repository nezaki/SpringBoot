package nezaki.demo.infrastructure.repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import nezaki.demo.infrastructure.entity.ExampleTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ExampleRepository {

  @Autowired private JdbcTemplate jdbc;

  public Optional<ExampleTable> selectOne(int id) {
    try {
      String sql = "select * from example where id = ? ";
      RowMapper<ExampleTable> rowMapper =
          new BeanPropertyRowMapper<ExampleTable>(ExampleTable.class);
      return Optional.ofNullable(jdbc.queryForObject(sql, rowMapper, id));
    } catch (EmptyResultDataAccessException ex) {
      return Optional.empty();
    }
  }

  public List<ExampleTable> selectAll() {
    String sql = "select * from example";
    RowMapper<ExampleTable> rowMapper = new BeanPropertyRowMapper<ExampleTable>(ExampleTable.class);
    return jdbc.query(sql, rowMapper);
  }

  public ExampleTable insert(ExampleTable exampleTable) {
    String sql =
        "INSERT INTO example("
            + "example_string,"
            + "example_number,"
            + "example_boolean,"
            + "example_datetime)"
            + "VALUES(?, ?, ?, ?)";

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbc.update(
        connection -> {
          PreparedStatement ps = connection.prepareStatement(sql, new String[] {"ID"});
          ps.setString(1, exampleTable.getExampleString());
          ps.setInt(2, exampleTable.getExampleNumber());
          ps.setBoolean(3, exampleTable.isExampleBoolean());
          ps.setDate(4, new java.sql.Date(exampleTable.getExampleDatetime().getTime()));
          return ps;
        },
        keyHolder);
    exampleTable.setId(keyHolder.getKey().longValue());
    return exampleTable;
  }

  public void update(ExampleTable exampleTable, int id) {
    String sql =
        "UPDATE example SET "
            + "example_string = ? ,"
            + "example_number = ? ,"
            + "example_boolean = ? ,"
            + "example_datetime = ? "
            + "WHERE id = ? ";

    jdbc.update(
        sql,
        exampleTable.getExampleString(),
        exampleTable.getExampleNumber(),
        exampleTable.isExampleBoolean(),
        exampleTable.getExampleDatetime(),
        id);
  }

  public void delete(int id) {
    String sql = "DELETE FROM example WHERE id = ?";
    jdbc.update(sql, id);
  }
}
