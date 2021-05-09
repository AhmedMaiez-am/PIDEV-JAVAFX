/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesServices;


import Entities.InventaireCours;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author maiez
 */
public interface IServiceInventaireCours {
    public List<InventaireCours> afficherCours() throws SQLException;
    public void supprimerCours(InventaireCours c);
}