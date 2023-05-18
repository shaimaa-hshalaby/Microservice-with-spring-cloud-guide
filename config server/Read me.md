# what is the config server?

**It is an externalized application configuration service. It is based on the open-source Spring Cloud Config project, which provides a centralized server for delivering external configuration properties to an application and a central source for managing this configuration across deployment environments.**

# where can we store the services' configuration files for the config server to search for? (Search Location)

1. File System
2. Class path
3. Git repository

# Setup Config server steps

### Initialize Spring boot application
1. navigate to https://start.spring.io/
2. choose the following configuration for this exercise
  - project -> Maven
  - Language -> Java 
  - Spring boot version -> 2.7.12
  - packaging -> jar
  - java version -> 11

3. add the following dependencies
  - Spring Web 
  - Config Server 
  - Spring Boot DevTools (Optional)

![image](https://github.com/shaimaa-hshalaby/Microservice-with-spring-cloud-guide/assets/3264417/fe1c7e1e-2e69-4587-aaa5-641b4ca5da3c)

  
