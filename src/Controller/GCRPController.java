/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import Services.ServiceReclamation;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class GCRPController implements Initializable {

    @FXML
    private ImageView listaff;
    @FXML
    private ListView<Reclamation> lstReclamation;
    @FXML
    private ListView<?> lstrecup;
    @FXML
    private TextField nomtxt;
    @FXML
    private TextField prenomtxt;
    @FXML
    private TextField reclamationtxt;
    @FXML
    private TextField nomrech;
    @FXML
    private ComboBox<?> etatcom;
    @FXML
    private Button rep;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> etats = new ArrayList<String>();
        etats.add("En Attente");
        etats.add("En Cours de Traitement.");
        etats.add("Déja Traiter.");
        ObservableList etatList = FXCollections.observableList(etats);
        etatcom.setItems(etatList);
        ServiceReclamation sr = new ServiceReclamation();
        
        try {
            lstReclamation.setItems((ObservableList<Reclamation>) sr.AfficherReclamationpa());
        } catch (SQLException ex) {
            Logger.getLogger(GCRPController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Ajoutlist(ActionEvent event) throws SQLException {
        Reclamation r=lstReclamation.getSelectionModel().getSelectedItem();
                   ServiceReclamation sr = new ServiceReclamation();

    nomtxt.setText(r.getNom());
    prenomtxt.setText(r.getPrenom());
    reclamationtxt.setText(r.getReclamation());
       etatcom.setPromptText("En cours de traitement");
       r.setEtat("En cours de traitement");
       sr.modifierReclamation(r.getIdr(), r);
               lstReclamation.setItems((ObservableList<Reclamation>) sr.AfficherReclamationpa());
    }

    @FXML
    private void Modifierlist(ActionEvent event) throws SQLException {
        ServiceReclamation sr = new ServiceReclamation();
           Reclamation r=lstReclamation.getSelectionModel().getSelectedItem();

      
       
       r.setEtat("Traité");
       sr.modifierReclamation(r.getIdr(), r);
               lstReclamation.setItems((ObservableList<Reclamation>) sr.AfficherReclamationpa());
    }

    @FXML
    private void Supprimerlist(ActionEvent event) {
        ServiceReclamation sr = new ServiceReclamation();
         Reclamation r=lstReclamation.getSelectionModel().getSelectedItems().get(0);
         
                sr.supprimerReclamation(r);
    }

    @FXML
    private void rechercherecl(ActionEvent event) {
        ServiceReclamation sr = new ServiceReclamation();
        Reclamation r=new Reclamation();
        r.setNom(nomrech.getText());
        sr.rechercherreclamation(r);
        ObservableList<Reclamation> rs=lstReclamation.getSelectionModel().getSelectedItems();
      lstReclamation.setItems((ObservableList<Reclamation>) sr.rechercherreclamation(r));
    }

    @FXML
    private void repondrereclamation(ActionEvent event) throws IOException {
        if (lstReclamation.getSelectionModel().getSelectedItem()!=null)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RepondreReclamation.fxml"));
       Parent root = loader.load();
        RepondreReclamationController RepondreReclamation = loader.getController();
        RepondreReclamation.setreclamation(lstReclamation.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
         stage.setTitle("Réponse de la réclamation");
         
        stage.show();
        }
    }
    
}
