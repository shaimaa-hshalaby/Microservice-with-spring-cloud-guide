# Synchronous versus asynchronous messaging
#### There are two basic messaging patterns that microservices can use to communicate with other microservices.

- **Synchronous communication** In this pattern, a service calls an API that another service exposes, using a protocol such as HTTP or gRPC. This option is a synchronous messaging pattern because the caller waits for a response from the receiver.

- **Asynchronous message passing** In this pattern, a service sends message without waiting for a response, and one or more services process the message asynchronously.

#### There are different ways to make synchronous call from one microservice to another 

1.	RestTemplate
2.	WebClient 
3.	Open Feign
-----
## Making Synchronous call using RestTemplate



