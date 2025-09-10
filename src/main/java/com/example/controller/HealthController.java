package com.example.controller;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Health Controller
 * 
 * Provides custom health and status information about our microservice
 */
@RestController
@RequestMapping("/api/health")
public class HealthController {

    @Autowired
    private UserService userService;

    /**
     * GET /api/health/status - Get service status
     */
    @GetMapping("/status")
    public Map<String, Object> getServiceStatus() {
        Map<String, Object> status = new HashMap<>();
        
        status.put("service", "User Microservice");
        status.put("status", "UP");
        status.put("timestamp", LocalDateTime.now());
        status.put("version", "1.0.0");
        
        // Add some business metrics
        try {
            status.put("totalUsers", userService.getUserCount());
            status.put("databaseStatus", "CONNECTED");
        } catch (Exception e) {
            status.put("databaseStatus", "ERROR: " + e.getMessage());
        }
        
        return status;
    }

    /**
     * GET /api/health/info - Get detailed service information
     */
    @GetMapping("/info")
    public Map<String, Object> getServiceInfo() {
        Map<String, Object> info = new HashMap<>();
        
        info.put("application", Map.of(
            "name", "User Microservice",
            "description", "A simple microservice for managing users",
            "version", "1.0.0"
        ));
        
        info.put("features", Map.of(
            "authentication", "Basic Auth",
            "database", "H2 In-Memory",
            "monitoring", "Spring Boot Actuator",
            "security", "Spring Security"
        ));
        
        info.put("endpoints", Map.of(
            "users", "/api/users",
            "health", "/actuator/health",
            "database", "/h2-console"
        ));
        
        return info;
    }
}