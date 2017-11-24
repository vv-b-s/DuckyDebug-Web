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

import java.text.Collator;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class CreateViewEditController {
    // Declaring repositories
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private AnswerRepository answerRepository;

    //Make shortcuts to static variables class
    private List<Question> questions = StaticVariables.questions;
    private Set<Answer> answers = StaticVariables.answers;
    private Log log = StaticVariables.log;



    @GetMapping("/create")
    public String getCreate(Model model){
        List<Question> questions = questionRepository.findAll();

        // Since this is a demo app, there will be limited set of questions which will be generated on fist initialization
        if(questions.size()==0)
            questions = generateSampleQuestions();

        // Using binding model to extract answer and log data later
        LogBindingModel logBindingModel = new LogBindingModel(questions);

        model.addAttribute("postAction","/create");
        model.addAttribute("logBindingModel", logBindingModel);
        model.addAttribute("toolbarPageName", "Create Log");
        model.addAttribute("view","views/createEdit");

        return "base-layout";
    }

    @PostMapping("/create")
    public String postCreate(@ModelAttribute LogBindingModel logBindingModel, Model model){

        //Create a new log and save it to the database TODO: Fix DateTime string to a more simple one
        Log log = new Log(logBindingModel.getLogTitle(),
                logBindingModel.getLogDescription(),false,LocalDateTime.now().toString());
        logRepository.saveAndFlush(log);

        //Create answer entities and save them to the database
        for(Question question:logBindingModel.getQuestions()){
            String answerText = question.getAnswer();
            Answer answer = new Answer(answerText,question, log);
            answerRepository.saveAndFlush(answer);
        }

        return "redirect:/";
    }



    @GetMapping("/log/{id}")
    public String getLogView(Model model, @PathVariable Integer id){
        log = logRepository.findOne(id);

        if(log != null){
            // Get the answers for the log from the database and create list to hold their questions
            answers = log.getAnswers();
            questions = new ArrayList<>();

            //Find the questions for each answers and fill their answer placeholder with the answer text
            for(Answer answer:answers){
                String answerString = answer.getText();
                questions.add(answer.getQuestion());
                questions.get(questions.size()-1).setAnswer(answerString);
            }

            //Sort questions to come in the order of the question ids
            questions.sort(Comparator.comparing(Question::getId));

            model.addAttribute("logTitle", log.getTitle());
            model.addAttribute("logId",log.getId());
            model.addAttribute("logDescription",log.getDescription());
            model.addAttribute("questions",questions);
            model.addAttribute("toolbarPageName", log.getTitle());
            model.addAttribute("view","views/log");

            return "base-layout";
        }
        return "redirect:/";
    }



    @GetMapping("/log/{id}/edit")
    public String getLogEdit(Model model, @PathVariable Integer id){
        // Data will be loaded only if log is set to edit from its view path
        if(log.getId().equals(id)){

            //Fill the logBindingModel with the log data as it will be easy to refer to it
            LogBindingModel logBindingModel = new LogBindingModel(questions);
            logBindingModel.setLogTitle(log.getTitle());
            logBindingModel.setLogDescription(log.getDescription());
            logBindingModel.setLogId(log.getId());

            // send the data to the view
            model.addAttribute("postAction",String.format("/log/%d/edit",id));
            model.addAttribute("logBindingModel", logBindingModel);
            model.addAttribute("toolbarPageName",String.format("Edit: %s",log.getTitle()));
            model.addAttribute("view","views/createEdit");

            return "base-layout";
        }

        return "redirect:/";
    }

    @PostMapping("/log/{id}/edit")
    public String postLogEdit(@ModelAttribute LogBindingModel logBindingModel, @PathVariable Integer id){
        if(id.equals(logBindingModel.getLogId())){
            // Edit editable data in log
            log.setTitle(logBindingModel.getLogTitle());
            log.setDescription(logBindingModel.getLogDescription());
            logRepository.saveAndFlush(log);

            // Add the newly modified answers back to answers
            for(Answer answer:answers){
                String answerString = logBindingModel.getQuestions()
                        .stream().filter(q->q.getId().equals(answer.getQuestion().getId()))
                        .findFirst().orElse(null).getAnswer();
                answer.setText(answerString);
                answerRepository.saveAndFlush(answer);
            }
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
