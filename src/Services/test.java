/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Maitresse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maiez
 */
public class test {
    private Connection cnn;
    
    
    
    public ArrayList<Maitresse> Mait() {
            ArrayList<Maitresse> Liste = new ArrayList<>();

try {
            Statement stm = cnn.createStatement() ;
            String req = "select etat,cin,nomM,prenomM,emailMaitresse,dateDemande,path,cv from maitresse";
            ResultSet res = stm.executeQuery(req);
            while (res.next()) {
            
                 String etat =res.getString("etat");
                 String cin =res.getString("cin");
                 String nom = res.getString("nomM");
                 String prenom = res.getString("prenomM");
                 String email = res.getString("emailMaitresse");
                 String image = res.getString("path");
                 Timestamp dated = res.getTimestamp("dateDemande");
                 String cv = res.getString("cv");
                 Maitresse m = new  Maitresse(etat,cin,nom,prenom,email,dated,image,cv) ;
                 Liste.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Liste ;    }
    
}
