package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application Class
 * 
 * @SpringBootApplication is a convenience annotation that combines:
 * - @Configuration: Marks this as a configuration class
 * - @EnableAutoConfiguration: Enables Spring Boot's auto-configuration
 * - @ComponentScan: Enables component scanning in this package and sub-packages
 */
@SpringBootApplication
public class UserMicroserviceApplication {

    public static void main(String[] args) {
        // SpringApplication.run() starts the embedded server and initializes Spring context
        SpringApplication.run(UserMicroserviceApplication.class, args);
        System.out.println("🚀 User Microservice started successfully!");
        System.out.println("📊 Access H2 Console: http://localhost:8080/h2-console");
        System.out.println("🔍 Health Check: http://localhost:8080/actuator/health");
    }
}