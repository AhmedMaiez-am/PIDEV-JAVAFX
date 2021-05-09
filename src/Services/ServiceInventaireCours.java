/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.InventaireCours;
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
import InterfacesServices.IServiceInventaireCours;

/**
 *
 * @author maiez
 */
public class ServiceInventaireCours implements IServiceInventaireCours {
    Connection cnx;
    ObservableList<InventaireCours> items = FXCollections.observableArrayList();

    public ServiceInventaireCours() {
        cnx = MaConnection.getInstance().getConnection();
    }

    @Override
    public List<InventaireCours> afficherCours() throws SQLException {
        Statement stn = cnx.createStatement();
             String query ="SELECT * FROM `InventaireCours`";
             ResultSet rs = stn.executeQuery(query);
        
            while (rs.next()){
                InventaireCours m = new  InventaireCours() ;
    
                 m.setIdCc(rs.getInt("IdCc"));
                 m.setNomC(rs.getString("nomC"));
                 m.setTypec(rs.getString("typeCc"));
                 m.setDescC(rs.getString("descriptionCc"));
                

                 items.addAll(m);

           
                 
            }
            return items;
    }

    @Override
    public void supprimerCours(InventaireCours c) {
        try  {
        Statement stm=cnx.createStatement();
         String query = " DELETE FROM `InventaireCours` WHERE idCc='"+c.getIdCc()+"'";
            stm.executeUpdate(query);
             } catch (SQLException ex) {
            Logger.getLogger(ServiceInventaireCours.class.getName()).log(Level.SEVERE, null, ex);
   
       }
    }


}




    

