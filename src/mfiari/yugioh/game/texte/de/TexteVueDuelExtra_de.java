/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.texte.de;

import mfiari.lib.game.texte.Texte_de;
import mfiari.yugioh.game.texte.TexteVueDuelExtra;

/**
 *
 * @author mike
 */
public class TexteVueDuelExtra_de extends TexteVueDuelExtra {
    
    public TexteVueDuelExtra_de () {
        this.suivant = Texte_de.suivant;
        this.terminer = Texte_de.terminer;
        this.retour = Texte_de.retour;
        this.confirmer = "bestätigen";
    }
    
}