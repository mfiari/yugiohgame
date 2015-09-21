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
public class CarteMonstreEffet extends CarteMonstre {
    
    private Effet effet;
    private Action action;
    
    public CarteMonstreEffet (String nom, int etoiles, Type type, Categorie categorie, String description, int force, int def, int argent, String code, 
            String nomAttaque) {
        super(nom, etoiles, type, categorie, description, force, def, argent, code, nomAttaque);
    }
    
    public void setAction (Action action) {
        this.action = action;
    }
    
    public Action getAction () {
        return this.action;
    }
    
    public void setEffet (Effet effet) {
        this.effet = effet;
        this.effet.setCarte(this);
    }
    
    public Effet getEffet () {
        return this.effet;
    }
}