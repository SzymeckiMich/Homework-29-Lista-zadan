package com.example.demo.model;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

@Repository
public class TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //CREATE
    @Transactional
    public void addNewTask(Task task) {
        entityManager.persist(task);
    }

    //READ
    @Transactional
    public List<Task> showTasksToDO() {
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t WHERE t.done=false AND t.deadline > CURDATE() ORDER BY t.deadline", Task.class);
        return query.getResultList();
    }

    //UPDATE
    @Transactional
    public void update(Task taskToEdit, Task changed) {
        taskToEdit.setDescription(changed.getDescription());
        taskToEdit.setDeadline(changed.getDeadline());
        taskToEdit.setDateOfCompletion(changed.getDateOfCompletion());
        taskToEdit.setExecutionTimeInDays(changed.getExecutionTimeInDays());
        taskToEdit.setType(changed.getType());
        taskToEdit.setDone(changed.isDone());
        boolean deprecated = changed.getDeadline().isBefore(LocalDate.now());
    }
}
