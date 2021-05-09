/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Parents;
import Services.ServiceParent;
import Services.ServiceUser;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class AjoutParentController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtphone;
    @FXML
    private Button btnajout;
    private Button btnretour;
    @FXML
    private TextField carte;
    @FXML
    private PasswordField txtpass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void AjouterCompte(ActionEvent event) {
        Parents e = new Parents() ;
        boolean control = true;
        e.setNomP(txtnom.getText());
        e.setPrenomP(txtprenom.getText());
        if ((txtemail.getText()).contains("@gmail.com")){
            e.setEmailP(txtemail.getText());
        }
        else {
            control = false;
            txtemail.setText("");
            System.out.println("Adresse mail invalide !");
        }
        if ((txtphone.getText()).length()==12){
            e.setTelP(txtphone.getText());
        }
        else {
            control = false;
            txtphone.setText("");
            System.out.println("Le numéro de telephone doit etre composé de 12 chiffres et contenir +216 !");
        }
        e.setNumcarteP(carte.getText()); 
        if ((txtpass.getText()).length()<10){
            e.setPasswordP(txtpass.getText());
        }
        else {
            control = false;
            txtpass.setText("");
            System.out.println("La longeur du mot de passe doit etre inferieure à 10");
        }

        if (control == true){
        ServiceParent pr = new ServiceParent() ;
        ServiceUser su = new ServiceUser();
        pr.AjouterCompte(e);
        su.ajouterParent(e);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Compte Ajouté");
            alert.setHeaderText("Votre compte a été créer, vous pouvez vous connecter maintenant");
            alert.showAndWait();
            try {
            Stage stage = (Stage) btnretour.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Motdepasse.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(CreateAccController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
