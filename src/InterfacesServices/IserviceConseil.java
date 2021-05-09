/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesServices;

import Entities.Conseil;
import Entities.Maitresse;
import Entities.Parents;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maiez
 */
public interface IserviceConseil {
    public void AddConseil(Conseil c);
   public List<Conseil> AfficherConseil() throws SQLException;
      public void modifierConseil(Conseil c);
    public void supprimerConseil(Conseil c);
           public List<Conseil> rechercherconseil(Conseil c);
           public void afficherC(Parents p)throws SQLException;
       public void afficherCM(Maitresse m) throws SQLException;

}
