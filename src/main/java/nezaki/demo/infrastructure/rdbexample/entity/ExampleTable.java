package nezaki.demo.infrastructure.rdbexample.entity;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nezaki.demo.presentation.schema.ExampleSchema;

@Getter
@Setter
@NoArgsConstructor
public class ExampleTable {
  private long id;
  private String exampleString;
  private int exampleNumber;
  private boolean exampleBoolean;
  private Date exampleDatetime;
  private String exampleEnum;
  private String exampleEmail;
  private String exampleUuid;

  public ExampleTable(ExampleSchema ex) {
    this.id = ex.getId();
    this.exampleString = ex.getExampleString();
    this.exampleNumber = ex.getExampleNumber();
    this.exampleBoolean = ex.isExampleBoolean();
    this.exampleDatetime = ex.getExampleDatetime();
    this.exampleEnum = ex.getExampleEnum().getValue();
    this.exampleEmail = ex.getExampleEmail();
    this.exampleUuid = ex.getExampleUuid();
  }
}
