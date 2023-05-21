# Microservice-with-spring-cloud-guide
a guide to implement a microservice using Spring cloud - using in my courses


## What is the meaning of Microservices?
group of loosely coupled services

#### Each Microservice is:
  -	Self Contained
  -	Developed independently 
  -	Deployed independently
  -	Scaled independently
  -	Exposed its Rest APIs
  -	Connect to its own database



#### The communication between services may be:
  -	**Synchronous** the common used protocol is HTTP protocol 
  -	**Asynchronous**   use message broker between services such:
      -	Rappit MQ
      -	Kafka 

---------------------------------

## Microservice tutorials links:

- [Full Guide of Spring Boot application with Restful webservices and CRUD operations](00_spring_boot_CRUD_application)
- [Config Server tutorial](01_config_server)
- [Service Descovery - Naming server tutorial](02_naming_server)
- [How microservices communicate with each other synchronously tutorial](03_Synchronous_communication_between_microservices)
