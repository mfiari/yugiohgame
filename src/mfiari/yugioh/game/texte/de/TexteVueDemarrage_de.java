/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.texte.de;

import mfiari.lib.game.texte.Texte_de;
import mfiari.yugioh.game.texte.TexteVueDemarrage;

/**
 *
 * @author mike
 */
public class TexteVueDemarrage_de extends TexteVueDemarrage  {
    
    public TexteVueDemarrage_de () {
        this.suivant = Texte_de.suivant;
        this.nouvellePartie = Texte_de.nouvellePartie;
        this.continuer = Texte_de.continuer;
        this.extra = Texte_de.extra;
    }
}
