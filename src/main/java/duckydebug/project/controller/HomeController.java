package duckydebug.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model){

        ArrayList<Log> logs = new ArrayList<>();
        logs.add(new Log("Log1","1.1.1", "A log to test it"));
        logs.add(new Log("Log2","1.1.1", "A log to test it"));
        logs.add(new Log("Log3","1.1.1", "A log to test it"));


        model.addAttribute("logs",logs);
        model.addAttribute("toolbarPageName","Home");
        model.addAttribute("view","views/home");

        return "base-layout";
    }
}

class Log{
    String title;
    String creationDate;
    String description;

    public Log(String title, String creationDate, String description) {
        this.title = title;
        this.creationDate = creationDate;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
