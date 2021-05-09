/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Maitresse;
import Entities.MaitresseValidation;
import InterfacesServices.IServiceMaitresse;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author maiez
 */
public class ServiceMaitresse implements IServiceMaitresse {
    private Connection cnn;
    private PreparedStatement ste;
    ObservableList<Maitresse> items1 = FXCollections.observableArrayList();
    
    ObservableList<Maitresse> items2 = FXCollections.observableArrayList();

    public ServiceMaitresse() {
        cnn = MaConnection.getInstance().getConnection();
    }
    

    @Override
    public void AjouterDemande(Maitresse m) {
        Timestamp t = new Timestamp(System.currentTimeMillis());
        try {
          
            String req ="INSERT INTO maitresse (cin,nomM,prenomM,emailMaitresse,etat,dateDemande,path,cv)"+"values (?,?,?,?,?,?,?,?)";
            ste = cnn.prepareStatement(req);
            ste.setString(1, m.getCin());
            ste.setString(2, m.getNom());
            ste.setString(3, m.getPrenom());
            ste.setString(4, m.getEmail());
            ste.setString(5, "En Cours");
            ste.setString(6, String.valueOf(t));
            ste.setString(7, m.getImag());
            ste.setString(8, m.getCv());
            ste.executeUpdate();
            System.out.println("Demande ajoutée");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
    }

    @Override
    public boolean seConnecter1(MaitresseValidation m) {
         boolean b = true;
        try {
             Statement stm = cnn.createStatement() ;
             String sql = "select * from validation where loginM='"+m.getLoginM()+"' and passwordM='"+m.getPasswordM()+"' ";
             ResultSet res = stm.executeQuery(sql);
             if (res.next())
             { 
                 System.out.println("Maitresse Connectée"); 
             } else {
                 System.out.println("Maitresse Invalide");
                  b = false;
             }
        
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        } return b ;
    }

    @Override
    public void supprimerCompte(Maitresse o) {
        try {
        Statement st = cnn.createStatement() ;
        String sql = "Delete from maitresse where cin ='"+o.getCin()+"' and nomM ='"+o.getNom()+"'";
        st.executeUpdate(sql);
        System.out.println("Compte Supprimé");
    }
        catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage()); 
    }
    }

    @Override
    public void ModifierCompte(Maitresse o) {
        try {
        Statement st = cnn.createStatement() ;
        String sql = "update maitresse set nomM = '"+o.getNom()+"' , prenomM ='"+o.getPrenom()+"',emailMaitresse='"+o.getEmail()+"' where cin ='"+o.getCin()+"'";
        st.executeUpdate(sql);
        System.out.println("Modifié");
    }
        catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage()); 
    }
    }

    @Override
    public List<Maitresse> afficherM() throws SQLException {
        Statement stn = cnn.createStatement();
             String query ="SELECT * FROM `maitresse`";
             ResultSet rs = stn.executeQuery(query);
             
            while (rs.next()){
                Maitresse m = new  Maitresse() ;
                
                 m.setNom(rs.getString("nomM"));
                 m.setPrenom(rs.getString("prenomM"));
                 
                 items2.addAll(m);    
            }
            
            return items2;
    }
    
}
