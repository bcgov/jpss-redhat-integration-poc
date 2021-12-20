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

    restConfiguration().host("edm-dems-mock-app").port(80);

    from("platform-http:/courtFileCreated?httpMethodRestrict=GET")
    .setHeader(Exchange.HTTP_METHOD, simple("GET"))
    //.to("rest:get:/createCourtFile?number=${header.number}");
    .removeHeader("CamelHttpUri")
    //.to("http://edm-dems-mock-app/createCourtfile?number=${header.number}");
    // test comment
    .to("rest:get:/createCourtFile?number=${header.number}")
    .log("{'court_file_number': header='${header}'}");
  }
}