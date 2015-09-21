/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.carte;

import java.io.Serializable;

/**
 *
 * @author mike
 */
abstract public class Carte implements Serializable {
    
    private int prix;
    private String nom;
    private String description;
    private String code;
    
    public Carte (String nom, String description, int prix, String code) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.code = code;
    }

    public int getPrix() {
        return this.prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
}
