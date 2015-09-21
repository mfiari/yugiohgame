/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.texte.fr;

import mfiari.lib.game.texte.Texte_fr;
import mfiari.yugioh.game.texte.TexteVueCombat;

/**
 *
 * @author mike
 */
public class TexteVueCombat_fr extends TexteVueCombat {
    
    public TexteVueCombat_fr () {
        this.suivant = Texte_fr.suivant;
        this.attaquer = "attaquer";
        this.attenteAdv = "En attente du joueur adverse...";
    }
    
}