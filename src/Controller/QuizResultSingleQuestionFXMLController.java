/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Questions;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class QuizResultSingleQuestionFXMLController implements Initializable {

    @FXML
    private Label question;
    @FXML
    private Label option1;
    @FXML
    private Label option2;
    @FXML
    private Label option3;
    @FXML
    private Label option4;
    private  Questions questionObject;
    private String userAnswer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setValues(Questions questionObject, String userAnswer) {
        this.questionObject = questionObject;
        if(userAnswer==null)
            userAnswer = "";
        else
            this.userAnswer = userAnswer;

        setTexts();
    }

    private void setTexts(){
        this.question.setText(this.questionObject.getQuestion());
        this.option1.setText(this.questionObject.getOption1());
        System.out.println(this.questionObject.getOption1());
        this.option2.setText(this.questionObject.getOption2());
        this.option3.setText(this.questionObject.getOption3());
        this.option4.setText(this.questionObject.getOption4());

        settingColors();
    }

    private void settingColors() {
        Label rightAnswer = null;

        if(option1.getText().trim().equalsIgnoreCase(this.questionObject.getAnswer().trim())){
            rightAnswer = option1;
        }
        else if(option2.getText().trim().equalsIgnoreCase(this.questionObject.getAnswer().trim())){
            rightAnswer = option2;
        }
        else if(option3.getText().trim().equalsIgnoreCase(this.questionObject.getAnswer().trim())){
            rightAnswer = option3;
        }
        else if(option4.getText().trim().equalsIgnoreCase(this.questionObject.getAnswer().trim())){
            rightAnswer = option4;
        }
       //rightAnswer.setTextFill(Color.web("#26ae60"));
       rightAnswer.setTextFill(javafx.scene.paint.Color.GREEN );
       rightAnswer.setText("✔ "+rightAnswer.getText());


        if(!userAnswer.trim().equalsIgnoreCase(this.questionObject.getAnswer().trim())){
            Label userAnswer = null;
            if(option1.getText().trim().equalsIgnoreCase(this.userAnswer.trim())){
                userAnswer = option1;
            }
            else if(option2.getText().trim().equalsIgnoreCase(this.userAnswer.trim().trim())){
                userAnswer = option2;
            }

            else if(option3.getText().trim().equalsIgnoreCase(this.userAnswer.trim().trim())){
                userAnswer = option3;
            }          
            else if(option4.getText().trim().equalsIgnoreCase(this.userAnswer.trim().trim())){
                userAnswer = option4;
            }

          userAnswer.setTextFill(Color.web("#B83227"));
            userAnswer.setText("✖ " + userAnswer.getText());
        }

    }
    
}
