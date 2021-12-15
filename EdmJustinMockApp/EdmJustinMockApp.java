// To run this integrations use:
// kamel run EdmJustinMockApp.java --dev -t service.enabled=true
// 
// recover the service location. If you're running on minikube, minikube service platform-http-server --url=true
// curl -H "name:World" http://<service-location>/hello
//

import org.apache.camel.builder.RouteBuilder;

public class EdmJustinMockApp extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    from("platform-http:/createCourtFile?httpMethodRestrict=GET")
    .setBody()
    .simple("{'court_file_number': '${header.number}''}")
    .to("log:info");
  }
}