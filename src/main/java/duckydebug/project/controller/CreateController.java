package duckydebug.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreateController {
    @GetMapping("/create")
    public String getCreate(Model model){

        model.addAttribute("toolbarPageName", "Create Log");
        model.addAttribute("view","views/create");

        return "base-layout";
    }

    @PostMapping("/create")
    public String postCreate(Model model){
        //TODO: Implemet Create POST
        return "redirect:/";
    }
}
