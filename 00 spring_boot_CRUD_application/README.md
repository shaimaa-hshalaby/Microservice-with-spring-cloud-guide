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
5. add application name and server port configuration to the application.properties file
      ```
      spring.application.name = course-service
      server.port = 8090
      ```
      
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
    
 2. add the database configuration to the application.properties as follows

      ```
      
      spring.datasource.url = jdbc:mysql://localhost:3306/courses_db
      spring.datasource.username =root
      spring.datasource.password=root
      
      ```
      
 3. add the following properties to the application.properties to force Spring Data jpa artifact to generate the schema from the Entities classes

      ```
      spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQLDialect
      spring.jpa.hibernate.ddl-auto=update
      
      ```
      
  4. Add CourseRepository interface that extends JpaRepository to obtain CRUD operations implemented automatically, you don't need to implement any method.

       ```
            public interface CourseRepository 
                                    extends JpaRepository<Course, Integer> {

            }
       ```

### Create The Service Layer

1. create CourseService interface that has the signature of all CRUD methods

      ```
      public interface CourseService {

            public Course getCourseById(int id);
            public List<Course> getAllCourses();
            public void createCourse(Course course);
            public void updateCourse(Course course);
            public void deleteCourse(int id);

      }
      ```

2. Create an implementation of the CourseService interface annotated with @Service

      ```
      @Service
      public class CourseServiceImpl implements CourseService {

            @Autowired
            private CourseRepository courseRepository;

            @Override
            public Course getCourseById(int id) {
                  Optional<Course> result= courseRepository.findById(id);
                  return result.get();
            }

            @Override
            public List<Course> getAllCourses() {
                  List<Course> courses = courseRepository.findAll();
                  return courses;
            }

            @Override
            public void createCourse(Course course) {
                  course.setId(0);
                  courseRepository.save(course);
            }

            @Override
            public void updateCourse(Course course) {
                  courseRepository.save(course);
            }

            @Override
            public void deleteCourse(int id) {
                  courseRepository.deleteById(id);
            }
      ```
	
      
      ### Create The Controller Layer
      
      1. create CourseController class that has the different requests handlers as follows

```
      @RestController
      @RequestMapping("/api/course")
      public class CourseController {

            @Autowired
            private CourseService courseService;

            @GetMapping
            public List<Course> getAllCourses() {
                  return courseService.getAllCourses();
            }

            @GetMapping("/{id}")
            public Course getCourseById(@PathVariable int id) {
                  return courseService.getCourseById(id);
            }

            @PostMapping
            public void createCourse(@RequestBody Course course) {
                  courseService.createCourse(course);
            }

            @PutMapping
            public void updateCourse(@RequestBody Course course) {
                  courseService.updateCourse(course);
            }

            @DeleteMapping("{/id}")
            public void deleteCourse(@PathVariable int id) {
                  courseService.deleteCourse(id);
            }


```

