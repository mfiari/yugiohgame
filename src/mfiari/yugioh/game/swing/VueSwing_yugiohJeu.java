/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import mfiari.lib.game.clavier.ActionPerso;
import mfiari.lib.game.clavier.KeyDispatcher;
import mfiari.lib.game.clavier.ListKeyAction;
import mfiari.lib.game.objet.ObjetEndroit;
import mfiari.lib.game.personnage.Gens;
import mfiari.lib.game.position.Position;
import mfiari.lib.game.swing.Ecran;
import mfiari.lib.game.swing.PanelCentre;
import mfiari.lib.game.swing.PanelCentreChargement;
import mfiari.lib.game.swing.PanelDeTexteSuivant;
import mfiari.lib.game.swing.PanelImage;
import mfiari.lib.game.swing.ViewComponent;
import mfiari.lib.game.swing.VueSwing;
import mfiari.lib.game.ville.Endroit;
import mfiari.yugioh.game.YugiohJeu;
import mfiari.yugioh.game.media.image.personnage.ImagePersonnage;
import mfiari.yugioh.game.texte.TexteVueJeu;

/**
 *
 * @author mike
 */
public class VueSwing_yugiohJeu extends VueSwing {
    
    private YugiohJeu jeu;
    private TexteVueJeu texte;

    public VueSwing_yugiohJeu(YugiohJeu jeu) {
        super(TexteVueJeu.getInstance(), jeu);
        this.jeu = jeu;
        this.texte = TexteVueJeu.getInstance();
        Ecran.panel.redimenssionnerCentre(1200, 550);
        Ecran.panel.redimenssionnerSud(1200, 100);
        Ecran.panel.afficherCentre();
        Ecran.panel.afficherSud();
        Ecran.panel.cacherEst();
        Ecran.panel.cacherOuest();
        Ecran.panel.cacherNord();
        System.out.println(Ecran.fenetreDuJeu);
        //Ecran.fenetreDuJeu.addKeyListener(new ActionPerso(this.jeu, this));
        ListKeyAction actions = new ListKeyAction();
        actions.addKeyAction(38, 1);
        actions.addKeyAction(37, 4);
        actions.addKeyAction(39, 3);
        actions.addKeyAction(40, 2);
        Ecran.fenetreDuJeu.addKeyBoardManager(new KeyDispatcher(new ActionPerso(this.jeu, this, actions)));
        //Ecran.fenetreDuJeu.addKeyListener(new ActionPerso());
        //panel.addKeyListener(new ActionPerso(this.jeu, this));
        this.jeu.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("afficheEndroit")) {
                    afficheEndroit();
                } else if (evt.getPropertyName().equals("deplacerPerso")) {
                    deplacerPerso((Position)evt.getOldValue(), (Position)evt.getNewValue());
                } else {
                    if (evt.getPropertyName().equals("perdu")) {
                        Perdu();
                    } else if (evt.getPropertyName().equals("parole")) {
                        parole();
                    } else if (evt.getPropertyName().equals("affichage")) {
                        affichage();
                    } else if (evt.getPropertyName().equals("choix")) {
                        choix();
                    } else if (evt.getPropertyName().equals("actionJeu")) {
                        actionJeu();
                    } else if (evt.getPropertyName().equals("afficheEndroitInnaccessible")) {
                        afficheEndroitInnaccessible();
                    }
                    attendre();
                }
            }
        });
    }
    
    public void afficheEndroitInnaccessible () {
        PanelDeTexteSuivant panelTexteSuivant = new PanelDeTexteSuivant(this);
        panelTexteSuivant.ajouterTexte(this.jeu.getPerso().getNom() + " : je ne peut pas aller ici");
        panelTexteSuivant.lancerAffichage();
        Ecran.panel.ajouterSud(panelTexteSuivant);
    }

    public void actionJeu() {
        JPanel panel2 = new JPanel (new GridLayout(2, 3));
        JButton boutonHaut = new JButton(this.texte.haut);
        boutonHaut.addActionListener(new boutonChoix(1));
        JButton boutonBas = new JButton(this.texte.bas);
        boutonBas.addActionListener(new boutonChoix(2));
        JButton boutonDroite = new JButton(this.texte.droite);
        boutonDroite.addActionListener(new boutonChoix(3));
        JButton boutonGauche = new JButton(this.texte.gauche);
        boutonGauche.addActionListener(new boutonChoix(4));
        JButton boutonAction = new JButton(this.texte.action);
        boutonAction.addActionListener(new boutonChoix(5));
        JButton boutonMenu = new JButton(this.texte.menu);
        boutonMenu.addActionListener(new boutonChoix(6));
        panel2.add(boutonHaut);
        panel2.add(boutonBas);
        panel2.add(boutonDroite);
        panel2.add(boutonGauche);
        panel2.add(boutonAction);
        panel2.add(boutonMenu);
        Ecran.panel.ajouterSud(panel2);
        //Ecran.fenetreDuJeu.addKeyListener(new ActionPerso(this.jeu, this));
        //Ecran.panel.addKeyListener(new ActionPerso(this.jeu, this));
    }

    public void Perdu() {
        /*
         * message lorsque l'on a perdu la partie (game over)
         */
        PanelDeTexteSuivant panelTexteSuivant = new PanelDeTexteSuivant(this);
        panelTexteSuivant.ajouterTexte(this.jeu.getPerso().getNom() + " n'a plus de pokemon en forme");
        panelTexteSuivant.ajouterTexte(this.jeu.getPerso().getNom() + " est hors jeu");
        panelTexteSuivant.lancerAffichage();
        Ecran.panel.ajouterSud(panelTexteSuivant);
    }

    public void parole() {
        /*
         * affichage lorsque qu'un personnage parle
         */
        PanelDeTexteSuivant panelTexteSuivant = new PanelDeTexteSuivant(this);
        panelTexteSuivant.ajouterTexte(this.jeu.getParole());
        panelTexteSuivant.lancerAffichage();
        Ecran.panel.ajouterSud(panelTexteSuivant);
    }

    public void affichage() {
        /*
         * pour faire des affichages divers
         */
        PanelDeTexteSuivant panelTexteSuivant = new PanelDeTexteSuivant(this);
        panelTexteSuivant.ajouterTexte(this.jeu.getAffichage());
        panelTexteSuivant.lancerAffichage();
        Ecran.panel.ajouterSud(panelTexteSuivant);
    }

    public void choix() {
        System.out.println(this.jeu.getAffichage());
    }

    public void afficheEndroit() {
        Endroit e = (Endroit)this.jeu.getEndroit();
        Ecran.panel.changerCentre(e.getLargeur(), e.getLongueur(), 1250, 550);
        PanelCentre panelCentre = Ecran.panel.getPanelCentre();
        Ecran.panel.changerCentre(new PanelCentreChargement(1250, 550), e.getLargeur(), e.getLongueur(), 1250, 550);
        int width = (panelCentre.getWidth() / e.getLongueur());
        int height = (panelCentre.getHeight() / e.getLargeur());
        for (int i = 0; i < e.getLargeur(); i++) {
            for (int j = 0; j < e.getLongueur(); j++) {
                Position p = new Position(i, j);
                if (this.jeu.getPerso().getPosition().equals(i, j, 0)) {
                    ImagePersonnage imagePersonnage = new ImagePersonnage();
                    if (imagePersonnage.aImagePerso(this.jeu.getPerso().getNom(), ImagePersonnage.dehors, this.jeu.getPerso().getPosition().getOrientation())) {
                        PanelImage image = new PanelImage(imagePersonnage.getImagePersoHeigth(this.jeu.getPerso().getNom(), height, ImagePersonnage.dehors, this.jeu.getPerso().getPosition().getOrientation()));
                        panelCentre.ajouterViewContent(new ViewComponent(image), i, j, 1);
                    } else {
                        JPanel panelFond = new JPanel ();
                        panelFond.setPreferredSize(new Dimension(width, height));
                        panelFond.setBackground(Color.YELLOW);
                        panelCentre.ajouterViewContent(new ViewComponent(panelFond), i, j, 1);
                    }
                } else if (e.aEndroit(p) != null) {
                    JPanel panelFond = new JPanel ();
                    panelFond.setPreferredSize(new Dimension(width, height));
                    panelFond.setBackground(Color.RED);
                    panelCentre.ajouterViewContent(new ViewComponent(panelFond), i, j, 1);
                } else if (e.aGens(p) != null) {
                    Gens gens = e.aGens(p);
                    ImagePersonnage imagePersonnage = new ImagePersonnage();
                    if (imagePersonnage.aImagePerso(gens.getNom(), ImagePersonnage.dehors, gens.getPosition().getOrientation())) {
                        PanelImage image = new PanelImage(imagePersonnage.getImagePersoHeigth(gens.getNom(), height, ImagePersonnage.dehors, gens.getPosition().getOrientation()));
                        panelCentre.ajouterViewContent(new ViewComponent(image), i, j, 1);
                    } else {
                        JPanel panelFond = new JPanel ();
                        panelFond.setPreferredSize(new Dimension(width, height));
                        panelFond.setBackground(Color.YELLOW);
                        panelCentre.ajouterViewContent(new ViewComponent(panelFond), i, j, 1);
                    }
                    
                } else if (e.aObjetEndroit(p) != null) {
                    JPanel panelFond = new JPanel ();
                    panelFond.setPreferredSize(new Dimension(width, height));
                    panelFond.setBackground(Color.BLUE);
                    panelCentre.ajouterViewContent(new ViewComponent(panelFond), i, j, 1);
                } else {
                    panelCentre.ajouterViewContent(null, i, j);
                }
                if (e.aObjetEndroit(p) != null) {
                    JPanel panelFond = new JPanel ();
                    panelFond.setPreferredSize(new Dimension(width, height));
                    panelFond.setBackground(Color.BLUE);
                    panelCentre.ajouterViewContent(new ViewComponent(panelFond), i, j, 0);
                }
                JPanel panelFond = new JPanel ();
                panelFond.setPreferredSize(new Dimension(width, height));
                panelFond.setBackground(Color.GREEN);
                panelCentre.ajouterViewBackground(new ViewComponent(panelFond), i, j);
            }
        }
        //Ecran.fenetreDuJeu.addKeyListener(new ActionPerso(this.jeu, this));
        Ecran.panel.changerCentre(panelCentre, e.getLargeur(), e.getLongueur(), 1250, 550);
    }
    
    public void deplacerPerso(Position oldPosition, Position newPosition) {
        if (this.jeu.getEndroit().equals(oldPosition.getEndroit())) {
            Endroit e = (Endroit)newPosition.getEndroit();
            int width = (Ecran.panel.getCentre().getWidth() / e.getLongueur());
            int height = (Ecran.panel.getCentre().getHeight() / e.getLargeur());
            Ecran.panel.getPanelCentre().ajouterContent(null, oldPosition.getPositionX(), oldPosition.getPositionY(), 1);
            ImagePersonnage imageDresseur = new ImagePersonnage();
            if (imageDresseur.aImagePerso(this.jeu.getPerso().getNom())) {
                PanelImage image = new PanelImage(imageDresseur.getImagePersoHeigth(this.jeu.getPerso().getNom(), height));
                //image.setBackground(ColorEndroit.getColorByTerrain(e.getTerrain()));
                Ecran.panel.getPanelCentre().ajouterContent(image, newPosition.getPositionX(), newPosition.getPositionY(), 1);
            } else {
                JPanel panelFond = new JPanel ();
                panelFond.setPreferredSize(new Dimension(width, height));
                panelFond.setBackground(Color.YELLOW);
                Ecran.panel.getPanelCentre().ajouterContent(panelFond, newPosition.getPositionX(), newPosition.getPositionY(), 1);
            }
        } else {
            this.afficheEndroit();
        }
    }
}
