package com.example.rest_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rest_service.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);

    User findByUsername(String userName);
}
