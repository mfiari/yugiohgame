/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.console;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import mfiari.lib.game.console.VueConsole;
import mfiari.yugioh.game.demarrage.Demarrage;
import mfiari.yugioh.game.texte.TexteVueDemarrage;

/**
 *
 * @author mike
 */
public class VueConsole_demarrage extends VueConsole {
    
    private Demarrage demarrage;
    private TexteVueDemarrage textes;
    
    public VueConsole_demarrage (Demarrage demarage) {
        super(TexteVueDemarrage.getInstance());
        this.demarrage = demarage;
        this.textes = TexteVueDemarrage.getInstance();
        this.demarrage.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                switch (evt.getPropertyName()) {
                    case "choixLangue":
                        choixLangue();
                        break;
                    case "debutJeu":
                        debutJeu();
                        break;
                }
            }
        });
    }

    public void choixLangue() {
    }

    public void debutJeu() {
    }
}
