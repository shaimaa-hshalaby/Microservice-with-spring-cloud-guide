# What is the Service Discovery?
Microservices service discovery is a way for applications and microservices to locate each other on a network.

# How the service discovery works
- While each instance of a service has a unique IP address and port, each service instance that comes up registers under the same service ID. 
- A service ID is nothing more than a key that uniquely identifies a group of the same service instances.
- A service usually only registers with one service discovery service instance.(the service discovery is a cluster of multiple instances) 
- Most service discovery implementations use a peer-to-peer model of data propagation, where the data around each service instance is communicated to all the other nodes in the cluster.
- Each service instance pushes to or pulls from its status by the service discovery service. 
- Any services failing to return a good health check are removed from the pool of available service instances.
- Once a service is registered with a service discovery service, it’s ready to be used by an application or service that needs to make use of its capabilities.


# Service Discovery mechanism features
The solution for a cloud-based microservice environment is to use a service discovery mechanism thats
- **Highly available**
    service lookups can be shared across multiple nodes in a service discovery cluster. If a node becomes unavailable, other nodes in the cluster should be able to take over.

- **Peer to peer**
    Each node in the service discovery cluster shares the state of a service instance.
    
- **Load Balanced**
    Service discovery needs to dynamically load balance requests across all service instances.

- **Resilient**
    The service discovery’s client should cache service information locally. Local caching allows for gradual degradation of the service discovery feature so that if the service discovery service becomes unavailable, applications can still function and locate the services based on the information maintained in their local cache.

- **Fault tolerant**
    Service discovery needs to detect when a service instance isn’t healthy and remove that instance from the list of available services that can take client requests.
        
    
# Client-Side load balancing approach

![239621828-877d754e-0dbe-4f0e-9a00-168dc1876384](https://github.com/shaimaa-hshalaby/Microservice-with-spring-cloud-guide/assets/3264417/97947d86-c8df-4e07-9b67-885f8a732a00)

    > Note: from spring microservice in action book
    
    
 # Building our Spring Eureka Discovery Server
 To include Eureka Server in your project, use the starter with a group ID of *org.springframework.cloud* and an artifact ID of *spring-cloud-starter-netflix-eureka-server*.
 
 1. navigate to the spring intializer tool https://start.spring.io/
 2. choose the following configuration for this exercise
    - project -> Maven
    - Language -> Java
    - Spring boot version -> 2.7.12
    - packaging -> jar
    - java version -> 11

 3. add the following dependency
    - Eureka Server

   ![239627931-9c762b07-1cd9-4915-8917-6d434d7d2982](https://github.com/shaimaa-hshalaby/Microservice-with-spring-cloud-guide/assets/3264417/c91b47ac-254f-4dd9-a3b1-c44acadded17)

    
 4. generate the spring boot project and then import it to your IDE.
 5. mark the springboot bootstrap class with **@EnableEurekaServer** annotation to enable the Eureka server.
 6. add the following properties to the *application.properties* file
    ```
    spring.application.name = naming_server
    server.port = 8761
    eureka.instance.hostname = localhost
    eureka.client.serviceUrl.defaultZone = http://${eureka.instance.hostname}:${server.port}/eureka/

    ```
    
 7. switch off the client-side behavior by adding the following properties to the *application.properties* and to run the naming server in the standalone mode (no other nodes in the cluster)
 
    ```
    eureka.client.registerWithEureka = false
    eureka.client.fetchRegistry = false
    ```
    
 8. run the application and then check if the Eureka server is up and running by navigating to http://localhost:8761/
 9. you should see this page with no services instances registered yet.
 
![239679544-dfcff679-4ebe-4933-b847-c63bf5244d31](https://github.com/shaimaa-hshalaby/Microservice-with-spring-cloud-guide/assets/3264417/695b4ced-09a0-4f6d-8bf3-25e66307e93e)


> Congratulation .. you have now your Eureka naming server is running into your machine

# Building and Registering Eureka Client
-----
## How to Include Eureka Client

To include the Eureka Client in your project, use the starter with a group ID of *org.springframework.cloud* and an artifact ID of *spring-cloud-starter-netflix-eureka-client*

1. navigate to the spring intializer tool https://start.spring.io/
2. choose the following configuration for this exercise
    - project -> Maven
    - Language -> Java
    - Spring boot version -> 2.7.12
    - packaging -> jar
    - java version -> 11

 3. add the following dependencies
    - Eureka Discovery Client
    - other dependencies that are needed to build our Microservice
    
![239684131-04f0729e-f48b-4abb-88ba-cd168e09ce67](https://github.com/shaimaa-hshalaby/Microservice-with-spring-cloud-guide/assets/3264417/10e7ed99-2b8c-47b2-86a3-449d2cc4d6c7)

## Registering with Eureka

- When a client registers with Eureka, it provides meta-data about itself — such as host, port, health indicator URL, home page, and other details.
- Eureka receives heartbeat messages from each instance belonging to a service. If the heartbeat fails over a configurable timetable, the instance is normally removed from the registry.
- we need to add the following property to the microservice configuration file to locate the Eureka Discovery Server.
    
    ``` 
    eureka.client.serviceUrl.defaultZone = http://localhost:8761/eureka/
    ```
 - By having spring-cloudstarter-netflix-eureka-client on the classpath, the application automatically registers with the Eureka Server.
 - start the microservice by running the bootstrap class.
 - navigate to the Eureka server from the browser http://localhost:8761, and make sure that the instance has been registered





