/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Enfant;
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
public class MSenfantController implements Initializable {

    @FXML
    private TextField nomsup;
    @FXML
    private TextField prenomsup;
    @FXML
    private Button btnsupp;
    @FXML
    private TextField txtNomE;
    @FXML
    private TextField txtPrenomE;
    @FXML
    private TextField txtAge;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnretour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SupprimeEnfant(ActionEvent event) {
        ServiceParent pr = new ServiceParent() ;
         String Nom = nomsup.getText();
         String prenom = prenomsup.getText();
         
        Enfant e = new Enfant() ;
        e.setNomEnfant(Nom);
        e.setPrenomEnfant(prenom);
        pr.SupprimerMonEnfant(e);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succés de suppression");
        alert.setHeaderText("Compte Supprimé");
        alert.setContentText("Votre compte enfant a été supprimé avec succés !");
        alert.showAndWait();  
    }

    @FXML
    private void modifierEnfant(ActionEvent event) {
        ServiceParent pc = new ServiceParent();
        
        Enfant e = new Enfant();
        e.setNomEnfant(txtNomE.getText());
        e.setPrenomEnfant(txtPrenomE.getText());
        e.setAge(txtAge.getText());
        
        pc.modifEnfant(e);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succés de modification");
        alert.setHeaderText("Compte Modifié");
        alert.setContentText("Votre compte enfant a été modifié avec succés !");
        alert.showAndWait();  
    }

    @FXML
    private void redirection(ActionEvent event) {
        try {
            Stage stage = (Stage) btnretour.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuEnfant.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouteEnfantsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
