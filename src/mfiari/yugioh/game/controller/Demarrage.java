/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.controller;

import mfiari.lib.game.controlleur.ControlleurVue;
import mfiari.lib.game.texte.Langue;
import mfiari.lib.game.texte.Texte;
import mfiari.lib.game.ville.Endroit;
import mfiari.yugioh.core.ville.EndroitFactory;
import mfiari.yugioh.game.YugiohJeu;
import mfiari.yugioh.game.demarrage.CreationEndroit;
import mfiari.yugioh.game.demarrage.CreationEvenement;
import mfiari.yugioh.game.demarrage.CreationPerso;
import mfiari.yugioh.game.evenement.Evenements;
import mfiari.yugioh.game.extra.MenuExtra;
import mfiari.yugioh.game.perso.Duelistes;

/**
 *
 * @author mike
 */
public class Demarrage extends ControlleurVue {
    
    public Demarrage () {
        super(true);
    }
    
    
    private void debutJeu() {
        do {
            this.pcsControlleurVue.firePropertyChange("choixLangue", null, null);
        } while (this.choix < 1 && this.choix > Langue.values().length);
        Texte.langue = Langue.values()[this.choix-1];
    }

    private void nouvellePartie() {
        /*CreationEndroit creationEndroit = new CreationEndroit();*/
        CreationEvenement creationEvenement = new CreationEvenement();
        CreationPerso creationPerso = new CreationPerso();
        /*ConnexionBD connexionBD = new ConnexionBD();
        Connexion c = new Connexion(connexionBD.getConnexionHSQL(Global.hsqlLocation, Global.hsqlUser, Global.hsqlMdp));
        if (c.isConnected()) {
            c.parties();
            c.CreerPartie();
            connexionBD.fermerConnexionHSQL();
        }*/
        EndroitFactory endroitFactory = new EndroitFactory();
        Endroit endroit = endroitFactory.createEndroit("2");
        if (endroit != null) {
            Duelistes.yugi.getPosition().setEndroit(endroit);
            YugiohJeu jeu = new YugiohJeu();
            jeu.jouer(Evenements.jeu, Duelistes.yugi);
        }
    }

    private void continuer() {
    }

    public void extra() {
        MenuExtra m = new MenuExtra ();
        m.menu();
    }
    
    

    public void jeu() {
        this.debutJeu();
        do {
            this.pcsControlleurVue.firePropertyChange("debutJeu", null, null);
            switch (this.choix) {
                case (1):
                    nouvellePartie();
                    break;
                case (2):
                    continuer();
                    break;
                case (3):
                    extra();
                    break;
            }
        } while (this.choix != 0);
    }
    
}