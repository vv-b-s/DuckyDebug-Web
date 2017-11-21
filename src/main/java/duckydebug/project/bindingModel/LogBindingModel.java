package duckydebug.project.bindingModel;

import duckydebug.project.entity.Question;

import java.util.List;

public class LogBindingModel {
    private List<Question> questions;

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
}
