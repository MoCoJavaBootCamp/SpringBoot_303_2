package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HomeController {
    @Autowired
    TaskRepository taskRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,
                new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true, 10));
    }

    @RequestMapping("/")
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "list";
    }

    @RequestMapping("/add")
    public String taskForm(Model model) {
        model.addAttribute("task", new Task());
        return "taskform";
    }

    @RequestMapping("/process")
    public String processForm(@Valid Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "taskform";
        }
        taskRepository.save(task);
        return "redirect:/";
    }

    @RequestMapping("/update/{id}")
    public String updateTask(@PathVariable("id") long id, Model model) {
        model.addAttribute("task", taskRepository.findById(id).get());
        return "taskform";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") long id) {
        taskRepository.deleteById(id);
        return "redirect:/";
    }
}
