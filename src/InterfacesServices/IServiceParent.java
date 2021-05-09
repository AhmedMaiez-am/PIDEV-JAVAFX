/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesServices;

import Entities.Donation;
import Entities.Enfant;
import Entities.Parents;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author maiez
 */
public interface IServiceParent {
    public void afficherPf(Parents p);
    public void afficherCarte(Parents p);
    public void modifierPf(Parents p);
    public void AjouterEnfant(Enfant e) ;
    public List<Enfant>  AfficherEnfant() ;
    public void AjouterCompte(Parents e) ;
    public boolean seConnecter(Parents p);
    public void SupprimerMonCompte (Parents e);
    public void SupprimerMonEnfant (Enfant e);
    public boolean seConnecter(Enfant p);
    public void modifEnfant (Enfant e);
    public void modifParent (Parents p);
    public List<Parents> Donner(Parents p)throws SQLException;
    public void donnation(Parents p);
    public void select(Parents k);
    public void ajouterPaiement (Donation d);
    public void sendMail(String recepient) ;
     public Map <String,String> getcode();
     public void updatepassword(Parents v);
     public List<String> getchamp() ;
     public List<Enfant> rechercheenfant(String nom);
}
