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
public class Donation {
    private int id ;
    private String montant ;
    private String numcarte ;

    public Donation(String montant, String numcarte) {
        this.montant = montant;
        this.numcarte = numcarte;
    }

    public Donation() {
    }

    public String getNumcarte() {
        return numcarte;
    }

    public void setNumcarte(String numcarte) {
        this.numcarte = numcarte;
    }
    
    public Donation(int id, String montant) {
        this.id = id;
        this.montant = montant;
    }

    public Donation(String montant) {
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }
  
            
}

