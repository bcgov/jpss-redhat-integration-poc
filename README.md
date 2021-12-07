# jpss-red-hat-integration-poc
Justice and Public Safety Sector Red Hat Integration POC repository

# Introduction

This Proof of concept (POC) project explores implementing the following areas of integration using Red Hat Integration Tool Kit:
- Enterprise Integration
  - ddf

# Documentation
- [Red Hat Integration](https://www.redhat.com/en/products/integration)

This POC project will use the following Red Hat Integration Tools to implement:
- Compute: Camel K
- Event Broker: Red Hat AMQ Streams (Kafka)
- Service and Schema Registry: Red Hat Service Registry (Apicur.io)
- API Management: 3scale


# POC Scope
![](/img/dems-integration-context-diagram.png)

This POC will implement a simple set of evidance and disclosure management services.  Specifically, the POC will cover the following two services:
- Notification Service
- Lookup Service


# Components
- edm-justin-mock-app
- edm-notification-service
- edm-lookup-service
- 
- edm-dems-mock-app