/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.texte.de;

import mfiari.lib.game.texte.Texte_de;
import mfiari.yugioh.game.texte.TexteVueCombat;

/**
 *
 * @author mike
 */
public class TexteVueCombat_de extends TexteVueCombat {
    
    public TexteVueCombat_de () {
        this.suivant = Texte_de.suivant;
        this.attaquer = "attack";
        this.attenteAdv = "waiting for the opponent...";
    }
    
}