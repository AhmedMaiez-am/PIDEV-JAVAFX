/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;
import java.util.Date;
/**
 *
 * @author maiez
 */
public class QuizResult {
    private Integer id ;
    private  Quiz quiz ;
    private  Student student ;
    private Integer rightAnswers ;
    private Timestamp timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(Integer rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
     public QuizResult() {
    }
    {
        timestamp = new Timestamp(new Date().getTime());
    }
    public QuizResult(Integer id, Quiz quiz, Student student, Integer rightAnswers) {
        this.id = id;
        this.quiz = quiz;
        this.student = student;
        this.rightAnswers = rightAnswers;
    }

    public QuizResult(Quiz quiz, Student student, Integer rightAnswers) {
        this.quiz = quiz;
        this.student = student;
        this.rightAnswers = rightAnswers;
    }
}
