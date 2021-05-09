/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.recuperation;
import Entities.Enfant;
import Entities.EnfantRecup;
import Entities.Parents;
import Entities.ParentsRecup;
import InterfacesServices.Iservicerecuperation;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author maiez
 */
public class ServiceRecuperation implements Iservicerecuperation {

    //var
    Connection cnx = MaConnection.getInstance().getConnection();
    ObservableList<recuperation> liste = FXCollections.observableArrayList();
    ObservableList<EnfantRecup> option1 = FXCollections.observableArrayList();
     ObservableList<ParentsRecup> option2 = FXCollections.observableArrayList();



    @Override
    public void ajouterRecuperation(recuperation r) {

        if(ControleString(r.getNomRec())&& ControleString(r.getnomEnf())   ){
            
        try {
            String req = "INSERT INTO recuperation (`nomRec`,`nomEnfant`,`emailP`,`nbr_point`) VALUES (?,?,?,?)";

            PreparedStatement ste = cnx.prepareStatement(req);
            ste.setString(1, r.getNomRec());
            ste.setString(2, r.getnomEnf());
            ste.setString(3, r.getEmailP());
            ste.setInt(4, r.getNbr_point());
        
            ste.executeUpdate();
            System.out.println("recompense ajouté");

        } catch (SQLException ex) {
            System.out.println("probleme");
            System.out.println(ex.getMessage());
        }
        }
    }

    @Override
    public List<recuperation> afficherRecuperation() {
        Statement st;

        try {
            st = cnx.createStatement();
            String query = "SELECT * FROM recuperation";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                recuperation r = new recuperation();
                r.setNomRec(rs.getString("NomRec"));
                r.setnomEnf(rs.getString("nomEnfant"));
                // r.setDateEnvoi(rs.getDate("dateEnvoi"));
                r.setEmailP(rs.getString("emailP"));
                r.setNbr_point(rs.getInt("nbr_point"));

                liste.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRecuperation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }
    
  
    @Override
    public List<recuperation> trierRecuperation() {
        
        Statement st;
        try {
            st = cnx.createStatement();
            String query= "SELECT * FROM recuperation order by nbr_point asc  ";
        ResultSet rs=st.executeQuery(query);
        while(rs.next()){
            recuperation r=new recuperation();
            r.setNomRec(rs.getString("nomRec"));
            r.setnomEnf(rs.getString("nomEnfant"));
            r.setEmailP(rs.getString("emailP"));
            r.setNbr_point(rs.getInt("nbr_point"));
            liste.add(r);
            
        }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRecuperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return liste;
        
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
     
       

    @Override
    public void sendmail(String Recepient) {
     
         System.out.println("Preparing to send email");
        
        Properties properties=new Properties();
       
        //Enable authentication
        properties.put("mail.smtp.auth", "true");
           //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable",true);
         //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
         //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address

        
        String myAccountEmail = "maitressecole13@gmail.com";
        //Your gmail password
        String  password="ghadaarbia123456";    

//Create a session with account credentials

        Session  session = Session.getInstance(properties, new javax.mail.Authenticator() {
            
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail, password);
            }
             
        });
        Message msg=prepareMessage(session,myAccountEmail,Recepient);
        try {
            Transport.send(msg);
        } catch (MessagingException ex) {
            Logger.getLogger(ServiceRecuperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Message sent successfully");
        
        }
    private Message prepareMessage(Session session,String myAccountEmail,String Recepient) {
        Message msg=new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(myAccountEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(Recepient));
            msg.setSubject("Recompense");
              String htmlCode = "<h1>Félicitations !</h1>"
                      + "<h3> Votre fils  a obtenus une recompense </h3> "+
                      "<h3>merci de nous contacter pour recuperer votre recompense</h3>";
            msg.setContent(htmlCode, "text/html");
            return msg;
        } catch (MessagingException ex) {
            Logger.getLogger(ServiceRecuperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    
    


    @Override
    public List<EnfantRecup> fillcombobox1() {
       
       
        PreparedStatement st;
        try {
             String query="SELECT * from enfant";
            st = cnx.prepareStatement(query);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                EnfantRecup e=new EnfantRecup();
                e.setNomEnfant(rs.getString("nomEnfant"));
                e.setPrenomEnfant(rs.getString("prenomEnfant"));
                option1.add(e);
                //System.out.println(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRecuperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return option1;
    }

    @Override
    public List<ParentsRecup> fillcombobox2() {
        
          PreparedStatement st;
        try {
             String query="SELECT * from parent";
            st = cnx.prepareStatement(query);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                ParentsRecup p=new ParentsRecup();
                p.setNomP(rs.getString("nomP"));
                p.setNumcarteP(rs.getString("NumCarte"));
                p.setPasscaretp(rs.getString("passCarte"));
                p.setPf(rs.getString("portefeuille"));
                p.setEmailP(rs.getString("emailP"));
                option2.add(p);
                //System.out.println(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRecuperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return option2;
    }

   

}

