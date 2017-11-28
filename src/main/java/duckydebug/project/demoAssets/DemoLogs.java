package duckydebug.project.demoAssets;

import duckydebug.project.entity.Answer;
import duckydebug.project.entity.Log;
import duckydebug.project.repository.AnswerRepository;
import duckydebug.project.repository.LogRepository;
import duckydebug.project.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class DemoLogs {
    private static LinkedHashMap<String, String> getDemoLogsAndDescriptions(){
        LinkedHashMap<String, String> outMap = new LinkedHashMap<>();
        outMap.put("CSS not overriding my div",
                "Using material.io designs and having issues with overriding the provided mdc-toolbar CSS class. I need to change " +
                        "the background but no success with that...");
        outMap.put("Android Studio lint warning about Activity memory leak",
                "My app works fine though it's a bad practice to use static references to activities because it prevents the onDestroy" +
                        " method to get invoked which causes memory leakage.");
        outMap.put("ORM Sugar gives me SQLiteException",
                "Having problem with executing query to find all related answers with a log. Getting an error message saying \"No such column\".");

        return outMap;
    }

    public static void createDemoLogs(LogRepository logRepository, QuestionRepository questionRepository, AnswerRepository answerRepository){
        LinkedHashMap<String, String> demoLogStrings = getDemoLogsAndDescriptions();

        //Each log will randomly be completed or not
        Random rand = new Random();

        // Get the demo answers linked with questions to them
        ArrayList<ArrayList<Answer>> answerSets = DemoAnswers.getAnswersWithQuestions(questionRepository.findAll());

        //Use this index to map logs with different answers
        int answerSetIndex = 0;

        //Bind log strings with log entities
        for(Map.Entry<String, String> demoLog:demoLogStrings.entrySet()){
            Log log = new Log(demoLog.getKey(),demoLog.getValue(), rand.nextBoolean(), LocalDateTime.now().toString());

            logRepository.saveAndFlush(log);

            // Each log has its own set of answers
            ArrayList<Answer> answers = answerSets.get(answerSetIndex);

            //Change the answer's innitial log to match the current log
            for(Answer answer:answers) answer.setLog(log);
            answerRepository.save(answers);
            answerRepository.flush();

            answerSetIndex++;
        }
    }
}
