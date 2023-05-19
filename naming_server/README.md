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

![image](https://github.com/shaimaa-hshalaby/Microservice-with-spring-cloud-guide/assets/3264417/877d754e-0dbe-4f0e-9a00-168dc1876384)
    Note: from spring microservice in action book
    
    
 # Building our Spring Eureka Service
 
 1. navigate to the spring intializer tool https://start.spring.io/
 2. 
