package nezaki.demo.presentation.controller.examplecontroller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import nezaki.demo.presentation.schema.ExampleSchema;
import nezaki.demo.util.DatetimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExampleControllerTests {

  @Autowired private TestRestTemplate testRestTemplate;

  @Test
  @Sql(
      scripts = "classpath:sql/presentation/controller/examplecontroller/testGetNormal.sql",
      config = @SqlConfig(encoding = "utf-8"))
  public void testGetNormal() throws ParseException {
    final String url = "/examples";

    int id = 1;
    String exampleString = "test";
    int exampleNumber = 12;
    boolean exampleBoolean = true;
    Date exampleDatetime = DatetimeUtil.parseDate("2021-01-02 03:04:05");
    ExampleSchema.StatusEnum exampleEnum = ExampleSchema.StatusEnum.AVAILABLE;
    Optional<String> exampleEmail = Optional.of("test@example.com");
    Optional<String> exampleUuid = Optional.of("6CA99996-8621-4499-A6C7-8C9A41D5A057");

    ResponseEntity<ExampleSchema> getResult =
        this.testRestTemplate.getForEntity(url + "/" + id, ExampleSchema.class);
    assertThat(getResult.getStatusCodeValue()).isEqualTo(200);
    assertThat(getResult.getBody().getId()).isEqualTo(id);
    assertThat(getResult.getBody().getExampleString()).isEqualTo(exampleString);
    assertThat(getResult.getBody().getExampleNumber()).isEqualTo(exampleNumber);
    assertThat(getResult.getBody().isExampleBoolean()).isEqualTo(exampleBoolean);
    assertThat(getResult.getBody().getExampleDatetime()).isEqualTo(exampleDatetime);
    assertThat(getResult.getBody().getExampleEnum()).isEqualTo(exampleEnum);
    assertThat(getResult.getBody().getExampleEmail()).isEqualTo(exampleEmail);
    assertThat(getResult.getBody().getExampleUuid()).isEqualTo(exampleUuid);
  }

  @Test
  @Sql(
      scripts = "classpath:sql/presentation/controller/examplecontroller/testPostNormal.sql",
      config = @SqlConfig(encoding = "utf-8"))
  public void testPostNormal() {
    final String url = "/examples";

    int id = 1;
    String exampleString = "exampleString";
    int exampleNumber = 12;
    boolean exampleBoolean = true;
    Date exampleDatetime = DatetimeUtil.getCurrentDate();
    ExampleSchema.StatusEnum exampleEnum = ExampleSchema.StatusEnum.AVAILABLE;
    Optional<String> exampleEmail = Optional.of("test@example.com");
    Optional<String> exampleUuid = Optional.of("c3dc7c29-2749-4fbf-942f-53fe60953f5b");
    ExampleSchema exampleSchema =
        new ExampleSchema(
            id,
            exampleString,
            exampleNumber,
            exampleBoolean,
            exampleDatetime,
            exampleEnum,
            exampleEmail,
            exampleUuid);

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<ExampleSchema> request = new HttpEntity<>(exampleSchema, headers);

    ResponseEntity<ExampleSchema> postResult =
        this.testRestTemplate.postForEntity(url, request, ExampleSchema.class);
    assertThat(postResult.getStatusCodeValue()).isEqualTo(200);
    assertThat(postResult.getBody().getExampleString()).isEqualTo(exampleString);
    assertThat(postResult.getBody().getExampleNumber()).isEqualTo(exampleNumber);
    assertThat(postResult.getBody().isExampleBoolean()).isEqualTo(exampleBoolean);
    assertThat(postResult.getBody().getExampleDatetime()).isEqualTo(exampleDatetime);
    assertThat(postResult.getBody().getExampleEnum()).isEqualTo(exampleEnum);
    assertThat(postResult.getBody().getExampleEmail()).isEqualTo(exampleEmail);
    assertThat(postResult.getBody().getExampleUuid()).isEqualTo(exampleUuid);

    ResponseEntity<ExampleSchema> getResult =
        this.testRestTemplate.getForEntity(url + "/" + id, ExampleSchema.class);
    assertThat(getResult.getStatusCodeValue()).isEqualTo(200);
    assertThat(getResult.getBody().getId()).isEqualTo(id);
    assertThat(getResult.getBody().getExampleString()).isEqualTo(exampleString);
    assertThat(getResult.getBody().getExampleNumber()).isEqualTo(exampleNumber);
    assertThat(getResult.getBody().isExampleBoolean()).isEqualTo(exampleBoolean);
    assertThat(getResult.getBody().getExampleDatetime()).isEqualTo(exampleDatetime);
    assertThat(getResult.getBody().getExampleEnum()).isEqualTo(exampleEnum);
    assertThat(getResult.getBody().getExampleEmail()).isEqualTo(exampleEmail);
    assertThat(getResult.getBody().getExampleUuid()).isEqualTo(exampleUuid);
  }
}
