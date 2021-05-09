/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Conseil;
import Entities.Maitresse;
import Entities.Parents;
import Services.ServiceConseil;
import Utils.MaConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class CCPController implements Initializable {

    @FXML
    private TableView<Conseil> tabconseil;
    @FXML
    private TableColumn<Conseil, Integer> nomco;
    @FXML
    private TableColumn<Conseil, Integer> prenomco;
    @FXML
    private TableColumn<Conseil, Integer> conseilre;

    /**
     * Initializes the controller class.
     */
    ObservableList<Conseil> conseillist= FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       /* try {
            ServiceConseil sc = new ServiceConseil();
            Maitresse m = new Maitresse();
            Parents p = new Parents();
            sc.afficherCM(m);
            sc.afficherC(p);
        } catch (SQLException ex) {
            Logger.getLogger(CCPController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        afficher_PC();
    }    
     public void afficher_PC()
    {    
        try {
            Connection cnx = MaConnection.getInstance().getConnection();
            String query = "SELECT * FROM conseil WHERE type ='Professeur'";//jointure avec id_enfant pour recevoir juste les reclamation du prof pour son enfant
            Statement st;
            ResultSet rs;
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Conseil conseil;
            while (rs.next()) {
                conseil = new Conseil(rs.getString("nomc"), rs.getString("prenomc"), rs.getString("conseil"));
                conseillist.add(conseil);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
        
        nomco.setCellValueFactory(new PropertyValueFactory<>("nomc"));
        prenomco.setCellValueFactory(new PropertyValueFactory<>("prenomc"));
        conseilre.setCellValueFactory(new PropertyValueFactory<>("conseil"));
        
        //button tabview on action mailling appyuier sur le button ok renvoit automique un aquiser de reception .
        
        
        tabconseil.setItems(conseillist);
    }
    
}
