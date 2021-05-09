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
public class Parents {
    private int idP;
    private String nomP;
    private String prenomP;
    private String telP ;
    private String passwordP ;
    private String emailP ;
    private String NumcarteP ;
    private String passcaretp;
    private String pf;
    private String codep ;
    private String block;

    public Parents() {
    }

    public Parents(String emailP) {
        this.emailP = emailP;
    }

    public Parents(String nomP, String emailP, String NumcarteP, String passcaretp, String pf) {
        this.nomP = nomP;
        this.emailP = emailP;
        this.NumcarteP = NumcarteP;
        this.passcaretp = passcaretp;
        this.pf = pf;
    }

    public Parents(String nomP, String prenomP) {
        this.nomP = nomP;
        this.prenomP = prenomP;
    }

    public Parents(String nomP, String NumcarteP, String passcaretp, String pf) {
        this.nomP = nomP;
        this.NumcarteP = NumcarteP;
        this.passcaretp = passcaretp;
        this.pf = pf;
    }

    public Parents(String nomP, String NumcarteP, String passcaretp) {
        this.nomP = nomP;
        this.NumcarteP = NumcarteP;
        this.passcaretp = passcaretp;
    }

    public Parents(int idP, String nomP, String prenomP, String telP, String passwordP, String emailP) {
        this.idP = idP;
        this.nomP = nomP;
        this.prenomP = prenomP;
        this.telP = telP;
        this.passwordP = passwordP;
        this.emailP = emailP;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    
    

    public String getPasswordP() {
        return passwordP;
    }

    public void setPasswordP(String passwordP) {
        this.passwordP = passwordP;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getTelP() {
        return telP;
    }

    public void setTelP(String telP) {
        this.telP = telP;
    }

    public String getEmailP() {
        return emailP;
    }

    public void setEmailP(String emailP) {
        this.emailP = emailP;
    }

    public String getNumcarteP() {
        return NumcarteP;
    }

    public void setNumcarteP(String NumcarteP) {
        this.NumcarteP = NumcarteP;
    }

    public String getPasscaretp() {
        return passcaretp;
    }

    public void setPasscaretp(String passcaretp) {
        this.passcaretp = passcaretp;
    }

    public String getCodep() {
        return codep;
    }

    public void setCodep(String codep) {
        this.codep = codep;
    }
    

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public String getPrenomP() {
        return prenomP;
    }

    public void setPrenomP(String prenomP) {
        this.prenomP = prenomP;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    @Override
    public String toString() {
        return "Nom parent: "+nomP+" Num carte :"+NumcarteP+" Passe carte :"+passcaretp+"Portefeuille :"+pf+" Email :"+emailP;
    }
    
    
}
