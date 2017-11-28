package duckydebug.project.demoAssets;

import duckydebug.project.entity.Question;
import duckydebug.project.repository.QuestionRepository;

import java.util.ArrayList;

public class DemoQuestions {
    private static String[] questions = {
            "Where does the problem occur?",
            "Do you have any guesses why does it happen?",
            "What have you tried in order to fix it?",
            "What can you try to fix it?",
            "What resources have you checked in attempt to find a solution?",
            "What search keywords did you use?",
            "Is there anything on your mind you still haven't tried?"
    };

    /**
     * Create demo questions if required
     * @param questionRepository
     */
    public static void createDemoQuestions(QuestionRepository questionRepository){
        for(String question:questions){
            Question questionEntity = new Question(question);
            questionRepository.saveAndFlush(questionEntity);
        }
    }
}
