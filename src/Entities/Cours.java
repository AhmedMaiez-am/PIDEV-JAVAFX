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
public class Cours {
    private int idC;
    private String nom;
    private String type;
    private String desc;
    private String cours;
    private String prixC;

    public Cours() {
    }

    public Cours(String type, String desc, String nom, String prixC) {
        this.type = type;
        this.desc = desc;
        this.nom = nom;
        this.prixC = prixC;
    }

    public String getCours() {
        return cours;
    }

    public void setCours(String cours) {
        this.cours = cours;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrixC() {
        return prixC;
    }

    public void setPrixC(String prixC) {
        this.prixC = prixC;
    }
    

    @Override
    public String toString() {
        return ("Nom du cours : \n"+nom);
    }


    
    
}
