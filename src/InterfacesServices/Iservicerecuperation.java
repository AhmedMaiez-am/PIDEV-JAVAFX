/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesServices;

import Entities.Enfant;
import Entities.EnfantRecup;
import Entities.Parents;
import Entities.ParentsRecup;
import Entities.recuperation;
import java.util.List;

/**
 *
 * @author maiez
 */
public interface Iservicerecuperation {
     public void ajouterRecuperation(recuperation r);
    public List<recuperation> afficherRecuperation();
    public List<recuperation> trierRecuperation();
    public List<EnfantRecup> fillcombobox1();
    public List<ParentsRecup> fillcombobox2();
    public void sendmail(String Recepient);
    
}
