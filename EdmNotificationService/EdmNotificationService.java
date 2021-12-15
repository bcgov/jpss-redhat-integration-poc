// To run this integrations use:
// kamel run EdmNotificationService.java --dev -t service.enabled=true
// 
// recover the service location. If you're running on minikube, minikube service platform-http-server --url=true
// curl -H "name:World" http://<service-location>/hello
//

import org.apache.camel.builder.RouteBuilder;

public class EdmNotificationService extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    from("platform-http:/hello?httpMethodRestrict=GET").setBody(simple("Hello ${header.name} from ${header.from}"));
  }
}