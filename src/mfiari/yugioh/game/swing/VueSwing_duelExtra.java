/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.swing;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import mfiari.lib.game.swing.BoutonImage;
import mfiari.lib.game.swing.Ecran;
import mfiari.lib.game.swing.PanelDeTexteAffichage;
import mfiari.lib.game.swing.PanelImage;
import mfiari.lib.game.swing.VueSwing;
import mfiari.yugioh.game.extra.DuelExtra;
import mfiari.yugioh.game.liste.ListeDeDueliste;
import mfiari.yugioh.game.media.image.personnage.ImagePersonnage;
import mfiari.yugioh.game.perso.Dueliste;
import mfiari.yugioh.game.texte.TexteVueDuelExtra;

/**
 *
 * @author mike
 */
public class VueSwing_duelExtra extends VueSwing {
    
    private DuelExtra duel;
    private TexteVueDuelExtra textes;
    private int widthImage;
    private int heightImage;
    
    public VueSwing_duelExtra (DuelExtra duel) {
        super(TexteVueDuelExtra.getInstance(), duel);
        this.duel = duel;
        this.textes = TexteVueDuelExtra.getInstance();
        Ecran.panel.redimenssionnerNord(1200, 100);
        Ecran.panel.redimenssionnerCentre(1200, 450);
        Ecran.panel.redimenssionnerSud(1200, 100);
        Ecran.panel.afficherNord();
        Ecran.panel.afficherCentre();
        Ecran.panel.afficherSud();
        Ecran.panel.cacherEst();
        Ecran.panel.cacherOuest();
        this.widthImage = 1200/15;
        this.heightImage = 450 / 7;
        this.duel.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                switch (evt.getPropertyName()) {
                    case "choixPerso":
                        choixPerso();
                        break;
                    case "confirmerChoixPerso":
                        confirmerChoixPerso();
                        break;
                    case "choixCarte":
                        confirmerChoixPerso();
                        break;
                }
                attendre();
            }
        });
    }
    
    public void choixPerso() {
        JPanel panel2 = new JPanel (new GridLayout(5, 6));
        ListeDeDueliste personnages = this.duel.getParticipant();
        ImagePersonnage imagePerso = new ImagePersonnage();
        String nom;
        for (int i = 0 ; i < personnages.size() ; i++) {
            nom = personnages.getPersonnage(i).getNom();
            BoutonImage bouton;
            if (imagePerso.aImagePerso(nom)) {
                bouton = new BoutonImage(nom, imagePerso.getImagePersoWidthOrHeigth(nom, this.widthImage, this.heightImage));
            } else {
                bouton = new BoutonImage(nom);
            }
            bouton.addActionListener(new boutonChoixDresseur(i+1));
            panel2.add(bouton);
        }
        JButton nouveauDueliste = new JButton("Nouveau dueliste");
        nouveauDueliste.addActionListener(new boutonChoixDresseur(personnages.size()+1));
        JButton terminer = new JButton(this.textes.terminer);
        terminer.addActionListener(new boutonChoixDresseur(-1));
        JButton retour = new JButton(this.textes.retour);
        retour.addActionListener(new boutonRetour());
        panel2.add(nouveauDueliste);
        panel2.add(terminer);
        panel2.add(retour);
        Ecran.panel.ajouterCentre(panel2);
        Ecran.panel.viderSud();
    }
    
    public void confirmerChoixPerso () {
        Dueliste perso = this.duel.getPersonnageEnCour();
        JPanel panelPerso = new JPanel (new GridLayout(1, 2));
        ImagePersonnage imageDresseur = new ImagePersonnage();
        if (imageDresseur.aImagePerso(perso.getNom())) {
            panelPerso.add(new PanelImage(imageDresseur.getImagePersoHeigth(perso.getNom(), 400)));
        } else {
            panelPerso.add(new PanelDeTexteAffichage(perso.getNom()));
        }
        JPanel panelBouton = new JPanel();
        JButton boutonTerminer = new JButton(this.textes.confirmer);
        boutonTerminer.addActionListener(new boutonConfirmer());
        JButton boutonRetour = new JButton(this.textes.retour);
        boutonRetour.addActionListener(new boutonAnnuler());
        panelBouton.add(boutonTerminer);
        panelBouton.add(boutonRetour);
        Ecran.panel.ajouterCentre(panelPerso);
        Ecran.panel.ajouterSud(panelBouton);
    }
    
    private class boutonChoixDresseur implements ActionListener {
        int indice;
        public boutonChoixDresseur (int indice) {
            this.indice = indice;
        }
        @Override
        public void actionPerformed (ActionEvent event) {
            duel.setChoix(this.indice);
            reprendre();
        }
    }
    
    private class boutonRetour implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent event) {
            duel.setChoix(0);
            reprendre();
        }
    }
    
    private class boutonConfirmer implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent event) {
            duel.setValider(true);
            reprendre();
        }
    }
    
    private class boutonAnnuler implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent event) {
            duel.setValider(false);
            reprendre();
        }
    }
}