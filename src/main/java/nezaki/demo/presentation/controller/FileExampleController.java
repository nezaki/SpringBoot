package nezaki.demo.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "FileExample", description = "file example api description")
@RestController
@RequestMapping("/fileexamples")
public class FileExampleController {

  @Operation(
      summary = "ファイルダウンロードAPIのサンプル",
      description = "ファイルダウンロード",
      tags = {"fileExampleDownload"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(mediaType = "application/octet-stream")),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
      })
  @GetMapping(
      value = "/{filename}",
      produces = {"application/octet-stream"})
  public ResponseEntity<Resource> download(
      HttpServletResponse response, @PathVariable String filename) throws IOException {
    Path path = Paths.get("./tmp/" + filename);
    Resource resource = new PathResource(path);
    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .header(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
  }

  @Operation(
      summary = "ファイルアップロードAPIのサンプル",
      description = "ファイルアップロード",
      tags = {"fileExampleUpload"})
  @ApiResponses(
      value = {@ApiResponse(responseCode = "204", description = "No Content", content = @Content)})
  @PostMapping(consumes = {"multipart/form-data"})
  public ResponseEntity<Void> upload(MultipartFile multipartFile) throws IOException {
    File uploadFile = new File("./tmp/" + multipartFile.getOriginalFilename());
    byte[] bytes = multipartFile.getBytes();
    BufferedOutputStream uploadFileStream =
        new BufferedOutputStream(new FileOutputStream(uploadFile));
    uploadFileStream.write(bytes);
    uploadFileStream.close();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
