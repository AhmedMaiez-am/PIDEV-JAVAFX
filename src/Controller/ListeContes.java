/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Contes;
import Entities.InventaireContes;
import Services.ServiceContes;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class ListeContes implements Initializable {
Connection cnx;
    @FXML
    private ListView<Contes> lstContes;
    @FXML
    private Button btnAffiche;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnInv;
    @FXML
    private Button btnRet;
    @FXML
    private Button btnQR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherContes(ActionEvent event) throws SQLException {
        
        ServiceContes sc = new ServiceContes();
        
        lstContes.setItems((ObservableList<Contes>) sc.afficherContes());
    }

    @FXML
    private void ajouterContes(ActionEvent event) {
        ServiceContes sc = new ServiceContes();
        
        Contes c = lstContes.getSelectionModel().getSelectedItem();
        InventaireContes cc = new InventaireContes();
        cc.setAuteurC(c.getAuteur());
        cc.setTitreC(c.getTitre());
        sc.ajouterContes(cc);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout à la base");
        alert.setHeaderText("Succées d'ajout");
        alert.setContentText("Le cours choisi a été ajouter à votre inventaire avec succés !");
        alert.showAndWait(); 
       
    }

    @FXML
    private void redirectInventaireContes(ActionEvent event) {
        try {
        Stage stage = (Stage) btnInv.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("InventaireContes.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(InventaireContesController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void QRCode(ActionEvent event) throws IOException {
        Contes t = lstContes.getSelectionModel().getSelectedItem();
        String str = t.getTitre()+", "+t.getAuteur();
        try {
            String imageFormat = "png";
            String outputFileName = "D:\\ESPRIT\\3eme\\PI\\work\\QRCode\\Contes\\"+t.getTitre()+"." + imageFormat;
            BitMatrix matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 500, 500);
            MatrixToImageWriter.writeToPath(matrix, imageFormat, Paths.get(outputFileName));
        } catch (WriterException ex) {
            Logger.getLogger(ListeContes.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("QR Code");
        alert.setHeaderText("Gestion du QR Code");
        alert.setContentText("Votre QR Code a été géneré avec succées !");
        alert.showAndWait();  
    }
    
}
