/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Maitresse;
import Services.ServiceMaitresse;
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
public class MSmaitresseController implements Initializable {

    @FXML
    private Button dec;
    @FXML
    private TextField txtp;
    @FXML
    private TextField txtm;
    @FXML
    private Button Supp;
    @FXML
    private TextField idup;
    @FXML
    private Button getMaitresse;
    @FXML
    private TextField txtsecname;
    @FXML
    private TextField txtpre;
    @FXML
    private TextField txtemail;
    @FXML
    private Button btModifier;
    @FXML
    private Button btnRecomp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void deconnecter(ActionEvent event) {
        try {
            
            Stage stage = (Stage) dec.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuTuteur.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuTuteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SupprimerM(ActionEvent event) {
        String Nom = txtm.getText();
        String prenom = txtp.getText();
        ServiceMaitresse prc = new ServiceMaitresse();
        Maitresse o = new Maitresse(Nom ,prenom);
        prc.supprimerCompte(o);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succés de suppression");
        alert.setHeaderText("Compte Supprimé");
        alert.setContentText("Votre compte tuteur a été supprimé avec succés !");
        alert.showAndWait();  
    }

    @FXML
    private void getmaitresse(ActionEvent event) {
        ServiceMaitresse prc = new ServiceMaitresse();
        Maitresse m = new Maitresse();
        String p = idup.getText();
    }

    @FXML
    private void confirmeUpdate(ActionEvent event) {
        ServiceMaitresse prc = new ServiceMaitresse();
         Maitresse m = new Maitresse();
        String p = idup.getText();
            m.setCin(p);
            
             m.setNom(txtsecname.getText());
             m.setPrenom(txtpre.getText());
             m.setEmail(txtemail.getText());
             prc.ModifierCompte(m);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Compte Modifié");
                                        alert.setHeaderText("Votre compte a été bien modifié");
                                        
                                        alert.showAndWait(); 
    }

    @FXML
    private void redirectRecomp(ActionEvent event) {
        try {
            Stage stage = (Stage) dec.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
