package duckydebug.project.controller;

import duckydebug.project.bindingModel.LogBindingModel;
import duckydebug.project.entity.Question;
import duckydebug.project.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CreateController {
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/create")
    public String getCreate(Model model){
        List<Question> questions = questionRepository.findAll();
        if(questions.size()==0)
            questions = generateSampleQuestions();

        LogBindingModel logBindingModel = new LogBindingModel(questions);

        model.addAttribute("logBindingModel", logBindingModel);
        model.addAttribute("toolbarPageName", "Create Log");
        model.addAttribute("view","views/create");

        return "base-layout";
    }

    @PostMapping("/create")
    public String postCreate(@ModelAttribute LogBindingModel logBindingModel, Model model){

        return "redirect:/";
    }


    /**
     * If no questions persist, this method will be invoked
     * @return
     */
    private List<Question> generateSampleQuestions(){
        //Create an array of questions
        String[] questions = {
                "Where does the problem occur?",
                "Do you have any guesses why does it happen?",
                "What have you tried in order to fix it?",
                "What can you try to fix it?",
                "What resources have you checked in attempt to find a solution?",
                "What search keywords did you use?",
                "Is there anything on your mind you still haven't tried?"
        };

        ArrayList<Question> questionEntities = new ArrayList<>();
        for(String question:questions){
            Question questionEntity = new Question(question);
            this.questionRepository.saveAndFlush(questionEntity);
            questionEntities.add(questionEntity);
        }

        return questionEntities;
    }
}
