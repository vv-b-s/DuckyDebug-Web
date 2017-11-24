package duckydebug.project.controller;

import duckydebug.project.entity.Answer;
import duckydebug.project.entity.Log;
import duckydebug.project.repository.AnswerRepository;
import duckydebug.project.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    private LogRepository logRepository;
    @Autowired
    AnswerRepository answerRepository;

    @GetMapping("/")
    public String getHome(Model model){
        List<Log> logs = logRepository.findAll();

        StaticVariables.clearInstances();

        model.addAttribute("logs",logs);
        model.addAttribute("toolbarPageName","Home");
        model.addAttribute("view","views/home");

        return "base-layout";
    }

    /**
     * This method is called when a checkmark on a log page is clicked and if the log exist will
     * either check it as finished / unfinished according to its previous state
     * @param id
     * @return
     */
    @GetMapping("/log/{id}/ch-marked")
    public String getLogCheckMarked(@PathVariable Integer id){
        Log log = logRepository.findOne(id);
        if(log != null){
            log.setIsCompleted(!log.getIsCompleted());
            logRepository.saveAndFlush(log);
        }

        return "redirect:/";
    }

    /**
     * Delete a log and its associated data
     * @param id
     * @return
     */
    @GetMapping("/log/{id}/delete")
    public String getLogDelete(@PathVariable Integer id){
        Log log = logRepository.findOne(id);

        if(log!=null){
            Set<Answer> answers = log.getAnswers();
            answerRepository.deleteInBatch(answers);
            logRepository.delete(log);
        }

        return "redirect:/";
    }
}