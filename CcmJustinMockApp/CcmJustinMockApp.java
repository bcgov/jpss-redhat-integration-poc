// To run this integration in OpenShift in server mode, use:
// kamel run CcmJustinMockApp.java --profile openshift
//
// Add '--dev' to run in dev mode
// 
// curl http://ccm-justin-mock-app/courtFileCreated?number=1
//

import java.util.Calendar;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class CcmJustinMockApp extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    // from("platform-http:/createCourtFile?httpMethodRestrict=GET")
    // .setBody()
    // .simple("{'court_file_number': '${header.number}'}")
    // .to("log:info");

    //restConfiguration().host("edm-dems-mock-app").port(80);

    from("platform-http:/courtFileCreated?httpMethodRestrict=GET")
    .routeId("courtFileCreated")
    //.to("rest:get:/createCourtFile?number=${header.number}");
    .removeHeader("CamelHttpUri")
    .removeHeader("CamelHttpBaseUri")
    .removeHeaders("CamelHttp*")
    //.to("http://edm-dems-mock-app/createCourtfile?number=${header.number}");
    // test comment
    .setProperty("number").simple("${header.number}")
    .process(new Processor() {
      public void process(Exchange ex) {
        // https://stackoverflow.com/questions/12008472/get-and-format-yesterdays-date-in-camels-expression-language
        Calendar createdCal = Calendar.getInstance();
        createdCal.add(Calendar.DATE, 0);
        ex.getIn().setHeader("created_datetime", createdCal.getTime());
      }
    })
    //.setBody().simple("{'number': '${exchangeProperty.number}', 'created_datetime': '${date:header.created_datetime:yyyy-mm-dd}', 'approved_datetime': '${date:header.approved_datetime:yyyy-mm-dd}'}")
    .setBody().simple("{\"number\": \"${exchangeProperty.number}\", \"created_datetime\": \"${date:header.created_datetime:yyyy-MM-dd'T'HH:mm:ssX}\"}")
    //.to("rest:get:/createCourtFile?number=${header.number}")
    // https://camel.apache.org/manual/faq/how-to-send-the-same-message-to-multiple-endpoints.html
    //.multicast().to("http://edm-justin-utility-adapter/courtFileCreated?number=${header.number}", "http://edm-dems-mock-app/createCourtFile?number=${header.number}")
    .setHeader(Exchange.HTTP_METHOD, simple("POST"))
    .to("http://ccm-justin-utility-adapter/courtFileCreated")
    .log("Newly generated court file: ${body}");
  }
}