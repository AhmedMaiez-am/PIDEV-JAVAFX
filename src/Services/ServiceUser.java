/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Enfant;
import Entities.MaitresseValidation;
import Entities.Parents;
import Entities.User;
import InterfacesServices.IServiceUser;
import Utils.MaConnection;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ServiceUser implements IServiceUser{

    private Connection cnn;
    private PreparedStatement ste;
    private static final String c = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    //private static final int duration = ((30 * 60) + 59) * 1000;

    public ServiceUser() {
        cnn = MaConnection.getInstance().getConnection();
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
            message.setSubject("vide");
            String o = generateR(10);
          //  String htmlCode = o;
            message.setText(o);
            //recuperation r = new recuperation();
            User u = new User();
            
            u.setCode(o);
            u.setEmail(recepient);
            Date date=new Date();
            long time = date.getTime();
            Timestamp t = new Timestamp(time);
            u.setDateCreation(t);
            System.out.println(u.getDateCreation());
            //Insert date de création from date système
            String req = "update user set dateCreation=? where email=?";
             ste = cnn.prepareStatement(req);
             ste.setString(1, u.getDateCreation().toString());
             ste.setString(2, u.getEmail());
             ste.executeUpdate();
            
             // Get date création from database
            Statement stm = cnn.createStatement() ;
             String sql = "select dateCreation from user where email ='"+u.getEmail()+"'";
             stm.executeQuery(sql);
             ResultSet res = stm.executeQuery(sql);
             while (res.next())
             {
                 u.setDateCreation(res.getTimestamp("dateCreation"));
             }
             
            Timestamp tt = u.getDateCreation();
            
            //Add time to date de création = date d'expiration
            u.setDateExp(Timestamp.from(tt.toInstant().plus(1, ChronoUnit.MINUTES)));
            System.out.println(u.getDateExp());
            
            PreparedStatement st = cnn.prepareStatement("update user set code =?,dateExp=? where email=? ");
            st.setString(1,o);
            st.setString(2, u.getDateExp().toString());
            st.setString(3,u.getEmail());
            st.executeUpdate();
            
            System.out.println(u.getCode());
            System.out.println(u.getEmail());
            System.out.println("ajouté");
           //  m.setCodeM(o);
           //  m.setLoginM(o);
            // MaitresseValidation v = new MaitresseValidation();
            //r.setCode(o);
            
            //recup(m);
           //System.out.println(m.getCodeM());
            
            return message;
        } catch (Exception ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void getMail(User u) {
        try {
             Statement stm = cnn.createStatement() ;
             String sql = "select email from user where email ='"+u.getEmail()+"'";
             stm.executeQuery(sql);
             ResultSet res = stm.executeQuery(sql);
             while (res.next())
             {
                 u.setEmail(res.getString("login"));
                 u.setPassword(res.getString("password"));
                
                  
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
            Statement stm = cnn.createStatement() ;
            String req = "select email from user "  ; 
            ResultSet res = stm.executeQuery(req);
            while (res.next()) {
            
              //  MaitresseValidation m = new  MaitresseValidation();
         
                // m.setLoginM(res.getString("loginM"));
                // m.setPasswordM(res.getString("passwordM"));
                 
               //  m.setIdparent(p.);
                 Liste.add(res.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Liste ;
    }

    @Override
    public Map<String, String> getcode() {
        Map<String,String> Listecode = new HashMap<>();
                   try {
            Statement stm = cnn.createStatement() ;
            String req = "select email ,code from user  "  ; 
            ResultSet res = stm.executeQuery(req);
            while (res.next()) {
            
                
                 Listecode.put(res.getString("email"), res.getString("code"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Listecode ;   
    }

    @Override
    public void updatepassword(User u) {
        try {
        Statement st = cnn.createStatement() ;
        String sql = "update user set password = '"+u.getPassword()+"' where email = '"+u.getEmail()+"'";
        st.executeUpdate(sql);
        System.out.println("Modifié");
    }
        catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage()); 
    }
    }

    @Override
    public void ajouterMaitresse(MaitresseValidation m) {
        try {
             String req = "insert into user (email ,password)"+"values (?,?)";
             ste = cnn.prepareStatement(req);
             
             ste.setString(1, m.getLoginM());
             ste.setString(2, m.getPasswordM());
             
             ste.executeUpdate();
            System.out.println("Maitresse ajoutée");
        
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());   
        }
    }

    @Override
    public void ajouterParent(Parents p) {
        try {
            String req ="INSERT INTO user (email,password)"+"values (?,?)";
            ste = cnn.prepareStatement(req);
            ste.setString(1, p.getEmailP());
            ste.setString(2, p.getPasswordP());
            ste.executeUpdate();
            System.out.println("Parent ajouté");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
    }
     @Override
    public void getDate(User u) {
        try {
             Statement stm = cnn.createStatement() ;
             String sql = "select dateExp from user where email ='"+u.getEmail()+"'";
             stm.executeQuery(sql);
             ResultSet res = stm.executeQuery(sql);
             while (res.next())
             {
                 u.setDateExp(res.getTimestamp("dateExp"));  
             }
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
    }
    /*@Override
    public void ajouterEnfant(Enfant e) {
        try {
            String req ="INSERT INTO user (email,password)"+"values (?,?)";
            ste = cnn.prepareStatement(req);
            ste.setString(1, e.getNomEnfant());
            ste.setString(2, e.getMotdepasse());

            ste.executeUpdate();
            System.out.println("Enfant ajouté");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
    }*/

   

    
}
