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
public class InventaireCours {
    private int idCc;
    private String nomC;
    private String typec;
    private String descC;

    public InventaireCours() {
    }

    public InventaireCours(String typec, String descC, String nomC) {
        this.typec = typec;
        this.descC = descC;
        this.nomC = nomC;
    }

    public int getIdCc() {
        return idCc;
    }

    public void setIdCc(int idCc) {
        this.idCc = idCc;
    }



    public String getTypec() {
        return typec;
    }

    public void setTypec(String typec) {
        this.typec = typec;
    }

    public String getDescC() {
        return descC;
    }

    public void setDescC(String descC) {
        this.descC = descC;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    @Override
    public String toString() {
        return " Nom : "+nomC+"\n Type : "+typec+"\n Description : "+descC;
    }

}
