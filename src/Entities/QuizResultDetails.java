/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author maiez
 */
public class QuizResultDetails {
    private Integer id;
    private Questions question;
    private  String userAnswer ;
    private QuizResult quizResult;
  private  Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Questions getQuestion() {
        return question;
    }

    public void setQuestion(Questions question) {
        this.question = question;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public QuizResult getQuizResult() {
        return quizResult;
    }

    public void setQuizResult(QuizResult quizResult) {
        this.quizResult = quizResult;
    }
    
      public QuizResultDetails(Integer id, Questions question, String userAnswer, QuizResult quizResult) {
        this.id = id;
        this.question = question;
        this.userAnswer = userAnswer;
        this.quizResult = quizResult;
    }

    public QuizResultDetails(Questions question, String userAnswer, QuizResult quizResult) {
        this.question = question;
        this.userAnswer = userAnswer;
        this.quizResult = quizResult;
    }

    public QuizResultDetails(Integer id, Questions question, String userAnswer, QuizResult quizResult, Integer score) {
        this.id = id;
        this.question = question;
        this.userAnswer = userAnswer;
        this.quizResult = quizResult;
        this.score = score;
    }

    public QuizResultDetails() {
    }
}
