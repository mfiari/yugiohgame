/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.texte;

import mfiari.lib.game.texte.Texte;
import mfiari.yugioh.game.texte.de.TexteVueJeu_de;
import mfiari.yugioh.game.texte.en.TexteVueJeu_en;
import mfiari.yugioh.game.texte.fr.TexteVueJeu_fr;

/**
 *
 * @author mike
 */
public class TexteVueJeu extends Texte {
    
    public String haut;
    public String bas;
    public String droite;
    public String gauche;
    public String action;
    public String menu;
    public String queFaire;
    public String oui;
    public String non;
    public String quitter;
    
    public static TexteVueJeu getInstance () {
        switch (langue) {
            case fr:
                return new TexteVueJeu_fr();
            case en:
                return new TexteVueJeu_en();
            case de:
                return new TexteVueJeu_de();
            default :
                return new TexteVueJeu_fr();
        }
    }
}
