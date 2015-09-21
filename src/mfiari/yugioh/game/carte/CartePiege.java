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
abstract public class CartePiege extends Carte {
    
    private Effet effet;
    private Action action;
    private TypeMagie type;
    
    public CartePiege (String nom, TypeMagie type, String description, int argent, String code) {
        super(nom, description, argent, code);
        this.type = type;
    }

    public TypeMagie getType() {
        return this.type;
    }

    public void setType(TypeMagie type) {
        this.type = type;
    }
    
    public void setAction (Action action) {
        this.action = action;
    }
    
    public Action getAction () {
        return this.action;
    }
    
    public void setEffet (Effet effet) {
        this.effet = effet;
    }
    
    public Effet getEffet () {
        return this.effet;
    }
    
}