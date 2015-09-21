/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.reseau;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mfiari.lib.game.controlleur.ControlleurVue;
import mfiari.lib.game.reseau.Client;
import mfiari.lib.game.reseau.Serveur;
import mfiari.yugioh.game.Global;
import mfiari.yugioh.game.demarrage.CreationPerso;
import mfiari.yugioh.game.perso.Duelistes;

/**
 *
 * @author mike
 */
public class MenuReseau extends ControlleurVue {
    
    public MenuReseau () {
        
    }
    
    public void menu() {
        CreationPerso creationPerso = new CreationPerso();
        do {
            this.pcsControlleurVue.firePropertyChange("choixClientServeur", null, null);
            switch (choix) {
                case 1 :
                    Serveur serveur = new Serveur();
                    serveur.lancerServeur(Global.port);
                    while (!serveur.clientArrive()) {
                        this.pcsControlleurVue.firePropertyChange("attenteJoueur", null, null);
                        this.attendre(1000);
                    }
                    Socket socket = serveur.getSocket();
                    CombatServeur combat = new CombatServeur(socket);
                    combat.combatSimple(Duelistes.yugi, Duelistes.kaiba, null);
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuReseau.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 2 :
                    Client client = new Client();
                    do {
                        this.pcsControlleurVue.firePropertyChange("rechercheHote", null, null);
                        this.attendre(1000);
                        client.lancerClient(Global.serveur, Global.port);
                    } while (!client.estConnecter());
                    Socket socket2 = client.getSocket();
                    CombatClient c = new CombatClient (socket2);
                    c.combatSimple(Duelistes.kaiba, Duelistes.yugi, null);
                    try {
                        socket2.close();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuReseau.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        } while (choix != 0);
    }
    
}