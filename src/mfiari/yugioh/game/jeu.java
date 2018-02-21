/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game;

import mfiari.lib.game.connexionBD.ConnexionBD;
import mfiari.lib.game.swing.Ecran;
import mfiari.yugioh.game.connexion.CreationBase;
import mfiari.yugioh.game.controller.Demarrage;

/**
 *
 * @author mike
 */
public class jeu {
    
    //programme principale du jeu, permet de jouer au jeu
    public static void main(String args[]) {
        Ecran ecran = new Ecran("Yu-Gi-Oh");
        ConnexionBD connexionBD = new ConnexionBD();
        CreationBase creationBase = new CreationBase(connexionBD.getConnexionHSQL(Global.hsqlLocation, Global.hsqlUser, Global.hsqlMdp));
        connexionBD.fermerConnexionHSQL();
        Demarrage d = new Demarrage ();
        d.jeu();
    }
    
}
