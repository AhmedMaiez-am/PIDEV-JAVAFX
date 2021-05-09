/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesServices;

import Entities.Enfant;
import Entities.MaitresseValidation;
import Entities.Parents;
import Entities.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author maiez
 */
public interface IServiceUser {
    public void sendMail(String recepient) ;
    public void getMail (User u);
    public void getDate (User u);
    public List<String>  getchamp ();
    public Map<String,String>  getcode ();
    public void updatepassword(User u);
    public void ajouterMaitresse (MaitresseValidation m);
    public void ajouterParent (Parents p);
    //public void ajouterEnfant (Enfant e);
}
