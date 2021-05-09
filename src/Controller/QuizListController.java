/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Quiz;
import InterfacesServices.NewScreenListener;
import Services.Quiz_Service;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class QuizListController implements Initializable {

    Map<Quiz, Integer> quizes = null;
    private NewScreenListener screenListener;
    @FXML
    private FlowPane quizListContainer;
    Set<Quiz> keys;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            
            Services.Quiz_Service service = new Quiz_Service();
             
             
             quizes = service.getAllWithQuestionCount();
            keys = quizes.keySet();
        } catch (SQLException ex) {
        }
    }  
     private void setCards() {
        for (Quiz quiz : keys) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QuizCardLayoutFXML.fxml"));
            try {
                Node node = fxmlLoader.load();

                QuizCardLayoutFXMLController cardLayoutFXMLController = fxmlLoader.getController();
               cardLayoutFXMLController.setQuiz(quiz);
                cardLayoutFXMLController.setNoq(quizes.get(quiz) + "");
                 
                cardLayoutFXMLController.setScreenListener(this.screenListener);
                System.out.println("" + quizes.get(quiz));
                quizListContainer.getChildren().add(node);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void setScreenListener(NewScreenListener screenListener) {
        this.screenListener = screenListener;
        setCards();
    }
    
}
