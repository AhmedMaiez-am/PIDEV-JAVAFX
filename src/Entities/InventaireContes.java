/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author maiez
 */
public class InventaireContes {
    private int idConteC;
    private String titreC;
    private String auteurC;

    public InventaireContes() {
    }

    public int getIdConteC() {
        return idConteC;
    }

    public void setIdConteC(int idConteC) {
        this.idConteC = idConteC;
    }

    public String getTitreC() {
        return titreC;
    }

    public void setTitreC(String titreC) {
        this.titreC = titreC;
    }

    public String getAuteurC() {
        return auteurC;
    }

    public void setAuteurC(String auteurC) {
        this.auteurC = auteurC;
    }

    @Override
    public String toString() {
        return "Titre du conte :"+titreC+"\nAuteur du contes :"+auteurC;
    }
    
    
}
