// To run this integrations use:
// kamel run EdmJustinUtilityAdapter.java --property file:application.properties --profile openshift
// 
// recover the service location. If you're running on minikube, minikube service platform-http-server --url=true
// curl -H "name:World" http://<service-location>/hello
//

// camel-k: language=java
// camel-k: dependency=mvn:org.apache.camel.quarkus:camel-quarkus-kafka

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class EdmJustinUtilityAdapter extends RouteBuilder {
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
    .multicast().to("http://edm-dems-edge-adapter/courtFileCreated?number=${header.number}", "kafka:{{kafka.topic.name}}")
    .log("{'court_file_number': header='${header.number}'}");
  }
}

/*
[1] 2021-12-14 08:22:24,348 ERROR [org.apa.cam.qua.mai.CamelMainRuntime] (main) Failed to start application: org.apache.camel.RuntimeCamelException: java.lang.IllegalArgumentException: Cannot find RoutesBuilderLoader in classpath supporting file extension: EdmJustinUtilityAdapter.java
[1] 	at org.apache.camel.RuntimeCamelException.wrapRuntimeCamelException(RuntimeCamelException.java:51)
[1] 	at org.apache.camel.k.support.SourcesSupport.load(SourcesSupport.java:172)
*/