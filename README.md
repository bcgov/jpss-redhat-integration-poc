# jpss-red-hat-integration-poc
Justice and Public Safety Sector Red Hat Integration POC repository

# Introduction

This Proof of concept (POC) project explores implementing the following areas of integration using Red Hat Integration Tool Kit:
- ## Enterprise Integration
  - Comprehensive connectors
  - Microservices orchestration
  - Data Transformation
  - Low-code iPaaS
  - Kubernetes connectivity with Camel K
  

# Documentation
- [Red Hat Integration](https://www.redhat.com/en/products/integration)
- [Red Hat GitOps](https://github.com/redhat-developer/gitops-operator)
- [Configuring Service Registry authentication and authorization with Red Hat Signle Sign-On](https://access.redhat.com/documentation/en-us/red_hat_integration/2021.q3/html/installing_and_deploying_service_registry_on_openshift/securing-the-registry#registry-security)

This POC project will use the following Red Hat Integration Tools to implement:
- Compute: Camel K
- Event Broker: Red Hat AMQ Streams (Kafka)
- Service and Schema Registry: Red Hat Service Registry (Apicur.io)
- API Management: 3scale

## Considerations
  - [Apache Kafka Architecture - Delivery Guarantees](https://supergloo.com/kafka/kafka-architecture-delivery/)
    - at most once - which can lead to messages being lost, but they cannot be redelivered or duplicated
    - at least once - ensures messages are never lost but may be duplicated
    - exactly once - guarantees each message processing (not delivery) happens once and only once

# Reference Repos
- [https://github.com/gnunn1/seating-manifests](https://github.com/gnunn1/seating-manifests)
- [Git Hub Repo: Camel K Example Kafka by Evan Zhang](https://github.com/rhtevan/camel-k-example-kafka) - This demonstrates how to use Service Registry to serialize and deserialize events in a given topic


# POC Scope
![](/img/dems-integration-context-diagram.png)

This POC will implement a simple set of evidance and disclosure management services.  Specifically, the POC will cover the following two services:
- Notification Service
- Lookup Service


# Components
- edm-justin-mock-app
- edm-justin-utility-adapter
- edm-notification-service
- edm-lookup-service
- edm-dems-edge-adapter
- edm-dems-mock-app
- cmn-kafka