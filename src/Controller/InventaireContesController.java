/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Entities.Contes;
import Entities.InventaireContes;
import Entities.InventaireCours;
import Services.ServiceInventaireContes;
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
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class InventaireContesController implements Initializable {

    @FXML
    private ListView<InventaireContes> lstAffiche;
    @FXML
    private Button btnAffiche;
    @FXML
    private Button btnListeContes;
    @FXML
    private Button btnRecherche;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnPrint;
    @FXML
    private Label lbl1;
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
    private void afficherContes(ActionEvent event) throws SQLException {
        ServiceInventaireContes sc = new ServiceInventaireContes();
        
        lstAffiche.setItems((ObservableList<InventaireContes>) sc.afficherContes());
    }

    @FXML
    private void redirectListeContes(ActionEvent event) {
        try {
        Stage stage = (Stage) btnListeContes.getScene().getWindow();
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
    private void redirectRechercheContes(ActionEvent event) {
        try {
        Stage stage = (Stage) btnRecherche.getScene().getWindow();
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
    private void SupprimerContes(ActionEvent event) {
        ServiceInventaireContes sc = new ServiceInventaireContes();
        
        InventaireContes cc = lstAffiche.getSelectionModel().getSelectedItem();
        cc.getTitreC();
        cc.getAuteurC();
        sc.supprimerContes(cc);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText("Succées de suppression");
        alert.setContentText("Le cours choisi a été supprimé de votre inventaire avec succés !");
        alert.showAndWait();  
    }

    @FXML
    private void ImprimerConte(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();
        lbl1.setText(lstAffiche.getSelectionModel().getSelectedItem().toString());
        if(job != null){
          job.printPage(lbl1);
          job.endJob();
    }
    }


    @FXML
    private void redirectHome(ActionEvent event) {
         try {
             Stage stage = (Stage) btnRet.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("Accueil1.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
