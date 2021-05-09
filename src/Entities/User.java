/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;

/**
 *
 * @author maiez
 */
public class User {
    private String email;
    private String password;
    private String code;
    private Timestamp dateCreation;
    private Timestamp dateExp;

    public User() {
    }

    public Timestamp getDateExp() {
        return dateExp;
    }

    public void setDateExp(Timestamp dateExp) {
        this.dateExp = dateExp;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", password=" + password + ", code=" + code + '}';
    }
    
    
    
}
