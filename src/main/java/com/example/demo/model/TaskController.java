package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class TaskController {
    private TaskRepository taskRepository;

    @Autowired
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
        model.addAttribute("mode", "add");
        return "addForm";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam long id) {
        Task task = taskRepository.findById(id);
        model.addAttribute("task", task);
        model.addAttribute("mode", "edit");
        return "addForm";
    }

    @GetMapping("/finishNow")
    public String finishNow(@RequestParam long id) {
        Task task = taskRepository.findById(id);
        taskRepository.finishNow(task);
        return "redirect:/";
    }

    @GetMapping("/todolist")
    public String showToDoList(Model model) {
        model.addAttribute("list", taskRepository.showTasksToDo());
        return "list";
    }

    @GetMapping("/donelist")
    public String showDoneTasks(Model model) {
        model.addAttribute("list", taskRepository.showDoneTasks());
        return "archiveDoneList";
    }

    @GetMapping("/outdatedlist")
    public String showOutDateTasks(Model model) {
        model.addAttribute("list", taskRepository.showOutDateTasks());
        return "outdateList";
    }

    @PostMapping("/add")
    public String add(Task task) {
        try {
            task.setCreateOrEditDate(LocalDate.now());
            taskRepository.addNewTask(task);
            return "redirect:/";
        } catch (Exception ex) {
            return "error";
        }
    }

    @PostMapping("/edit")
    public String edit(Task task) {
        task.setCreateOrEditDate(LocalDate.now());
        taskRepository.update(task);
        return "redirect:/";
    }
}
