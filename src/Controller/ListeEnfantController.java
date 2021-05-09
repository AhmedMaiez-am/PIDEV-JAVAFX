/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Enfant;
import Entities.Maitresse;
import Services.ServiceParent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class ListeEnfantController implements Initializable {

    @FXML
    private ListView<Enfant> lstEnf;
    @FXML
    private Button btnEnf;
    @FXML
    private Button btnretour;
    @FXML
    private AnchorPane p1;
    @FXML
    private AnchorPane p2;
    @FXML
    private Button btnList;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;
    @FXML
    private ImageView imagee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherEnfant(ActionEvent event) {
        ServiceParent pr = new ServiceParent() ;
             
         lstEnf.setItems((ObservableList<Enfant>) pr.AfficherEnfant());
           lstEnf.setOnMouseClicked(new EventHandler <MouseEvent>(){
        @Override
        public void handle (MouseEvent click){
        if (click.getClickCount()==2){
            
            p1.setVisible(false);
            p2.setVisible(true);
            Enfant m = lstEnf.getSelectionModel().getSelectedItem();
            lbl1.setText(m.getNomEnfant());
            lbl2.setText(m.getPrenomEnfant());
            lbl3.setText(m.getAge());
            
            
            String c = m.getImg(); 
            System.out.println(c);
            File f = new File(c);
            Image img = new Image(f.toURI().toString());
            imagee.setImage(img);
            

            
            

           /* String b = m.getCv(); 
           System.out.println(b);
           File f1 = new File(b);
            try {
                Desktop.getDesktop().open(f1);
            } catch (IOException ex) {
                Logger.getLogger(ListeMaitresseController.class.getName()).log(Level.SEVERE, null, ex);
            }
           */
        }
    }
    });  
            
       /* PrinterJob job = PrinterJob.createPrinterJob();
        //.setText("ghada ");
        if(job != null){
          job.printPage(lstEnf); 
          job.endJob();
        }*/
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            
             Stage stage = (Stage) btnretour.getScene().getWindow();
             stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashbord.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage1 = new Stage();
                        stage1.setScene(new Scene(root));
                        stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirecListe(ActionEvent event) {
        p2.setVisible(false);
        p1.setVisible(true);
    }
    
}
