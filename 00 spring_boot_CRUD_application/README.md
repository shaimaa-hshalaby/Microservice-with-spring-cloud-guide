# Guide to create Springboot CRUD application with REST APIs


### Create Initial Springboot project

1. navitage to springboot intializer to generate the intial springboot project.
3. create a project called course-service and choose the following configuration for this exercise
      project -> Maven
      Language -> Java
      Spring boot version -> 2.7.12
      packaging -> jar
      java version -> 11
      
3. add the following dependencies
      Spring Web --> To be able to create REST APIs
      Spring Data JPA --> to use JPA as the domain layer
      MySQL Driver
      Lombok (Optional) --> to reduce the boilerplate code
      Spring Boot DevTools (Optional) --> for fast application restart

![image](https://github.com/shaimaa-hshalaby/Microservice-with-spring-cloud-guide/assets/3264417/97a001ec-98fb-44f0-a2c5-3f7519dba671)

4. import the generated project into your IDE

### Create The domain Layer
we need here to create the Entities classes that map to the database tables and the Repository Interface for Each Entity that extends JpaRepository to obtain the CRUD operations
implemented automatically by the spring data jpa artifact.

1. Create Course Entity Class and we can use Lombok annotations to generate constructors, setters and getters method automatically as shown below:
    ```
    @Data
    @NoArgsConstructor
    @Entity
    @Table(name = "courses")
    public class Course {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int id;

      @Column(name = "course_title")
      private String courseTitle;

      @Column(name = "course_description")
      private String courseDescription;

      @Column(name = "instructor_id")
      private int instructorId;

    }
    ```
