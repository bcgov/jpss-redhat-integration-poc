// To run this integrations use:
// kamel run EdmDemsEdgeAdapter.java --dev -t service.enabled=true
// 
// recover the service location. If you're running on minikube, minikube service platform-http-server --url=true
// curl -H "name:World" http://<service-location>/hello
//

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class EdmDemsEdgeAdapter extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    from("platform-http:/courtFileCreated?httpMethodRestrict=GET")
    .routeId("courtFileCreated")
    .setHeader(Exchange.HTTP_METHOD, simple("GET"))
    //.to("rest:get:/createCourtFile?number=${header.number}");
    .removeHeader("CamelHttpUri")
    .removeHeader("CamelHttpBaseUri")
    .removeHeaders("CamelHttp*")
    //.to("http://edm-dems-mock-app/createCourtfile?number=${header.number}");
    // test comment
    .setBody()
    .simple("{'court_file_number': '${header.number}'}")
    //.to("rest:get:/createCourtFile?number=${header.number}")
    // https://camel.apache.org/manual/faq/how-to-send-the-same-message-to-multiple-endpoints.html
    .to("http://edm-dems-mock-app/createCourtFile?number=${header.number}")
    .log("{'court_file_number': header='${header.number}'}");
  }
}