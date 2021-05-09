/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesServices;

import Entities.Recompense;
import java.util.List;

/**
 *
 * @author maiez
 */
public interface Iservicerecompense {
    public void ajouterRecompense(Recompense r);
    public  List<Recompense> afficherRecompense();
    public void supprimerRecompense(Recompense r);
    public void modifierRecompense(Recompense r);
    public List<Recompense> rechercherRecompense(Recompense r);
    
}
