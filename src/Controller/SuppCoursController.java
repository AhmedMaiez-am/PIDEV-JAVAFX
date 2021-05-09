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
public class SuppCoursController implements Initializable {

    @FXML
    private TextField txtsupp;
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
    private void supprimercours(ActionEvent event) {
        ServiceCours sp=new ServiceCours();
         Cours p=new Cours();
   
        p.setNom(txtsupp.getText());
        sp.supprimerCours(p);
        Alert alert =new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Suppression du cours");
        alert.setHeaderText(null);
        alert.setContentText("Ce cours sera supprimé !");
        alert.showAndWait();
    }

    @FXML
    private void load(ActionEvent event) throws IOException {
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
