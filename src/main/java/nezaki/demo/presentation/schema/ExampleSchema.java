package nezaki.demo.presentation.schema;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import nezaki.demo.infrastructure.dbexample.entity.ExampleTable;

@Getter
public class ExampleSchema {

  @Schema(
      title = "id title",
      description = "id description",
      example = "1",
      required = true,
      accessMode = Schema.AccessMode.READ_ONLY)
  private long id;

  @Schema(
      title = "exampleString title",
      description = "exampleString description",
      example = "exampleString example",
      required = true)
  @NotBlank
  @Size(min = 2, max = 64)
  private String exampleString;

  @Schema(
      title = "exampleNumber title",
      description = "exampleNumber description",
      example = "20",
      required = true,
      exclusiveMinimum = false,
      exclusiveMaximum = false)
  @Min(1)
  @Max(100)
  private int exampleNumber;

  @Schema(
      title = "exampleBoolean title",
      description = "exampleBoolean description",
      example = "true",
      required = true)
  private boolean exampleBoolean;

  @Schema(
      title = "exampleDatetime title",
      description = "exampleDatetime description",
      example = "2021-01-01T01:02:03.456+00:00",
      required = true)
  private Date exampleDatetime;

  public ExampleSchema() {}

  public ExampleSchema(
      long id,
      String exampleString,
      int exampleNumber,
      boolean exampleBoolean,
      Date exampleDatetime) {
    this.id = id;
    this.exampleString = exampleString;
    this.exampleNumber = exampleNumber;
    this.exampleBoolean = exampleBoolean;
    this.exampleDatetime = exampleDatetime;
  }

  public ExampleSchema(ExampleTable exampleTable) {
    this.id = exampleTable.getId();
    this.exampleString = exampleTable.getExampleString();
    this.exampleNumber = exampleTable.getExampleNumber();
    this.exampleBoolean = exampleTable.isExampleBoolean();
    this.exampleDatetime = exampleTable.getExampleDatetime();
  }
}
