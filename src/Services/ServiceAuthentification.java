/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Authentification;
import Entities.Parents;
import InterfacesServices.IServiceAuthentification;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maiez
 */
public class ServiceAuthentification implements IServiceAuthentification {
     
    Connection cnx;
    private PreparedStatement ste;


    public ServiceAuthentification() {
                cnx = MaConnection.getInstance().getConnection();
    }
    
    @Override
    public void AjouterAuthParent(Parents p) {
        try {
            
            String req ="INSERT INTO auth (emailP,passP)"+"values (?,?)";
            ste = cnx.prepareStatement(req);
            ste.setString(1, p.getEmailP());
            ste.setString(2,p.getPasswordP());

            ste.executeUpdate();
            System.out.println("Authentification ajoutée");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void SupprimerAuthParent() {
        String req="truncate table auth ";
       
        try {
            ste=cnx.prepareStatement(req);
            ste.executeUpdate();
            System.out.println("Authentification suprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void SelectAuthParent(Authentification p) {
         try {
            Statement stn = cnx.createStatement();
            String query ="SELECT emailP FROM `auth`";
            stn.executeQuery(query);
            ResultSet rs = stn.executeQuery(query);
            while (rs.next()){
                p.setEmailP(rs.getString("emailP"));
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
