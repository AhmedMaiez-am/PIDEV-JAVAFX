/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Cours;
import Entities.Enfant;
import Entities.MaitresseValidation;
import Services.ServiceCours;
import Services.ServiceDirecteur;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class DashbordController implements Initializable {

    @FXML
    private Button listeD;
    @FXML
    private Button listeEn;
    @FXML
    private Button fiche;
    @FXML
    private Button dec;
    @FXML
    private Button btnRecomp;
    @FXML
    private PieChart stat;
    ObservableList<PieChart.Data> piechartdata;
    @FXML
    private Button btnTelecharger;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void listerDemande(ActionEvent event) {
        try {
            Stage stage = (Stage) listeD.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListeMaitresse.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeMaitresseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void listerEnfant(ActionEvent event) {
        try {
            Stage stage = (Stage) listeEn.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListeEnfant.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeEnfantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void afficher(ActionEvent event) {
        int k ;
        piechartdata = FXCollections.observableArrayList();
        ServiceDirecteur d = new ServiceDirecteur();
        MaitresseValidation mv = new MaitresseValidation();
        ServiceCours sc = new ServiceCours();
        k = d.count(mv);
        int p = (int)k;
        
        Enfant v = new Enfant();
       int  o = d.count(v);

        Cours c = new Cours();
        int oo = sc.count(c);
        
        piechartdata.add(new PieChart.Data("Nombre des tuteurs = "+String.valueOf(p), p));
        piechartdata.add(new PieChart.Data("Nombre des enfants = "+String.valueOf(o), o));
        piechartdata.add(new PieChart.Data("Nombre des cours = "+String.valueOf(oo), oo));
        stat.setData(piechartdata);
    }

    @FXML
    private void deconncter(ActionEvent event) {
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

    @FXML
    private void redirectRecomp(ActionEvent event) {
        try {
            Stage stage = (Stage) dec.getScene().getWindow();
             stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void download(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new ExtensionFilter("PNG files", "*.PNG"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        File file = chooser.showSaveDialog(stage);
            if (file != null) {
                try {
                    Image img = stat.snapshot(null, null);
                    BufferedImage imgData = SwingFXUtils.fromFXImage(img, null);
                    ImageIO.write(imgData, "png", file);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }               
    }
    
}
}
