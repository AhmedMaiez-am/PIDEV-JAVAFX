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
public class Conseil {
    private int idc;
    private String nomc,prenomc,conseil,type;

    public Conseil() {
    }

    public Conseil(String nomc, String prenomc, String conseil) {
        this.nomc = nomc;
        this.prenomc = prenomc;
        this.conseil = conseil;
    }
    

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getNomc() {
        return nomc;
    }

    public void setNomc(String nom) {
        this.nomc = nom;
    }

    public String getPrenomc() {
        return prenomc;
    }

    public void setPrenomc(String prenom) {
        this.prenomc = prenom;
    }

    public String getConseil() {
        return conseil;
    }

    public void setConseil(String conseil) {
        this.conseil = conseil;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Conseil{" + " nom=" + nomc + ", prenom=" + prenomc + ", conseil=" + conseil + "}\n ";
    }
}
