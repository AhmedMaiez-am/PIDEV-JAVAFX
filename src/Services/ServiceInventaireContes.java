/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.InventaireContes;
import InterfacesServices.IServiceInventaireContes;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author maiez
 */
public class ServiceInventaireContes implements IServiceInventaireContes {

    Connection cnx;
    ObservableList<InventaireContes> items = FXCollections.observableArrayList();

    public ServiceInventaireContes() {
        cnx = MaConnection.getInstance().getConnection();
    }
    
    
    @Override
    public List<InventaireContes> afficherContes() throws SQLException {
        Statement stn = cnx.createStatement();
             String query ="SELECT * FROM `InventaireContes`";
             ResultSet rs = stn.executeQuery(query);
        
            while (rs.next()){
                InventaireContes m = new  InventaireContes() ;
    
                 m.setTitreC(rs.getString("titreC"));
                 m.setAuteurC(rs.getString("auteurC"));
                

                 items.addAll(m);

           
                 
            }
            return items;
    }

    @Override
    public void supprimerContes(InventaireContes c) {
        try  {
        Statement stm=cnx.createStatement();
         String query = " DELETE FROM `InventaireContes` WHERE titreC='"+c.getTitreC()+"'";
            stm.executeUpdate(query);
             } catch (SQLException ex) {
            Logger.getLogger(ServiceInventaireCours.class.getName()).log(Level.SEVERE, null, ex);
   
       }
    }
    
}
