package com.example.rest_service.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TASK", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
})

public class Task {

    public Task(String name2, String string, String description2, LocalDateTime now) {
        this.name = name2;
        this.state = string;
        this.description = description2;
        this.creationDate = now;
        this.updateDate = now;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 10, max = 20)
    private String name;

    @Size(min = 8, max = 12)
    private String state;

    @NotBlank
    @Size(min = 10, max = 50)
    private String description;

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;
    

}
