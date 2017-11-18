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
        logs.add(new Log("Log title here","1/01/2017", "A short description", false));
        logs.add(new Log("Log title here","1/01/2017", "A short description", true));
        logs.add(new Log("Log title here","1/01/2017", "A short description", false));


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
    Boolean isCompleted;

    public Log(String title, String creationDate, String description, Boolean isCompleted) {
        this.title = title;
        this.creationDate = creationDate;
        this.description = description;
        this.isCompleted = isCompleted;
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

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
