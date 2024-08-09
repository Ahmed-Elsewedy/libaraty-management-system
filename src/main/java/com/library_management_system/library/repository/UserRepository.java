package com.library_management_system.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library_management_system.library.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
