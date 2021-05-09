/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Maitresse;
import Entities.Reclamation;
import Services.ServiceDirecteur;
import Utils.MaConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class RepondreReclamationController implements Initializable {

    @FXML
    private TextField emailTXFLD;
    @FXML
    private TextField sujetTextField;
    @FXML
    private TextArea messageTextField;
    @FXML
    private Button envoyerMessageButton;
    
    private Reclamation recl;
    Connection cnx=MaConnection.getInstance().getConnection();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sujetTextField.setText("Reponse Reclamation");
    }    
    public void setreclamation (Reclamation recl){
    this.recl=recl;
    messageTextField.setText("Bonjour Mr/Mme " + recl.getNom() + " " + recl.getPrenom() + " Merci de nous avoir contactés,");

}
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
            message.setSubject(sujetTextField.getText());
          //  String htmlCode = o;
            message.setText(messageTextField.getText());
            //recuperation r = new recuperation();
            Maitresse m = new Maitresse();
            m.setEmail(recepient);
  
            return message;
        } catch (Exception ex) {
            Logger.getLogger(ServiceDirecteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void getMail(Maitresse m) {
        try {
             Statement stm = cnx.createStatement() ;
             String sql = "select emailMaitresse from maitresse where emailMaitresse ='"+m.getEmail()+"'";
             stm.executeQuery(sql);
             ResultSet res = stm.executeQuery(sql);
             while (res.next())
             {
                 m.setEmail(res.getString("emailMaitresse"));
                
                  
             }
            
        
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
    }
    @FXML
    private void envoyerMessage(ActionEvent event) {
        String to;
        if (emailTXFLD.getText().equals(""))
          to = recl.getEmail().trim();
        else
            to=emailTXFLD.getText();
        sendMail(to);
    }
    
}
