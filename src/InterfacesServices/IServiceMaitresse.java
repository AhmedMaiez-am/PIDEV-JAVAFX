/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesServices;

import Entities.Maitresse;
import Entities.MaitresseValidation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author maiez
 */
public interface IServiceMaitresse {
    public void AjouterDemande(Maitresse m);
    public boolean seConnecter1 (MaitresseValidation m);
    public void supprimerCompte (Maitresse o) ;
    public void ModifierCompte (Maitresse o);
    public List<Maitresse> afficherM() throws SQLException;
    
}
