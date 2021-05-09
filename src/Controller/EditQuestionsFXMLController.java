/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Questions;
import Services.Questions_Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class EditQuestionsFXMLController implements Initializable {

    @FXML
    private ComboBox<String> question;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField ques;
    @FXML
    private JFXTextField o1;
    @FXML
    private JFXTextField o2;
    @FXML
    private JFXTextField o3;
    @FXML
    private JFXTextField o4;
    @FXML
    private JFXTextField ans;
    @FXML
    private JFXButton update;
    @FXML
    private JFXButton delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Services.Questions_Service zz = new Questions_Service();
              for (int i = 0; i < zz.afficherquestions().size(); i++) {
                       
       
                    question.getItems().addAll(zz.afficherquestions().get(i).getQuestionId()+"  -  "+zz.afficherquestions().get(i).getQuestion());
           

                       }

       
        } catch (SQLException ex) {
          
        }
        
        id.setVisible(false);
    }    

    @FXML
    private void value(ActionEvent event) {
        boolean isMyComboBoxEmpty = question.getSelectionModel().isEmpty();
        if (isMyComboBoxEmpty){
            System.out.println(question.getSelectionModel().getSelectedItem());
        }
        else{
            System.out.println(question.getSelectionModel().getSelectedItem());
        }
    String str;
          str = question.getSelectionModel().getSelectedItem().replaceAll("\\D+","");
        
          id.setText(str);
    }

    @FXML
    private void updateQuestion(ActionEvent event) throws SQLException {
        try {
            Questions_Service questions_Service = new Questions_Service();
            Questions questions = new Questions();
            questions.setQuestion(ques.getText());
            questions.setOption1(o1.getText());
            questions.setOption2(o2.getText());
            questions.setOption3(o3.getText());
            questions.setOption4(o4.getText());
            questions.setAnswer(ans.getText());
            questions.setQuestionId(Integer.valueOf(id.getText()));
            questions_Service.updateQuestion(Integer.valueOf(id.getText()), questions);
            System.out.println("Info : La question avec l'ID : "+id.getText()+" " +"a été modifier");
            Notifications notification = Notifications.create();
            notification.text(" Modification du Quiz ");
            notification.title("Question a été modifier");
            notification.darkStyle();
            notification.position(Pos.CENTER);
            notification.hideAfter(Duration.millis(1500));
            notification.showInformation();
            question.getItems().clear();
              for (int i = 0; i < questions_Service.afficherquestions().size(); i++) {
                       
                
                    question.getItems().addAll(questions_Service.afficherquestions().get(i).getQuestionId()+"  -  "+questions_Service.afficherquestions().get(i).getQuestion());
           

                       }
        } catch (SQLException ex) {
             System.out.println("Erreur : Question n'a pas été modifier");
             
    }}

    @FXML
    private void deleteQuestion(ActionEvent event) throws SQLException {
        Questions_Service questions_Service = new Questions_Service();
         try {
            
            questions_Service.deleteQuestion(Integer.valueOf(id.getText()));
            System.out.println("Info : La question avec l'ID : "+id.getText()+" " +"a été supprimer");
        } catch (SQLException ex) {
             System.out.println("Erreur : n'a pas été supprimer !");
        }
         question.getItems().clear();
        for (int i = 0; i < questions_Service.afficherquestions().size(); i++) {

            question.getItems().addAll(questions_Service.afficherquestions().get(i).getQuestionId() + "  -  " + questions_Service.afficherquestions().get(i).getQuestion());

        }
          Notifications notification = Notifications.create();
            notification.text(" Question Supprimé ");
            notification.title("Question a été supprimer avec succés !");
            notification.darkStyle();
            notification.position(Pos.CENTER);
            notification.hideAfter(Duration.millis(1500));
            notification.showInformation();
    }
    
}
