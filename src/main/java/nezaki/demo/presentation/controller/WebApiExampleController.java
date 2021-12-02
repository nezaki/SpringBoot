package nezaki.demo.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Objects;
import javax.validation.Valid;
import nezaki.demo.application.service.WebApiExampleService;
import nezaki.demo.infrastructure.webapiexample.entity.WebApiExampleEntity;
import nezaki.demo.presentation.schema.ExampleSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "WebApiExample", description = "web api example description")
@RestController
@RequestMapping("/webapiexamples")
public class WebApiExampleController {

  @Autowired WebApiExampleService webApiExampleService;

  @Operation(
      summary = "WebAPI(GET)のサンプル",
      description = "指定されたIDのデータ取得",
      tags = {"webApiExamplesGet"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = ExampleSchema.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
      })
  @GetMapping(
      value = "/{exampleId}",
      produces = {"application/json"})
  public ExampleSchema getExample(@PathVariable int exampleId) {
    ResponseEntity<WebApiExampleEntity> response = this.webApiExampleService.getExample(exampleId);
    return new ExampleSchema(Objects.requireNonNull(response.getBody()));
  }

  @Operation(
      summary = "WebAPI(POST)のサンプル",
      description = "データ登録",
      tags = {"exampleInsert"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = ExampleSchema.class)))
      })
  @PostMapping(
      consumes = {"application/json"},
      produces = {"application/json"})
  public ExampleSchema insert(@RequestBody @Valid ExampleSchema exampleSchema) {
    WebApiExampleEntity webApiExampleEntity = new WebApiExampleEntity(exampleSchema);
    ResponseEntity<WebApiExampleEntity> responseEntity =
        this.webApiExampleService.postExample(webApiExampleEntity);
    return new ExampleSchema(Objects.requireNonNull(responseEntity.getBody()));
  }
}
