/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.effet;

import mfiari.yugioh.game.carte.Carte;
import mfiari.yugioh.game.carte.Resultat;
import mfiari.yugioh.game.combat.AbstractCombat;

/**
 *
 * @author mike
 */
public abstract class Effet {
    
    private String nom;
    private Carte cartePossedantEffet;
    
    public Effet (String nom) {
        this.nom = nom;
    }
    
    public String getNom () {
        return this.nom;
    }
    
    public void setCarte (Carte carte) {
        this.cartePossedantEffet = carte;
    }
    
    public Carte getCarte () {
        return this.cartePossedantEffet;
    }
    
    public abstract Resultat run (AbstractCombat combat);
    
    public abstract void annule (AbstractCombat combat);
    
}
