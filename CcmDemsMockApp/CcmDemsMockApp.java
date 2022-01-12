// To run this integrations use:
// kamel run CcmDemsMockApp.java --dev -t service.enabled=true
// 
// recover the service location. If you're running on minikube, minikube service platform-http-server --url=true
// curl -H "name:World" http://<service-location>/hello
//

import org.apache.camel.builder.RouteBuilder;

public class CcmDemsMockApp extends RouteBuilder {
  @Override
  public void configure() throws Exception {

    // https://camel.apache.org/components/next/eips/delay-eip.html
    from("platform-http:/createCourtCase?httpMethodRestrict=GET")
    .routeId("createCourtFile")
    .delay(3000)
    .setBody(simple("Court case '${header.number}' processed successfully after 3 seconds."))
    .to("log:info");
  }
}