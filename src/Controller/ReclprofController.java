/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Conseil;
import Entities.Cours;
import Entities.Reclamation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Services.ServiceConseil;
import Services.ServiceCours;
import Services.ServiceReclamation;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class ReclprofController implements Initializable {

    int selectedBonplanID;
    int selectedCompteID;
    @FXML
    private Label titre;
    @FXML
    private TextField nomr;
    @FXML
    private TextField prenomr;
    @FXML
    private TextField reclamationr;
    @FXML
    private Label LAfficher;
    @FXML
    private ComboBox<String> typec;
    @FXML
    private TextField emailc;
    @FXML
    private Button btnRet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stage window = new Stage();
       typec.setPromptText("Veuillez selectionner un type");
        List<String> etats = new ArrayList<String>();
        etats.add("Cours");
        etats.add("Contes");
        etats.add("Tuteur");
        etats.add("Enfant");
        etats.add("Autre");
        ObservableList etatList = FXCollections.observableList(etats);
        typec.setItems(etatList);
    }    

    @FXML
    private void AjouterReclamationr(ActionEvent event) {
        String x="Tuteur";
        ServiceReclamation sr= new ServiceReclamation();
        Reclamation r=new Reclamation();
        
        r.setNom(nomr.getText());
        r.setPrenom(prenomr.getText());
        r.setReclamation(reclamationr.getText());
        r.setPp(x);
          r.setType(typec.getValue()+ ": " +listeCours.getSelectionModel().getSelectedItem().getNom()+" "+listeCours.getSelectionModel().getSelectedItem().getType());
        r.setEmail(emailc.getText());
                sr.AddReclamation(r);
        
            LAfficher.setText(r.toString());
            nomr.clear();
            prenomr.clear();
            reclamationr.clear();
            
                
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout à la base");
        alert.setHeaderText("Succées d'ajout");
        alert.setContentText("La reclamation choisi a été envoyer et va etre traité dans les plus brefs délais !");
        alert.showAndWait();  
    }
    TableView<Cours> listeCours = new TableView<>();
    TextField rechercheReclamationn = new TextField();

    @FXML
    private void recherchereclprof(ActionEvent event) throws SQLException {
        if (typec.getValue().toString() =="Cours"){
                    rechercheReclamationn.setOnKeyReleased((KeyEvent a) -> {listReclamationn();});
                    
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Veuillez choisir une reclamation");

       TableColumn<Cours, String> nomc = new TableColumn<>("Nom");
            nomc.setCellValueFactory(new PropertyValueFactory<>("nom"));
            
             TableColumn<Cours, String> type = new TableColumn<>("Type");
            type.setCellValueFactory(new PropertyValueFactory<>("type"));

            
            ServiceCours sc = new ServiceCours();
            
            listeCours.setEditable(true);
            listeCours.setItems((ObservableList<Cours>) sc.afficherCours());
            listeCours.getColumns().setAll(nomc,type);
            
         
            
            listeCours.setOnMouseClicked((MouseEvent event2)
                    -> {
                if (event2.getClickCount() >= 2) {
                    if (listeCours.getSelectionModel().getSelectedItem() != null) {
                      
                        selectedBonplanID = listeCours.getSelectionModel().getSelectedItem().getIdC();
                        selectedCompteID=0;
                        System.out.println("id du bon plan selectioné=" + selectedBonplanID);
                        window.close();
                     
                           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Choix de la reclamation ");
        alert.setHeaderText("Succées d'ajout");
        alert.setContentText("La reclamation concernant ce cours est selectionnée avec succès!");
        alert.showAndWait(); 
                              
         }
                }
    });
        
            Button closeButton = new Button("Fermer");

            closeButton.setOnAction(e -> window.close());
            
 VBox layout = new VBox(10);
            layout.getChildren().setAll(rechercheReclamationn,listeCours,closeButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.show();
        }
        
    }
     public void listReclamationn() {
        ArrayList arrayList = null;
        ServiceCours sc = new ServiceCours();

            arrayList = (ArrayList) sc.recherchecours(rechercheReclamationn.getText());


        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listeCours.setItems(observableList);
    }

    @FXML
    private void redirectRetour(ActionEvent event) {
        try {
        Stage stage = (Stage) btnRet.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("Conseil.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(ReclprofController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
