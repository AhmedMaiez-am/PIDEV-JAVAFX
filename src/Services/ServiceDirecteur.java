/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Directeur;
import Entities.Enfant;
import Entities.Maitresse;
import Entities.MaitresseValidation;
import InterfacesServices.IServiceDirecteur;
import Utils.MaConnection;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Authenticator;
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
public class ServiceDirecteur implements IServiceDirecteur{
    private Connection cnx;
    private PreparedStatement ste;
    ObservableList<Maitresse> items = FXCollections.observableArrayList();
    ObservableList<Maitresse> items1 = FXCollections.observableArrayList();
    private static final String c = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

   
    
    public ServiceDirecteur() {
       cnx = MaConnection.getInstance().getConnection();
    }

    
    @Override
    public boolean ConnecterDirecteur(Directeur p) {
        boolean b = true;
        try {
             Statement stm = cnx.createStatement() ;
             
             String sql = "select * from directeur where login='"+p.getLogin()+"' and password='"+p.getPassword()+"'";
             ResultSet res = stm.executeQuery(sql);
             if (res.next())
             {  
                 System.out.println("Directeur Connecté"); 
                 
             } else 
             {
                  System.out.println("Directeur Invalide"); 
                  
                 b = false;
             }
            
        
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
        return b;
    }

    @Override
    public void Valider(MaitresseValidation m) {
        try {
             Statement stm = cnx.createStatement() ;
             String sql = "insert into validation (loginM ,passwordM,idm,img )"+"values (?,?,?,?)";
             ste = cnx.prepareStatement(sql);
             
             ste.setString(1, m.getLoginM());
             ste.setString(2, m.getPasswordM());
             ste.setInt(3, m.getIdm());
             ste.setString(4, m.getImg());
             
             ste.executeUpdate();
            System.out.println(" Compte Maitresse ajouté");
        
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
    }

    @Override
    public void refuserDemande(Maitresse p) {
        try {
        Statement st = cnx.createStatement() ;
        String sql = "Delete from maitresse where cin ='"+p.getCin()+"' and nomM ='"+p.getNom()+"'";
        st.executeUpdate(sql);
        System.out.println("Compte Supprimé");
    }
        catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage()); 
    }
    }

    @Override
    public List<Maitresse> ListerMaitresse() {
        try {
            Statement stm = cnx.createStatement() ;
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
                 items.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDirecteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return items ;
    }

    @Override
    public List<Enfant> AfficherEnfant() {
        List<Enfant> ListeEnfant = new ArrayList<>();
                   try {
            Statement stm = cnx.createStatement() ;
            String req = "select * from enfant   "  ; 
            ResultSet res = stm.executeQuery(req);
            while (res.next()) {
            
                Enfant m = new  Enfant() ;
    
                 m.setIdEnfant(res.getInt("idE"));
                 m.setNomEnfant(res.getString("nomEnfant"));
                 m.setPrenomEnfant(res.getString("prenomEnfant"));
                 m.setAge(res.getString("age"));
               //  m.setIdparent(p.);
                 ListeEnfant.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDirecteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ListeEnfant ;
    }

    @Override
    public List<Maitresse> recherche(Maitresse m) throws SQLException {
        try {
            Statement ps = cnx.createStatement();
            ResultSet res;
            
            res = ps.executeQuery("select * from maitresse where nomM like '%" + m.getNom() + "%' ");
            while (res.next()) {
                Maitresse mm = new Maitresse();
                mm.setEtat(res.getString("etat"));
                mm.setNom(res.getString("nomM"));
                mm.setPrenom(res.getString("prenomM"));
                mm.setCin(res.getString("cin"));
                mm.setEmail(res.getString("emailMaitresse"));
                items1.add(mm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDirecteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items1 ;
    }
    public static String generateR(int l){
             Random r = new SecureRandom();
             if (l <= 0){
                 throw new IllegalArgumentException("longueur doit être positive");
             }
             StringBuilder sb = new StringBuilder(l);
             for (int i = 0;i < l;i++){
         sb.append(c.charAt(r.nextInt(c.length())));
     }
             String out = sb.toString();
             System.out.println(out);
             
             return out;
         }

    @Override
    public void sendMail(String recepient) {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "maitressecole13@gmail.com";
        //Your gmail password
        String password = "ghadaarbia123456";
        

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient);

        try {
            //Send mail
            Transport.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(ServiceDirecteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Message sent successfully");
    }

    private Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(" vide");
            String o = generateR(10);
          //  String htmlCode = o;
            message.setText(o);
            //recuperation r = new recuperation();
            MaitresseValidation m = new MaitresseValidation();
            m.setCodeM(o);
            m.setLoginM(recepient);
            PreparedStatement st = cnx.prepareStatement("update validation set codem =? where loginM=? ");
            st.setString(1,o);
            st.setString(2,m.getLoginM());
            st.executeUpdate();
            System.out.println(m.getCodeM());
            System.out.println(m.getLoginM());
            System.out.println("ajouté");
           //  m.setCodeM(o);
           //  m.setLoginM(o);
            // MaitresseValidation v = new MaitresseValidation();
            //r.setCode(o);
            
            //recup(m);
           //System.out.println(m.getCodeM());
            
            return message;
        } catch (Exception ex) {
            Logger.getLogger(ServiceDirecteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void getMail(MaitresseValidation m) {
        try {
             Statement stm = cnx.createStatement() ;
             String sql = "select loginM from validation where loginM ='"+m.getLoginM()+"'";
             stm.executeQuery(sql);
             ResultSet res = stm.executeQuery(sql);
             while (res.next())
             {
                 m.setLoginM(res.getString("loginM"));
                 m.setPasswordM(res.getString("passwordM"));
                
                  
             }
            
        
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
    }

    @Override
    public List<String> getchamp() {
        List<String> Liste = new ArrayList<>();
                   try {
            Statement stm = cnx.createStatement() ;
            String req = "select loginM from validation "  ; 
            ResultSet res = stm.executeQuery(req);
            while (res.next()) {
            
              //  MaitresseValidation m = new  MaitresseValidation();
         
                // m.setLoginM(res.getString("loginM"));
                // m.setPasswordM(res.getString("passwordM"));
                 
               //  m.setIdparent(p.);
                 Liste.add(res.getString("loginM"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDirecteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Liste ;
    }

    @Override
    public Map<String, String> getcode() {
        Map<String,String> Listecode = new HashMap<>();
                   try {
            Statement stm = cnx.createStatement() ;
            String req = "select codem ,loginM from validation  "  ; 
            ResultSet res = stm.executeQuery(req);
            while (res.next()) {
            
                
                 Listecode.put(res.getString("loginM"), res.getString("codem"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDirecteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Listecode ;   
    }
    /* @Override
    public void getCode(MaitresseValidation v) {
        
        try {
            
             Statement stm = cnn.createStatement() ;
             String sql = "select codem from validation where loginM ='"+v.getLoginM()+"'";
             stm.executeQuery(sql);
             ResultSet res = stm.executeQuery(sql);
             while (res.next())
             {
                 v.setLoginM(res.getString("codem"));
             }
            
        
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
   
    }
    
    */

    @Override
    public void updatepassword(MaitresseValidation v) {
        try {
        Statement st = cnx.createStatement() ;
        String sql = "update Validation set passwordM = '"+v.getPasswordM()+"' where loginM = '"+v.getLoginM()+"'";
        st.executeUpdate(sql);
        System.out.println("Modifié");
    }
        catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage()); 
    }
    }

    @Override
    public int count(MaitresseValidation v) {
        int k = 0 ;
       try {
          
        Statement st = cnx.createStatement() ;
        String sql = "select * from validation";
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
    public void updateEtat(Maitresse m) {
        try {
        Statement st = cnx.createStatement() ;
        String sql = "update maitresse set etat = '"+m.getEtat()+"' where emailMaitresse='"+m.getEmail()+"' ";
        st.executeUpdate(sql);
        System.out.println("Modifié");
    }
        catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage()); 
    }
    }

    @Override
    public void sendMailValidation(String recepient) {
         System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "maitressecole13@gmail.com";
        //Your gmail password
        String password = "ghadaarbia123456";
        

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage1(session, myAccountEmail, recepient);

        try {
            //Send mail
            Transport.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(ServiceDirecteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Message sent successfully");
    }

    private Message prepareMessage1(Session session, String myAccountEmail, String recepient) {
        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Validation de Compte ");
           String htmlCode = "<style>"
                   + ".demo {"
                   + "border:1px solid;"
                   + "}"
                   + "</style>"
                   + "<table class='demo'>"
                   + "<thead>"
                   + "<tr>"
                   + "<th><h1>Validation du compte</h1></th>"
                   + "</tr>"
                   + "</thead>"
                   + "<tbody>"
                   + "<tr>"
                   + "<td>Nous avons le plaisir de vous informer que votre compte maitresse a été validé avec succées</td>"
                   + "</tr>"
                   + "</tbody>"
                   + "</table>";
            message.setContent(htmlCode,"text/html");
            //recuperation r = new recuperation();
            
           
            return message;
        } catch (Exception ex) {
            Logger.getLogger(ServiceDirecteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int count(Enfant v) {
        int k = 0 ;
       try {
          
        Statement st = cnx.createStatement() ;
        String sql = "select * from enfant";
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
    public String getImg(MaitresseValidation m) {
        try {
             Statement stm = cnx.createStatement() ;
             String sql = "select img from validation where loginM ='"+m.getLoginM()+"'";
             stm.executeQuery(sql);
             ResultSet res = stm.executeQuery(sql);
             while (res.next())
             {
                 m.setImg(res.getString("img"));
                
                  
             }
            
        
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
        return m.getImg();
    }

    @Override
    public ArrayList<Maitresse> Mait() {
            ArrayList<Maitresse> Liste = new ArrayList<>();

try {
            Statement stm = cnx.createStatement() ;
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
            Logger.getLogger(ServiceDirecteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Liste ;    }

    @Override
    public List<Maitresse> recherchemaitresse(String nom) {
        List<Maitresse> myList = new ArrayList<Maitresse>();
  Statement st;
       
        try { 
          st = cnx.createStatement();
          ResultSet rs=st.executeQuery("SELECT * from maitresse where nomM like '%" + nom + "%'");
             
            while (rs.next()) {
                Maitresse p2 = new Maitresse();

                p2.setNom(rs.getString(4));
                p2.setPrenom(rs.getString(5));
                

                myList.add(p2);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("probleme dans les l'odre des champs dans BD");
        }
        return myList;
    }

    
}
