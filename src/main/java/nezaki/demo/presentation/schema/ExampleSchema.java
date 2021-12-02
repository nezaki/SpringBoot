package nezaki.demo.presentation.schema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nezaki.demo.infrastructure.rdbexample.entity.ExampleTable;
import nezaki.demo.infrastructure.webapiexample.entity.WebApiExampleEntity;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExampleSchema {

  @Schema(
      title = "id",
      description = "id description",
      example = "1",
      required = true,
      nullable = true,
      accessMode = Schema.AccessMode.READ_ONLY)
  private long id;

  /**
   * NotNull
   *   null: Error
   *   空文字: OK
   *   半角スペースだけ: OK
   *   全角スペースだけ: OK
   *
   * NotEmpty
   *   null: Error
   *   空文字: Error
   *   半角スペースだけ: OK
   *   全角スペースだけ: OK
   *
   * NotBlank
   *   null: Error
   *   空文字: Error
   *   半角スペースだけ: Error
   *   全角スペースだけ: OK
   */
  @Schema(
      title = "exampleString",
      description = "exampleString description",
      example = "文字列サンプル",
      defaultValue = "デフォルト値",
      required = true)
  @NotBlank
  @Size(min = 1, max = 64)
  private String exampleString;

  /**
   * exclusiveMinimum, exclusiveMaximum
   * Min, Maxで指定した値を含めない
   * true: 含めない(<, >)
   * false: 含める(<=, >=)
   */
  @Schema(
      title = "exampleNumber",
      description = "exampleNumber description",
      example = "20",
      required = true,
      exclusiveMinimum = false,
      exclusiveMaximum = false)
  @Min(1)
  @Max(100)
  @NotNull
  private int exampleNumber;

  @Schema(
      title = "exampleBoolean",
      description = "exampleBoolean description",
      example = "true",
      required = true)
  @NotNull
  private boolean exampleBoolean;

  @Schema(
      title = "exampleDatetime",
      description = "exampleDatetime description",
      example = "2021-01-04T03:04:15+00:00",
      required = true)
  @NotNull
  private Date exampleDatetime;

  public enum StatusEnum {
    AVAILABLE("1"),
    PENDING("2");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @Schema(
      title = "exampleEnum",
      description = "exampleEnum description",
      example = "1",
      required = true)
  @NotNull
  private StatusEnum exampleEnum;

  @Schema(
      title = "exampleEmail",
      description = "exampleEmail description",
      example = "test@example.com",
      required = true,
      format = "email")
  @Email
  private String exampleEmail;

  @Schema(
      title = "exampleUuid",
      description = "exampleUuid description",
      example = "c3dc7c29-2749-4fbf-942f-53fe60953f5b",
      required = false,
      format = "uuid")
  @Pattern(regexp = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}")
  private String exampleUuid;

  @Schema(hidden = true)
  @AssertTrue(message = "this.exampleBoolean && this.exampleEmail.equals(\"\")")
  public boolean isValid() {
    return !(this.exampleBoolean && this.exampleEmail.equals(""));
  }

  public ExampleSchema(ExampleTable exampleTable) {
    this.id = exampleTable.getId();
    this.exampleString = exampleTable.getExampleString();
    this.exampleNumber = exampleTable.getExampleNumber();
    this.exampleBoolean = exampleTable.isExampleBoolean();
    this.exampleDatetime = exampleTable.getExampleDatetime();
    this.exampleEnum = StatusEnum.fromValue(exampleTable.getExampleEnum());
    this.exampleEmail = exampleTable.getExampleEmail();
    this.exampleUuid = exampleTable.getExampleUuid();
  }

  public ExampleSchema(WebApiExampleEntity webApiExampleEntity) {
    this.id = webApiExampleEntity.getId();
    this.exampleString = webApiExampleEntity.getExampleString();
    this.exampleNumber = webApiExampleEntity.getExampleNumber();
    this.exampleBoolean = webApiExampleEntity.isExampleBoolean();
    this.exampleDatetime = webApiExampleEntity.getExampleDatetime();
    this.exampleEnum = StatusEnum.fromValue(webApiExampleEntity.getExampleEnum());
    this.exampleEmail = webApiExampleEntity.getExampleEmail();
    this.exampleUuid = webApiExampleEntity.getExampleUuid();
  }
}
