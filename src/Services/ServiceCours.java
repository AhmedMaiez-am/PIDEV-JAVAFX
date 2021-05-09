/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Cours;
import Entities.InventaireCours;
import Entities.typecours;
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
import InterfacesServices.IServiceCours;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author maiez
 */
public class ServiceCours implements IServiceCours {
    Connection cnx;
    ObservableList<Cours> items = FXCollections.observableArrayList();
    ObservableList<Cours> items1 = FXCollections.observableArrayList();
    

    public ServiceCours() {
        cnx = MaConnection.getInstance().getConnection();
    }

    @Override
    public List<Cours> afficherCours() throws SQLException {


             Statement stn = cnx.createStatement();
             String query ="SELECT * FROM `cours`";
             ResultSet rs = stn.executeQuery(query);
             
            while (rs.next()){
                Cours m = new  Cours() ;
                 m.setIdC(rs.getInt("IdC"));
                 m.setNom(rs.getString("nom"));
                 m.setType(rs.getString("type"));
                 m.setDesc(rs.getString("description"));
                 m.setPrixC(rs.getString("prix"));
                 
                 items.addAll(m);    
            }
            
            return items;
             
    }

    @Override
    public void ajouterCours(InventaireCours c){
        
        try {
            Statement stn = cnx.createStatement();
            String query ="INSERT INTO InventaireCours (nomC, typeCc, descriptionCc) VALUES ('"+c.getNomC()+"','"+c.getTypec()+"','"+c.getDescC()+"')";
            stn.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCours.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Cours> rechercheCours(Cours m) throws SQLException {
        
            Statement stn = cnx.createStatement();
             String query ="SELECT * FROM `cours` WHERE nom = '"+m.getNom()+"'";
             ResultSet rs = stn.executeQuery(query);
             
             while (rs.next()){
                 Cours c = new Cours();
                 c.setIdC(rs.getInt("IdC"));
                 c.setNom(rs.getString("nom"));
                 c.setType(rs.getString("description"));
                 c.setPrixC(rs.getString("prix"));
                 
                 items1.add(c);
             }
             return items1;
    }

    @Override
    public int count(Cours v) {
        int k = 0 ;
       try {
          
        Statement st = cnx.createStatement() ;
        String sql = "select * from cours";
        ResultSet res = st.executeQuery(sql);
         while (res.next()) {
             k=k+1;
         }
    }
        catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage()); 
    }

    return k ;
    }

    @Override
    public void AddCours(Cours c) {
        try  {
        Statement stm=cnx.createStatement();

       String query= "INSERT INTO `cours`(`nom`, `type`,`description`,`cours`,`prix`) VALUES ('"+c.getNom()+"','"+c.getType()+"','"+c.getDesc()+"','"+c.getCours()+"','"+c.getPrixC()+"')";
    stm.executeUpdate(query);
    } catch (SQLException ex) {
        Logger.getLogger(ServiceCours.class.getName()).log(Level.SEVERE, null, ex);
   
       }
    }

   /* @Override
    public void modifCours(Cours c) {
    }*/

    @Override
    public void supprimerCours(Cours c) {
        try  {
        Statement stm=cnx.createStatement();
         String query = " DELETE FROM `cours` WHERE nom='"+c.getNom()+"'";
    stm.executeUpdate(query);
    } catch (SQLException ex) {
        Logger.getLogger(ServiceCours.class.getName()).log(Level.SEVERE, null, ex);
   
       }
    }

    @Override
    public void updateCours(Cours c, String n) {
        try  {
        Statement stm=cnx.createStatement();
     String query = "UPDATE cours SET nom= '"+c.getNom()+"', description= '"+c.getDesc()+"', type= '"+c.getType()+"', cours= '"+c.getCours()+"' WHERE  nom='"+n+"'";
    stm.executeUpdate(query);
    } catch (SQLException ex) {
        Logger.getLogger(ServiceCours.class.getName()).log(Level.SEVERE, null, ex);
   
       }  
    }
    public List<typecours> lister() {
            String query="SELECT nom FROM `typecours`";
        List<typecours>types=new ArrayList<>();
        try {
        Statement stm=cnx.createStatement();

    ResultSet rst=stm.executeQuery(query);
   
    while (rst.next())
    {
        typecours P =new typecours();
          P.setType(rst.getString("nom"));
        types.add(P);
    }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceCours.class.getName()).log(Level.SEVERE, null, ex);
    }
      return types; 
    }
     public void exportCours () throws SQLException, FileNotFoundException, IOException
    {
         
      String query="SELECT * FROM `cours`";
           
      Statement stm=cnx.createStatement();

            ResultSet rst=stm.executeQuery(query);
            XSSFWorkbook wb=new XSSFWorkbook();
            XSSFSheet sheet=wb.createSheet("cours details");
            XSSFRow header=sheet.createRow(0);
            //header.createCell(0).setCellValue("id");
            header.createCell(1).setCellValue("nom");
            header.createCell(2).setCellValue("type");
            header.createCell(3).setCellValue("description");
            header.createCell(4).setCellValue("prix");
            int index =1;
            while (rst.next())
            {
            XSSFRow row=sheet.createRow(index);        
            //row.createCell(0).setCellValue(rst.getString("id"));
            row.createCell(1).setCellValue(rst.getString("nom")); 
            row.createCell(2).setCellValue(rst.getString("type"));
            row.createCell(3).setCellValue(rst.getString("description"));
            row.createCell(4).setCellValue(rst.getString("prix"));
            index++;
            FileOutputStream fileout=new FileOutputStream("cours details.xlsx");
            wb.write(fileout);
            fileout.close();


               }


           }

    @Override
    public List<Cours> recherchecours(String nom) {
         List<Cours> myList = new ArrayList<Cours>();
  Statement st;
       
        try { 
          st = cnx.createStatement();
          ResultSet rs=st.executeQuery("SELECT * from cours where nom like '%" + nom + "%'");
             
            while (rs.next()) {
                Cours p2 = new Cours();

                p2.setNom(rs.getString(2));
                p2.setType(rs.getString(3));
                

                myList.add(p2);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("probleme dans les l'odre des champs dans BD");
        }
        return myList;
    }
}
