package nezaki.demo.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nezaki.demo.application.service.ExampleService;
import nezaki.demo.infrastructure.entity.ExampleTable;
import nezaki.demo.presentation.schema.ExampleSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Examples", description = "example api description")
@RestController
public class ExampleController {

  @Autowired private ExampleService exampleService;

  @Operation(
      summary = "operation summary",
      description = "operation description",
      tags = {"examples"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "successful operation",
            content = @Content(schema = @Schema(implementation = ExampleSchema.class)))
      })
  @GetMapping(
      value = "/exmaples/{exampleId}",
      produces = {"application/json"})
  public ExampleSchema getExample(@PathVariable int exampleId) {
    ExampleTable example = exampleService.selectOne(exampleId);
    return new ExampleSchema(example);
  }
}
