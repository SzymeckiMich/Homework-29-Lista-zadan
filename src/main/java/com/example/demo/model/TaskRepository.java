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
    public List<Task> showTasksToDo() {
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t WHERE t.done=false AND t.deadline > CURDATE() ORDER BY t.deadline", Task.class);
        return query.getResultList();
    }

    @Transactional
    public List<Task> showDoneTasks() {
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t WHERE t.done=true ORDER BY t.deadline", Task.class);
        return query.getResultList();
    }

    @Transactional
    public List<Task> showOutDateTasks() {
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t WHERE t.done=false AND t.deadline <= CURDATE() ORDER BY t.deadline", Task.class);
        return query.getResultList();
    }


    //UPDATE
    @Transactional
    public void update(Task task) {
        entityManager.merge(task);
    }


    public Task findById(Long id) {
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t WHERE t.id = " + id, Task.class);
        return query.getSingleResult();
    }


    @Transactional
    public void finishNow(Task task) {
        task.setExecutionTimeInMinutes(0);
        task.setExecutionTimeInHours(0);
        task.setExecutionTimeInDays(0);
        task.setDateOfCompletion(LocalDate.now());
        task.setDone(true);
        update(task);
    }
}
