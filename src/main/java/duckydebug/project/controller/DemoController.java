package duckydebug.project.controller;

import duckydebug.project.demoAssets.DemoLogs;
import duckydebug.project.demoAssets.DemoQuestions;
import duckydebug.project.entity.DatabaseStatus;
import duckydebug.project.entity.Question;
import duckydebug.project.repository.AnswerRepository;
import duckydebug.project.repository.DatabaseStatusRepository;
import duckydebug.project.repository.LogRepository;
import duckydebug.project.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DemoController {

    /**
     * Create demo logs if database is opened for the fist time
     * @param databaseStatusRepository
     * @param logRepository
     * @param questionRepository
     * @param answerRepository
     */
    public static void createDemoContent(DatabaseStatusRepository databaseStatusRepository, LogRepository logRepository, QuestionRepository questionRepository, AnswerRepository answerRepository){

        //Checking if database is new
        long databaseCount = databaseStatusRepository.count();
        if(databaseCount==0){
            //Creating new database status and saving it to the table
            DatabaseStatus dbs = new DatabaseStatus();
            databaseStatusRepository.saveAndFlush(dbs);

            //creating demo content
            DemoQuestions.createDemoQuestions(questionRepository);
            DemoLogs.createDemoLogs(logRepository,questionRepository,answerRepository);

        }else
            System.out.println("Database not new. No need for demo content creation.");
    }
}
