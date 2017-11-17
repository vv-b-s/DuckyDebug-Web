package duckydebug.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("toolbarPageName","Home");
        model.addAttribute("view","views/home");

        return "base-layout";
    }
}
