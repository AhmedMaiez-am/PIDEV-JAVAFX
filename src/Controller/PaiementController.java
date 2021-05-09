/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Authentification;
import Entities.Donation;
import Entities.Parents;
import Services.ServiceAuthentification;
import Services.ServiceParent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class PaiementController implements Initializable {

    @FXML
    private TextField carte;
    @FXML
    private TextField montant;
    @FXML
    private Button bntvali;
    @FXML
    private Button btnretour;
    @FXML
    private Button dec;
    @FXML
    private Label lbl1;
    @FXML
    private Label label1;
    @FXML
    private TextField txtTel;
    @FXML
    private TextField txtNomP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Parents p = new Parents();
        Authentification a = new Authentification();
        ServiceAuthentification sa = new ServiceAuthentification();
        sa.SelectAuthParent(a);
        txtNomP.setText(a.getEmailP());
        String np = txtNomP.getText();
        p.setEmailP(np);
        ServiceParent sp = new ServiceParent();
        sp.afficherPf(p);
        txtTel.setText(p.getTelP());
    }    

    @FXML
    private void imprimer(ActionEvent event) throws IOException, SQLException {
        ServiceParent pc = new ServiceParent();
        Parents p = new Parents();
        String n = carte.getText();
        //String pa = idp.getText();
        String pf = montant.getText();
        Date date=new Date();
            long time = date.getTime();
            Timestamp t = new Timestamp(time);
        p.setNumcarteP(n);
        //p.setPasscaretp(pa);
       ObservableList<Parents> arti = (ObservableList<Parents>) pc.Donner(p);
       
       String a  = arti.toString();
        System.out.println(a);
        
        PDPage page = new PDPage();
        
        PDDocument doc = new PDDocument();
        doc.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(doc, page);


        PDImageXObject pdImage = PDImageXObject.createFromFile("C:\\Users\\maiez\\Documents\\NetBeansProjects\\Kidzy\\src\\UI\\logo.png", doc);
        contentStream.drawXObject(pdImage, 50, 605, 140, 140);
        
        PDFont font = PDType1Font.TIMES_ROMAN;
        contentStream.beginText();
        contentStream.setFont(font, 24);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(220, 650);
        contentStream.showText("Reçu de paiement");
        contentStream.endText();
        

        PDFont font3 = PDType1Font.TIMES_ROMAN;
        contentStream.beginText();
        contentStream.setFont(font3, 24);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(100, 400);
        contentStream.showText("ID du client :" + n );
        contentStream.endText();
        
        PDFont font1 = PDType1Font.TIMES_ROMAN;
        contentStream.beginText();
        contentStream.setFont(font1, 24);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(100, 350);
        contentStream.showText("Le montant  :" + pf +" DT" );
        contentStream.endText();
        
        PDFont font2 = PDType1Font.TIMES_ROMAN;
        contentStream.beginText();
        contentStream.setFont(font2, 24);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(100, 300);
        contentStream.showText("La date de la Don  :" + t );
        contentStream.endText();
        contentStream.close();

        
         doc.save("Reçu de paiement.pdf");
        doc.close();
        System.out.println("pdf saved");
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Imprimé du paiement");
        alert.setHeaderText("Succées de sauvegarde");
        alert.setContentText("Votre reçu de paiement a été sauvegardé avec succés, on vous remercie pour votre générosité !");
        alert.showAndWait();  
       
  
    }

    @FXML
    private void Valider(ActionEvent event) {
      
       Stripe.apiKey="sk_test_51Ib3FZKQYIto2Z2mYr1eT4KYtwpdFpipwGhgLtSmpqCehJenE9NpOhANgmEFTzC7iaahuUVJby0HjB51SjmWisxj00SXbmWNC7";
     Customer customer=new Customer();
       
       Map<String,Object> chargeparam=new HashMap<String,Object>();
            
                Map<String, Object> retrieveParams =new HashMap<>();
                    
                    List<String> expandList = new ArrayList<>();
                    expandList.add("sources");
                    retrieveParams.put("expand", expandList);
                       
        try {
            
            customer = Customer.retrieve(carte.getText(),retrieveParams,null);
            Card card =(Card) customer.getSources().retrieve(customer.getDefaultSource());
            System.out.println(customer.toString());
            chargeparam.put("amount",montant.getText());
                 chargeparam.put("currency","usd");
                 chargeparam.put("customer",customer.getId());
                 Charge.create(chargeparam);
            
        } catch (StripeException ex) {
            Logger.getLogger(PaiementController.class.getName()).log(Level.SEVERE, null, ex);
        }
                       
        
          envoyerSMS();
      
        } 
        

    @FXML
    private void retour(ActionEvent event) {
        try {
            Stage stage = (Stage) btnretour.getScene().getWindow();
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

    @FXML
    private void deconnecter(ActionEvent event) {
        try {
            Stage stage = (Stage) dec.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Motdepasse.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MotdepasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void envoyerSMS(){
        Twilio.init("ACe3140c7406949824edc08d44bddf6e51", "445fce8e72df408d1e7b8543df2071bd");
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(txtTel.getText()),
                new com.twilio.type.PhoneNumber("+15124003293"),
                "Votre don a été reçu avec succés sous l'addresse mail "+txtNomP.getText()+", Merci pour votre générosité")
            .create();

        System.out.println(message.getSid());
    }
    
}
