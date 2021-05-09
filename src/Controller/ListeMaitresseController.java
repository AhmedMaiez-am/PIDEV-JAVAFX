/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Maitresse;
import Entities.MaitresseValidation;
import Services.ServiceDirecteur;
import Services.ServiceUser;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class ListeMaitresseController implements Initializable {

    @FXML
    private AnchorPane p;
    @FXML
    private Button btnRecherche;
    @FXML
    private ListView<Maitresse> lstAffiche;
    @FXML
    private Button btnretour;
    @FXML
    private TextField txtNom;
    @FXML
    private Button btnAfficher;
    @FXML
    private AnchorPane p1;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;
    @FXML
    private Label lbl4;
    @FXML
    private Label lbl5;
    @FXML
    private Label lbl6;

    @FXML
    private Button afficherimage;
    @FXML
    private ImageView image;
    private Label l7;
    @FXML
    private Label l8;
    @FXML
    private Button btnRefuser;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void recherche(ActionEvent event) throws SQLException {
        Maitresse m = new Maitresse();
        m.setNom(txtNom.getText());
         ServiceDirecteur dr = new ServiceDirecteur();
         
         lstAffiche.setItems((ObservableList<Maitresse>) dr.recherche(m));
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            Stage stage = (Stage) btnretour.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashbord.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Afficher(ActionEvent event) {
         ServiceDirecteur dr = new ServiceDirecteur();
        lstAffiche.setItems((ObservableList<Maitresse>) dr.ListerMaitresse());
        
        
        lstAffiche.setOnMouseClicked(new EventHandler <MouseEvent>(){
        @Override
        public void handle (MouseEvent click){
        if (click.getClickCount()==2){
            p.setVisible(false);
            p1.setVisible(true);
           
            Maitresse m = lstAffiche.getSelectionModel().getSelectedItem();
            lbl1.setText(m.getCin());
            lbl2.setText(m.getNom());
            lbl3.setText(m.getPrenom());
            lbl4.setText(m.getEmail());
            lbl5.setText(m.getEtat());
            lbl6.setText(m.getDated().toString());
            
            afficherimage.setVisible(true);
            String c = m.getImag(); 
            System.out.println(c);
            File f = new File(c);
            Image img = new Image(f.toURI().toString());
            image.setImage(img);
            
            
            
           /* String b = m.getCv(); 
           System.out.println(b);
           File f1 = new File(b);
            try {
                Desktop.getDesktop().open(f1);
            } catch (IOException ex) {
                Logger.getLogger(ListeMaitresseController.class.getName()).log(Level.SEVERE, null, ex);
            }
           */
        }
    }
    });
    }

    @FXML
    private void afficherimage(ActionEvent event) throws IOException {
        ServiceDirecteur prc = new ServiceDirecteur();
        Maitresse m = lstAffiche.getSelectionModel().getSelectedItem();
      /* String c = m.getImag(); 
       System.out.println(c);
      
       File f = new File(c);
        Image img = new Image(f.toURI().toString());
        ach.setVisible(true);
         view.setImage(img);*/
      
     String c = m.getCv(); 
     System.out.println(c);
       File f1 = new File(c);
    Desktop.getDesktop().open(f1);
    }

    @FXML
    private void Refuser(ActionEvent event) {
         ServiceDirecteur prc = new ServiceDirecteur();
        Maitresse m = lstAffiche.getSelectionModel().getSelectedItem(); 
                                    m.getNom();
                                    m.getCin();
                                    Maitresse p = new Maitresse();
                                    String l = m.getEmail();
                                    p.setEmail(l);
                                    p.setEtat("Refusé");
                                    prc.updateEtat(p);
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Ajout à la base");
                                        alert.setHeaderText("Succées de suppression");
                                        alert.setContentText("la demande a été refuser !");
                                        alert.showAndWait(); 
                                        prc.refuserDemande(m); 
    }

    @FXML
    private void Valider(ActionEvent event) {
         ServiceDirecteur prc = new ServiceDirecteur();
         Maitresse m = lstAffiche.getSelectionModel().getSelectedItem();
                                    MaitresseValidation mm = new MaitresseValidation();  
                                    Maitresse p = new Maitresse();
                                    mm.setLoginM(m.getEmail());
                                    mm.setPasswordM(m.getCin());
                                    mm.setImg(m.getImag());
                                    String l = m.getEmail();
                                    p.setEmail(l);
                                    p.setEtat("Validé");
                                    prc.updateEtat(p);
                                    ServiceUser su = new ServiceUser();
                                    su.ajouterMaitresse(mm);
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Ajout à la base");
                                        alert.setHeaderText("Succées d'ajout");
                                        alert.setContentText("La demande a été ajoutée");
                                        alert.showAndWait(); 
                                        prc.Valider(mm); 
                                        prc.refuserDemande(m);
                                        String recepient= lstAffiche.getSelectionModel().getSelectedItem().getEmail();
                                   prc.sendMailValidation(recepient);
    }

    @FXML
    private void redirecListe(ActionEvent event) {
        p1.setVisible(false);
        p.setVisible(true);
    }
    
}
