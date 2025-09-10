# User Microservice - Spring Boot Learning Project

A simple microservice demonstrating core Spring Boot concepts for Java beginners.

## 🎯 What You'll Learn

This project demonstrates:
- **Auto-configuration**: Spring Boot automatically configures components
- **Embedded Server**: No need to deploy to external Tomcat
- **Starter Dependencies**: Pre-configured dependency bundles
- **RESTful APIs**: Using `@RestController`
- **Dependency Injection**: Using `@Autowired`
- **Spring Data JPA**: Database operations without SQL
- **Spring Security**: Basic authentication
- **Configuration**: Using `application.properties`
- **Monitoring**: Spring Boot Actuator

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Running the Application

1. **Clone and navigate to the project**
```bash
cd user-microservice
```

2. **Run the application**
```bash
mvn spring-boot:run
```

3. **The service will start on port 8080**
```
🚀 User Microservice started successfully!
📊 Access H2 Console: http://localhost:8080/h2-console
🔍 Health Check: http://localhost:8080/actuator/health
```

## 🔧 Key Components Explained

### 1. Auto-Configuration Magic
```java
@SpringBootApplication  // This single annotation does it all!
public class UserMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserMicroserviceApplication.class, args);
    }
}
```

### 2. RESTful API with @RestController
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping          // GET /api/users
    @PostMapping         // POST /api/users
    @PutMapping("/{id}")  // PUT /api/users/1
    @DeleteMapping("/{id}") // DELETE /api/users/1
}
```

### 3. Dependency Injection with @Autowired
```java
@Service
public class UserService {
    
    @Autowired  // Spring automatically injects this!
    private UserRepository userRepository;
}
```

### 4. Spring Data JPA - No SQL Required!
```java
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring creates these methods automatically!
    Optional<User> findByEmail(String email);
    List<User> findByFirstNameIgnoreCase(String firstName);
}
```

## 🌐 API Endpoints

### Authentication
- **Username**: `admin`
- **Password**: `admin123`

### User Management
```http
GET    /api/users              # Get all users
GET    /api/users/{id}         # Get user by ID
POST   /api/users              # Create new user
PUT    /api/users/{id}         # Update user
DELETE /api/users/{id}         # Delete user
GET    /api/users/search?firstName=John  # Search users
```

### Health & Monitoring
```http
GET /actuator/health           # Spring Boot health check
GET /api/health/status         # Custom service status
GET /api/health/info          # Service information
```

## 📝 Example API Calls

### Create a User
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "password": "password123"
  }'
```

### Get All Users (with authentication)
```bash
curl -u admin:admin123 http://localhost:8080/api/users
```

### Search Users
```bash
curl -u admin:admin123 "http://localhost:8080/api/users/search?firstName=John"
```

## 🗄️ Database Access

The application uses H2 in-memory database:
- **URL**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:userdb`
- **Username**: `sa`
- **Password**: `password`

## 📊 Configuration (application.properties)

```properties
# Server runs on port 8080
server.port=8080

# H2 database configuration
spring.datasource.url=jdbc:h2:mem:userdb

# JPA shows SQL queries (great for learning!)
spring.jpa.show-sql=true

# Security credentials
spring.security.user.name=admin
spring.security.user.password=admin123

# Actuator endpoints for monitoring
management.endpoints.web.exposure.include=health,info,metrics
```

## 🔍 Spring Boot Concepts in Action

### 1. Starter Dependencies (pom.xml)
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <!-- Includes: Tomcat, Spring MVC, Jackson JSON -->
</dependency>
```

### 2. Embedded Server
- No need to install Tomcat separately
- Application runs with `java -jar` command
- Perfect for microservices and containers

### 3. Component Scanning
- `@Service`, `@Repository`, `@Controller` are automatically detected
- Spring creates and manages these objects (beans)
- Dependency injection happens automatically

### 4. Data Access Layer
- `JpaRepository` provides CRUD operations
- Method names like `findByEmail` create queries automatically
- No need to write SQL for basic operations

### 5. Security Integration
- Basic authentication with minimal configuration
- Password encryption with BCrypt
- Endpoint-level security rules

## 🎓 Learning Path

1. **Start Here**: Run the application and explore the H2 console
2. **API Testing**: Use the provided curl commands
3. **Code Reading**: Study each class and its annotations
4. **Experimentation**: Add new fields to User entity
5. **Extension**: Add new endpoints or business logic

## 🔧 Next Steps

To extend this microservice:
- Add user roles and permissions
- Implement JWT authentication
- Add input validation
- Create unit tests
- Add Docker support
- Implement caching with Redis

## 📚 Key Annotations Reference

- `@SpringBootApplication`: Main application class
- `@RestController`: REST API controller
- `@Service`: Business logic layer
- `@Repository`: Data access layer
- `@Entity`: JPA database entity
- `@Autowired`: Dependency injection
- `@GetMapping/@PostMapping`: HTTP method mapping
- `@PathVariable/@RequestParam`: Extract URL parameters
- `@RequestBody`: Parse JSON request body

This microservice demonstrates production-ready patterns while remaining simple enough for beginners to understand!