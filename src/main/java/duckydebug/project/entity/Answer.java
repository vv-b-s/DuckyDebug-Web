package duckydebug.project.entity;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;

@Entity
@Table(name = "asnwers")
public class Answer {
    private Integer id;
    private String text;
    private Log log;
    private Question question;

    public Answer(String text, Question question,Log log) {
        this.text = text;
        this.log = log;
        this.question = question;
    }

    public Answer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(columnDefinition = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne()
    @JoinColumn(nullable = false, name="logID")
    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    @ManyToOne()
    @JoinColumn(nullable = false, name="questionID")
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
