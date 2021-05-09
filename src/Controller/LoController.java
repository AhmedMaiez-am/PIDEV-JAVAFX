/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Login;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class LoController implements Initializable {

    @FXML
    private Label ss;
    @FXML
    private TextField Login;
    @FXML
    private TextField Password;
    @FXML
    private ImageView user;
    @FXML
    private ImageView lock;
    @FXML
    private Button okk;
    @FXML
    private CheckBox prof;
    @FXML
    private CheckBox Parent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ok(ActionEvent event) throws IOException {
        if (prof.isSelected()){
            
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Conseil.fxml"));
       Parent root = loader.load();
        ConseilController conseilController = loader.getController();
        
        Stage stage = new Stage();
       
        stage.setScene(new Scene(root));
        stage.setTitle("Fonctionnalite des professeurs");
        
        stage.show();
        
    }
        else if(Parent.isSelected()){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Document.fxml"));
       Parent root = loader.load();
        DocumentController reclamationController = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
         stage.setTitle("Fonctionnalite des parents");
         
        stage.show();
    }
    }

    @FXML
    private void ch(ActionEvent event) {
    }
    
}
