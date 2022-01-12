# Benefits with Red Hat Integration
    1. Automation
        - Operator-assisted deployments and run time
    2. Packaging
        - Low code integration with microservice benefits - based on 3rd
          generation Apache Camel framework (able to develop and deploy
          microservices without the overhead that goes into creating
          microservice code)
    3. Open Source
        - Built on best-of-breed open source solutions
    4. No Vendor Lock-In / Hybrid Friendly
        - Based on a set of integration building blocks that can co-exist 
          or replaced with alternative solutions
          E.g. Implementing an API catalog using DataBC's Kong API Gateway instead of Red Hat's 3scale Gateway
    5. Agile Integration Solution and Support
        - Best practice design and implementation of agile integration
            - Data Integration
            - Application Integration
            - Process Integration
        - Supported documentation and development journey
            https://developers.redhat.com/integration
        - Distributed integration as core building block
            Based on the famous Apache Camel, Camel K is designed and optimized 
        - Event + messaging based enterprise integration
        - Natural support for domain-based integration (aka API first design)
        - Reusable integration by adding new event producers, event consumers, and event processors
    6. OpenShift Friendly
        - Out of the box compatibility with OpenShift
        - Ready to deploy into traditional virtual machines
    7. Enterprise Support
        - Access to Red Hat support

# Limitations with Red Hat Integration
    1. Leading Edge Technology
        - Several integration operators and components have only recently become generally available (GA) and are going through the product hardening process, when compared to more mature Red Hat products
        - Not ready to operate in serverless mode in BC Gov OpenShift Container Platform (yet) due to missing multi-tenancy support
    2. Lacking Centralized Documentation
        - The nature of building the integration suite on open source projects means that the documentation is naturally more scattered and varied (multiple versions).  This leads to higher initial learning curve to leverage / fine tune integration components
           E.g. managing kamel traits through -t parameter or profiles through the --profile parameter

# Impacts
    1. Platform team dependencies (what to install and manage)
    
# Analysis Report Table of Contents
- Recommendation from the Integration Delivery Services Team
- Description of the toolkit
- What it includes
- The benefits of building on Red Hat Integration
    - support
    - automation tools
- Why this is not another webMethods
    - Open Source
    - Not an all-in-one vendor lock-in
- Generic diagrams
    - Synchronous integration
    - Asynchronous integration
- Definitions
    - Camel K
- How the microservices meet the requirements of DEMS
