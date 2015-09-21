/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.texte;

import mfiari.lib.game.texte.Texte;
import mfiari.yugioh.game.texte.de.TexteVueCombat_de;
import mfiari.yugioh.game.texte.en.TexteVueCombat_en;
import mfiari.yugioh.game.texte.fr.TexteVueCombat_fr;

/**
 *
 * @author mike
 */
public class TexteVueCombat extends Texte {
    
    public String attaquer;
    public String attenteAdv;
    
    public static TexteVueCombat getInstance () {
        switch (langue) {
            case fr:
                return new TexteVueCombat_fr();
            case en:
                return new TexteVueCombat_en();
            case de:
                return new TexteVueCombat_de();
        }
        return null;
    }
}