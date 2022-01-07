// To run this integration use:
// kamel run EdmDemsEdgeAdapter.java --property file:application.properties --profile openshift
// 
// recover the service location. If you're running on minikube, minikube service platform-http-server --url=true
// curl -H "name:World" http://<service-location>/hello
//

// camel-k: language=java
// camel-k: dependency=mvn:org.apache.camel.quarkus:camel-quarkus-kafka

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class EdmDemsEdgeAdapter extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    log.info("About to start courtFileCreated route: kafka -> edm-dems-mock-app");

    from("kafka:{{kafka.topic.name}}")
    .routeId("courtFileCreated")
    .log("Message received from Kafka : ${body}")
    .log("    on the topic ${headers[kafka.TOPIC]}")
    .log("    on the partition ${headers[kafka.PARTITION]}")
    .log("    with the offset ${headers[kafka.OFFSET]}")
    .log("    with the key ${headers[kafka.KEY]}")
    .setHeader(Exchange.HTTP_METHOD, simple("GET"))
    //.to("rest:get:/createCourtFile?number=${header.number}");
    //.to("rest:get:/createCourtFile?number=${header.number}")
    // https://camel.apache.org/manual/faq/how-to-send-the-same-message-to-multiple-endpoints.html
    .setProperty("number").simple("${body}")
    .setHeader("number").simple("${body}")
    .to("http://edm-dems-mock-app/createCourtFile")
    .log("{'court_file_number': '${exchangeProperty.number}', 'response_message': '${body}'}");
  }
}