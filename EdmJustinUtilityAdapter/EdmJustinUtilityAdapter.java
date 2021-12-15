// To run this integrations use:
// kamel run EdmJustinUtilityAdapter.java --dev -t service.enabled=true
// 
// recover the service location. If you're running on minikube, minikube service platform-http-server --url=true
// curl -H "name:World" http://<service-location>/hello
//

// camel-k: language=java

import org.apache.camel.builder.RouteBuilder;

public class EdmJustinUtilityAdapter extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    from("platform-http:/hello?httpMethodRestrict=GET").setBody(simple("Hello ${header.name}"));
  }
}

/*
[1] 2021-12-14 08:22:24,348 ERROR [org.apa.cam.qua.mai.CamelMainRuntime] (main) Failed to start application: org.apache.camel.RuntimeCamelException: java.lang.IllegalArgumentException: Cannot find RoutesBuilderLoader in classpath supporting file extension: EdmJustinUtilityAdapter.java
[1] 	at org.apache.camel.RuntimeCamelException.wrapRuntimeCamelException(RuntimeCamelException.java:51)
[1] 	at org.apache.camel.k.support.SourcesSupport.load(SourcesSupport.java:172)
*/