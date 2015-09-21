/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.texte.en;

import mfiari.lib.game.texte.Texte_en;
import mfiari.yugioh.game.texte.TexteVueDemarrage;

/**
 *
 * @author mike
 */
public class TexteVueDemarrage_en extends TexteVueDemarrage {
    
    public TexteVueDemarrage_en () {
        this.suivant = Texte_en.suivant;
        this.nouvellePartie = Texte_en.nouvellePartie;
        this.continuer = Texte_en.continuer;
        this.extra = Texte_en.extra;
    }
}
