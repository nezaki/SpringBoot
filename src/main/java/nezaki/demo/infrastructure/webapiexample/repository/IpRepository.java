package nezaki.demo.infrastructure.webapiexample.repository;

import lombok.extern.slf4j.Slf4j;
import nezaki.demo.infrastructure.webapiexample.entity.Ip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
@Slf4j
public class IpRepository {

  @Autowired private RestTemplate restTemplate;

  public static final String URL = "https://ipapi.co/126.67.83.5/json";

  public Ip get() {
    Ip ip = restTemplate.getForObject(URL, Ip.class);
    log.debug(ip.toString());
    return ip;
  }
}
