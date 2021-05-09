/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Contes;
import Services.ServiceContes;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class AjoutContesController implements Initializable {

    @FXML
    private TextField txtcon;
    @FXML
    private TextField txtau;
    @FXML
    private TextField txtt;
    @FXML
    private Button browse;
    @FXML
    private Button btnRet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        browse.setOnMouseClicked((MouseEvent e)->{
        
        final FileChooser fileChooser = new FileChooser();
        final Stage stage = null;
        
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
          txtcon.setText(file.toURI().toString());
          String s;
         s=txtcon.getText();
            try {
                uploadtp(txtt.getText(),s.substring(6));
            } catch (IOException ex) {
                Logger.getLogger(AjoutContesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });     
    }    
    
    public void  uploadtp(String path,String fullpath) throws IOException
    {
        String server = "127.0.0.1";
        int port = 21;
        String user = "aziz";
        String pass = "admin";
 
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(server, port);
        ftpClient.login(user, pass);
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        // APPROACH #1: uploads first file using an InputStream
        File firstLocalFile = new File(fullpath);
        String firstRemoteFile = path;
        InputStream inputStream = new FileInputStream(firstLocalFile);
        System.out.println("Start uploading first file");
        boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
        inputStream.close();
        if (done) {
            System.out.println("The first file is uploaded successfully.");
        }
        // APPROACH #2: uploads second file using an OutputStream
        if (ftpClient.isConnected()) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    @FXML
    private void ajoutercontes(ActionEvent event) {
        ServiceContes sp=new ServiceContes();
   Contes c=new Contes();
   c.setTitre(txtt.getText());
   c.setAuteur(txtau.getText() );
   c.setContes(txtcon.getText());
   sp.Addcont(c);
   Alert alert =new Alert (AlertType.INFORMATION);
   alert.setTitle("Ajout du conte");
   alert.setHeaderText(null);
   alert.setContentText("Cette conte a été ajoutée avec succés !");
   alert.showAndWait();
    }

    @FXML
    private void load(ActionEvent event) throws IOException {
        try {
        Stage stage = (Stage) btnRet.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("MainContes.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MainContesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    

