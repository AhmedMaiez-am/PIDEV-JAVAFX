/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Reclamation;
import Services.ServiceReclamation;
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class CRPController implements Initializable {

    @FXML
    private ListView<Reclamation> listViewRR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceReclamation sr = new ServiceReclamation();
        
        try {
            listViewRR.setItems((ObservableList<Reclamation>) sr.AfficherReclamationpr());
        } catch (SQLException ex) {
            Logger.getLogger(CRPController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void reponsereclama(ActionEvent event) throws IOException {
        if (listViewRR.getSelectionModel().getSelectedItem()!=null)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RepondreReclamation.fxml"));
       Parent root = loader.load();
        RepondreReclamationController RepondreReclamation = loader.getController();
        RepondreReclamation.setreclamation(listViewRR.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
         stage.setTitle("Réponse de la réclamation");
         
        stage.show();
        }
    }
    
}
