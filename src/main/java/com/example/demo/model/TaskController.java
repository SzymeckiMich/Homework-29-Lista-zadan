package com.example.demo.model;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TaskController {

    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("task", new Task());
        return "addForm";
    }

    @GetMapping("/todolist")
    public String showToDoList(Model model) {
        model.addAttribute("list", taskRepository.showTasksToDo());
        return "list";
    }

    @GetMapping("/donelist")
    public String showDoneTasks(Model model) {
        model.addAttribute("list", taskRepository.showDoneTasks());
        return "list";
    }

    @GetMapping("/outdatedlist")
    public String showOutDateTasks(Model model) {
        model.addAttribute("list", taskRepository.showOutDateTasks());
        return "list";
    }


    @PostMapping("/add")
    public String add(Task task) {
        taskRepository.addNewTask(task);
        return "success";
    }

    @PostMapping("/edit")
    public String edit(Task task){

    }




}
