package nezaki.demo.infrastructure.rdbexample.repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import nezaki.demo.infrastructure.rdbexample.entity.ExampleTable;
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

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public ExampleRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<ExampleTable> selectAll() {
    String sql = "select * from example";
    RowMapper<ExampleTable> rowMapper = new BeanPropertyRowMapper<ExampleTable>(ExampleTable.class);
    return jdbcTemplate.query(sql, rowMapper);
  }

  public Optional<ExampleTable> selectOne(int id) {
    try {
      String sql = "select * from example where id = ? ";
      RowMapper<ExampleTable> rowMapper =
          new BeanPropertyRowMapper<ExampleTable>(ExampleTable.class);
      return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    } catch (EmptyResultDataAccessException ex) {
      return Optional.empty();
    }
  }

  public ExampleTable insert(ExampleTable exampleTable) {
    String sql =
        "INSERT INTO example("
            + "example_string,"
            + "example_number,"
            + "example_boolean,"
            + "example_datetime,"
            + "example_enum,"
            + "example_email,"
            + "example_uuid)"
            + "VALUES(?, ?, ?, ?, ?, ?, ?)";

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(
        connection -> {
          PreparedStatement ps = connection.prepareStatement(sql, new String[] {"ID"});
          ps.setString(1, exampleTable.getExampleString());
          ps.setInt(2, exampleTable.getExampleNumber());
          ps.setBoolean(3, exampleTable.isExampleBoolean());
          ps.setTimestamp(4, new Timestamp(exampleTable.getExampleDatetime().getTime()));
          ps.setString(5, exampleTable.getExampleEnum());
          // Java9以降であれば、ifPresentOrElseを使う
          if (exampleTable.getExampleEmail().isPresent()) {
            ps.setString(6, exampleTable.getExampleEmail().get());
          } else {
            ps.setNull(6, Types.NULL);
          }
          if (exampleTable.getExampleUuid().isPresent()) {
            ps.setString(7, exampleTable.getExampleUuid().get());
          } else {
            ps.setNull(7, Types.NULL);
          }
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
            + "example_datetime = ?, "
            + "example_enum = ? ,"
            + "example_email = ? ,"
            + "example_uuid = ? "
            + "WHERE id = ? ";

    jdbcTemplate.update(
        sql,
        exampleTable.getExampleString(),
        exampleTable.getExampleNumber(),
        exampleTable.isExampleBoolean(),
        exampleTable.getExampleDatetime(),
        exampleTable.getExampleEnum(),
        exampleTable.getExampleEmail(),
        exampleTable.getExampleUuid(),
        id);
  }

  public void delete(int id) {
    String sql = "DELETE FROM example WHERE id = ?";
    jdbcTemplate.update(sql, id);
  }
}
