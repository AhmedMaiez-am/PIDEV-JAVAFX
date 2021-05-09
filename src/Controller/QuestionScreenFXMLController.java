/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Questions;
import Entities.Quiz;
import Entities.QuizResult;
import Entities.Student;
import InterfacesServices.NewScreenListener;
import Services.Quiz_Result_Service;
import Services.Quiz_Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
 import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
 import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class QuestionScreenFXMLController implements Initializable {

     private class QuestionsObservable {
        Property<String> question = new SimpleStringProperty();
        
        Property<String>   option1 = new SimpleStringProperty();
        Property<String>   option2 = new SimpleStringProperty();
        Property<String>   option3 = new SimpleStringProperty();
        Property<String>   option4 = new SimpleStringProperty();
        Property<String>   answer = new SimpleStringProperty();
        public void setQuestion(Questions question) {
           this.question.setValue(question.getQuestion());
           this.option1.setValue(question.getOption1());
           this.option2.setValue(question.getOption2());
           this.option3.setValue(question.getOption3());
           this.option4.setValue(question.getOption4());
           this.answer.setValue(question.getAnswer());
        }
        
    }
     
    @FXML
    private Label title;
    @FXML
    private Label time;
    @FXML
    private Label question;
    @FXML
    private JFXRadioButton option1;
    @FXML
    private ToggleGroup options;
    @FXML
    private JFXRadioButton option2;
    @FXML
    private JFXRadioButton option3;
    @FXML
    private JFXRadioButton option4;
    @FXML
    private JFXButton next;
    @FXML
    private JFXButton submit;
    @FXML
    private FlowPane progressPane;
    private QuestionsObservable questionsObservable;
 //    listeners
    private NewScreenListener screenListener;
    
    public void setQuiz(Quiz quiz) throws SQLException {
        this.quiz = quiz;
        this.title.setText(this.quiz.getTitle());
        this.getData();
    }


    //NON FXML FIELDS
    private Quiz quiz;
    private List<Questions> questionList;
    private Questions currentQuestion;
    int currentIndex = 0;
   // private QuestionsObservable questionsObservable;
    private Map<Questions, String> studentAnswers = new HashMap<>();
    private Integer numberOfRightAnswers = 0;
    private Integer score = 0 ;
    private Student student;
    private int wa9t=10;
    public void setStudent(Student student) {
        this.student = student;
    }

    public void setScreenListener(NewScreenListener screenListener) {
        this.screenListener = screenListener;
    }

    //    timer fields
    private long min, sec, hr, totalSec = 0; //250 4 min 10 sec
    private Timer timer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.showNextQuestionButton();
        this.hideSubmitQuizButton();
        this.questionsObservable= new QuestionsObservable();
        bindFields();
        this.option1.setSelected(true);
    }    
    private String format(long value) {
        if (value < 10) {
            return 0 + "" + value;
        }

        return value + "";
    }
     public void convertTime() {

        min = TimeUnit.SECONDS.toMinutes(totalSec);
        sec = totalSec - (min * 60);
        hr = TimeUnit.MINUTES.toHours(min);
        min = min - (hr * 60);
        time.setText(format(hr) + ":" + format(min) + ":" + format(sec));

        totalSec--;
    }
     private void setTimer() {
        totalSec = this.questionList.size() * 10;
        this.timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        wa9t-=1;
                        System.out.println("After 1 sec...");
                        convertTime();
                        if (totalSec <= 0) {
                            timer.cancel();
                            time.setText("00:00:00");
                            
                            try {
                                // saveing data to database
                                SubmitQuiz(null);
                            } catch (SQLException ex) {
                                Logger.getLogger(QuestionScreenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Notifications.create()
                                    .title("Erreur")
                                    .text("Temps Ecoulé !")
                                    .position(Pos.BOTTOM_RIGHT)
                                    .showError();
                        }
                        if (wa9t<=1){
                           next.fire();
                        }
                    }
                });
            }
        };

        timer.schedule(timerTask, 0, 1000);
        
      
    }
     private void renderProgress(){
           for( int i = 0 ; i < this.questionList.size() ; i++) {
                FXMLLoader fxmlloader =  new FXMLLoader(getClass().getResource("ProgressCircleFXML.fxml"));
                try{
                    Node node = fxmlloader.load();
                    ProgressCircleFXMLController circleFXMLController = fxmlloader.getController();
                    circleFXMLController.setNumber(i+1);
                    circleFXMLController.setDefaultColor();
                    progressPane.getChildren().add(node);
                }catch(IOException e){
                    e.printStackTrace();
                }
                
            }
        }
     private void bindFields(){
        this.question.textProperty().bind(this.questionsObservable.question);
        this.option1.textProperty().bind(this.questionsObservable.option1);
        this.option2.textProperty().bind(this.questionsObservable.option2);
        this.option3.textProperty().bind(this.questionsObservable.option3);
        this.option4.textProperty().bind(this.questionsObservable.option4);
        
    }
    private void getData() throws SQLException{
        Services.Quiz_Service quiz_Service= new Quiz_Service();
        if(quiz != null ){
            this.questionList = quiz_Service.getQuestions(quiz.getQuizId());
            Collections.shuffle(this.questionList); //Random List
            renderProgress();
            setNextQuestion();
            setTimer();
            
        }
    }
    private void setNextQuestion(){
        if(!(currentIndex >= questionList.size())){
            Node circle_node = this.progressPane.getChildren().get(currentIndex);
            {
            ProgressCircleFXMLController controller = (ProgressCircleFXMLController)circle_node.getUserData();
            controller.setCurrentQuestionColor();
            this.currentQuestion = this.questionList.get(currentIndex);
            }
            List<String> options =new ArrayList<>();
            options.add(this.currentQuestion.getOption1());
            options.add(this.currentQuestion.getOption2());
            options.add(this.currentQuestion.getOption3());
            options.add(this.currentQuestion.getOption4());
                Collections.shuffle(options); //Random Option radio
                
            this.currentQuestion.setOption1(options.get(0));
            this.currentQuestion.setOption2(options.get(1));
            this.currentQuestion.setOption3(options.get(2));
            this.currentQuestion.setOption4(options.get(3));
            
          /*  this.question.setText(this.currentQuestion.getQuestion());
            this.option1.setText(options.get(0));
            this.option2.setText(options.get(1));
            this.option3.setText(options.get(2));
            this.option4.setText(options.get(3));*/
          this.questionsObservable.setQuestion(this.currentQuestion);
             
                currentIndex ++ ;
        }
        else {
            hideNextQuestionButton();
            showSubmitQuizButton();
            Notifications.
                    create().
                        title("Pas de questions").
                            text("il n'existe pas d'autre questions").
                                showInformation();
        }
    }
    private void showNextQuestionButton(){
        this.next.setVisible(true);
    }
    private void showSubmitQuizButton(){
        this.submit.setVisible(true);
    }
    private void hideNextQuestionButton(){
        this.next.setVisible(false);
    }
    private void hideSubmitQuizButton(){
        this.submit.setVisible(false);
    }

    @FXML
    private void nextQuestion(ActionEvent event) {
         boolean isRight = false;
        {
            // checking answer
            JFXRadioButton selectedButton = (JFXRadioButton) options.getSelectedToggle();
            String userAnswer = selectedButton.getText();
            String rightAnswer = this.currentQuestion.getAnswer();
            if (userAnswer.trim().equalsIgnoreCase(rightAnswer.trim())) {
                isRight = true;
                this.numberOfRightAnswers++;
            }
            if ( quiz.getIsamericain() == 1 ){
                 if (userAnswer.trim().equalsIgnoreCase(rightAnswer.trim())) {
                score+=1;
              
            }
                 else{
                     score-=1;
                 }
            }
            else{
                score=0;
            }
            // saving Answer to hashMap
            studentAnswers.put(this.currentQuestion, userAnswer);
        }
         Node circleNode = this.progressPane.getChildren().get(currentIndex - 1);
        ProgressCircleFXMLController controller = (ProgressCircleFXMLController) circleNode.getUserData();


        if (isRight) {
            controller.setRightAnsweredColor();
        } else {
            controller.setWrongAnsweredColor();
        }
        this.setNextQuestion();
    }

    @FXML
    private void SubmitQuiz(ActionEvent event) throws SQLException {
        System.out.println(this.studentAnswers);
         Student student = new Student();
        student.setId(1);
        QuizResult quizResult = new QuizResult(this.quiz, student,numberOfRightAnswers);
        Services.Quiz_Result_Service quiz_Result_Service = new Quiz_Result_Service();
        quiz_Result_Service.add_Quiz_Result(this.studentAnswers , this.quiz ,numberOfRightAnswers ,score );
         Notifications.
                    create().
                        title("Quiz terminé").
                            text("Tu as terminer le Quiz avec succés...").
                                showInformation();
         timer.cancel();
         OpenResultQuizScreen();
         hideSubmitQuizButton();
    }
     private void OpenResultQuizScreen(){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().
                getResource("QuizResultScreenFXML.fxml"));

        try {
            Node node = fxmlLoader.load();
            QuizResultScreenFXMLController controller = fxmlLoader.getController();
            controller.setValues(this.studentAnswers , numberOfRightAnswers , quiz , questionList);
            this.screenListener.removeTopScreen();
            this.screenListener.ChangeScreen(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
