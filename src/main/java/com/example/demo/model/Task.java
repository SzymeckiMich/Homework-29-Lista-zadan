package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
//@Table(name = "Zadania")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfCompletion;
    private double executionTimeInDays;
    @Enumerated(value = EnumType.STRING)
    private TaskType type;
    private boolean done;

    public Task() {
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(LocalDate dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public double getExecutionTimeInDays() {
        return executionTimeInDays;
    }

    public void setExecutionTimeInDays(double executionTimeInDays) {
        this.executionTimeInDays = executionTimeInDays;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
