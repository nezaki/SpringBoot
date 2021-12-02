package nezaki.demo.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import nezaki.demo.application.service.ExampleService;
import nezaki.demo.infrastructure.rdbexample.entity.ExampleTable;
import nezaki.demo.presentation.schema.ExampleSchema;
import nezaki.demo.presentation.schema.ListExampleSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Example", description = "example api description")
@RestController
@RequestMapping("/examples")
public class ExampleController {

  @Autowired ExampleService exampleService;

  @Operation(
      summary = "一件取得APIのサンプル",
      description = "指定されたIDのデータ取得",
      tags = {"examplesGet"})
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
    ExampleTable exampleTable = this.exampleService.selectOne(exampleId);
    return new ExampleSchema(exampleTable);
  }

  @Operation(
      summary = "複数件取得APIのサンプル",
      description = "データ取得",
      tags = {"examplesGet"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = ListExampleSchema.class))),
      })
  @GetMapping(produces = {"application/json"})
  public ListExampleSchema getExamples() {
    List<ExampleTable> examples = this.exampleService.selectAll();
    return new ListExampleSchema(
        examples.stream().map(ExampleSchema::new).collect(Collectors.toList()));
  }

  @Operation(
      summary = "登録APIのサンプル",
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
    ExampleTable exampleTable = new ExampleTable(exampleSchema);
    ExampleTable insertedExampleTable = this.exampleService.insert(exampleTable);
    return new ExampleSchema(insertedExampleTable);
  }

  @Operation(
      summary = "更新APIのサンプル",
      description = "データ更新",
      tags = {"exampleUpdate"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = ExampleSchema.class)))
      })
  @PutMapping(
      value = "/{exampleId}",
      consumes = {"application/json"},
      produces = {"application/json"})
  public ExampleSchema update(
      @PathVariable int exampleId, @RequestBody @Valid ExampleSchema exampleSchema) {
    ExampleTable exampleTable = new ExampleTable(exampleSchema);
    this.exampleService.update(exampleTable, exampleId);
    exampleTable.setId(exampleId);
    return new ExampleSchema(exampleTable);
  }

  @Operation(
      summary = "削除APIのサンプル",
      description = "データ削除",
      tags = {"exampleDelete"})
  @ApiResponses(value = {@ApiResponse(responseCode = "204")})
  @DeleteMapping(value = "/{exampleId}")
  public ResponseEntity<Void> delete(@PathVariable int exampleId) {
    this.exampleService.delete(exampleId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
