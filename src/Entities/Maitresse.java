/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;

/**
 *
 * @author HP
 */
public class Maitresse {
      private int idM ;
    private String cin ;
    private String nom ;
    private String prenom ;
    private String email ;
    private String etat ;
    private Timestamp dated;
    private String imag ;
    private String cv ;

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public Maitresse(String etat, String cin, String nom, String prenom, String email,Timestamp dated, String imag, String cv) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.etat = etat;
        this.dated = dated;
        this.imag = imag;
        this.cv = cv;
    }

    public Maitresse(int idM, String cin, String nom, String prenom, String email, String etat, Timestamp dated, String imag, String cv) {
        this.idM = idM;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.etat = etat;
        this.dated = dated;
        this.imag = imag;
        this.cv = cv;
    }

    public String getImag() {
        return imag;
    }

    public void setImag(String imag) {
        this.imag = imag;
    }

    public Maitresse(String etat ,String cin, String nom, String prenom, String email,Timestamp dated, String imag) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.etat = etat;
        this.dated = dated;
        this.imag = imag ;
    }

    public Maitresse(int idM, String cin, String nom, String prenom, String email, String etat, Timestamp dated, String imag) {
        this.idM = idM;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.etat = etat;
        this.dated = dated;
        this.imag = imag;
    }

    public Timestamp getDated() {
        return dated;
    }

    public void setDated(Timestamp dated) {
        this.dated = dated;
    }

    
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Maitresse(int idM, String cin, String nom, String prenom, String email, String etat) {
        this.idM = idM;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.etat = etat;
    }
    public Maitresse() {
    }

    public Maitresse(String cin, String nom) {
        this.cin = cin;
        this.nom = nom;
    }

    public Maitresse(int idM, String cin, String nom, String prenom, String email) {
        this.idM = idM;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Maitresse(String cin, String nom, String prenom, String email) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public int getIdM() {
        return idM;
    }

    public void setIdM(int idM) {
        this.idM = idM;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ("Le nom du maitresse : "+nom+" ");
    }

}

