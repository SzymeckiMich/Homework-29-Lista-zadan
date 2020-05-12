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


    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping
    public String add(Model model) {
        model.addAttribute("task", new Task());
        return "addForm";
    }
    @GetMapping("/todo")
    public String showToDoList(Model model){
        model.addAttribute("todolist", taskRepository.showTasksToDO());
        return "list";
    }

    @PostMapping("/add")
    public String add(Task task) {
        taskRepository.addNewTask(task);
        return "success";
    }


}
