/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Maitresse;
import Entities.Parents;
import Entities.Reclamation;
import Services.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class DocumentController implements Initializable {

    private Label label;
    private TextField nom;
    private TextField prenom;
    private TextField reclamation;
    private Label LAffiche;
    private TextField idr;
    @FXML
    private TextField nom1;
    @FXML
    private TextField prenom1;
    @FXML
    private Button btnRet;
    @FXML
    private Button btnRecla;
    @FXML
    private Button btnConsRecla;
    @FXML
    private Button btnAffichCons;
    @FXML
    private Button btnConCon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void AjouterReclamation(ActionEvent event) throws SQLException {
    ServiceReclamation sr= new ServiceReclamation();
        Reclamation r=new Reclamation();
        Parents p = new Parents();
        Maitresse m = new Maitresse();
        sr.afficher(p);
        sr.afficherM(m);
        
        if (nom.getText().equals(p.getNomP()) || nom.getText().equals(m.getNom())){
        r.setNom(nom.getText());
        }
        else {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur");
        alert.setHeaderText("Echec d'ajout");
        alert.setContentText("Le nom saisie est introuvable !");
        alert.showAndWait();
        nom.clear();
        }
        
        if (prenom.getText().equals(p.getPrenomP()) || prenom.getText().equals(m.getPrenom())){
        r.setPrenom(prenom.getText());
        }
        else {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur");
        alert.setHeaderText("Echec d'ajout");
        alert.setContentText("Le nom saisie est introuvable !");
        alert.showAndWait();
        prenom.clear(); 
        }
        r.setReclamation(reclamation.getText());
        
        sr.AddReclamation(r);
        
            LAffiche.setText(r.toString());
       
    }
     private void AfficherReclamation(ActionEvent event) {
    ServiceReclamation sr= new ServiceReclamation();
    
        try {
            LAffiche.setText(sr.AfficherReclamationpr().toString());
        } catch (SQLException ex) {
        }
    }
      private void ModifierReclamation(ActionEvent event) {
        
          ServiceReclamation sr=new ServiceReclamation();
        Reclamation r=new Reclamation();
        int id;
        
        id=Integer.parseInt(idr.getText());
        
        r.setIdr(Integer.parseInt(idr.getText()));
        r.setNom(nom.getText());
        r.setPrenom(prenom.getText());
        r.setReclamation(reclamation.getText());
        sr.modifierReclamation(id, r);
        
    }
      private void SupprimerReclamation(ActionEvent event) {
       
    
       ServiceReclamation sr=new ServiceReclamation();
       Reclamation r=new Reclamation();
       r.setIdr(Integer.parseInt(idr.getText()));
       sr.supprimerReclamation(r);
    
    }
    @FXML
    private void ajout_affi_recla(ActionEvent event) throws IOException {
         try {
        Stage stage = (Stage) btnRecla.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("Reclparent.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void consulter_recla(ActionEvent event) throws IOException {
        try {
        Stage stage = (Stage) btnConsRecla.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("CRP.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void ajout_affi_conseil(ActionEvent event) throws IOException {
        try {
        Stage stage = (Stage) btnAffichCons.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("ConseilParent.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void consulter_conseil(ActionEvent event) throws IOException {
        try {
        Stage stage = (Stage) btnConCon.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("CCP.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void retour(ActionEvent event) {
         try {
            Stage stage = (Stage) btnRet.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuTuteur.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MotdepasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
