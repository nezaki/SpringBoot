package nezaki.demo.presentation.schema;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import nezaki.demo.infrastructure.entity.ExampleTable;

public class ExampleSchema {

  @Schema(title = "id title", description = "id description", example = "1", required = true)
  private final long id;

  @Schema(
      title = "exampleString title",
      description = "exampleString description",
      example = "exampleString example",
      required = true)
  @NotBlank
  @Size(min = 2, max = 64)
  private final String exampleString;

  @Schema(
      title = "exampleNumber title",
      description = "exampleNumber description",
      example = "20",
      required = true,
      exclusiveMinimum = false,
      exclusiveMaximum = false)
  @Min(1)
  @Max(100)
  private final int exampleNumber;

  @Schema(
      title = "exampleBoolean title",
      description = "exampleBoolean description",
      example = "true",
      required = true)
  private final boolean exampleBoolean;

  @Schema(
      title = "exampleDatetime title",
      description = "exampleDatetime description",
      // example = "2021/01/01T01:02:03.456Z",
      required = true)
  private final Date exampleDatetime;

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

  public long getId() {
    return id;
  }

  public String getExampleString() {
    return exampleString;
  }

  public int getExampleNumber() {
    return exampleNumber;
  }

  public boolean isExampleBoolean() {
    return exampleBoolean;
  }

  public Date getExampleDatetime() {
    return exampleDatetime;
  }
}
