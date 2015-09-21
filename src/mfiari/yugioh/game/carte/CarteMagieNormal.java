/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.carte;

import mfiari.yugioh.game.effet.Effet;

/**
 *
 * @author mike
 */
public class CarteMagieNormal extends CarteMagie {
    
    private Effet effet;
    
    public CarteMagieNormal (String nom, TypeMagie type, String description, int argent, String code, Affectation affectation) {
        super(nom, type, description, argent, code, affectation);
    }
    
    public void ajouterEffet (Effet effet) {
        this.effet = effet;
    }
    
    public Effet getEffet () {
        return this.effet;
    }
}
