/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesServices;

import Entities.Cours;
import Entities.InventaireCours;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author maiez
 */
public interface IServiceCours {
    public List<Cours> afficherCours() throws SQLException;
    public void ajouterCours(InventaireCours c);
    public List<Cours> rechercheCours(Cours m) throws SQLException;
    public int count(Cours v);
    //CRUD Cours
    public void AddCours(Cours c);
    //public void modifCours(Cours c);
    public void supprimerCours(Cours c);
    public void updateCours(Cours c ,String n);
    public List<Cours> recherchecours( String nom);
    
}
