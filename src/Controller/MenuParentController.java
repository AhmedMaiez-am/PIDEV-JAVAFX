/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Services.ServiceAuthentification;
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
public class MenuParentController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private Button msj;
    @FXML
    private Button btnespace;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnPay;
    @FXML
    private Button btnDeco;
    @FXML
    private Button btnRec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void redirecm(ActionEvent event) {
        try {
            Stage stage = (Stage) retour.getScene().getWindow();
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
    private void miseajour(ActionEvent event) {
        try {
            Stage stage = (Stage) msj.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MSparents.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MSparentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnespace(ActionEvent event) {
        try {
            Stage stage = (Stage) btnespace.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjouteEnfants.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouteEnfantsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirectAccueil(ActionEvent event) {
        try {
            Stage stage = (Stage) btnespace.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Accueil1.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirecPay(ActionEvent event) {
        try {
            Stage stage = (Stage) btnPay.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Paiement.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(PaiementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deconnection(ActionEvent event) {
        ServiceAuthentification sa = new ServiceAuthentification();
        sa.SupprimerAuthParent();
         try {
            Stage stage = (Stage) btnPay.getScene().getWindow();
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

    @FXML
    private void conseil(ActionEvent event) {
        try {
            Stage stage = (Stage) btnRec.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Document.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MotdepasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}