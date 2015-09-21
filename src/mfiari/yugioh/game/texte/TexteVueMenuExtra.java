/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.texte;

import mfiari.lib.game.texte.Texte;
import mfiari.yugioh.game.texte.de.TexteVueMenuExtra_de;
import mfiari.yugioh.game.texte.en.TexteVueMenuExtra_en;
import mfiari.yugioh.game.texte.fr.TexteVueMenuExtra_fr;

/**
 *
 * @author mike
 */
public class TexteVueMenuExtra extends Texte {
    
    public String ligne;
    public String duel;
    public String configuration;
    public String retour;
    public String choixLangue;
    
    public static TexteVueMenuExtra getInstance() {
        switch (langue) {
            case fr :
                return new TexteVueMenuExtra_fr();
            case en:
                return new TexteVueMenuExtra_en();
            case de:
                return new TexteVueMenuExtra_de();
            default :
                return new TexteVueMenuExtra_fr();
        }
    }
}
