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
public class Contes {
    private int id;
    private String titre;
    private String auteur;
    private String contes;

    public Contes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContes() {
        return contes;
    }

    public void setContes(String contes) {
        this.contes = contes;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    @Override
    public String toString() {
        return ("Titre du conte : "+titre+"\nAuteur du conte : "+auteur);
    }
    
    
}
