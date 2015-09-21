/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.texte;

import mfiari.lib.game.texte.Texte;
import mfiari.yugioh.game.texte.de.TexteVueMenuReseau_de;
import mfiari.yugioh.game.texte.en.TexteVueMenuReseau_en;
import mfiari.yugioh.game.texte.fr.TexteVueMenuReseau_fr;

/**
 *
 * @author mike
 */
public class TexteVueMenuReseau extends Texte {
    
    public String retour;
    
    public static TexteVueMenuReseau getInstance() {
        switch (langue) {
            case fr :
                return new TexteVueMenuReseau_fr();
            case en:
                return new TexteVueMenuReseau_en();
            case de:
                return new TexteVueMenuReseau_de();
            default:
                return new TexteVueMenuReseau_fr();
        }
    }
}