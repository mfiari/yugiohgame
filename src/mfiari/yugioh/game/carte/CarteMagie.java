/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.carte;

/**
 *
 * @author mike
 */
abstract public class CarteMagie extends Carte {
    
    private TypeMagie type;
    private Affectation affectation;
    
    public CarteMagie (String nom, TypeMagie type, String description, int argent, String code, Affectation affectation) {
        super(nom, description, argent, code);
        this.type = type;
        this.affectation = affectation;
    }

    public TypeMagie getTypeMagie() {
        return this.type;
    }

    public void setTypeMagie(TypeMagie type) {
        this.type = type;
    }

    public Affectation getAffectation() {
        return this.affectation;
    }

    public void setAffectation(Affectation affectation) {
        this.affectation = affectation;
    }
    
    public boolean estPermanent () {
        return this.type == TypeMagie.continu || this.type == TypeMagie.equipement || this.type == TypeMagie.terrain;
    }
    
}