/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.texte.en;

import mfiari.lib.game.texte.Texte_en;
import mfiari.yugioh.game.texte.TexteVueDuelExtra;

/**
 *
 * @author mike
 */
public class TexteVueDuelExtra_en extends TexteVueDuelExtra {
    
    public TexteVueDuelExtra_en () {
        this.suivant = Texte_en.suivant;
        this.terminer = Texte_en.terminer;
        this.retour = Texte_en.retour;
        this.confirmer = "confirm";
    }
    
}