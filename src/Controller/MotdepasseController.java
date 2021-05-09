        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;



import Entities.Directeur;
import Entities.MaitresseValidation;
import Entities.Parents;
import Entities.User;
import Services.ServiceAuthentification;
import Services.ServiceDirecteur;
import Services.ServiceMaitresse;
import Services.ServiceParent;
import Services.ServiceUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class MotdepasseController implements Initializable {

    @FXML
    private TextField txtMail;
    @FXML
    private PasswordField txtPass;
    @FXML
    private PasswordField txtPass1;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnenvoyer;
    @FXML
    private AnchorPane p3;
    @FXML
    private AnchorPane p2;
    @FXML
    private Button btnConfirm;
    @FXML
    private TextField txtCode;
    @FXML
    private AnchorPane p1;
    @FXML
    private TextField txtMail1;
    @FXML
    private Button btnRedirec;
    @FXML
    private PasswordField ps;
    @FXML
    private Button btnconnc;
    @FXML
    private AnchorPane p4;
    @FXML
    private ImageView img1;
    @FXML
    private Button btnCreate;
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
    private void updatepass(ActionEvent event) {
        String p = txtMail.getText();
        String m = txtPass.getText();
        String l = txtPass1.getText();
        //les services
        ServiceUser d = new ServiceUser();
        ServiceDirecteur sd = new ServiceDirecteur();
        ServiceParent sp = new ServiceParent();
        //les instances
        MaitresseValidation mv = new MaitresseValidation();
        User k = new User();
        Parents pp = new Parents();
        
        k.setEmail(p);
        mv.setLoginM(p);
        pp.setEmailP(p);
        
        if (m.equals(l)) {
            
        k.setPassword(m);
        mv.setPasswordM(m);
        pp.setPasswordP(m);
        
        d.updatepassword(k);
        sd.updatepassword(mv);
        sp.updatepassword(pp);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succés de modification");
        alert.setHeaderText("Mot de passe modifiée");
        alert.setContentText("Votre mot de passe a été modifiée avec succés !");
        alert.showAndWait();  
        p4.setVisible(false);
        p1.setVisible(true);
        
    }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Echec de modification");
        alert.setHeaderText("Mot de passe incorrecte");
        alert.setContentText("Vérifier votre mot de passe !");
        alert.showAndWait();  
        }
    }

    @FXML
    private void envoyer(ActionEvent event) {
        String l = txtMail.getText();
        System.out.println(l);
        MaitresseValidation mv = new MaitresseValidation(l);
        mv.setLoginM(l);
        ServiceDirecteur sd = new ServiceDirecteur();
        ServiceUser d = new ServiceUser();
       List <String> mv1 = new ArrayList<>();
       //List <String> lc = new ArrayList<>();
       //lc=d.getcode();
        mv1=d.getchamp();
        if (mv1.contains(l)){
            System.out.println("Mail  valide !");
            User u = new User();
            u.setEmail(l);
            System.out.println(u.getEmail());
             d.sendMail(l);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification du mot de passe");
        alert.setHeaderText("Code Envoyé ");
        alert.setContentText("Votre code de récupération a été envoyer a votre boite mail !");
        alert.showAndWait();  
        }
        
        sd.getImg(mv);
        String c = mv.getImg(); 
            System.out.println(c);
            File f = new File(c);
            Image img = new Image(f.toURI().toString());
            img1.setImage(img);
        
        
        
        p2.setVisible(false);
        p3.setVisible(true);
    }

    @FXML
    private void confirm(ActionEvent event) {
        String o = txtCode.getText();
        String l = txtMail.getText();
        ServiceUser d = new ServiceUser();
        User u = new User();
          u.setEmail(l);
          d.getDate(u);
          Timestamp tt = u.getDateExp();
          System.out.println(tt);
          
          Date date=new Date();
          long time = date.getTime();
          Timestamp t = new Timestamp(time);
          System.out.println(t);
          
          int i = t.compareTo(tt);
          if (i > 0){
              System.out.println("Date du code a expiré !");
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur");
        alert.setHeaderText("Code Expiré !");
        alert.setContentText("La date de votre code est expirée !");
        alert.showAndWait();
        p3.setVisible(false);
        p2.setVisible(true);
          }else{
         Map <String, String> m = new HashMap<String, String>();
         m=d.getcode();
          for(Map.Entry<String,String> en : m.entrySet())
              if (o.equals(m.get(l))) {
                    System.out.println("code valide") ;
              } 
              else 
              {
           System.out.println("code  non  valide") ;   
    }
          p3.setVisible(false);
          p4.setVisible(true);
    }}

    @FXML
    private void redirection(ActionEvent event) {
        p1.setVisible(false);
        p2.setVisible(true);
    }

    @FXML
    private void connecter(ActionEvent event) {
        String rNom = txtMail1.getText();
         String rprenom = ps.getText();
          Directeur p = new Directeur(rNom,rprenom);
          Parents pm = new Parents();
          pm.setEmailP(rNom);
          pm.setPasswordP(rprenom);
          MaitresseValidation m = new MaitresseValidation();
          m.setLoginM(rNom);
          m.setPasswordM(rprenom);
          ServiceMaitresse prm = new ServiceMaitresse();
         ServiceDirecteur prc = new ServiceDirecteur();
          ServiceParent prc1 = new ServiceParent();
            if(prc.ConnecterDirecteur(p)) {
                                                 
            try {
            Stage stage = (Stage) btnconnc.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashbord.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
            //Afficher Personne
    } else if (prc1.seConnecter(pm))  { 
        ServiceAuthentification sa = new ServiceAuthentification();
        sa.AjouterAuthParent(pm);
              // if (prc1.seConnecter(pm)) {
                
                try {
            Stage stage = (Stage) btnconnc.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuParent.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuParentController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    else if  (prm.seConnecter1(m)) {
            
             try {
            Stage stage = (Stage) btnconnc.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuTuteur.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MSmaitresseController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            }
    }

    @FXML
    private void redirecCreate(ActionEvent event) {
        try {
            Stage stage = (Stage) btnconnc.getScene().getWindow();
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
    private void redirectAccueil(ActionEvent event) {
        try {
            Stage stage = (Stage) btnconnc.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}