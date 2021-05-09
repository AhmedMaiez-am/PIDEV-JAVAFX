/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Conseil;
import Entities.Maitresse;
import Entities.Parents;
import Services.ServiceConseil;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class ConseilParentController implements Initializable {

    @FXML
    private Label titre;
    @FXML
    private TextField nomp;
    @FXML
    private TextField prenomp;
    @FXML
    private TextField conseilp;
    @FXML
    private Label LAfficheconseil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterConseilp(ActionEvent event) throws SQLException {
        String x = "Parent";
        
        ServiceConseil sc= new ServiceConseil();
        Conseil c=new Conseil();
        //Maitresse m = new Maitresse();
        Parents p = new Parents();
        //sc.afficherCM(m);
        sc.afficherC(p);
        
        if (/*nomp.getText().equals(m.getNom()) || */nomp.getText().equals(p.getNomP())){
        c.setNomc(nomp.getText());
        }
        else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur");
        alert.setHeaderText("Echec d'ajout");
        alert.setContentText("Le nom saisie est introuvable !");
        alert.showAndWait();
        nomp.clear();
        }
        
        if (/*prenomp.getText().equals(m.getPrenom()) ||*/ prenomp.getText().equals(p.getPrenomP())){
        c.setPrenomc(prenomp.getText());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur");
        alert.setHeaderText("Echec d'ajout");
        alert.setContentText("Le prenom saisie est introuvable !");
        alert.showAndWait(); 
        prenomp.clear();
        }
        c.setConseil(conseilp.getText());
        c.setType(x);
        sc.AddConseil(c);
        
            LAfficheconseil.setText(c.toString());
            nomp.clear();
            prenomp.clear();
            conseilp.clear();
            
                
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout à la base");
        alert.setHeaderText("Succées d'ajout");
        alert.setContentText("Merci pour votre conseil, on va le prendre en considération et sera traité dans les plus brefs délais !");
        alert.showAndWait();  
    }
    
}
