package duckydebug.project.bindingModel;

import duckydebug.project.entity.Question;

import java.util.List;

public class LogBindingModel {
    private List<Question> questions;
    private String logTitle;
    private String logDescription;

    public LogBindingModel(List<Question> questions) {
        this.questions = questions;
    }

    public LogBindingModel() {
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getLogTitle() {
        return logTitle;
    }

    public void setLogTitle(String logTitle) {
        this.logTitle = logTitle;
    }

    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }
}
