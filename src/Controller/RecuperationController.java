/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Enfant;
import Entities.EnfantRecup;
import Entities.Parents;
import Entities.ParentsRecup;
import Entities.Recompense;
import Entities.recuperation;
import Services.ServiceRecompense;
import Services.ServiceRecuperation;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class RecuperationController implements Initializable {

    @FXML
    private ListView<Recompense> lafficher;
    @FXML
    private TextField tfnom1;
    @FXML
    private TextField tfnbr;
    @FXML
    private ComboBox<EnfantRecup> combonom;
    @FXML
    private ComboBox<ParentsRecup> combomail;
    @FXML
    private ListView<recuperation> laf;
    @FXML
    private TextField tfemail;
    @FXML
    private Button btnRet;
    @FXML
    private TextField tftel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ServiceRecuperation sr=new ServiceRecuperation();
        
        combomail.setItems((ObservableList<ParentsRecup>)sr.fillcombobox2());
        combonom.setItems((ObservableList<EnfantRecup>)sr.fillcombobox1());
    }    

    @FXML
    private void ajouterRecuperation(ActionEvent event) {
        ServiceRecuperation s=new ServiceRecuperation();
             recuperation r =new recuperation();
            
             r.setNomRec(tfnom1.getText());
             r.setNbr_point(Integer.parseInt(tfnbr.getText()));
     /*       combomail.setItems((ObservableList<Parents>)s.fillcombobox2());
            combomail.getSelectionModel().getSelectedItem();
            r.setEmailP(combomail.getEditor().getSelectedText());
            combonom.setItems((ObservableList<Enfant>)s.fillcombobox1());
            combonom.getSelectionModel().getSelectedItem();
            r.setnomEnf(combonom.getEditor().getSelectedText());*/
            // r.setEmailP(tfemailP.getText());
             //r.setnomEnf(tfnomE.getText());
             r.setEmailP(combomail.getSelectionModel().getSelectedItem().getEmailP());
             r.setnomEnf(combonom.getSelectionModel().getSelectedItem().getPrenomEnfant());
             s.ajouterRecuperation(r);
              Alert alert=new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("recuperation ajouté");
          alert.setHeaderText("votre recuperation a été bien ajouté");
          alert.showAndWait();
    }

    @FXML
    private void afficherRecompense(ActionEvent event) {
        ServiceRecompense sr=new ServiceRecompense();
     lafficher.setItems((ObservableList<Recompense>) sr.afficherRecompense());
    }

    @FXML
    private void recupererRecompense(ActionEvent event) {
        Recompense rc=lafficher.getSelectionModel().getSelectedItem();
          
            tfnom1.setText(rc.getNomRec());
            tfnbr.setText(String.valueOf(rc.getNbr_point()));
    }

    @FXML
    private void afficherRecuperation(ActionEvent event) {
        ServiceRecuperation sr=new ServiceRecuperation();
        laf.setItems((ObservableList<recuperation>)sr.afficherRecuperation());
    }

    @FXML
    private void trierRecuperation(ActionEvent event) {
        ServiceRecuperation sr=new ServiceRecuperation();
       laf.setItems((ObservableList<recuperation>)sr.trierRecuperation());
    }

    @FXML
    private void envoyerMail(ActionEvent event) {
        ServiceRecuperation sr=new ServiceRecuperation();
    if(valideremail()){
   String Recepient=tfemail.getText();
    
   sr.sendmail(Recepient);
    Alert alert=new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Mail envoyé");
          alert.setHeaderText("votre mail a été bien envoyé");
          alert.showAndWait();
        }
    }

    @FXML
    private void GoToMenu(ActionEvent event) throws IOException {
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
    public  boolean valideremail(){
            Pattern p=Pattern.compile(

"^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"+ "[a-zA-Z0-9._-]*[a-zA-Z0-9]+.[a-zA-Z]");
            recuperation r=new recuperation();
            Matcher m=p.matcher(tfemail.getText());
            if(m.find() && m.group().equals(tfemail.getText())){
                return true;
            }
            else{
                
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("non valide email");
        alert.setHeaderText("");
        alert.setContentText("entrer  un valide email ");
        alert.showAndWait();
        return false;
            }
            
        }

    @FXML
    private void envoyerSms(ActionEvent event) {
        String tel=tftel.getText();
        if ((tftel.getText()).length()==12){
           
         String ACCOUNT_SID =
            "ACfeaf963c34a2bbd21d41a370d1011ce1";
     String AUTH_TOKEN =
            "cda9cf0f2617cf5cea40122fe8ca03e7";
          Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      
       Message message = Message.creator(new PhoneNumber(tel),
        new PhoneNumber("+13346058453"),"felicitation, votre fils a gagné une recompense merci de nous contacter").create();
          Alert alert=new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("SMS");
          alert.setHeaderText("votre SMS a été bien envoyé");
          alert.showAndWait();
          }else {
            Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Numero non valide");
        alert.setHeaderText("");
        alert.setContentText("entrer  un numero de telephone valide");
        alert.showAndWait();
        tftel.setText("");
        }
    }
    
}
