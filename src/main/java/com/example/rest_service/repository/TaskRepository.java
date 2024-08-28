package com.example.rest_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rest_service.models.Task;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task findByName(String name);

    List<Task> findAllByOrderByCreationDateDesc();
}
