package nezaki.demo.infrastructure.entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import nezaki.demo.presentation.schema.ExampleSchema;

@Getter
@Setter
public class ExampleTable {
  private long id;
  private String exampleString;
  private int exampleNumber;
  private boolean exampleBoolean;
  private Date exampleDatetime;

  public ExampleTable() {}

  public ExampleTable(ExampleSchema exampleSchema) {
    this.id = exampleSchema.getId();
    this.exampleString = exampleSchema.getExampleString();
    this.exampleNumber = exampleSchema.getExampleNumber();
    this.exampleBoolean = exampleSchema.isExampleBoolean();
    this.exampleDatetime = exampleSchema.getExampleDatetime();
  }
}
