/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.texte;

import mfiari.lib.game.texte.Texte;
import mfiari.yugioh.game.texte.de.TexteVueDuelExtra_de;
import mfiari.yugioh.game.texte.en.TexteVueDuelExtra_en;
import mfiari.yugioh.game.texte.fr.TexteVueDuelExtra_fr;

/**
 *
 * @author mike
 */
public class TexteVueDuelExtra extends Texte {
    
    public String terminer;
    public String retour;
    public String confirmer;
    
    public static TexteVueDuelExtra getInstance() {
        switch (langue) {
            case fr :
                return new TexteVueDuelExtra_fr();
            case en:
                return new TexteVueDuelExtra_en();
            case de:
                return new TexteVueDuelExtra_de();
            default :
                return new TexteVueDuelExtra_fr();
        }
    }
}