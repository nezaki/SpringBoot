package nezaki.demo.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nezaki.demo.application.service.WebApiExampleService;
import nezaki.demo.infrastructure.webapiexample.entity.Ip;
import nezaki.demo.presentation.schema.WebApiExampleSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "WebAPIExamples", description = "Web API Example")
@RestController
@RequestMapping("/webapiexamples")
public class WebApiExampleController {

  @Autowired private WebApiExampleService webApiExampleService;

  @Operation(
      summary = "WebAPI呼び出しのサンプル",
      description = "WebAPIを利用してのデータ取得",
      tags = {"webApiExampleGet"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = WebApiExampleSchema.class)))
      })
  @GetMapping(produces = {"application/json"})
  public WebApiExampleSchema getWebApiExample() {
    Ip ip = webApiExampleService.get();
    return new WebApiExampleSchema(ip);
  }
}
