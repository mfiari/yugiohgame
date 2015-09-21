/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.texte;

import mfiari.lib.game.texte.Texte;
import static mfiari.lib.game.texte.Texte.langue;
import mfiari.yugioh.game.texte.de.TexteVueDemarrage_de;
import mfiari.yugioh.game.texte.en.TexteVueDemarrage_en;
import mfiari.yugioh.game.texte.fr.TexteVueDemarrage_fr;

/**
 *
 * @author mike
 */
public class TexteVueDemarrage extends Texte {
    
    public String nouvellePartie;
    public String continuer;
    public String extra;
    
    public static TexteVueDemarrage getInstance () {
        switch (langue) {
            case fr:
                return new TexteVueDemarrage_fr();
            case en:
                return new TexteVueDemarrage_en();
            case de:
                return new TexteVueDemarrage_de();
        }
        return null;
    }
}
