/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Quiz;
import Services.Quiz_Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class EditQuizFXMLController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private VBox eventcontainer;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private JFXTextField search;
    @FXML
    private Button refreshbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // TODO
            Services.Quiz_Service qs = new Quiz_Service();
                   afficherquiz(qs.afficherquiz());
        } catch (SQLException ex) {
           
        }
    }    
    public void afficherquiz(List<Quiz> quizes) {

        for (int i = 0; i < quizes.size(); i++) {
            Quiz current = quizes.get(i);
            Pane postpane = new Pane();
            Label username = new Label();
            username.setTextFill(Color.BLACK);
            username.setText(quizes.get(i).getTitle());
               username.setStyle("-fx-background-color : transparent;\n"
                        + "	-fx-padding:00px;\n"
                        + "	-fx-background-radius:35px;\n"
                        + "	-fx-font-size:30;\n"
                        + "	-fx-cursor: pointer;");
            Button delete = new Button();
                delete.setVisible(true);
                delete.setText("❌");
                      delete.setOnMouseEntered(e -> {
                          System.out.println("test");
                    delete.setTextFill(Color.RED);
                    delete.setCursor(Cursor.HAND);
                });
                delete.setLayoutY(25);
                delete.setLayoutX(480);
                
                delete.setTextFill(Color.BLACK);
                delete.setStyle("	-fx-background-color : transparent;\n"
                        + "	-fx-padding:00px;\n"
                        + "	-fx-background-radius:35px;\n"
                        + "	-fx-font-size:20;\n"
                        + "	-fx-cursor: pointer;");
          
                
                delete.setOnMouseExited(e -> {
                    delete.setTextFill(Color.BLACK);
                    delete.setCursor(Cursor.DEFAULT);
                });

                delete.setOnMouseClicked(e -> {                  
                try {
                  Quiz_Service ev=  new Quiz_Service();
              
          
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Are You Sure To delete " + current.getTitle());
                    alert.setContentText("");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                       
                            ev.deleteQuiz(current.getQuizId());
                
                        eventcontainer.getChildren().clear();
                        afficherquiz(ev.afficherquiz());
           
                    }
                      }
                     catch (SQLException ex) {
                         System.out.println(ex.getMessage());
                }
                });

 
            postpane.setPrefHeight(56);
            postpane.setPrefWidth(200);
            postpane.setMinHeight(100);
            username.setLayoutX(0);
            username.setLayoutY(0);
        
          
            postpane.getChildren().addAll(username, delete);
            
            eventcontainer.getChildren().add(postpane);
            
        }
        scrollPane.setLayoutY(350);
        scrollPane.setLayoutX(550);
        scrollPane.setPrefWidth(800);
 
        scrollPane.setContent(eventcontainer);
    }

    @FXML
    private void rechercher(KeyEvent event) throws SQLException {
        Quiz_Service ps = new Quiz_Service();
        eventcontainer.getChildren().clear();
        afficherquiz(ps.rechercherquiz(search.getText()));
    }

    @FXML
    private void refresh(MouseEvent event) throws SQLException {
        Quiz_Service ps = new Quiz_Service();
        eventcontainer.getChildren().clear();
        afficherquiz(ps.afficherquiz());
    }
    
}
