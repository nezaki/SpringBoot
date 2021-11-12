package nezaki.demo.presentation.schema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import nezaki.demo.infrastructure.webapiexample.entity.Ip;

@Getter
public class WebApiExampleSchema {

  @Schema(title = "ip", description = "ip address", example = "126.67.83.5", required = true)
  private String ip;

  @Schema(title = "version", description = "version", example = "IPv4", required = true)
  private String version;

  @Schema(title = "postal", description = "postal code", example = "980-0821", required = true)
  private String postal;

  public WebApiExampleSchema(Ip ip) {
    this.ip = ip.getIp();
    this.version = ip.getVersion();
    this.postal = ip.getPostal();
  }
}
