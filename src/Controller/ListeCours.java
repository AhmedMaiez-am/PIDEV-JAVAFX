/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Authentification;
import Entities.Cours;
import Entities.InventaireCours;
import Entities.Parents;
import Services.ServiceAuthentification;
import Services.ServiceCours;
import Services.ServiceParent;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author maiez
 */
public class ListeCours implements Initializable {
    Connection cnx;
    
    
    
    @FXML
    private Button afich;
    @FXML
    private ListView<Cours> tbAffiche;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnPayer;
    @FXML
    private TextField txtNomP;
    @FXML
    private Label txtMoney;
    @FXML
    private Button btnChange;
    @FXML
    private Button btnChange1;
    @FXML
    private Label lblNom;
    @FXML
    private Label lblType;
    @FXML
    private Label lblDesc;
    @FXML
    private Label lblPrix;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;
    @FXML
    private Label lbl4;
    @FXML
    private Label lblinfo;
    @FXML
    private AnchorPane p1;
    @FXML
    private AnchorPane p2;
    @FXML
    private Label lblinfooo;
    @FXML
    private Button btnAchat;
    @FXML
    private Button btnRetour;
    @FXML
    private AnchorPane p3;
    @FXML
    private Button btnR;
    @FXML
    private Button btnImp;
    @FXML
    private Label lblM;
    @FXML
    private Button btnQR;
    @FXML
    private Button btnQuiz;
    @FXML
    private TextField carte;
    @FXML
    private TextField txtTel;
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void afficherCours(ActionEvent event) throws SQLException {
        //Appel du service cours
        ServiceCours sc = new ServiceCours();
        
        //Création du composant graphique TableView
            tbAffiche.setItems((ObservableList<Cours>) sc.afficherCours());
            
        tbAffiche.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2){
                    Cours c = tbAffiche.getSelectionModel().getSelectedItem();
                    lblNom.setText(c.getNom());
                    lblType.setText(c.getType());
                    lblDesc.setText(c.getDesc());
                    lblPrix.setText(c.getPrixC());
                    lblNom.setVisible(true);
                    lblType.setVisible(true);
                    lblDesc.setVisible(true);
                    lblPrix.setVisible(true);
                    lbl1.setVisible(true);
                    lbl2.setVisible(true);
                    lbl3.setVisible(true);
                    lbl4.setVisible(true);
                    lblinfooo.setVisible(false);
                    lblinfo.setVisible(true);
                    btnAchat.setVisible(true);
                    btnAjout.setVisible(true);
                    //p1.setVisible(false);
                    p2.setVisible(true);
                            
                }
            }
            
        });
    }

    @FXML
    private void ajouterCours(ActionEvent event) {
        ServiceCours sc = new ServiceCours();
        
        Cours c = tbAffiche.getSelectionModel().getSelectedItem();
        if (c.getPrixC().equals("Gratuit")){
        InventaireCours cc = new InventaireCours();
        cc.setNomC(c.getNom());
        cc.setTypec(c.getType());
        cc.setDescC(c.getDesc());
        sc.ajouterCours(cc);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout à la base");
        alert.setHeaderText("Succées d'ajout");
        alert.setContentText("Le cours choisi a été ajouter à votre inventaire avec succés !");
        alert.showAndWait(); 
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout à la base");
        alert.setHeaderText("Echec d'ajout");
        alert.setContentText("Le cours choisi est payant, vous devez payer les frais du cours afin de l'ajouter à l'inventaire !");
        alert.showAndWait(); 
            
        }
                               
    }
    

    @FXML
    private void payerCours(ActionEvent event) {
     
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
            chargeparam.put("amount",lblPrix.getText());
                 chargeparam.put("currency","usd");
                 chargeparam.put("customer",customer.getId());
                 Charge.create(chargeparam);
            
        } catch (StripeException ex) {
            Logger.getLogger(PaiementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succés d'achat");
            alert.setHeaderText("Paiement Valide");
            alert.setContentText("Le cours a été ajouté à votre inventaire, un SMS de confirmation sera envoyé à votre numéro télèphone !");
            alert.showAndWait();
            
            //envoyerSMS();
        
    }
    public void envoyerSMS(){
        Twilio.init("ACe3140c7406949824edc08d44bddf6e51", "445fce8e72df408d1e7b8543df2071bd");
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(txtTel.getText()),
                new com.twilio.type.PhoneNumber("+15124003293"),
                "Votre paiement a été reçu avec succés sous l'addresse mail "+txtNomP.getText()+", Merci pour votre générosité")
            .create();

        System.out.println(message.getSid());
    }


    @FXML
    private void changeInventaire(ActionEvent event){
        try {
        Stage stage = (Stage) btnChange.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("Inventaire.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(InventaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void changeRecherche(ActionEvent event) {
        try {
        Stage stage = (Stage) btnChange1.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("RechercheCours.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(RechercheCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirectAchat(ActionEvent event) {
        p1.setVisible(false);
        p2.setVisible(false);
        p3.setVisible(true);
    }

    @FXML
    private void redirectHome(ActionEvent event) {
        try {
        Stage stage = (Stage) btnR.getScene().getWindow();
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

    @FXML
    private void redirectR(ActionEvent event) {
        p3.setVisible(false);
        p2.setVisible(true);
        p1.setVisible(true);
    }

    @FXML
    private void imprimer(ActionEvent event) throws IOException {
        Cours c = tbAffiche.getSelectionModel().getSelectedItem();
        
        
        
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
        contentStream.showText("** Reçu de paiement **");
        contentStream.endText();
        

        PDFont font3 = PDType1Font.TIMES_ROMAN;
        contentStream.beginText();
        contentStream.setFont(font3, 15);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(10, 500);
        contentStream.showText("Votre adresse Email :" + txtNomP.getText() );
        contentStream.endText();
        
        PDFont font11 = PDType1Font.TIMES_ROMAN;
        contentStream.beginText();
        contentStream.setFont(font11, 15);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(10, 480);
        contentStream.showText("Le nom du cours  :" + lblNom.getText());
        contentStream.endText();
        
        PDFont font12 = PDType1Font.TIMES_ROMAN;
        contentStream.beginText();
        contentStream.setFont(font12, 15);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(10, 460);
        contentStream.showText("La description du cours  :" + lblDesc.getText());
        contentStream.endText();
        
        PDFont font1 = PDType1Font.TIMES_ROMAN;
        contentStream.beginText();
        contentStream.setFont(font1, 15);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(10, 440);
        contentStream.showText("Le prix du cours  :" + lblPrix.getText() +" DT" );
        contentStream.endText();
        
        PDFont font2 = PDType1Font.TIMES_ROMAN;
        contentStream.beginText();
        contentStream.setFont(font2, 15);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(10, 420);
        contentStream.showText("Le code client   :" + carte.getText() );
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
    private void QRCode(ActionEvent event) throws IOException {
        String str = lblNom.getText()+", "+lblType.getText()+", "+lblDesc.getText()+", "+lblPrix.getText();
        try {
            String imageFormat = "png";
            String outputFileName = "D:\\ESPRIT\\3eme\\PI\\work\\QRCode\\Cours\\"+lblNom.getText()+"." + imageFormat;
            BitMatrix matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 500, 500);
            MatrixToImageWriter.writeToPath(matrix, imageFormat, Paths.get(outputFileName));
        } catch (WriterException ex) {
            Logger.getLogger(ListeCours.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("QR Code");
        alert.setHeaderText("Gestion du QR Code");
        alert.setContentText("Votre QR Code a été géneré avec succées !");
        alert.showAndWait();  
    }

    @FXML
    private void redirectQuiz(ActionEvent event) {
        try {
        Stage stage = (Stage) btnQuiz.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("StudentMainScreenFxml.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(RechercheCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}