# what is the config server?

**It is an externalized application configuration service. It is based on the open-source Spring Cloud Config project, which provides a centralized server for delivering external configuration properties to an application and a central source for managing this configuration across deployment environments.**

# where can we store the services' configuration files for the config server to search for? (Search Location)

1. File System
2. Class path
3. Git repository

## Setup Config server steps

### Initialize config server
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


![239523971-f309b269-0896-433d-b8b3-6ffe239d7f08](https://github.com/shaimaa-hshalaby/Microservice-with-spring-cloud-guide/assets/3264417/c70d4d90-01a9-4b78-afeb-0e87ec8e50a7)

4. generate the spring boot maven project

5. import the generated project to your IDE

6. Add application name and server port to the application.properties

```
spring.application.name = config-server
server.port = 8071
```

7. Enable the config server by adding the annotation **@EnableConfigServer** to the spring bootstrap class as shown below

![239534715-30e98125-92fd-4c04-9531-46d41cccf61a](https://github.com/shaimaa-hshalaby/Microservice-with-spring-cloud-guide/assets/3264417/30ffe08c-1854-488f-890b-5ebdab119672)


### Initialize the Config client microservice

1. navigate to https://start.spring.io/
2. choose the following configuration for this exercise
  - project -> Maven
  - Language -> Java 
  - Spring boot version -> 2.7.12
  - packaging -> jar
  - java version -> 11

3. add the following dependencies
  - Spring Web 
  - Config Client 
  - Spring Boot DevTools (Optional)

![239393673-e0a4eda4-e307-40bb-85af-2479546d97e6](https://github.com/shaimaa-hshalaby/Microservice-with-spring-cloud-guide/assets/3264417/eda28206-dad2-4429-b94d-6e1823d68a7b)


4. import the generated project to your IDE 
5. add spring.config.import property to application.properties to refer to the config server url
```
spring.config.import = configserver:http://localhost:8071
```
------
## Adding Microservice Configuration Files to Config server
As we mentioned before, there are 3 types of search locations:
- file system
- classpath
- git repository

### File System search location

1. The active profile property in the config server application.properties should be native if the search location would be the file system, so add the following property to the config server application.properties:

```
spring.profiles.active = native
```
2. Then add the location of the configuration file system to the config server application.properties as follows:

```
spring.cloud.config.server.native.searchLocations= file:///${config.dir}
```

3. create properties file with the same name of the client microservice (for example: employee-service.properties)
4. add the properties file to the same location defined in the config server properties file in the property **spring.cloud.config.server.native.searchLocations**

Example for the properties defined in the employee-service.properties
```
employee.name = Shaimaa Shalaby
employee.title = Senior developer
```
5. Add a RestController to the EmployeeService ,then use the **@Value** annotation to read the configuration from the configuration file saved in the config server side

```
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Value("${employee.name}")
	private String employeeName;
	
	@Value("${employee.title}")
	private String employeeTitle;
	
	@GetMapping("/name")
	public String getEmployeeName() {
		return this.employeeName;
		
	}
	
	@GetMapping("/title")
	public String getEmployeeTitle() {
		return this.employeeTitle;
		
	}

```
6. use Postman application to test the employee-service rest controller and make sure that the microservice could read the configuration


### Classpath search location

1. create a directory inside the classpath - under the resources folder - as shown below

![239532666-193f2bec-b9cd-47a5-b6ac-69cdaef12870](https://github.com/shaimaa-hshalaby/Microservice-with-spring-cloud-guide/assets/3264417/05bc6b78-ede3-4e82-a9c1-8ec2461cae9f)


2. add the configuration files of the config client microservice under this directory

3. The config server active profile property should be native also with the classpath search location.
   Then add the location of the configuration directory under the classpath in application.properties as follows:

```
spring.profiles.active = native
spring.cloud.config.server.native.searchLocations= classpath:/{config-dir-name}
```


### Git repository search location

1. create a git repository
2. change the config server active profile to git in the application.properties
```
spring.profiles.active = git
```
3. define the git repository url in the config server application.properties as follows:
```
spring.cloud.config.server.git.uri = https://github.com/shaimaa-hshalaby/config-server-repo.git
```

4. add the microservice config client configuration files to this git repository.
5. follow the steps explained before to create Restcontroller at the client side and test if the client microservice able to read the configuration or not.

-------

## How to create a separated configuration file for each environment dev, test, production and so on?

The naming convention for the application configuration files are appname-env. properties or appname-env.yml 

1. we need to define the active profile property in the microservice config client application.properties with the environement name as follows:
```
spring.profiles.active = dev
```

2. name the microservice configuration file under the config server search location with the following convention 
```
{microservice_name}-{active_profile}.properties
```
	for Example: employeeservice-dev.properties

3. you can add configuration files for different environment as much as you can if you follow the mentioned convention

![image](https://github.com/shaimaa-hshalaby/Microservice-with-spring-cloud-guide/assets/3264417/4400360d-b3eb-4a31-b6a0-acfc0665b7d4)

--------

## How to read the configuration programmatically inside the code?

you can use one of the following methods
- use @Value annotation with properties
- create a class to map to the entire configuration file and marked it with @ConfigurationProperties annotation


#### use @Value annotation
we have used it before in this tutorial in the EmployeeRestController to read the configuration from the configuration file

```
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Value("${employee.name}")
	private String employeeName;
	
	@Value("${employee.title}")
	private String employeeTitle;
	
	@GetMapping("/name")
	public String getEmployeeName() {
		return this.employeeName;
		
	}
	
	@GetMapping("/title")
	public String getEmployeeTitle() {
		return this.employeeTitle;
		
	}

```

#### use @ConfigurationProperties
Spring Boot @ConfigurationProperties is letting developer maps the entire .properties and yml file into an object easily. 

we can create a class that bind the data in the properties file to the class properties as follows:

1. the configuration properties file is shown below:
![image](https://github.com/shaimaa-hshalaby/Microservice-with-spring-cloud-guide/assets/3264417/9c40bc1f-c288-4de6-be9d-54c1bc790781)

2. the prefix of all shown properties is employee so we can define the class as follows
```
@ConfigurationProperties("employee")
public class MyProperties {
	
	// binded to employee.name
	private String name;
	
	// binded to employee.title
	private String title;
	
	// binded to employee.address
	private Address address;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

```

3. the complex property should be represented as a static public inner class inside the MyProperties class as the employee.address property

```
public static class Address{
	private String country;
	private String city;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

}

```

4. Enable the scan of the configuration property class by adding **@EnableConfigurationProperties(MyProperties.class)** to the spring boot bootstrap class as follows

```
@SpringBootApplication
@EnableConfigurationProperties(MyProperties.class)
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
```

5. To read the configuration into our controller, we should inject the Configuration properties object inside our controller as follows:

```
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	private final MyProperties myProp;
	
	public EmployeeController(MyProperties myProp) {
		this.myProp = myProp;
	}
	
	@GetMapping("/name")
	public String getEmployeeName() {
		return this.myProp.getName();
		
	}
	
	@GetMapping("/title")
	public String getEmployeeTitle() {
		return this.myProp.getTitle();
		
	}
	
	@GetMapping("/address")
	public Address getEmployeeCountry(){
		return this.myProp.getAddress();
	}


}

```
---------
### To do List

1. How to add secured configuration in vault
2. how to refresh the configuration values in the microservice client
  
