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
public class Enfant {
    private int IdEn ;
    private String nomEnfant ;
    private String prenomEnfant ;
    private int nbr_point ;
    private String age ;
    private int idparent ;
    private String motdepasse ;
    private String img;

    public Enfant(int IdEn, String nomEnfant, String prenomEnfant, int nbr_point, String age, int idparent, String motdepasse, String img) {
        this.IdEn = IdEn;
        this.nomEnfant = nomEnfant;
        this.prenomEnfant = prenomEnfant;
        this.nbr_point = nbr_point;
        this.age = age;
        this.idparent = idparent;
        this.motdepasse = motdepasse;
        this.img=img;
    }

    public Enfant(String nomEnfant, String prenomEnfant, String age, String motdepasse) {
        this.nomEnfant = nomEnfant;
        this.prenomEnfant = prenomEnfant;
        this.age = age;
        this.motdepasse = motdepasse;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public Enfant() {
    }

    public Enfant(String nomEnfant, String motdepasse) {
        this.nomEnfant = nomEnfant;
        this.motdepasse = motdepasse;
    }

   
     
    public Enfant( String nomEnfant, String prenomEnfant, String age) {
        
        this.nomEnfant = nomEnfant;
        this.prenomEnfant = prenomEnfant;
        this.age = age;
    }

    public Enfant(int IdEn, String nomEnfant, String prenomEnfant, String age) {
        this.IdEn = IdEn;
        this.nomEnfant = nomEnfant;
        this.prenomEnfant = prenomEnfant;
        this.age = age;
    }
    

    public Enfant(int IdEn, String nomEnfant, String prenomEnfant, int nbr_point, String age, int idparent) {
        this.IdEn = IdEn;
        this.nomEnfant = nomEnfant;
        this.prenomEnfant = prenomEnfant;
        this.nbr_point = nbr_point;
        this.age = age;
        this.idparent = idparent;
    }

    public int getIdEnfant() {
        return IdEn;
    }

    public void setIdEnfant(int IdEn) {
        this.IdEn = IdEn;
    }

    public String getNomEnfant() {
        return nomEnfant;
    }

    public void setNomEnfant(String nomEnfant) {
        this.nomEnfant = nomEnfant;
    }

    public String getPrenomEnfant() {
        return prenomEnfant;
    }

    public void setPrenomEnfant(String prenomEnfant) {
        this.prenomEnfant = prenomEnfant;
    }

    public int getNbr_point() {
        return nbr_point;
    }

    public void setNbr_point(int nbr_point) {
        this.nbr_point = nbr_point;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getIdparent() {
        return idparent;
    }

    public void setIdparent(int idparent) {
        this.idparent = idparent;
    }

    @Override
    public String toString() {
        return ("Nom : "+nomEnfant+", Prenom : "+prenomEnfant);
    }

  
    
}

