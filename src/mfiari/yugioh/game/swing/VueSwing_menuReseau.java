/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.swing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import mfiari.lib.game.swing.Ecran;
import mfiari.lib.game.swing.PanelDeTexteAffichage;
import mfiari.lib.game.swing.VueSwing;
import mfiari.yugioh.game.reseau.MenuReseau;
import mfiari.yugioh.game.texte.TexteVueMenuReseau;

/**
 *
 * @author mike
 */
public class VueSwing_menuReseau extends VueSwing {
    
    private TexteVueMenuReseau textes;
    private MenuReseau menu;

    public VueSwing_menuReseau(MenuReseau menu) {
        super(TexteVueMenuReseau.getInstance(), menu);
        this.menu = menu;
        this.textes = TexteVueMenuReseau.getInstance();
        Ecran.panel.redimenssionnerCentre(900, 550);
        Ecran.panel.redimenssionnerNord(1200, 100);
        Ecran.panel.afficherNord();
        Ecran.panel.afficherCentre();
        Ecran.panel.cacherEst();
        Ecran.panel.cacherOuest();
        Ecran.panel.cacherSud();
        this.menu.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                switch (evt.getPropertyName()) {
                    case "actualiseLangue":
                        actualiseLangue();
                        break;
                    case "attenteJoueur":
                        attenteJoueur();
                        break;
                    case "rechercheHote":
                        rechercheHote();
                        break;
                    default:
                        switch (evt.getPropertyName()) {
                            case "afficherMenu":
                                afficherMenu();
                                break;
                            case "choixClientServeur":
                                choixClientServeur();
                                break;
                        }
                        attendre();
                }
            }
        });
    }
    
    private void actualiseLangue() {
        super.actualiseLangue(TexteVueMenuReseau.getInstance());
        this.textes = TexteVueMenuReseau.getInstance();
   }
    
    private void afficherMenu() {
        JPanel panel2 = new JPanel ();
        JButton boutonRetour = new JButton(this.textes.retour);
        boutonRetour.addActionListener(new VueSwing.boutonChoix(0));
        panel2.add(boutonRetour);
        Ecran.panel.ajouterCentre(panel2);
        Ecran.panel.viderNord();
        Ecran.panel.viderSud();
    }
    
    private void choixClientServeur() {
        JPanel panel2 = new JPanel ();
        JButton boutonServeur = new JButton("Creer une partie");
        boutonServeur.addActionListener(new VueSwing.boutonChoix(1));
        JButton boutonClient = new JButton("rejoindre une partie");
        boutonClient.addActionListener(new VueSwing.boutonChoix(2));
        JButton boutonRetour=new JButton(this.textes.retour);
        boutonRetour.addActionListener(new VueSwing.boutonChoix(0));
        panel2.add(boutonServeur);
        panel2.add(boutonClient);
        panel2.add(boutonRetour);
        Ecran.panel.ajouterSud(panel2);
        Ecran.panel.viderCentre();
    }
    
    private void attenteJoueur() {
        Ecran.panel.ajouterCentre(new PanelDeTexteAffichage("en attente de joueur"));
    }
    
    private void rechercheHote() {
        Ecran.panel.ajouterCentre(new PanelDeTexteAffichage("recherche d'hôte"));
    }
    
}