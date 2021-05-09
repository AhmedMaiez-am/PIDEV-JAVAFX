/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Contes;
import Services.ServiceContes;
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
public class RechercheContesController implements Initializable {

    @FXML
    private ListView<Contes> lstAffiche;
    @FXML
    private TextField txtRecherche;
    @FXML
    private Button btnRecherche;
    @FXML
    private Button btnList;
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
    private void rechercheConte(ActionEvent event) throws SQLException {
        Contes e = new Contes();
        e.setTitre(txtRecherche.getText());
        
        ServiceContes sc = new ServiceContes();
        
        lstAffiche.setItems((ObservableList<Contes>) sc.rechercheContes(e));
    }

    @FXML
    private void redirectListeConte(ActionEvent event) {
        try {
        Stage stage = (Stage) btnList.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("ListeContes.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeContes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirectInventaireConte(ActionEvent event) {
        try {
        Stage stage = (Stage) btnInv.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("InventaireContes.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(InventaireContesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirectHome(ActionEvent event) {
        try {
            Stage stage = (Stage) btnRet.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("MainContes.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MainContesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
