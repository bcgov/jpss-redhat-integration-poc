// To run this integrations use:
// kamel run EdmDemsMockApp.java --dev -t service.enabled=true
// 
// recover the service location. If you're running on minikube, minikube service platform-http-server --url=true
// curl -H "name:World" http://<service-location>/hello
//

import org.apache.camel.builder.RouteBuilder;

public class EdmDemsMockApp extends RouteBuilder {
  @Override
  public void configure() throws Exception {

    // https://camel.apache.org/components/next/eips/delay-eip.html
    from("platform-http:/createCourtFile?httpMethodRestrict=GET")
    .delay(3000)
    .setBody(simple("Court file '${header.number}' processed successfully after 3 seconds."))
    .to("log:info");
  }
}