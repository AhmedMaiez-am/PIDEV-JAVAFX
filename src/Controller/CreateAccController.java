/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class CreateAccController implements Initializable {

    @FXML
    private Button btnParent;
    @FXML
    private Button btnTut;
    @FXML
    private Button btnRetour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void redirecParent(ActionEvent event) {
        try {
            Stage stage = (Stage) btnParent.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjoutParent.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutParentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirecTuteur(ActionEvent event) {
        try {
            Stage stage = (Stage) btnTut.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Maitresse.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MaitresseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirectRetour(ActionEvent event) {
        try {
            Stage stage = (Stage) btnRetour.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Motdepasse.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MotdepasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
