/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Quiz;
import Entities.Student;
import InterfacesServices.NewScreenListener;
import Services.Quiz_Service;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class QuizCardLayoutFXMLController implements Initializable {

    @FXML
    private AnchorPane color;
    @FXML
    private Label title;
    @FXML
    private Label noq;
    @FXML
    private JFXButton startButton;
    private Quiz quiz;
    
    private NewScreenListener screenListener;
    private Student student;
    @FXML
    private Label lbl;

    public void setStudent(Student student) {
        this.student = student;
    }

      public void setScreenListener(NewScreenListener screenListener) {
        this.screenListener = screenListener;
    }
    public void setQuiz(Quiz quiz) {
        System.out.println(quiz.getIsamericain());
        this.quiz = quiz;
        this.title.setText(this.quiz.getTitle());
         if ( quiz.getIsamericain()== 1 ){
              color.setStyle("-fx-background-color: Aqua");
              lbl.setText("Quiz Americain");
          }
         else{
             color.setStyle("-fx-background-color: BlueViolet");
             lbl.setText("Quiz Français");
 
         }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
           
            // TODO
            Services.Quiz_Service service = new Quiz_Service();
            
            
            System.out.println("aaaaaaaaaaaaaaa"+service.getQuestions(23));
        } catch (SQLException ex) {
         }
    }    
    public void setNoq(String  value) {
        this.noq.setText(value);
    }

    @FXML
    private void startQuiz(ActionEvent event) throws SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().
                getResource("QuestionScreenFXML.fxml"));

        try {
            Node node = fxmlLoader.load();
            QuestionScreenFXMLController questionScreenFXMLController = fxmlLoader.getController();
            questionScreenFXMLController.setStudent(this.student);
            questionScreenFXMLController.setQuiz(this.quiz);
           questionScreenFXMLController.setScreenListener(this.screenListener);
            this.screenListener.ChangeScreen(node);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
