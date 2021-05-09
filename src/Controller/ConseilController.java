/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Conseil;
import Entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Services.ServiceConseil;
import Services.ServiceReclamation;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class ConseilController implements Initializable {

    @FXML
    private Label LAffichec;
    @FXML
    private TextField nomc;
    @FXML
    private TextField prenomc;
    @FXML
    private TextField conseil;
    @FXML
    private ListView<Conseil> lstconseil;
    @FXML
    private TextField nomr;
    @FXML
    private Button btnRet;
    @FXML
    private Button btnAffich;
    @FXML
    private Button btnRecl;
    @FXML
    private Button btnStat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajout_Affich_recl(ActionEvent event) throws IOException {
        try {
        Stage stage = (Stage) btnAffich.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("Reclprof.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(ConseilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Cons_gerer_recl(ActionEvent event) throws IOException {
        try {
        Stage stage = (Stage) btnRecl.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("GCRP.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(ConseilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void affichstati(ActionEvent event) throws IOException {
        try {
        Stage stage = (Stage) btnRecl.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("statistique.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(ConseilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AjouterConseil(ActionEvent event) {    
    Conseil c=lstconseil.getSelectionModel().getSelectedItem();
    nomc.setText(c.getNomc());
    prenomc.setText(c.getPrenomc());
    conseil.setText(c.getConseil());
    }

    @FXML
    private void SupprimerConseil(ActionEvent event) {
        ServiceConseil sc = new ServiceConseil();
         Conseil c=lstconseil.getSelectionModel().getSelectedItems().get(0);
         
                sc.supprimerConseil(c);
    }

    @FXML
    private void ModifierConseil(ActionEvent event) {
         ServiceConseil sc= new ServiceConseil();
        Conseil c=new Conseil();
        /*int id;
        
        id=Integer.parseInt(idc.getText());
        
        c.setIdc(Integer.parseInt(idc.getText()));*/
        c.setNomc(nomc.getText());
        c.setPrenomc(prenomc.getText());
        c.setConseil(conseil.getText());
        sc.modifierConseil(c);
    }

    @FXML
    private void AfficherConseil(ActionEvent event) throws SQLException {
        ServiceConseil sc= new ServiceConseil();
    
       ObservableList lstConseil = FXCollections.observableList(sc.AfficherConseil());
       lstconseil.setItems(lstConseil);
    }

    @FXML
    private void recherchec(ActionEvent event) {
        System.out.println(nomr.getText());
       ServiceConseil sc= new ServiceConseil();
       Conseil c=new Conseil();
        c.setNomc(nomr.getText());
        sc.rechercherconseil(c);
       
      lstconseil.setItems((ObservableList<Conseil>) sc.rechercherconseil(c));
    }

    @FXML
    private void redirectMenuTuteur(ActionEvent event) {
        try {
        Stage stage = (Stage) btnRet.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("MenuTuteur.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MotdepasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
