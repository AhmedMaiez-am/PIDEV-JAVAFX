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
public class MaitresseValidation {
    private String loginM ;
    private String passwordM ;
    private String codeM;
    private int idm ;
    private String img;

    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public MaitresseValidation(String loginM, String passwordM) {
        this.loginM = loginM;
        this.passwordM = passwordM;
    }

    public MaitresseValidation(String loginM) {
        this.loginM = loginM;
    }

    public String getLoginM() {
        return loginM;
    }

    public void setLoginM(String loginM) {
        this.loginM = loginM;
    }

    public String getPasswordM() {
        return passwordM;
    }

    public void setPasswordM(String passwordM) {
        this.passwordM = passwordM;
    }

    public String getCodeM() {
        return codeM;
    }

    public void setCodeM(String codeM) {
        this.codeM = codeM;
    }
   

    public MaitresseValidation() {
    }
}
