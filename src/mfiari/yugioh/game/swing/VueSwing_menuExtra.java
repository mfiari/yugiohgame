/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.swing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import mfiari.lib.game.clavier.ActionPerso;
import mfiari.lib.game.clavier.CodeBouton;
import mfiari.lib.game.clavier.KeyDispatcher;
import mfiari.lib.game.clavier.ListComponentNumber;
import mfiari.lib.game.clavier.ListKeyAction;
import mfiari.lib.game.swing.BoutonImage;
import mfiari.lib.game.swing.Ecran;
import mfiari.lib.game.swing.VueSwing;
import mfiari.lib.game.texte.Langue;
import mfiari.yugioh.game.extra.MenuExtra;
import mfiari.yugioh.game.media.image.util.ImageUtil;
import mfiari.yugioh.game.texte.TexteVueMenuExtra;

/**
 *
 * @author mike
 */
public class VueSwing_menuExtra extends VueSwing {
    
    private TexteVueMenuExtra textes;
    private MenuExtra menu;
    private ImageUtil imageUtil;

    public VueSwing_menuExtra(MenuExtra menu) {
        super(TexteVueMenuExtra.getInstance(), menu);
        this.menu = menu;
        this.imageUtil = new ImageUtil();
        this.textes = TexteVueMenuExtra.getInstance();
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
                if (evt.getPropertyName().equals("actualiseLangue")) {
                    actualiseLangue();
                } else {
                    switch (evt.getPropertyName()) {
                        case "afficherMenu":
                            afficherMenu();
                            break;
                        case "configuration":
                            configuration();
                            break;
                        case "choixLangue":
                            choixLangue();
                            break;
                    }
                    attendre();
                }
            }
        });
    }
    
    private void actualiseLangue() {
        super.actualiseLangue(TexteVueMenuExtra.getInstance());
        this.textes = TexteVueMenuExtra.getInstance();
   }
    
    private void afficherMenu() {
        JPanel panel2 = new JPanel ();
        BoutonImage boutonLigne = new BoutonImage(this.textes.ligne, this.imageUtil.getImageEnLigne());
        boutonLigne.addActionListener(new boutonChoix(1));
        BoutonImage boutonCombat = new BoutonImage(this.textes.duel, this.imageUtil.getImageDuel());
        boutonCombat.addActionListener(new boutonChoix(2));
        BoutonImage boutonConfiguration = new BoutonImage(this.textes.configuration, this.imageUtil.getImageConfiguration());
        boutonConfiguration.addActionListener(new boutonChoix(2));
        BoutonImage boutonRetour = new BoutonImage(this.textes.retour, this.imageUtil.getImageRetour());
        boutonRetour.addActionListener(new boutonChoix(0));
        panel2.add(boutonLigne);
        panel2.add(boutonCombat);
        panel2.add(boutonConfiguration);
        panel2.add(boutonRetour);
        Ecran.panel.ajouterCentre(panel2);
        Ecran.panel.viderNord();
        Ecran.panel.viderSud();
    }
    
    private void configuration() {
        JPanel panel2 = new JPanel ();
        JButton boutonChoixLangue = new JButton(this.textes.choixLangue);
        boutonChoixLangue.addActionListener(new boutonChoix(1));
        JButton boutonRetour=new JButton(this.textes.retour);
        boutonRetour.addActionListener(new boutonChoix(0));
        panel2.add(boutonChoixLangue);
        panel2.add(boutonRetour);
        Ecran.panel.ajouterNord(panel2);
        Ecran.panel.viderCentre();
    }

    private void choixLangue() {
        JPanel panel2 = new JPanel ();
        ListKeyAction actions = new ListKeyAction();
        actions.addKeyAction(CodeBouton.GAUCHE, ActionPerso.DECREMENTE);
        actions.addKeyAction(CodeBouton.DROITE, ActionPerso.INCREMENTE);
        actions.addKeyAction(CodeBouton.ACTION, ActionPerso.ACTION);
        ListComponentNumber componentNumber = new ListComponentNumber();
        for (int i = 0 ; i < Langue.values().length ; i++) {
            BoutonImage boutonDrapeau = new BoutonImage(this.imageUtil.getDrapeau(Langue.values()[i]));
            boutonDrapeau.addActionListener(new boutonChoix((i+1)));
            componentNumber.addKeyAction(boutonDrapeau, (i+1));
            panel2.add(boutonDrapeau);
        }
        Ecran.panel.ajouterCentre(panel2);
        Ecran.fenetreDuJeu.addKeyBoardManager(new KeyDispatcher(new ActionPerso(this.menu, this, actions, componentNumber)));
        Ecran.panel.viderNord();
    }
}
