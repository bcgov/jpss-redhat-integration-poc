// To run this integration in OpenShift in server mode, use:
// kamel run EdmJustinMockApp.java --profile openshift
//
// Add '--dev' to run in dev mode
// 
// curl -H "number:1234" http://<service-location>/createCourtFile
//

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class EdmJustinMockApp extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    // from("platform-http:/createCourtFile?httpMethodRestrict=GET")
    // .setBody()
    // .simple("{'court_file_number': '${header.number}'}")
    // .to("log:info");

    //restConfiguration().host("edm-dems-mock-app").port(80);

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
    //.multicast().to("http://edm-justin-utility-adapter/courtFileCreated?number=${header.number}", "http://edm-dems-mock-app/createCourtFile?number=${header.number}")
    .to("http://edm-justin-utility-adapter/courtFileCreated?number=${header.number}")
    .log("{'court_file_number': header='${header.number}'}");
  }
}