# What is the Service Discovery?
Microservices service discovery is a way for applications and microservices to locate each other on a network.

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
