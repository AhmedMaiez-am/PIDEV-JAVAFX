/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Cours;
import Services.ServiceCours;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class MainCoursController implements Initializable {

    @FXML
    private ListView<Cours> list;
    @FXML
    private Button btnrech;
    @FXML
    private Button exporter;
    private TextField txtx;
    
    private ObservableList<Cours> dataList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane p1;
    @FXML
    private Label lblType;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;
    @FXML
    private Label lblNom;
    @FXML
    private Label lblDesc;
    @FXML
    private Label lblPrix;
    @FXML
    private Label lbl4;
    @FXML
    private AnchorPane p;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnMod;
    @FXML
    private Button btnAjout;
    @FXML
    private ImageView btnRetour;
    @FXML
    private Button btnRet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceCours su =new ServiceCours();
            List<Cours> liste;
        
            liste = new ArrayList<Cours>(su.afficherCours());
            list.getItems().addAll(liste);
            
            list.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2){
                    Cours c = list.getSelectionModel().getSelectedItem();
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
                    p.setVisible(false);
                    p1.setVisible(true);
                            
                }
            }
            
        });
        } catch (SQLException ex) {
            Logger.getLogger(MainCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void change(KeyEvent event) throws SQLException {
        ServiceCours su =new ServiceCours();
            List<Cours> liste;
        
            liste = new ArrayList<Cours>(su.afficherCours());
        list.getItems().addAll(liste);
      // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<Cours> filteredData = new FilteredList<>(dataList, b -> true);

    //Set the filter Predicate whenever the filter changes.
    txtx.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(client ->{
            // If filter text is empty, display all persons.
            if(newValue == null || newValue.isEmpty()){
                return true;
            }

            // Compare first name and last name of every client with filter text.
            String lowerCaseFilter = newValue.toLowerCase();

            if(client.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true; //filter matches first name
            }else if(client.getDesc().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true; //filter matches last name
            }
            return false; //Does not match
        });
    });
   // 3. Wrap the FilteredList in a SortedList.
            SortedList<Cours> sortedData = new SortedList<>(filteredData);

    //put the sorted list into the listview
    list.setItems(sortedData);
    }

    @FXML
    private void load2(ActionEvent event) throws IOException {
        try {
        Stage stage = (Stage) btnSupp.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("SuppCours.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(SuppCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void load1(ActionEvent event) throws IOException {
         try {
        Stage stage = (Stage) btnMod.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("ModCours.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(ModCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadajout(ActionEvent event) throws IOException {
         try {
        Stage stage = (Stage) btnAjout.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("AjoutCours.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void load3(ActionEvent event) throws IOException {
         try {
        Stage stage = (Stage) btnrech.getScene().getWindow();
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
    private void exporter(ActionEvent event) throws SQLException, IOException, FileNotFoundException {
        ServiceCours se =new ServiceCours();
        // cours c=new cours();
        se.exportCours();
        Alert alert =new Alert (Alert.AlertType.INFORMATION);
   alert.setTitle("Exportation des cours");
   alert.setHeaderText(null);
   alert.setContentText("La liste des cours a été exporté avec succés !");
   alert.showAndWait();
    }


    @FXML
    private void retour(ActionEvent event) {
        try {
        Stage stage = (Stage) btnRet.getScene().getWindow();
        stage.close();
        
        FXMLLoader fxmlloader = new FXMLLoader (getClass().getResource("GestionCC.fxml"));
        Parent root;
        
            root = (Parent) fxmlloader.load();
        
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionCCController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
