/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Maitresse;
import Entities.MaitresseValidation;
import Services.ServiceMaitresse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class MaitresseController implements Initializable {

    @FXML
    private ImageView mait;
    @FXML
    private Button btnAjout;
    @FXML
    private TextField txtsecname;
    @FXML
    private TextField txtpre;
    @FXML
    private TextField txtcin;
    @FXML
    private TextField txtemail;
    @FXML
    private Button btnretour;
    @FXML
    private Button image;
    @FXML
    private Button cv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   /* @FXML
    private void Connecter(ActionEvent event) {
        String rNom = login.getText();
            String rprenom = password.getText();
            MaitresseValidation p = new MaitresseValidation(rNom,rprenom);
            ServiceMaitresse prc = new ServiceMaitresse();
            
            if (prc.seConnecter(p)) {
                
                try {
            Stage stage = (Stage) con.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MSmaitresse.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MSmaitresseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            }
            
            else { Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur");
        alert.setHeaderText("Compte introuvable");
        alert.setContentText("Vérifier vos paramètres de connection !");
        alert.showAndWait();  
    }
    }*/

    @FXML
    private void AjoutDemande(ActionEvent event) {
        Maitresse m = new Maitresse();
        boolean control = true;
        
        m.setNom(txtsecname.getText());
        m.setPrenom(txtpre.getText());
        m.setImag(image.getText());
        m.setCv(cv.getText());
        if ((txtcin.getText()).length()==8){
            m.setCin(txtcin.getText());
        }
        else {
            control = false;
            txtcin.setText("");
            System.out.println("Le numéro de la carte d'identité doit être de longueur 8");
        }
        if((txtemail.getText()).contains("@gmail.com")){
            m.setEmail(txtemail.getText());
        }
        else {
            control = false;
            txtemail.setText("");
            System.out.println("Addresse mail non valide !");
        }
      
        if (control == true){
        ServiceMaitresse prc = new ServiceMaitresse();
        prc.AjouterDemande(m);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succés d'ajout");
        alert.setHeaderText("Demande Ajoutée");
        alert.setContentText("Votre demande a été ajouté avec succés !");
        alert.showAndWait();  
    }

   /* @FXML
    private void recup(ActionEvent event) {
         try {
            
             Stage stage = (Stage) btnoublie.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Passchange.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(PasschangeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
*/
    @FXML
    private void retour(ActionEvent event) {
        try {
            Stage stage = (Stage) btnretour.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createAcc.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(CreateAccController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void upload(ActionEvent event) {
        Stage primary = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selectionner une image");

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(primary);
        String path = "C:\\Users\\maiez\\Desktop\\";
        image.setText(file.getPath());
        String m = file.getPath();
        
        if (file != null) {
            try {
                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath());
                
             //  String m = "C:\\Users\\HP\\Desktop\\" + "+file.getName()+"";
               System.out.println(m);
               
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void uploadcv(ActionEvent event) {
        Stage primary1 = new Stage();
        FileChooser fileChooser1 = new FileChooser();
        fileChooser1.setTitle("Selectionner le cv");

        fileChooser1.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Files", "*.pdf", "*.jpg", "*.gif"));
        File file = fileChooser1.showOpenDialog(primary1);
        String path = "C:\\Users\\maiez\\Desktop\\";
        cv.setText(file.getPath());
        String m = file.getPath();
        
        if (file != null) {
            try {
                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath());
                
             //  String m = "C:\\Users\\HP\\Desktop\\" + "+file.getName()+"";
               System.out.println(m);
               
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
