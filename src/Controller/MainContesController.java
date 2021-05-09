/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Contes;
import Services.ServiceContes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class MainContesController implements Initializable {

    @FXML
    private ListView<Contes> list;
    @FXML
    private Button btnRet;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnMod;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnSupp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceContes su =new ServiceContes();
            List<Contes> liste;
        
            liste = new ArrayList<Contes>(su.afficherContes());
            list.getItems().addAll(liste);
        } catch (SQLException ex) {
            Logger.getLogger(MainContesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void load3(ActionEvent event) throws IOException {
        try {
        Stage stage = (Stage) btnSearch.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("RechercheContes.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(RechercheContesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void load(ActionEvent event) throws IOException {
        try {
        Stage stage = (Stage) btnAjout.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("AjoutContes.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutContesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void load1(ActionEvent event) throws IOException {
        try {
        Stage stage = (Stage) btnMod.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("ModContes.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(ModContesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void load2(ActionEvent event) throws IOException {
        try {
        Stage stage = (Stage) btnSupp.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("SuppContes.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(SuppContesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void exporter1(ActionEvent event) throws SQLException, IOException, FileNotFoundException {
        ServiceContes sc = new ServiceContes();
        sc.exportContes();
        
        Alert alert =new Alert (Alert.AlertType.INFORMATION);
   alert.setTitle("Export du conte");
   alert.setHeaderText(null);
   alert.setContentText("La liste des contes a été exportée avec succés !");
   alert.showAndWait();
    }

    @FXML
    private void redirectRetour(ActionEvent event) {
        try {
        Stage stage = (Stage) btnRet.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("GestionCC.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionCCController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
