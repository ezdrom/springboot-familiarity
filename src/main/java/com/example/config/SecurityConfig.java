package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security Configuration
 * 
 * This configures Spring Security for our microservice.
 * @Configuration marks this as a configuration class
 * @EnableWebSecurity enables Spring Security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configure HTTP Security
     * This method defines which endpoints require authentication
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF for REST APIs (since we're not using sessions)
            .csrf(csrf -> csrf.disable())
            
            // Configure authorization rules
            .authorizeHttpRequests(authz -> authz
                // Allow access to H2 console (for development)
                .requestMatchers("/h2-console/**").permitAll()
                
                // Allow access to Actuator health endpoint
                .requestMatchers("/actuator/health").permitAll()
                
                // Allow access to create user endpoint (registration)
                .requestMatchers("POST", "/api/users").permitAll()
                
                // All other requests require authentication
                .anyRequest().authenticated()
            )
            
            // Use HTTP Basic authentication (simple for microservices)
            .httpBasic(basic -> {})
            
            // Allow frames for H2 console
            .headers(headers -> headers.frameOptions().disable());

        return http.build();
    }

    /**
     * Password Encoder Bean
     * BCrypt is a strong hashing algorithm for passwords
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}