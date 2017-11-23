package duckydebug.project.controller;

import duckydebug.project.bindingModel.LogBindingModel;
import duckydebug.project.entity.Answer;
import duckydebug.project.entity.Log;
import duckydebug.project.entity.Question;
import duckydebug.project.repository.AnswerRepository;
import duckydebug.project.repository.LogRepository;
import duckydebug.project.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Controller
public class CreateViewEditController {
    // Declaring repositories
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private AnswerRepository answerRepository;

    private List<Question> questions;

    @GetMapping("/create")
    public String getCreate(Model model){
        questions = questionRepository.findAll();

        // Since this is a demo app, there will be limited set of questions which will be generated on fist initialization
        if(questions.size()==0)
            questions = generateSampleQuestions();

        // Using binding model to extract answer and log data later
        LogBindingModel logBindingModel = new LogBindingModel(questions);

        model.addAttribute("logBindingModel", logBindingModel);
        model.addAttribute("toolbarPageName", "Create Log");
        model.addAttribute("view","views/create");

        return "base-layout";
    }

    @PostMapping("/create")
    public String postCreate(@ModelAttribute LogBindingModel logBindingModel, Model model){

        //Create a new log and save it to the database TODO: Fix DateTime string to a more simple one
        Log log = new Log(logBindingModel.getLogTitle(),
                logBindingModel.getLogDescription(),false,LocalDateTime.now().toString());
        logRepository.saveAndFlush(log);

        //Create answer entities and save them to the database
        for(Question questionBindingModel:logBindingModel.getQuestions()){
            String answerText = questionBindingModel.getAnswer();
            Question question = questions.stream().filter(q->q.getId().equals(questionBindingModel.getId())).findFirst().orElse(null);
            Answer answer = new Answer(answerText,question, log);
            answerRepository.saveAndFlush(answer);
        }

        return "redirect:/";
    }

    @GetMapping("/log/{id}")
    public String getLogView(Model model, @PathVariable Integer id){
        Log log = logRepository.findOne(id);

        if(log != null){
            Set<Answer> answers = log.getAnswers();
            LinkedList<Question> questions = new LinkedList<>();

            for(Answer answer:answers){
                String answerString = answer.getText();
                questions.add(answer.getQuestion());
                questions.getLast().setAnswer(answerString);
            }
            LogBindingModel lbm = new LogBindingModel(questions);

            model.addAttribute("logTitle", log.getTitle());
            model.addAttribute("logDescription",log.getDescription());
            model.addAttribute("logBindingModel",lbm);
            model.addAttribute("toolbarPageName", log.getTitle());
            model.addAttribute("view","views/log");

            return "base-layout";
        }
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
