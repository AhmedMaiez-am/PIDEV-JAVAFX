/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesServices;


import Entities.Authentification;
import Entities.Parents;

/**
 *
 * @author maiez
 */
public interface IServiceAuthentification {
    public void AjouterAuthParent(Parents p) ;
    public void SupprimerAuthParent();
    public void SelectAuthParent(Authentification p);
    

    
}
