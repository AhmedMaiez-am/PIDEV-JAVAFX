/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesServices;


import Entities.Conseil;
import Entities.Maitresse;
import Entities.Parents;
import Entities.Reclamation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author maiez
 */
public interface IserviceReclamation {
    public void AddReclamation(Reclamation r);
   public List<Reclamation> AfficherReclamationpa() throws SQLException;
   public List<Reclamation> AfficherReclamationpr() throws SQLException;
   public void modifierReclamation(int id, Reclamation r);
    public void supprimerReclamation(Reclamation r);
       public List<Reclamation> rechercherreclamation(Reclamation r);
       public List<Conseil> rechercheconseil( String nom);
       public void afficher(Parents p)throws SQLException;
       public void afficherM(Maitresse m)throws SQLException;
}
