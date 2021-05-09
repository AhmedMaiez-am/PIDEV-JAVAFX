/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Cours;
import Entities.typecours;
import Services.ServiceCours;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class AjoutCoursController implements Initializable {

    @FXML
    private TextField tfprenom;
    private Label labaffiche;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private TextField tfnom;
    @FXML
    private Button browse;
    @FXML
    private TextField txtc;
    @FXML
    private Label msger;
    @FXML
    private TextField tfprix;
    @FXML
    private Button btnRet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceCours sp=new ServiceCours();
            List<typecours> liste;
        
       liste = new ArrayList<typecours>(sp.lister());
      //  list.getItems().addAll(liste);
        ObservableList<typecours> coursList=FXCollections.observableArrayList(liste);
      choiceBox.setItems(coursList);
       // choiceBox.setItems((ObservableList<typecours>)sp.lister());

  browse.setOnMouseClicked((MouseEvent e)->{
            
        final FileChooser fileChooser = new FileChooser();
        final Stage stage = null;

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
          txtc.setText(file.toURI().toString());
         String s;
         s=txtc.getText();
         
          
            try {
              // desktop.open(file);
             //  uploadtp(tfnom.getText(),"C:/Users/asus/Desktop/p.pdf");
             uploadtp(tfnom.getText(),s.substring(6));
            } catch (IOException ex) {
                Logger.getLogger(AjoutCoursController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void load(ActionEvent event) throws IOException {
        try {
        Stage stage = (Stage) btnRet.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("MainCours.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MainCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouterCours(ActionEvent event) {
        ServiceCours sp=new ServiceCours();
   Cours p =new Cours();
   
   p.setNom(tfnom.getText());
   
   p.setDesc(tfprenom.getText() );
   if (tfprenom.getText().isEmpty())
   {
       msger.setText("veulliez saisir un nom");
       
   }else 
   {  
   p.setType(choiceBox.getValue().toString());
   p.setCours(txtc.getText());
   p.setPrixC(tfprix.getText());
   sp.AddCours(p);
   Alert alert =new Alert (Alert.AlertType.INFORMATION);
   alert.setTitle("Ajout du cours");
   alert.setHeaderText(null);
   alert.setContentText("Ce cours a été ajouté avec succés !");
   alert.showAndWait();
   }
    }

    
}
