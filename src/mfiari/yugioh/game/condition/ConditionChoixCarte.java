/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.condition;

import mfiari.lib.game.condition.ConditionPrimaire;
import mfiari.yugioh.game.carte.Carte;
import mfiari.yugioh.game.carte.Mode;
import mfiari.yugioh.game.carte.Visibilite;

/**
 *
 * @author mike
 */
public class ConditionChoixCarte extends ConditionPrimaire {
    
    private Carte carte;
    private Mode mode;
    private Visibilite visibilite;
    
    public ConditionChoixCarte (Carte carte, Mode mode, Visibilite visibilite) {
        this.carte = carte;
        this.mode = mode;
        this.visibilite = visibilite;
    }
    
    public boolean equals (Carte carte) {
        return this.carte.equals(carte);
    }
    
    public boolean equals (Carte carte, Mode mode, Visibilite visibilite) {
        return this.carte.equals(carte) && this.mode == mode && this.visibilite == visibilite;
    }
    
}
