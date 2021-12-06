package nezaki.demo.application.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileExampleService {

  public Resource download(String filename) {
    Path path = Paths.get("./tmp/" + filename);
    return new PathResource(path);
  }

  public void upload(MultipartFile multipartFile) throws IOException {
    File uploadFile = new File("./tmp/" + multipartFile.getOriginalFilename());
    byte[] bytes = multipartFile.getBytes();
    BufferedOutputStream uploadFileStream =
        new BufferedOutputStream(new FileOutputStream(uploadFile));
    uploadFileStream.write(bytes);
    uploadFileStream.close();
  }
}
