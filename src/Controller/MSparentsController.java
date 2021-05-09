/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Parents;
import Services.ServiceParent;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class MSparentsController implements Initializable {

    @FXML
    private TextField supemail;
    @FXML
    private TextField suppass;
    @FXML
    private Button btnsup;
    @FXML
    private TextField txtpasspar;
    @FXML
    private TextField txtcinpa;
    @FXML
    private TextField txtemailp;
    @FXML
    private TextField txtpren;
    @FXML
    private TextField txtnomp;
    @FXML
    private Button btnmodif;
    @FXML
    private Button retourtb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SupprimerCompte(ActionEvent event) {
        String mail = supemail.getText();
        String pass = suppass.getText();
         Parents e = new Parents() ;
         e.setEmailP(mail);
         e.setPasswordP(pass);
          ServiceParent pr = new ServiceParent() ;
          pr.SupprimerMonCompte(e);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succés de suppression");
        alert.setHeaderText("Compte Supprimé");
        alert.setContentText("Votre compte parent a été supprimé avec succés !");
        alert.showAndWait();  
    }

    @FXML
    private void modifierComp(ActionEvent event) {
        ServiceParent pc = new ServiceParent();
        
        Parents p = new Parents();
        p.setNomP(txtnomp.getText());
        p.setPrenomP(txtpren.getText());
        p.setEmailP(txtemailp.getText());
        p.setTelP(txtcinpa.getText());
        p.setPasswordP(txtpasspar.getText());
        
        pc.modifParent(p);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succés de modification");
        alert.setHeaderText("Compte modifié");
        alert.setContentText("Votre compte parent a été modifié avec succés !");
        alert.showAndWait();  
    }

    @FXML
    private void redirec(ActionEvent event) {
        try {
            Stage stage = (Stage) retourtb.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuParent.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuParentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
