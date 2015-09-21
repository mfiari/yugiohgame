/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.texte.fr;

import mfiari.lib.game.texte.Texte_fr;
import mfiari.yugioh.game.texte.TexteVueDemarrage;

/**
 *
 * @author mike
 */
public class TexteVueDemarrage_fr extends TexteVueDemarrage  {
    
    public TexteVueDemarrage_fr () {
        this.suivant = Texte_fr.suivant;
        this.nouvellePartie = Texte_fr.nouvellePartie;
        this.continuer = Texte_fr.continuer;
        this.extra = Texte_fr.extra;
    }
}
