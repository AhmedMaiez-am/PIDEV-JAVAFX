/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;



import Entities.Donation;
import Entities.Enfant;
import Entities.Maitresse;
import Entities.Parents;
import InterfacesServices.IServiceParent;
import Utils.MaConnection;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class ServiceParent implements IServiceParent{
    Connection cnx;
    private PreparedStatement ste;
    ObservableList<Maitresse> items = FXCollections.observableArrayList();
     ObservableList<Enfant> items1 = FXCollections.observableArrayList();
     ObservableList<Enfant> items2 = FXCollections.observableArrayList();
     ObservableList<Parents> itemsP = FXCollections.observableArrayList();
     private static final String c = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public ServiceParent() {
        cnx = MaConnection.getInstance().getConnection();
    }
    

    @Override
    public void afficherPf(Parents p){
        
        try {
            Statement stn = cnx.createStatement();
            String query ="SELECT * FROM `parent` WHERE emailP = '"+p.getEmailP()+"'";
            stn.executeQuery(query);
            ResultSet rs = stn.executeQuery(query);
            while (rs.next()){
                p.setIdP(rs.getInt("idP"));
                p.setNomP(rs.getString("nomP"));
                p.setPrenomP(rs.getString("prenomP"));
                p.setPf(rs.getString("portefeuille"));
                p.setTelP(rs.getString("telP"));
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modifierPf(Parents p) {
        try {
            Statement stn = cnx.createStatement();
            String query = "update parent set portefeuille ='"+p.getPf()+"' where emailP ='"+p.getEmailP()+"'";
            stn.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void AjouterEnfant(Enfant e) {
        try {
            
            String req ="INSERT INTO enfant (nomEnfant,prenomEnfant,age,password,image)"+"values (?,?,?,?,?)";
            ste = cnx.prepareStatement(req);
            ste.setString(1, e.getNomEnfant());
            ste.setString(2, e.getPrenomEnfant());
            ste.setString(3, e.getAge());
            ste.setString(4, e.getMotdepasse());
            ste.setString(5, e.getImg());

            ste.executeUpdate();
            System.out.println("Enfant ajoutée");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
    }

    @Override
    public List<Enfant> AfficherEnfant() {
        try {
            Statement stm = cnx.createStatement() ;
            String req = "select * from enfant   "  ; 
            ResultSet res = stm.executeQuery(req);
            while (res.next()) {
            
                Enfant m = new  Enfant() ;
    
                 //m.setIdEnfant(res.getInt("idE"));
                 m.setNomEnfant(res.getString("nomEnfant"));
                 m.setPrenomEnfant(res.getString("prenomEnfant"));
                 m.setAge(res.getString("age"));
                 m.setImg(res.getString("image"));
               //  m.setIdparent(p.);
                items1.addAll(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return items1 ;
    }

    @Override
    public void AjouterCompte(Parents e) {
        try {
            
            String req ="INSERT INTO parent (emailP,nomP,prenomP,telP,passwordP,NumCarte)"+"values (?,?,?,?,?,?)";
            ste = cnx.prepareStatement(req);
            ste.setString(1, e.getEmailP());
            ste.setString(2, e.getNomP());
            ste.setString(3, e.getPrenomP());
            ste.setString(4, e.getTelP());
            ste.setString(5, e.getPasswordP());
            ste.setString(6, e.getNumcarteP());
            ste.executeUpdate();
            System.out.println("Compte ajoutée");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
    }

    @Override
    public boolean seConnecter(Parents l) {
        boolean b = true;
         try {
             Statement stm = cnx.createStatement() ;
             String sql = "select * from parent where emailP='"+l.getEmailP()+"' and passwordP='"+l.getPasswordP()+"'";
             ResultSet res = stm.executeQuery(sql);
             if (res.next())
             { 
                 System.out.println("Parent Connecté"); 
             } else {
                 System.out.println("Parent Invalide");
                  b = false;
             }
            
        
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
        return b ;
    }

    @Override
    public void SupprimerMonCompte(Parents e) {
        try {
        Statement st = cnx.createStatement() ;
        String sql = "Delete from parent where emailP ='"+e.getEmailP()+"' and passwordP ='"+e.getPasswordP()+"'";
        st.executeUpdate(sql);
        System.out.println("CompteParent Supprimé");
    }
        catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage()); 
    }
    }

    @Override
    public void SupprimerMonEnfant(Enfant e) {
        try {
        Statement st = cnx.createStatement() ;
        String sql = "Delete from enfant where nomEnfant ='"+e.getNomEnfant()+"' and prenomEnfant ='"+e.getPrenomEnfant()+"'";
        st.executeUpdate(sql);
        System.out.println("CompteEnfant Supprimé");
    }
        catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage()); 
    }
    }

    @Override
    public boolean seConnecter(Enfant p) {
        boolean b = true;
        try {
             Statement stm = cnx.createStatement() ;
             String sql = "select * from enfant where nomEnfant='"+p.getNomEnfant()+"' and password='"+p.getMotdepasse()+"' ";
             ResultSet res = stm.executeQuery(sql);
             if (res.next())
             { 
                 System.out.println("Enfant Connectée"); 
             } else {
                 System.out.println("Enfant Invalide");
                  b = false;
             }
        
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        } return b ;
    }

    @Override
    public void modifEnfant(Enfant e) {
        Statement stn;
        try {
            stn = cnx.createStatement();
        
        String nom = e.getNomEnfant();
        String prenom = e.getPrenomEnfant();
        String age = e.getAge();
        
        String query = "update enfant set nomEnfant='"+nom+"', prenomEnfant='"+prenom+"',age='"+age+"'where nomEnfant='"+nom+"'";
        stn.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifParent(Parents p) {
        try {
            Statement stn;
            stn = cnx.createStatement();
            String query = "update parents set nomP='"+p.getNomP()+"', prenomP='"+p.getPrenomP()+"',emailP='"+p.getEmailP()+"',telP='"+p.getTelP()+"',passwordP= '"+p.getPasswordP()+"'where nomP='"+p.getNomP()+"'";
            stn.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Parents> Donner(Parents p) throws SQLException {
        Statement stn;
        stn = cnx.createStatement();
        String sql = "select nomP,NumCarte,passCarte,portefeuille,emailP from parent where NumCarte ='"+p.getNumcarteP()+"'and passCarte ='"+p.getPasscaretp()+"' ";
        ResultSet rs = stn.executeQuery(sql);
        
      
            while (rs.next()){
                Parents pp = new Parents(); 
                
                pp.setNomP(rs.getString("nomP"));
                pp.setNumcarteP(rs.getString("NumCarte"));
                pp.setPasscaretp(rs.getString("passCarte"));
                pp.setPf(rs.getString("portefeuille")) ;
                pp.setEmailP(rs.getString("emailP"));
                
                
                
                itemsP.addAll(pp);
            }
            return itemsP;
    }

    @Override
    public void donnation(Parents p) {
        try {
        Statement st = cnx.createStatement() ;
        String sql = "update parent set portefeuille = '"+p.getPf()+"' where NumCarte = '"+p.getNumcarteP()+"' ";
        st.executeUpdate(sql);
        System.out.println("Modifié");
        
       
    }   catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void select(Parents k) {
        try {
            Statement st = cnx.createStatement() ;
            String sql =" select portefeuille from parent where NumCarte='"+k.getNumcarteP()+"'";
            ResultSet rs =st.executeQuery(sql);
            while (rs.next()){
                k.setPf(rs.getString("portefeuille"));
                //arents p = new Parents(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouterPaiement(Donation d) {
        try {
            Statement stn = cnx.createStatement();
            String query = "insert into donation (NumC,Montant) values ('"+d.getNumcarte()+"','"+d.getMontant()+"')";
            stn.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Parents m = new Parents();
            m.setCodep(o);
            m.setEmailP(recepient);
            PreparedStatement st = cnx.prepareStatement("update parent set codep =? where emailP=? ");
            st.setString(1,o);
            st.setString(2,m.getEmailP());
            st.executeUpdate();
            System.out.println(m.getCodep());
            System.out.println(m.getEmailP());
            
           // System.out.println("ajouté");
           //  m.setCodeM(o);
           //  m.setLoginM(o);
            // MaitresseValidation v = new MaitresseValidation();
            //r.setCode(o);
            
            //recup(m);
           //System.out.println(m.getCodeM());
            
            return message;
        } catch (Exception ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Map<String, String> getcode() {
        Map<String,String> Listecode = new HashMap<>();
                   try {
            Statement stm = cnx.createStatement() ;
            String req = "select emailP , codep from parent  "  ; 
            ResultSet res = stm.executeQuery(req);
            while (res.next()) {
            
                
                 Listecode.put(res.getString("emailP"), res.getString("codep"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Listecode ;   
    }

    @Override
    public void updatepassword(Parents v) {
        try {
        Statement st = cnx.createStatement() ;
        String sql = "update parent set passwordP = '"+v.getPasswordP()+"' where emailP = '"+v.getEmailP()+"'";
        st.executeUpdate(sql);
        System.out.println("Modifié");
    }
        catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage()); 
    }
    }

    @Override
    public List<String> getchamp() {
         List<String> Liste = new ArrayList<>();
                   try {
            Statement stm = cnx.createStatement() ;
            String req = "select emailP from parent "  ; 
            ResultSet res = stm.executeQuery(req);
            while (res.next()) {
            
              //  MaitresseValidation m = new  MaitresseValidation();
         
                // m.setLoginM(res.getString("loginM"));
                // m.setPasswordM(res.getString("passwordM"));
                 
               //  m.setIdparent(p.);
                 Liste.add(res.getString("emailP"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDirecteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Liste ;
    }

    @Override
    public void afficherCarte(Parents p) {
 try {
            Statement stn = cnx.createStatement();
            String query ="SELECT * FROM `parent` WHERE emailP = '"+p.getEmailP()+"'";
            stn.executeQuery(query);
            ResultSet rs = stn.executeQuery(query);
            while (rs.next()){
                p.setNumcarteP(rs.getString("NumCarte"));
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public List<Enfant> rechercheenfant(String nom) {
        List<Enfant> myList = new ArrayList<Enfant>();
  Statement st;
       
        try { 
          st = cnx.createStatement();
          ResultSet rs=st.executeQuery("SELECT * from enfant where nomEnfant like '%" + nom + "%'");
             
            while (rs.next()) {
                Enfant p2 = new Enfant();

                p2.setNomEnfant(rs.getString(2));
                p2.setPrenomEnfant(rs.getString(3));
                

                myList.add(p2);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("probleme dans les l'odre des champs dans BD");
        }
        return myList;
    }
}
