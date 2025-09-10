package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * User Repository Interface
 * 
 * Spring Data JPA automatically implements this interface.
 * No need to write SQL queries for basic operations!
 */
@Repository // Marks this as a repository component
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data JPA automatically creates this method based on the method name
    Optional<User> findByEmail(String email);

    // Find users by first name (case-insensitive)
    List<User> findByFirstNameIgnoreCase(String firstName);

    // Find users by last name containing a string
    List<User> findByLastNameContaining(String lastName);

    // Custom query using @Query annotation
    @Query("SELECT u FROM User u WHERE u.firstName = :firstName AND u.lastName = :lastName")
    List<User> findByFullName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    // Check if user exists by email
    boolean existsByEmail(String email);

    // Count users by first name
    long countByFirstName(String firstName);
}