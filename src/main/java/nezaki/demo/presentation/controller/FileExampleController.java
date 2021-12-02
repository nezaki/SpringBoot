package nezaki.demo.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

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
            content = @Content(mediaType = "image/png, image/jpeg")),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
      })
  @GetMapping(
      value = "/{filename}",
      produces = {"image/png", "image/jpeg"})
  public void download(HttpServletResponse response, @PathVariable String filename)
      throws IOException {
    File file = new File("./tmp/" + filename);
    if (!file.exists()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    String mimeType = URLConnection.guessContentTypeFromName(file.getName());
    if (mimeType == null) {
      mimeType = "application/octet-stream";
    }
    response.setContentType(mimeType);
    response.setHeader(
        "Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
    response.setContentLength((int) file.length());
    InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
    FileCopyUtils.copy(inputStream, response.getOutputStream());
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
