package duckydebug.project.controller;

import duckydebug.project.bindingModel.LogBindingModel;
import duckydebug.project.entity.Answer;
import duckydebug.project.entity.Log;
import duckydebug.project.entity.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Use this class for variables which are going to be used more than once
 */
public class StaticVariables {
    public static Log log;
    public static LogBindingModel logBindingModel;
    public static Set<Answer> answers;
    public static List<Question> questions;

    /**
     * Clear all instances if needed
     */
    public static void clearInstances(){
        log = null;
        logBindingModel = null;
        answers = null;
        questions = null;
    }
}
