package nezaki.demo.infrastructure.webapiexample.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Ip {
  private String ip;
  private String version;
  private String city;
  private String region;
  private String country;
  private String postal;
  private String org;
}
