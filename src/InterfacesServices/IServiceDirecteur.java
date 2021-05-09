/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesServices;

import Entities.Directeur;
import Entities.Enfant;
import Entities.Maitresse;
import Entities.MaitresseValidation;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author maiez
 */
public interface IServiceDirecteur {
    public boolean ConnecterDirecteur(Directeur p);
    public void Valider(MaitresseValidation m);
    public void refuserDemande(Maitresse p);
    public List<Maitresse> ListerMaitresse() ;
    public List<Enfant>  AfficherEnfant() ;
    public List<Maitresse> recherche(Maitresse m) throws SQLException ;
    public void sendMail(String recepient) ;
   // public void getCode (MaitresseValidation v);
    public void getMail (MaitresseValidation m);
    public List<String>  getchamp ();
    public Map<String,String>  getcode ();
    public void updatepassword(MaitresseValidation v);
    public int count(MaitresseValidation v);
    public void updateEtat (Maitresse m);
    public void sendMailValidation(String recepient) ;
    public int count(Enfant v);
    public String getImg(MaitresseValidation m);
    public ArrayList<Maitresse> Mait() ;
    public List<Maitresse> recherchemaitresse(String nom);
}
