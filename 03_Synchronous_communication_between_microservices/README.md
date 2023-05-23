# Synchronous versus asynchronous messaging
#### There are two basic messaging patterns that microservices can use to communicate with other microservices.

- **Synchronous communication** In this pattern, a service calls an API that another service exposes, using a protocol such as HTTP or gRPC. This option is a synchronous messaging pattern because the caller waits for a response from the receiver.

- **Asynchronous message passing** In this pattern, a service sends message without waiting for a response, and one or more services process the message asynchronously.

#### There are different ways to make synchronous call from one microservice to another 

1.	RestTemplate
2.	WebClient 
3.	Open Feign
-----

## Exercise
- we will build 2 microservices *course-service* and *instructor-service*.
- each microservice has its own database schema.
- each microservice exposes its endpoints for the CRUD operations.
- when we call the endpoint of getting the course details by id, the *course-service* makes a synchronous call to the *instructor-service* to get the course's instructor details.


![image](https://github.com/shaimaa-hshalaby/Microservice-with-spring-cloud-guide/assets/3264417/84a61563-4db1-4f2a-a914-2227dd53d735)

## Building the Course-service
[Follow this guide to build the course-service](../00_spring_boot_CRUD_application)

- we can create course object using the postman application as follows:
    ![image](https://github.com/shaimaa-hshalaby/Microservice-with-spring-cloud-guide/assets/3264417/a3d04766-e3a5-4218-9bcf-dbc673619816)

- test getting course by id using the postman application as follows:
   ![image](https://github.com/shaimaa-hshalaby/Microservice-with-spring-cloud-guide/assets/3264417/d5493ae2-4b6c-4a54-852d-01728306706e)


## Build the Instructor-service

1. create Springboot application from the spring initializer 
    ![image](https://github.com/shaimaa-hshalaby/Microservice-with-spring-cloud-guide/assets/3264417/d11150bf-ff88-4c1d-8701-a90307c8cf2d)

2. import the project into your IDE.
3. add application name and server port configuration to the application.properties file.

   ```
      spring.application.name = instructor-service
      server.port = 8090
   ```
 4. Create the instructor Entity class
    ```
        @Entity
        @Table(name = "instructors")
        @Data
        @NoArgsConstructor
        public class Instructor {
          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          @Column(name ="id")
          private int id;

          @Column(name ="instructor_name")
          private String name;

          @Column(name ="instructor_email")
          private String email;
        }
    ```
    
  5. create *instructor_db* schema into your mysql server instance.
  6. add the database configuration to the application.properties as follows:

      ```
      spring.datasource.url = jdbc:mysql://localhost:3306/instructor_db
      spring.datasource.username =root
      spring.datasource.password=root
      
      spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
      spring.jpa.hibernate.ddl-auto=update
      
      ```
7. add *InstructorRepository* that extends *JpaRepository* to generate the implementation automatically using Spring Data JPA module

      ```
      
        public interface InstructorRepository 
                       extends JpaRepository<Instructor, Integer>{

        }
        
      ```
 8. Create the *InstructorService* interface
  ```
     public interface InstructorService {

      public void saveInstructor(Instructor instructor);
      public Instructor getInstructorById(Integer id);

    }
 ```
 
 9. Create the *InstructorServiceImpl* that implement the  *InstructorService* interface and annotate it with *@Service*
 ```
    @Service
    public class InstructorServiceImpl implements InstructorService {

      @Autowired
      private InstructorRepository repo;

      @Override
      public void saveInstructor(Instructor instructor) {
        instructor.setId(0);
        repo.save(instructor);

      }

      @Override
      public Instructor getInstructorById(Integer id) {
        Optional<Instructor> result = repo.findById(id);
        return result.get();
      }

    }
 ```
 
 10. Create *InstructorController* to declare CRUD Endpoints as follows:
 ```
        @RestController
        @RequestMapping("/api/instructor")
        public class InstructorController {

            @Autowired
            private InstructorService service;

            @GetMapping("/{id}")
            public Instructor getInstructorById(@PathVariable int id) {
                return service.getInstructorById(id);
            }

            @PostMapping
            public void saveInstructor(@RequestBody Instructor instructor) {
                service.saveInstructor(instructor);
            }


        }

```

11. start the instructor-service by running the springboot bootstrap class.
12. we can now create an instructor from the postman as follows:
    ![image](https://github.com/shaimaa-hshalaby/Microservice-with-spring-cloud-guide/assets/3264417/447926e8-cd57-4c2a-bcfa-1e23e8838a1e)

## Create CourseDTO Class

we need to retrieve the instructor data nested into the course JSON object instead of retrieve the instructor id as shown below:



    
## Making Synchronous call using RestTemplate



