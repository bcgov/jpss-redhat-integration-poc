// To run this integration use:
// kamel run CcmLookupService.java --property file:application.properties --profile openshift
// 
// curl -H "user_id: 2" -H "court_case_number: 6" http://ccm-lookup-service/getCourtCaseDetails
//

// camel-k: language=java
// camel-k: dependency=mvn:org.apache.camel.quarkus:camel-quarkus-kafka

import java.util.Calendar;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class CcmLookupService extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    from("platform-http:/getCourtCaseDetails?httpMethodRestrict=GET")
    .routeId("getCourtCaseDetails")
    //.setHeader(Exchange.HTTP_METHOD, simple("GET"))
    //.to("rest:get:/createCourtFile?number=${header.number}");
    .removeHeader("CamelHttpUri")
    .removeHeader("CamelHttpBaseUri")
    .removeHeaders("CamelHttp*")
    //.to("http://edm-dems-mock-app/createCourtfile?number=${header.number}");
    // test comment
    //
    .process(new Processor() {
      public void process(Exchange ex) {
        // https://stackoverflow.com/questions/12008472/get-and-format-yesterdays-date-in-camels-expression-language
        Calendar createdCal = Calendar.getInstance();
        createdCal.add(Calendar.DATE, 0);
        ex.getIn().setHeader("audit_datetime", createdCal.getTime());
      }
    })
    .transform(simple("{\"audit_type\": \"get_court_case_details\", \"user_id\": \"${header.user_id}\", \"court_case_number\": \"${header.court_case_number}\", \"audit_datetime\": \"${header.audit_datetime}\"}"))
    .log("body (after transform): '${body}'")
    //.log("body.court_file_number: '${body.court_file_number}'")
    //.to("rest:get:/createCourtFile?number=${header.number}")
    // https://camel.apache.org/manual/faq/how-to-send-the-same-message-to-multiple-endpoints.html
    //.multicast().to("http://edm-dems-edge-adapter/courtFileCreated?number=${header.number}", "kafka:{{kafka.topic.name}}")
    //.to("kafka:{{kafka.topic.name}}");
    ;
  }
}