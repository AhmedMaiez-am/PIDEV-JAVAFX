/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Cours;
import Services.ServiceCours;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class RechercheCoursController implements Initializable {

    @FXML
    private TextField txtNom;
    @FXML
    private Button btnRecherche;
    @FXML
    private ListView<Cours> lstAffiche;
    @FXML
    private Button btnListe;
    @FXML
    private Button btnInv;
    @FXML
    private Button btnRet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void rechercherCours(ActionEvent event) throws SQLException {
        
        Cours e = new Cours();
        e.setNom(txtNom.getText());
        
        ServiceCours sc = new ServiceCours();
        
        lstAffiche.setItems((ObservableList<Cours>) sc.rechercheCours(e));
    }

    @FXML
    private void changeList(ActionEvent event) {
        try {
        Stage stage = (Stage) btnListe.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("ListeCours.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeCours.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void changeInv(ActionEvent event) {
        try {
        Stage stage = (Stage) btnInv.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("Inventaire.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(InventaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}

    @FXML
    private void redirectHome(ActionEvent event) {
        try {
            Stage stage = (Stage) btnRet.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("MainCours.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MainCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
