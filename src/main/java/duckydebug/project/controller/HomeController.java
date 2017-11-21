package duckydebug.project.controller;

import duckydebug.project.entity.Log;
import duckydebug.project.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private LogRepository logRepository;

    @GetMapping("/")
    public String getHome(Model model){
        List<Log> logs = logRepository.findAll();

        model.addAttribute("logs",logs);
        model.addAttribute("toolbarPageName","Home");
        model.addAttribute("view","views/home");

        return "base-layout";
    }
}