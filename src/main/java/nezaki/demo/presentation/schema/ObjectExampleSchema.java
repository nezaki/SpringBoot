package nezaki.demo.presentation.schema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ObjectExampleSchema {

  @Schema(
      title = "ExampleSchema",
      description = "exampleSchema description",
      required = true,
      nullable = true)
  private final ExampleSchema exampleSchema;

  public ObjectExampleSchema(ExampleSchema exampleSchema) {
    this.exampleSchema = exampleSchema;
  }
}
