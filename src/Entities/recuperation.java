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
 public class recuperation {
     private int idRecuperation;
    private String NomRec;
    private String nomEnf;
    private  String emailP;
    private int nbr_point;

    public recuperation() {
    }

    public recuperation(int idRecuperation, String nomEnf, String emailP, String NomRec,int nbr_point) {
        this.idRecuperation = idRecuperation;
        this.nomEnf = nomEnf;
        this.emailP = emailP;
        this.NomRec = NomRec;
        this.nbr_point=nbr_point;
       
    }
    public int getIdRecuperation() {
        return idRecuperation;
    }

    public void setIdRecuperation(int idRecuperation) {
        this.idRecuperation = idRecuperation;
    }

    public String getnomEnf() {
        return nomEnf;
    }

    public void setnomEnf(String nomEnf) {
        this.nomEnf = nomEnf;
    }

    public String getEmailP() {
        return emailP;
    }

    public void setEmailP(String emailP) {
        this.emailP = emailP;
    }
 
    public String getNomRec() {
        return NomRec;
    }
 
    public void setNomRec(String NomRec) {
        this.NomRec = NomRec;
    }
   

    public int getNbr_point() {
        return nbr_point;
    }

    public void setNbr_point(int nbr_point) {
        this.nbr_point = nbr_point;
    }

    @Override
    public String toString() {
        return   "Nom du récompense =" + NomRec +"\n" + "Nom de l'enfant =" + nomEnf +"\n" +"Email du parent =" + emailP  +"\n"+ "Nombre des points =" + nbr_point + "";
    }

}
