/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesServices;

import Entities.Contes;
import Entities.InventaireContes;
import Entities.InventaireCours;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author maiez
 */
public interface IServiceContes {
    public List<Contes> afficherContes() throws SQLException;
    public void ajouterContes(InventaireContes c);
    public List<Contes> rechercheContes(Contes c) throws SQLException;
    //CRUD Contes
    public void Addcont( Contes c);
    //public void modifcontes(Contes c);
    public void supprimercontes(Contes c);
    public void upadatecontes(Contes c,String n);
    public List<Contes> recherchecontes( String nom);
}
