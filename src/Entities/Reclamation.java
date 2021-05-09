/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author maiez
 */
public class Reclamation {
    private int idr;
    private String nom,prenom,reclamation,type,etat,email,pp;
    Date date_creation;
 public Reclamation(String nom, String prenom, String reclamation) {
       
        this.nom = nom;
        this.prenom = prenom;
        this.reclamation = reclamation;
     
    }

    public Reclamation(String nom, String prenom, String reclamation, String etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.reclamation = reclamation;
        this.etat = etat;
    }
 public Reclamation(String nom, String prenom, String reclamation, String etat , String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.reclamation = reclamation;
        this.etat = etat;
        this.email=email;
    }
    
 
    public Reclamation() {
    }

    public int getIdr() {
        return idr;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getReclamation() {
        return reclamation;
    }

    public void setReclamation(String reclamation) {
        this.reclamation = reclamation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }
    

    @Override
    public String toString() {
        return "La Reclamation de : " + " " + nom +  " " + prenom +  "\n" + "est : " + reclamation +  "\n" + "Son etat :" + etat + "\n" + "Sa date de création est :" + date_creation + '}';
    }
  
}
