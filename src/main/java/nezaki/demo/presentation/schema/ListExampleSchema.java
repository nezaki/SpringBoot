package nezaki.demo.presentation.schema;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

@Getter
public class ListExampleSchema {

  @Schema(
      title = "examples",
      description = "examples description",
      required = true,
      nullable = true)
  private List<ExampleSchema> examples;

  public ListExampleSchema(List<ExampleSchema> exampleSchemas) {
    this.examples = exampleSchemas;
  }
}
