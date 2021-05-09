/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Contes;
import Entities.InventaireContes;
import InterfacesServices.IServiceContes;
import Utils.MaConnection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author maiez
 */
public class ServiceContes implements IServiceContes{
    Connection cnx;
    ObservableList<Contes> items = FXCollections.observableArrayList();
    ObservableList<Contes> items1 = FXCollections.observableArrayList();

    public ServiceContes() {
        cnx = MaConnection.getInstance().getConnection();
    }

    @Override
    public List<Contes> afficherContes() throws SQLException {
        Statement stn = cnx.createStatement();
             String query ="SELECT * FROM `contes`";
             ResultSet rs = stn.executeQuery(query);
             
            while (rs.next()){
                Contes m = new  Contes() ;
                
                 m.setTitre(rs.getString("titre"));
                 m.setAuteur(rs.getString("auteur"));
                 
                 items.addAll(m);    
            }
            
            return items;
    }

    @Override
    public void ajouterContes(InventaireContes c) {
        try {
            Statement stn = cnx.createStatement();
            String query ="INSERT INTO InventaireContes (titreC,auteurC) VALUES ('"+c.getTitreC()+"','"+c.getAuteurC()+"')";
            stn.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCours.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @Override
    public List<Contes> rechercheContes(Contes c) throws SQLException {
        Statement stn = cnx.createStatement();
             String query ="SELECT * FROM `contes` WHERE titre = '"+c.getTitre()+"'";
             ResultSet rs = stn.executeQuery(query);
             
             while (rs.next()){
                 c.setTitre(rs.getString("titre"));
                 c.setAuteur(rs.getString("auteur"));
                 
                 items1.add(c);
             }
             return items1;
    }

    @Override
    public void Addcont(Contes c) {
         try  {
        Statement stm=cnx.createStatement();
    
       String query= "INSERT INTO `contes`(`titre`, `auteur`,`contes`) VALUES ('"+c.getTitre()+"','"+c.getAuteur()+"','"+c.getContes()+"')";
    stm.executeUpdate(query);
    } catch (SQLException ex) {
        Logger.getLogger(ServiceContes.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    @Override
    public void supprimercontes(Contes c) {
        try  {
        Statement stm=cnx.createStatement();
         String query = " DELETE FROM `contes` WHERE titre='"+c.getTitre()+"'";
    stm.executeUpdate(query);
    } catch (SQLException ex) {
        Logger.getLogger(ServiceContes.class.getName()).log(Level.SEVERE, null, ex);
       } 
    }
    @Override
    public void upadatecontes(Contes c, String n) {
        try  {
        Statement stm=cnx.createStatement();
    
     String query = "UPDATE contes SET titre= '"+c.getTitre()+"', auteur= '"+c.getAuteur()+"', contes= '"+c.getContes()+"' WHERE titre='"+n+"'";
    stm.executeUpdate(query);
    } catch (SQLException ex) {
        Logger.getLogger(ServiceContes.class.getName()).log(Level.SEVERE, null, ex);
   
       }
    }
     public void exportContes () throws SQLException, FileNotFoundException, IOException
    {
         
      String query="SELECT * FROM `contes`";
           
      Statement stm=cnx.createStatement();

    ResultSet rst=stm.executeQuery(query);
    XSSFWorkbook wb=new XSSFWorkbook();
    XSSFSheet sheet=wb.createSheet("contes details");
    XSSFRow header=sheet.createRow(0);
    //header.createCell(0).setCellValue("id");
    header.createCell(1).setCellValue("titre");
    header.createCell(2).setCellValue("auteur");
    //header.createCell(3).setCellValue("type");
     int index =1;
      while (rst.next())
        {
     XSSFRow row=sheet.createRow(index);        
     //row.createCell(0).setCellValue(rst.getString("id"));
     row.createCell(1).setCellValue(rst.getString("titre")); 
     row.createCell(2).setCellValue(rst.getString("auteur"));
    //row.createCell(3).setCellValue(rst.getString("type"));
     index++;
     FileOutputStream fileout=new FileOutputStream("contesdetails.xlsx");
     wb.write(fileout);
     fileout.close();


        }
    }

    @Override
    public List<Contes> recherchecontes(String nom) {
        List<Contes> myList = new ArrayList<Contes>();
  Statement st;
       
        try { 
          st = cnx.createStatement();
          ResultSet rs=st.executeQuery("SELECT * from contes where titre like '%" + nom + "%'");
             
            while (rs.next()) {
                Contes p2 = new Contes();

                p2.setTitre(rs.getString(2));
                p2.setAuteur(rs.getString(3));
                

                myList.add(p2);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("probleme dans les l'odre des champs dans BD");
        }
        return myList;
    }
    
}
