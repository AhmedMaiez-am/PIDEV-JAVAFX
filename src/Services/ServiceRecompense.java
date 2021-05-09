/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Recompense;
import InterfacesServices.Iservicerecompense;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author maiez
 */
public class ServiceRecompense implements Iservicerecompense {
     Connection cnx;
    private PreparedStatement ste;
    ObservableList<Recompense> list=FXCollections.observableArrayList();
  
    
     public ServiceRecompense() {
        cnx=MaConnection.getInstance().getConnection();
        
    }
    
    @Override
    public void ajouterRecompense(Recompense r) {
       
        if(ControleString(r.getNomRec()) ){
         String req ="INSERT INTO recompense (`nomRec`, `nbr_point`) VALUES (?,?)";
        try {
            ste= cnx.prepareStatement(req);
            //ste.setInt(1, r.getIdRec());
             ste.setString(1, r.getNomRec());
             ste.setInt(2,r.getNbr_point());
             ste.executeUpdate();
             System.out.println("recompense ajouté");
                     
        } catch (SQLException ex) {
            System.out.println("probleme");
            System.out.println(ex.getMessage());
        }       
        
        
    }
        
    }
    @Override
    public List<Recompense> afficherRecompense() {
        Statement st;
        try {
        st = cnx.createStatement();
        String query="SELECT * FROM recompense";
        ResultSet rs=st.executeQuery(query);
        
         while(rs.next()){
             Recompense r=new Recompense();
             r.setNomRec(rs.getString("nomRec"));
             r.setNbr_point(rs.getInt("nbr_point"));
             list.add(r);
            
             
         }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRecompense.class.getName()).log(Level.SEVERE, null, ex);
        }
       
      
        return list;
    }

    @Override
    public void modifierRecompense(Recompense r) {
       
         try {
             //ste=cnx.prepareStatement("UPDATE recompense SET  nbr_point=? where nomRec=?");
              ste=cnx.prepareStatement("UPDATE recompense SET  nbr_point='"+r.getNbr_point()+"' where nomRec='"+r.getNomRec()+"'");

             //ste.setInt(1, r.getIdRec());
             //ste.setString(1,r.getNomRec());
             //ste.setInt(2,r.getNbr_point());
             //ste.setInt(3, id);
             ste.executeUpdate();
             System.out.println("recompense modifié");
             
         } catch (SQLException ex) {
             Logger.getLogger(ServiceRecompense.class.getName()).log(Level.SEVERE, null, ex);
         }
               
        
        
    }

    @Override
    public void supprimerRecompense(Recompense r) {
     
        String req="delete from recompense where NomRec=? ";
       
        try {
            ste=cnx.prepareStatement(req);
            //ste.setInt(1,r.getIdRec() );
            ste.setString(1,r.getNomRec() );
            ste.executeUpdate();
            System.out.println("recompense suprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }

    @Override
    public List<Recompense> rechercherRecompense(Recompense r) {
        
     Statement st;
        try {
            st = cnx.createStatement();
             ResultSet rs=st.executeQuery("SELECT * FROM recompense where nomRec like '%"+r.getNomRec()+ "%'");
     while(rs.next()){
         Recompense rc=new Recompense();
         rc.setNomRec(rs.getString("nomRec"));
         rc.setNbr_point(rs.getInt("nbr_point"));
         list.add(rc);
     }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRecompense.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list;
    } 
    
    
     public static  boolean ControleString(String str){
            
            if(str.length() == 0)
            {
               
                Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("non valide");
        alert.setHeaderText("");
        alert.setContentText("entrer une chaine valide");
        alert.showAndWait();
                 return false;
                
            }
           
                char[] chararray=str.toCharArray();
            for(int i=0;i<chararray.length;i++){
                char ch=chararray[i];
                if(((ch>='a' && ch<= 'z')|| (String.valueOf(ch).equals("")))){
                    return true;
                }
                
                else{
                    Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("non valide");
        alert.setHeaderText("");
        alert.setContentText("entrer un nom valide");
        alert.showAndWait();
        return false;
                }
            }
             
            
            
            return true;
        }
    
}
