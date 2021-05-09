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
public class Authentification {
    private int idAuth;
    private String emailP;
    private String passP;

    public Authentification() {
    }

    public int getIdAuth() {
        return idAuth;
    }

    public void setIdAuth(int idAuth) {
        this.idAuth = idAuth;
    }

    public String getEmailP() {
        return emailP;
    }

    public void setEmailP(String emailP) {
        this.emailP = emailP;
    }

    public String getPassP() {
        return passP;
    }

    public void setPassP(String passP) {
        this.passP = passP;
    }

    @Override
    public String toString() {
        return "Authentification{" + "emailP=" + emailP + ", passP=" + passP + '}';
    }
    
    
    
}
