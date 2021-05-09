/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Conseil;
import Entities.Maitresse;
import Entities.Parents;
import Entities.Reclamation;
import InterfacesServices.IserviceReclamation;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author maiez
 */


/**
 *
 * @author maiez
 */
public class ServiceReclamation implements IserviceReclamation {
        Connection cnx;
    ObservableList<Parents> itemsP = FXCollections.observableArrayList();
    ObservableList<Maitresse> itemsM = FXCollections.observableArrayList();
        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();


    public ServiceReclamation() {
         cnx = MaConnection.getInstance().getConnection();
    }
    
    

    @Override
    public void AddReclamation(Reclamation r) {
        Date actuelle =new Date();
       DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
       String date= dateformat.format(actuelle);
       String dc= date;
        String query= "INSERT INTO `reclamation`(`nom`, `prenom`, `reclamation`, `type`, `email`, `pp` , `date_creation`) VALUES (?,?,?,?,?,?,'"+dc+"')";
        try {
            PreparedStatement stm=cnx.prepareStatement(query);
        
      // String query= "INSERT INTO `reclamation`(`nom`, `prenom`, `reclamation`, `type`) VALUES ('"+r.getNom()+"','"+r.getPrenom()+"','"+r.getReclamation()+"','"+r.getType()+"')";
       
        stm.setString(1,r.getNom());
         stm.setString(2,r.getPrenom());
          stm.setString(3,r.getReclamation());
           stm.setString(4,r.getType());
            stm.setString(5,r.getEmail());
            stm.setString(6,r.getPp());
           //  stm.setDate(7, r.getDate_creation());
        stm.executeUpdate();
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reclamation> AfficherReclamationpa() throws SQLException {
        Statement stm=cnx.createStatement();
              String query="select * from `reclamation` where pp='Parent' ";
            ResultSet rst =stm.executeQuery(query);
          
            while (rst.next())
            {
                Reclamation R =new Reclamation();
                R.setIdr(rst.getInt("idr"));
                R.setNom(rst.getString("nom"));
                R.setPrenom(rst.getString("prenom"));
                R.setReclamation(rst.getString("reclamation"));
                R.setType(rst.getString("type"));
                R.setEtat(rst.getString("etat"));
                R.setDate_creation(rst.getDate("date_creation"));
                        
                        reclamations.addAll(R);
            } 
           
  return reclamations;
    }

    @Override
    public List<Reclamation> AfficherReclamationpr() throws SQLException {
Statement stm=cnx.createStatement();
              String query="select * from `reclamation` where pp='Tuteur'  ";
            ResultSet rst =stm.executeQuery(query);
          
            while (rst.next())
            {
                Reclamation R =new Reclamation();
                R.setIdr(rst.getInt("idr"));
                R.setNom(rst.getString("nom"));
                R.setPrenom(rst.getString("prenom"));
                R.setReclamation(rst.getString("reclamation"));
                R.setType(rst.getString("type"));
                R.setEtat(rst.getString("etat"));
                R.setDate_creation(rst.getDate("date_creation"));
                        
                        reclamations.addAll(R);
            } 
           
  return reclamations;
    }

    @Override
    public void modifierReclamation(int id, Reclamation r) {
        PreparedStatement ste;
         try {
             ste=cnx.prepareStatement("UPDATE reclamation SET etat=? where idr=?");
             
             ste.setString(1, r.getEtat());

             ste.setInt(2,id);
             
             ste.executeUpdate();
             
             System.out.println("reclamation modifié");
             
         } catch (SQLException ex) {
             Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
         }  
    }

    @Override
    public void supprimerReclamation(Reclamation r) {
        PreparedStatement ste;
        String req="delete from reclamation where idr=?";//getid
       
        try {
            ste=cnx.prepareStatement(req);
            ste.setInt(1,r.getIdr() );
            int i=ste.executeUpdate();
            System.out.println(i+"reclamation suprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reclamation> rechercherreclamation(Reclamation r) {
         Statement st;
        try {
            st = cnx.createStatement();
             ResultSet rs=st.executeQuery("SELECT * FROM reclamation where nom like '%"+r.getNom()+ "%'");
     while(rs.next()){
        //Reclamation r=new Reclamation();
         r.setNom(rs.getString("nom"));
         r.setPrenom(rs.getString("prenom"));
         r.setReclamation(rs.getString("reclamation"));
         reclamations.add(r);
     }
        } catch (SQLException ex) {
            
        Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    return reclamations;
    }

    @Override
    public List<Conseil> rechercheconseil(String nom) {
         List<Conseil> myList = new ArrayList<Conseil>();
  Statement st;
       
        try { 
          st = cnx.createStatement();
          ResultSet rs=st.executeQuery("SELECT * from Conseil where Nomc like '%" + nom + "%'");
             
            while (rs.next()) {
                Conseil p2 = new Conseil();

                p2.setNomc(rs.getString(3));
                p2.setPrenomc(rs.getString(4));
                

                myList.add(p2);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("probleme dans les l'odre des champs dans BD");
        }
        return myList;
    }

    @Override
    public void afficher(Parents p) throws SQLException {
        Statement stn;
        stn = cnx.createStatement();
        String sql = "select nomP,prenomP,emailP from parent";
        ResultSet rs = stn.executeQuery(sql);
        
      
            while (rs.next()){
                p.setNomP(rs.getString("nomP"));
                p.setPrenomP(rs.getString("prenomP"));
                p.setEmailP(rs.getString("emailP"));

            }
   
    }

    @Override
    public void afficherM(Maitresse m) throws SQLException {
         Statement stn;
        stn = cnx.createStatement();
        String sql = "select nomM,prenomM,emailMaitresse from maitresse";
        ResultSet rs = stn.executeQuery(sql);
        
      
            while (rs.next()){
                m.setNom(rs.getString("nomM"));
                m.setPrenom(rs.getString("prenomM"));
                m.setEmail(rs.getString("emailMaitresse"));
                
            }  
    }
   
}
