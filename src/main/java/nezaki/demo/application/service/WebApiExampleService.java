package nezaki.demo.application.service;

import nezaki.demo.infrastructure.webapiexample.entity.Ip;
import nezaki.demo.infrastructure.webapiexample.repository.IpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebApiExampleService {

  @Autowired private IpRepository ipRepository;

  public Ip get() {
    return ipRepository.get();
  }
}
