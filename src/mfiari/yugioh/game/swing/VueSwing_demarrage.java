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
import mfiari.lib.game.swing.PanelImage;
import mfiari.lib.game.swing.VueSwing;
import mfiari.lib.game.texte.Langue;
import mfiari.yugioh.game.demarrage.Demarrage;
import mfiari.yugioh.game.media.image.util.ImageUtil;
import mfiari.yugioh.game.texte.TexteVueDemarrage;

/**
 *
 * @author mike
 */
public class VueSwing_demarrage extends VueSwing {
    
    private Demarrage demarrage;
    private TexteVueDemarrage textes;
    private ImageUtil imageUtil;
    
    public VueSwing_demarrage (Demarrage demarage) {
        super(TexteVueDemarrage.getInstance(), demarage);
        this.demarrage = demarage;
        this.imageUtil = new ImageUtil();
        this.textes = TexteVueDemarrage.getInstance();
        Ecran.panel.redimenssionnerCentre(1200, 400);
        Ecran.panel.redimenssionnerNord(1200, 100);
        Ecran.panel.redimenssionnerSud(1200, 100);
        Ecran.panel.afficherNord();
        Ecran.panel.afficherCentre();
        Ecran.panel.afficherSud();
        Ecran.panel.cacherEst();
        Ecran.panel.cacherOuest();
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
                attendre();
            }
        });
    }

    public void choixLangue() {
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
        Ecran.fenetreDuJeu.addKeyBoardManager(new KeyDispatcher(new ActionPerso(this.demarrage, this, actions, componentNumber)));
    }

    public void debutJeu() {
        this.textes = TexteVueDemarrage.getInstance();
        JPanel panelBouton = new JPanel ();
        JButton boutonNouvellePartie = new JButton(this.textes.nouvellePartie);
        boutonNouvellePartie.addActionListener(new boutonChoix(1));
        JButton boutonContinuer=new JButton(this.textes.continuer);
        boutonContinuer.addActionListener(new boutonChoix(2));
        JButton boutonExtra=new JButton(this.textes.extra);
        boutonExtra.addActionListener(new boutonChoix(3));
        panelBouton.add(boutonNouvellePartie);
        panelBouton.add(boutonContinuer);
        panelBouton.add(boutonExtra);
        Ecran.panel.ajouterCentre(new PanelImage(this.imageUtil.getLogo()));
        Ecran.panel.ajouterSud(panelBouton);
        ListKeyAction actions = new ListKeyAction();
        actions.addKeyAction(CodeBouton.GAUCHE, ActionPerso.DECREMENTE);
        actions.addKeyAction(CodeBouton.DROITE, ActionPerso.INCREMENTE);
        actions.addKeyAction(CodeBouton.ACTION, ActionPerso.ACTION);
        ListComponentNumber componentNumber = new ListComponentNumber();
        componentNumber.addKeyAction(boutonNouvellePartie, 1);
        componentNumber.addKeyAction(boutonContinuer, 2);
        componentNumber.addKeyAction(boutonExtra, 3);
        Ecran.fenetreDuJeu.addKeyBoardManager(new KeyDispatcher(new ActionPerso(this.demarrage, this, actions, componentNumber)));
    }
    
}
