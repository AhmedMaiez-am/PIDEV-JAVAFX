/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesServices;

import Entities.InventaireContes;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author maiez
 */
public interface IServiceInventaireContes {
    public List<InventaireContes> afficherContes() throws SQLException;
    public void supprimerContes(InventaireContes c);
}
