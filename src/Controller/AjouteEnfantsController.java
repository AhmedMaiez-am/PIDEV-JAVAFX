/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Enfant;
import Services.ServiceParent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class AjouteEnfantsController implements Initializable {

    @FXML
    private Button btnsc;
    @FXML
    private TextField txtnomEnf1;
    @FXML
    private TextField txtage;
    @FXML
    private TextField txtprenomEnf;
    @FXML
    private TextField txtnomEnf;
    @FXML
    private Button btnAjoutE;
    @FXML
    private PasswordField txtmps1;
    @FXML
    private PasswordField txtmps;
    @FXML
    private Button btnImg;
    @FXML
    private AnchorPane p1;
    @FXML
    private Button btnInsc;
    @FXML
    private AnchorPane p2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void connecterEnfant(ActionEvent event) {
        String rNom = txtnomEnf1.getText();
            String rprenom = txtmps1.getText();
            Enfant p = new Enfant(rNom,rprenom);
            //p.setLogin(rNom);
           // p.setPassword(rprenom);
            ServiceParent prc = new ServiceParent();
            if(prc.seConnecter(p)) {
                                                 
            try {
            Stage stage = (Stage) btnsc.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuEnfant.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeCours.class.getName()).log(Level.SEVERE, null, ex);
        }
            //Afficher Personne
    } else { 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur");
        alert.setHeaderText("Compte introuvable");
        alert.setContentText("Vérifier vos paramètres de connection !");
        alert.showAndWait();  
    
    
}
    }

    @FXML
    private void AjoutEnfant(ActionEvent event) {
        boolean control = true;
         Enfant e = new Enfant() ;
        e.setNomEnfant(txtnomEnf.getText());
        e.setPrenomEnfant(txtprenomEnf.getText());
        if ((txtage.getText()).length()<=2){
            e.setAge(txtage.getText());
        }
        else {
            control = false;
            txtage.setText("");
            System.out.println("Age n'est pas valide !");
        }
        if ((txtmps.getText()).length()<10){
            e.setMotdepasse(txtmps.getText());
        }
        else {
            control = false;
            txtmps.setText("");
            System.out.println("La longueur de la mot de passe doit etre inferieur à 10");
        }
        e.setImg(btnImg.getText());
        if (control == true){
        ServiceParent pr = new ServiceParent() ;
        pr.AjouterEnfant(e);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("hello Parents");
                                        alert.setHeaderText("Votre enfant a été bien inscrit !");
                                        
                                        alert.showAndWait(); }
        p2.setVisible(false);
        p1.setVisible(true);
    }

   

    /* To move
    @FXML
    private void redirection(ActionEvent event) {
        try {
            Stage stage = (Stage) help.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Paiement.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(PaiementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    @FXML
    private void ajouterImg(ActionEvent event) {
        Stage primary = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selectionner une image");

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(primary);
        String path = "C:\\Users\\maiez\\Desktop\\";
        btnImg.setText(file.getPath());
        String m = file.getPath();
        
        if (file != null) {
            try {
                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath());
                
             //  String m = "C:\\Users\\HP\\Desktop\\" + "+file.getName()+"";
               System.out.println(m);
               
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void redirecInscri(ActionEvent event) {
        p1.setVisible(false);
        p2.setVisible(true);
    }
    
}
