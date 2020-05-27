package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    TaskRepository taskRepository;

    @RequestMapping("/")
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "list";
    }


}
