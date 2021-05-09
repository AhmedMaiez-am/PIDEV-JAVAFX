/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Recompense;
import Services.ServiceRecompense;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class RecompenseController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfnbre;
    @FXML
    private ListView<Recompense> lbaffiche;
    @FXML
    private TextField tfnr;
    @FXML
    private TextField tfn;
    @FXML
    private TextField tfnb;
    @FXML
    private Button btnRet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public   boolean vailderNumber(){
        Pattern p=Pattern.compile("[0-9]+");
        Matcher m=p.matcher(tfnbre.getText());
        if(m.find() && m.group().equals(tfnbre.getText())){
            return true;
        }
        else{
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle(" non valide nombre");
        alert.setHeaderText("");
        alert.setContentText("entrer  un valide nombre  ");
        alert.showAndWait();
            return false;
            
        }
    }

    @FXML
    private void ajouterRecompense(ActionEvent event) {
        ServiceRecompense sr=new ServiceRecompense();
          Recompense r=new Recompense();
         if(vailderNumber()){
          r.setNomRec(tfnom.getText());
          r.setNbr_point(Integer.parseInt(tfnbre.getText()));
          sr.ajouterRecompense(r);
          Alert alert=new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("recompense ajouté");
          alert.setHeaderText("votre recompense a été bien ajouté");
          alert.showAndWait();
          
        }
    }

    @FXML
    private void afficherRecompense(ActionEvent event) {
        ServiceRecompense sr=new ServiceRecompense();
     lbaffiche.setItems((ObservableList<Recompense>) sr.afficherRecompense());
     lbaffiche.refresh();
    }

    @FXML
    private void supprimerRecompense(ActionEvent event) {
        Recompense r=lbaffiche.getSelectionModel().getSelectedItem();
        r.getNomRec();
        r.getNbr_point();
        ServiceRecompense sr=new ServiceRecompense();
        sr.supprimerRecompense(r);
        lbaffiche.getSelectionModel().clearSelection();
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("suppresion");
        alert.setHeaderText("succés de suppression");
        alert.setContentText("recompense supprimé avec succés");
        alert.showAndWait();
    }

    @FXML
    private void chercherRecompense(ActionEvent event) {
        ServiceRecompense sr=new ServiceRecompense();
        Recompense r=new Recompense();
        r.setNomRec(tfnr.getText());
        lbaffiche.setItems((ObservableList<Recompense>)sr.rechercherRecompense(r));
    }

    @FXML
    private void modifierRecompense(ActionEvent event) {
        Recompense r=lbaffiche.getSelectionModel().getSelectedItem();
    
        r.setNomRec(tfn.getText());
        r.setNbr_point(Integer.parseInt(tfnb.getText()));
        ServiceRecompense sr=new ServiceRecompense();
        sr.modifierRecompense(r);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("modification");
        alert.setHeaderText("succés de modification");
        alert.setContentText("recompense modifié avec succés");
        alert.showAndWait();
    }

    @FXML
    private void recupererRecomp(ActionEvent event) {
        Recompense r=lbaffiche.getSelectionModel().getSelectedItem();
      //tfidrec.setText(String.valueOf(r.getIdRec()));
      tfn.setText(r.getNomRec());
      tfnb.setText(String.valueOf(r.getNbr_point()));
    }

    @FXML
    private void BackToMenu(ActionEvent event) throws IOException {
        try {
        Stage stage = (Stage) btnRet.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("Menu.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
