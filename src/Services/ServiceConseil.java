/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Conseil;
import Entities.Maitresse;
import Entities.Parents;
import InterfacesServices.IserviceConseil;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author maiez
 */
public class ServiceConseil implements IserviceConseil{
    Connection cnx;
    ObservableList<Parents> itemsP = FXCollections.observableArrayList();
    ObservableList<Maitresse> itemsM = FXCollections.observableArrayList();
    ObservableList<Conseil> conseils = FXCollections.observableArrayList();

    public ServiceConseil() {
         cnx = MaConnection.getInstance().getConnection();
    }
    
    

    @Override
    public void AddConseil(Conseil c) {
        String query= "INSERT INTO `conseil`(`type`, `nomc`, `prenomc`, `conseil`) VALUES (?,?,?,?)";
        try {
            PreparedStatement stm=cnx.prepareStatement(query);
        
       stm.setString(1, c.getType());
        stm.setString(2,c.getNomc());
         stm.setString(3,c.getPrenomc());
          stm.setString(4,c.getConseil());
           
        stm.executeUpdate();
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Conseil> AfficherConseil() throws SQLException {
        Statement stm=cnx.createStatement();
              String query="select * from `conseil`";
            ResultSet rst =stm.executeQuery(query);
            List<Conseil> conseils =new ArrayList<>();
            while (rst.next())
            {
                Conseil C =new Conseil();
                C.setIdc(rst.getInt("idc"));
                C.setNomc(rst.getString("nomc"));
                C.setPrenomc(rst.getString("prenomc"));
                 C.setConseil(rst.getString("conseil"));
                conseils.add(C);
            } 
           
   return conseils;
    }

    @Override
    public void modifierConseil(Conseil c) {
        //PreparedStatement ste;
         try {
             Statement stn = cnx.createStatement();
             String query = "UPDATE conseil SET conseil= '"+c.getConseil()+"' where nomc ='"+c.getNomc()+"' AND prenomc ='"+c.getPrenomc()+"'";
             stn.executeUpdate(query);
             
             System.out.println("conseil modifié");
             
         } catch (SQLException ex) {
             Logger.getLogger(ServiceConseil.class.getName()).log(Level.SEVERE, null, ex);
         }  
    }

    @Override
    public void supprimerConseil(Conseil c) {
        PreparedStatement ste;
        String req="delete from conseil where idc=?";
       
        try {
            ste=cnx.prepareStatement(req);
            ste.setInt(1,c.getIdc() );
            int i=ste.executeUpdate();
            System.out.println(i +" conseil supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Conseil> rechercherconseil(Conseil c) {
         Statement st;
        try {
            st = cnx.createStatement();
             ResultSet rs=st.executeQuery("SELECT * FROM conseil where nomc like '%"+c.getNomc()+ "%'");
     while(rs.next()){
        Conseil cs=new Conseil();
         cs.setNomc(rs.getString("nomc"));
         cs.setPrenomc(rs.getString("prenomc"));
         cs.setConseil(rs.getString("conseil"));
         conseils.add(cs);
     }
        } catch (SQLException ex) {
            
        Logger.getLogger(ServiceConseil.class.getName()).log(Level.SEVERE, null, ex);
        }
    return conseils;
    }

    @Override
    public void afficherC(Parents p) throws SQLException {
         Statement stn;
        stn = cnx.createStatement();
        String sql = "select nomP,prenomP from parent";
        ResultSet rs = stn.executeQuery(sql);
            while (rs.next()){ 
                
                p.setNomP(rs.getString("nomP"));
                p.setPrenomP(rs.getString("prenomP"));
     
            }    
    }

    @Override
    public void afficherCM(Maitresse m) throws SQLException {
        Statement stn;
        stn = cnx.createStatement();
        String sql = "select nomM,prenomM from maitresse";
        ResultSet rs = stn.executeQuery(sql);
        
            while (rs.next()){  
                m.setNom(rs.getString("nomM"));
                m.setPrenom(rs.getString("prenomM"));
               
            }    
    }

    
}
