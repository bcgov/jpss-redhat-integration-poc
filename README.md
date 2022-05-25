[![Lifecycle:Dormant](https://img.shields.io/badge/Lifecycle-Dormant-ff7f2a)](<Redirect-URL>)

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

## Considerations
  - [Apache Kafka Architecture - Delivery Guarantees](https://supergloo.com/kafka/kafka-architecture-delivery/)
    - at most once - which can lead to messages being lost, but they cannot be redelivered or duplicated
    - at least once - ensures messages are never lost but may be duplicated
    - exactly once - guarantees each message processing (not delivery) happens once and only once
  - [Should You Put Several Event Types in the Same Kafka Topic?](https://www.confluent.io/blog/put-several-event-types-kafka-topic/)

# Reference Repos
- [GitHub Repo: Seating Manifests by Gerald Nunn](https://github.com/gnunn1/seating-manifests)
- [GitHub Repo: Camel K: Event Streaming Example (Discussed at POC kick off)](https://github.com/openshift-integration/camel-k-example-event-streaming/tree/1.4.x)
- [GitHub Gist: OCP Serverless | Knative - Demo Scripts by Evan Zhang](https://gist.github.com/rhtevan/aeeedf4d3037f5c605e94179ddf6f5e1)
- [GitHub Repo: OpenShift Integration/camel-k-example-basic](https://github.com/openshift-integration/camel-k-example-basic/blob/main/readme.didact.md)
- [GitHub Repo: Apache Camel K - Kafka Example](https://github.com/apache/camel-k/tree/main/examples/kafka)
- [GitHub Repo: Camel K Example Kafka by Evan Zhang](https://github.com/rhtevan/camel-k-example-kafka) - This demonstrates how to use Service Registry to serialize and deserialize events in a given topic

# Fun / Useful Demos
- [Building a contact form API with Apache Camel and Quarkus - by Tom Donohue](https://tomd.xyz/camel-quarkus-contact-form/)

# Kafkacat usage

<pre>
oc run kafkacat -it --rm --restart=Never --image=edenhill/kafkacat:1.6.0 --command – /bin/sh

alias kafkacat='oc exec kafkacat -it -- kafkacat'

kafkacat -b cmn-kafka-kafka-bootstrap:9092 -t my-topic -C 
</pre>

Note: Need to run this in a real Linux shell; e.g. a Windows Subsystem for Linux (WSL) session, rather than a Git Bash (emulation) session


# POC Scope
![](/img/dems-integration-context-diagram.png)

This POC will implement a simple set of court case management (CCM) services.  Specifically, the POC will cover the following two services:
- Notification Service
- Lookup Service

# POC System Context Diagram

Below is the system context diagram for the DEMS Integration POC where the following event-based solution is implemented using Red Hat Integration toolkit:

- **Actors**
  - JUSTIN System
  - DEMS User

- **Events / Commands**
  - Court Case Created
  - Get Court Case Details

- **Domain Services**
  - Notification Service
  - Lookup Service

- **Event Topics**
  - Court Cases
  - KPIs (no personal information)
  - Audits

![](/img/poc_system_context_diagram.png)

# Integration Components Included in the POC

The POC is comprised of the following integration and common components

| Component ID | Component Description | Integration Component Type |
| ------------ | --------------------- | -------------------------- |
| ccm-justin-mock-app | CCM JUSTIN Mock Application | Mock Enterprise Application |
| ccm-justin-utility-adapter | CCM JUSTIN Utility Adapter | Enterprise Application Integration Outbound Interface |
| ccm-dems-edge-adapter | CCM DEMS Edge Adapter | Enterprise Application Integration Inbound Gateway Interface |
| ccm-dems-mock-app | CCM DEMS Mock Application | Mock Enterprise Application |
| ccm-notification-service | CCM Notification Service | Domain Stream Processor |
| ccm-lookup-service | CCM Lookup Service | Domain API Interface |
| cmn-kafka | Common Kafka Event Server | Common Service |
