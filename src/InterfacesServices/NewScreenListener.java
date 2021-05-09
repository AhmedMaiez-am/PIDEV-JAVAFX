/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesServices;


import javafx.event.EventHandler;
import javafx.scene.Node;
/**
 *
 * @author maiez
 */
public interface NewScreenListener extends EventHandler {
    public void ChangeScreen(Node node);
    public void removeTopScreen();
}
