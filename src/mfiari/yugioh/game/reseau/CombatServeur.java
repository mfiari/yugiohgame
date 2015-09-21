/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.reseau;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mfiari.yugioh.game.carte.Action;
import mfiari.yugioh.game.carte.Resultat;
import mfiari.yugioh.game.combat.Regles;
import mfiari.yugioh.game.perso.Dueliste;

/**
 *
 * @author mike
 */
public class CombatServeur extends CombatReseau {
    
    public CombatServeur (Socket socket) {
        super(socket);
    }

    @Override
    public void combatSimple(Dueliste perso1, Dueliste perso2, Regles[] regles) {
        try {
        this.perso1 = perso1;
        this.perso2 = perso2;
        this.perso1.getDeck().melanger();
        this.perso2.getDeck().melanger();
        this.envoi.writeObject(this.perso1);
        this.envoi.writeObject(this.perso2);
        this.pcsControlleurVue.firePropertyChange("debutCombat", null, null);
        for (int i = 0 ; i < 4 ; i++) {
            this.mainPerso1.ajouterCarte(this.perso1.getDeck().prendreCarte());
            this.mainPerso2.ajouterCarte(this.perso2.getDeck().prendreCarte());
        }
        this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
        while (this.pvPerso1 > 0 && this.pvPerso2 > 0) {
            this.choixPerso(this.perso1, this.perso2, null);
            this.choixEnnemie(this.perso1, this.perso2);
            //this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
            //this.combat(this.perso1, this.perso2);
            this.choixCarteTerrain = 0;
        }
        if (this.pvPerso1 > 0) {
            this.argent(this.perso1, this.perso2);
        } else {
            this.defaite();
        }
        } catch (IOException ex) {
            Logger.getLogger(CombatReseau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Resultat activeEffet(boolean attaquePerso, Action action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
