/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class AdminHomeScreenFXMLController implements Initializable {

    @FXML
    private JFXTabPane tabPane;
    @FXML
    private Tab addQuiz;
    @FXML
    private Tab EditQuiz;
    @FXML
    private Tab EditQuestions;
    private Tab addQuestion;
    private Tab addResponses;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            Parent nodeQuiz = FXMLLoader.load(getClass().getResource("AddQuizFXML.fxml"));
            Parent nodeEditQuestions = FXMLLoader.load(getClass().getResource("EditQuestionsFXML.fxml"));
            Parent nodeEditQuiz = FXMLLoader.load(getClass().getResource("EditQuizFXML.fxml"));
            addQuiz.setContent(nodeQuiz);
            EditQuiz.setContent(nodeEditQuiz);
            EditQuestions.setContent(nodeEditQuestions);
        }catch(Exception ex ){
            ex.printStackTrace();
        }
    }    
    
}
