/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Cours;
import Entities.InventaireCours;
import Entities.Parents;
import Services.ServiceCours;
import Services.ServiceInventaireCours;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.Printer;
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
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class InventaireController implements Initializable {
    Connection cnx;

    @FXML
    private ListView<InventaireCours> tbAfficher;
    @FXML
    private Button btnAffiche;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnList;
    @FXML
    private Button btnList1;
    @FXML
    private Button btnImp;
    @FXML
    private Label lbl1;
    @FXML
    private Button btnRet;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherCours(ActionEvent event) throws SQLException {
        //Appel du service inventaire
        ServiceInventaireCours si = new ServiceInventaireCours();
        
        //Création du composant graphique TableView
        tbAfficher.setItems((ObservableList<InventaireCours>) si.afficherCours());
    }

    @FXML
    private void supprimerCours(ActionEvent event) {
        ServiceInventaireCours si = new ServiceInventaireCours();
        
        InventaireCours cc = tbAfficher.getSelectionModel().getSelectedItem();
        cc.getIdCc();
        cc.getNomC();
        cc.getTypec();
        cc.getDescC();
        si.supprimerCours(cc);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText("Succées de suppression");
        alert.setContentText("Le cours choisi a été supprimé de votre inventaire avec succés !");
        alert.showAndWait();  
    }

    @FXML
    private void changeList(ActionEvent event) {
        try {
        Stage stage = (Stage) btnList.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("ListeCours.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeCours.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void changeRecherche(ActionEvent event) {
        try {
        Stage stage = (Stage) btnList1.getScene().getWindow();
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
    private void imprimer(ActionEvent event) throws IOException {
        InventaireCours c = tbAfficher.getSelectionModel().getSelectedItem();
        lbl1.setText(c.getNomC());
        lbl2.setText(c.getTypec());
        lbl3.setText(c.getDescC());
        
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
        contentStream.showText("** Imprimé du cours **");
        contentStream.endText();
        

        PDFont font3 = PDType1Font.TIMES_ROMAN;
        contentStream.beginText();
        contentStream.setFont(font3, 15);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(10, 500);
        contentStream.showText("Le nom du cours :" + lbl1.getText());
        contentStream.endText();
        
        PDFont font1 = PDType1Font.TIMES_ROMAN;
        contentStream.beginText();
        contentStream.setFont(font1, 15);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(10, 480);
        contentStream.showText("Le type du cours  :" + lbl2.getText() );
        contentStream.endText();
        
        PDFont font2 = PDType1Font.TIMES_ROMAN;
        contentStream.beginText();
        contentStream.setFont(font2, 15);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(10, 460);
        contentStream.showText("La description du cours  :" + lbl3.getText() );
        contentStream.endText();
        contentStream.close();

        
         doc.save("Imprimé du cours.pdf");
        doc.close();
        System.out.println("pdf saved");
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Imprimé du cours");
        alert.setHeaderText("Votre cours a été sauvegardé avec succés");
        alert.showAndWait();  
        
        
        
        
        
        
       /* PrinterJob job = PrinterJob.createPrinterJob();
        if(job != null){
          //job.showPrintDialog(null);
          job.printPage(lbl1);
          job.endJob();
    }*/
    }

    private void changeListContes(ActionEvent event) {
        try {
        Stage stage = (Stage) btnList1.getScene().getWindow();
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
    

