/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import mfiari.lib.game.image.TraitementImage;
import mfiari.lib.game.swing.Ecran;
import mfiari.lib.game.swing.PanelDeTexteAffichage;
import mfiari.lib.game.swing.PanelDeTexteSuivant;
import mfiari.lib.game.swing.PanelImage;
import mfiari.lib.game.swing.PannelDeTexte;
import mfiari.lib.game.swing.VueSwing;
import mfiari.yugioh.game.combat.AbstractCombat;
import mfiari.yugioh.game.liste.Deck;
import mfiari.yugioh.game.media.image.carte.ImageCarte;
import mfiari.yugioh.game.media.image.personnage.ImagePersonnage;
import mfiari.yugioh.game.carte.Carte;
import mfiari.yugioh.game.carte.CarteMagie;
import mfiari.yugioh.game.carte.CarteMonstre;
import mfiari.yugioh.game.carte.CartePiege;
import mfiari.yugioh.game.perso.Dueliste;
import mfiari.yugioh.game.carte.Mode;
import mfiari.yugioh.game.carte.Visibilite;
import mfiari.yugioh.game.combat.Regles;
import mfiari.yugioh.game.texte.TexteVueCombat;
import mfiari.yugioh.game.ville.Terrain;
import mfiari.yugioh.game.ville.Terrains;

/**
 *
 * @author mike
 */
public class VueSwing_combat extends VueSwing {
    
    private AbstractCombat combat;
    private TexteVueCombat textes;
    private Box vuePvPerso;
    private Box vuePvAdv;
    
    public VueSwing_combat (AbstractCombat combat) {
        super(TexteVueCombat.getInstance(), combat);
        this.combat = combat;
        this.textes = TexteVueCombat.getInstance();
        this.vuePvPerso = Box.createVerticalBox();
        this.vuePvPerso.setPreferredSize(new Dimension(135, 80));
        this.vuePvAdv = Box.createVerticalBox();
        this.vuePvAdv.setPreferredSize(new Dimension(135, 80));
        Ecran.panel.redimenssionnerCentre(900, 550);
        Ecran.panel.redimenssionnerSud(1200, 100);
        Ecran.panel.afficherCentre();
        Ecran.panel.afficherSud();
        Ecran.panel.cacherNord();
        Ecran.panel.cacherEst();
        Ecran.panel.cacherOuest();
        this.combat.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                switch (evt.getPropertyName()) {
                    case "afficherTerrainCombat":
                        afficherTerrainCombat();
                        break;
                    case "debutCombat":
                        debutCombat();
                        break;
                    case "afficherPvPerso":
                        afficherPvPerso();
                        break;
                    case "afficherPvAdv":
                        afficherPvAdv();
                        break;
                    case "attenteAdv":
                        attenteAdv();
                        break;
                    default:
                        switch (evt.getPropertyName()) {
                            case "afficherCarteJoueur":
                                afficherCarteJoueur();
                                break;
                            case "afficherMenuJoueur":
                                afficherMenuJoueur();
                                break;
                            case "affichage":
                                setAffichageCombat();
                                break;
                            case "choixMode":
                                choixMode();
                                break;
                            case "choixVisibilite":
                                choixVisibilite();
                                break;
                            case "afficherMenuCarteTerrain":
                                afficherMenuCarteTerrain();
                                break;
                            case "choixCarteAdv":
                                break;
                            case "choixActivationEffet":
                                choixActivationEffet((Carte) evt.getOldValue());
                                break;
                            case "afficherRegles":
                                afficherRegles();
                                break;
                        }
                        attendre();
                        break;
                }
            }
        });
    }
    
    private void afficherRegles () {
        Regles[] regles = this.combat.getRegles();
        JPanel panel2 = new JPanel (new GridLayout(regles.length, 1));
        for (int i= 0 ; i < regles.length ; i++) {
            panel2.add(new PanelDeTexteAffichage(regles[i].name()));
        }
        Ecran.panel.ajouterCentre(panel2);
        Ecran.panel.ajouterSud(VueSwing.boutonSuivant);
    }
    
    private void debutCombat () {
        ImagePersonnage imagePerso = new ImagePersonnage();
        Dueliste perso1 = this.combat.getPerso1();
        Dueliste perso2 = this.combat.getPerso2();
        JPanel panel2 = new JPanel (new GridLayout(2, 1));
        if (imagePerso.aImagePerso(perso2.getNom(), ImagePersonnage.combat)) {
            PanelImage image = new PanelImage(imagePerso.getImagePersoHeigth(perso2.getNom(), 100, ImagePersonnage.combat));
            panel2.add(image);
        } else {
            panel2.add(new PanelDeTexteAffichage(perso2.getNom()));
        }
        if (imagePerso.aImagePerso(perso1.getNom(), ImagePersonnage.combat)) {
            PanelImage image = new PanelImage(imagePerso.getImagePersoHeigth(perso1.getNom(), 100, ImagePersonnage.combat));
            panel2.add(image);
        } else {
            panel2.add(new PanelDeTexteAffichage(perso1.getNom()));
        }
        Ecran.panel.ajouterCentre(panel2);
    }
    
    private Color getColorByTerrainType () {
        Terrain terrain = this.combat.getTerrain();
        if (terrain.equals(Terrains.terrain_desert)) {
            return Color.GRAY;
        } else if (terrain.equals(Terrains.terrain_foret)) {
            return Color.GREEN;
        } else if (terrain.equals(Terrains.terrain_prairie)) {
            return Color.GREEN;
        } else if (terrain.equals(Terrains.terrain_mer)) {
            return Color.BLUE;
        } else if (terrain.equals(Terrains.terrain_montagne)) {
            return Color.LIGHT_GRAY;
        } else if (terrain.equals(Terrains.terrain_tenebres)) {
            return Color.MAGENTA;
        }
        return Color.WHITE;
    }

    private void afficherTerrainCombat() {
        ImagePersonnage imagePerso = new ImagePersonnage();
        ImageCarte imageCarte = new ImageCarte();
        Dueliste perso1 = this.combat.getPerso1();
        Dueliste perso2 = this.combat.getPerso2();
        Color affichageTypeTerrain = this.getColorByTerrainType();
        JPanel panel2 = new JPanel (new GridLayout(6, 5));
        int width = 135;
        int height = 80;
        for (int i=0 ; i<6 ; i++) {
            for (int j=0 ; j<5 ; j++) {
                switch (i) {
                    case (0):
                        if (j==2 && perso2 != null) {
                            JButton boutonAdv = new JButton();
                            boutonAdv.addActionListener(new boutonChoixCarteTerrainAdv(0));
                            if (imagePerso.aImagePerso(perso2.getNom(), ImagePersonnage.combat)) {
                               PanelImage image = new PanelImage(imagePerso.getImagePersoHeigth(perso2.getNom(), height, ImagePersonnage.combat));
                               boutonAdv.add(image);
                            } else {
                                boutonAdv.add(new PanelDeTexteAffichage(perso2.getNom()));
                            }
                            panel2.add(boutonAdv);
                        } else if (j==3 && perso2 != null) {
                            this.afficherPvAdv();
                            panel2.add(this.vuePvAdv);
                        } else {
                            JPanel panelFond = new JPanel ();
                            panelFond.setPreferredSize(new Dimension(width, height));
                            panelFond.setBackground(affichageTypeTerrain);
                            panel2.add(panelFond);
                        }
                        break;
                    case (1):
                        if (this.combat.getPlateauDeCartePerso2().isCarteMagie(j)) {
                            Carte carteMagie = this.combat.getPlateauDeCartePerso2().getCarteMagie(j);
                            JButton boutonCarte = new JButton();
                            boutonCarte.addActionListener(new boutonChoixCarteTerrainAdv(j+6));
                            String code;
                            if (this.combat.getPlateauDeCartePerso2().getVisibiliteCarteMagie(j).equals(Visibilite.cache)) {
                                code = "0";
                            } else {
                                code = carteMagie.getCode();
                            }
                            if (imageCarte.aImageCarte(code)) {
                                PanelImage image = new PanelImage(imageCarte.getImageCarteHeigth(code, height));
                                boutonCarte.add(image);
                            } else {
                                boutonCarte.add(new PanelDeTexteAffichage(code));
                            }
                            panel2.add(boutonCarte);
                        } else if (this.combat.getPlateauDeCartePerso2().isCartePiege(j)) {
                            Carte carteMagie = this.combat.getPlateauDeCartePerso2().getCartePiege(j);
                            JButton boutonCarte = new JButton();
                            boutonCarte.addActionListener(new boutonChoixCarteTerrainAdv(j+6));
                            String code;
                            if (this.combat.getPlateauDeCartePerso2().getVisibiliteCarteMagie(j).equals(Visibilite.cache)) {
                                code = "0";
                            } else {
                                code = carteMagie.getCode();
                            }
                            if (imageCarte.aImageCarte(code)) {
                                PanelImage image = new PanelImage(imageCarte.getImageCarteHeigth(code, height));
                                boutonCarte.add(image);
                            } else {
                                boutonCarte.add(new PanelDeTexteAffichage(code));
                            }
                            panel2.add(boutonCarte);
                        } else {
                            JPanel panelFond = new JPanel ();
                            panelFond.setPreferredSize(new Dimension(width, height));
                            panelFond.setBackground(affichageTypeTerrain);
                            panel2.add(panelFond);
                        }
                        break;
                    case (2):
                        if (this.combat.getPlateauDeCartePerso2().getCarteMonsre(j) != null) {
                            Box boxMonstre =Box.createVerticalBox();
                            CarteMonstre carteMonstre = this.combat.getPlateauDeCartePerso2().getCarteMonsre(j);
                            JButton boutonCarte = new JButton();
                            boutonCarte.addActionListener(new boutonChoixCarteTerrainAdv(j+1));
                            String code;
                            if (this.combat.getPlateauDeCartePerso2().getVisibiliteCarteMonsre(j).equals(Visibilite.cache)) {
                                code = "0";
                            } else {
                                code = carteMonstre.getCode();
                            }
                            if (imageCarte.aImageCarte(code)) {
                                PanelImage image;
                                if (this.combat.getPlateauDeCartePerso2().getModeCarteMonsre(j).equals(Mode.defense)) {
                                    image = new PanelImage(TraitementImage.rotateImage(imageCarte.getImageCarteHeigth(code, height-30), 270));
                                } else {
                                    image = new PanelImage(TraitementImage.rotateImage(imageCarte.getImageCarteHeigth(code, height-30), 180));
                                }
                                boutonCarte.add(image);
                            } else {
                                boutonCarte.add(new PanelDeTexteAffichage(code));
                            }
                            boxMonstre.add(new PannelDeTexte(carteMonstre.getForce()+"/"+carteMonstre.getDef()));
                            boxMonstre.add(boutonCarte);
                            panel2.add(boxMonstre);
                        } else {
                            JPanel panelFond = new JPanel ();
                            panelFond.setPreferredSize(new Dimension(width, height));
                            panelFond.setBackground(affichageTypeTerrain);
                            panel2.add(panelFond);
                        }
                        break;
                    case (3):
                        if (this.combat.getPlateauDeCartePerso1().getCarteMonsre(j) != null) {
                            Box boxMonstre = Box.createVerticalBox();
                            CarteMonstre carteMonstre = this.combat.getPlateauDeCartePerso1().getCarteMonsre(j);
                            JButton boutonCarte = new JButton();
                            boutonCarte.addActionListener(new boutonChoixCarteTerrain(j+1));
                            String code;
                            if (this.combat.getPlateauDeCartePerso1().getVisibiliteCarteMonsre(j).equals(Visibilite.cache)) {
                                code = "0";
                            } else {
                                code = carteMonstre.getCode();
                            }
                            if (imageCarte.aImageCarte(code)) {
                                PanelImage image;
                                if (this.combat.getPlateauDeCartePerso1().getModeCarteMonsre(j).equals(Mode.defense)) {
                                    image = new PanelImage(TraitementImage.rotateImage(imageCarte.getImageCarteHeigth(code, height-30), 90));
                                } else {
                                    image = new PanelImage(imageCarte.getImageCarteHeigth(code, height-30));
                                }
                                boutonCarte.add(image);
                            } else {
                                boutonCarte.add(new PanelDeTexteAffichage(code));
                            }
                            boxMonstre.add(boutonCarte);
                            boxMonstre.add(new PannelDeTexte(carteMonstre.getForce()+"/"+carteMonstre.getDef()));
                            panel2.add(boxMonstre);
                        } else {
                            JPanel panelFond = new JPanel ();
                            panelFond.setPreferredSize(new Dimension(width, height));
                            panelFond.setBackground(affichageTypeTerrain);
                            panel2.add(panelFond);
                        }
                        break;
                    case (4):
                        if (this.combat.getPlateauDeCartePerso1().isCarteMagie(j)) {
                            Carte carteMagie = this.combat.getPlateauDeCartePerso1().getCarteMagie(j);
                            JButton boutonCarte = new JButton();
                            boutonCarte.addActionListener(new boutonChoixCarteTerrain(j+6));
                            String code;
                            if (this.combat.getPlateauDeCartePerso1().getVisibiliteCarteMagie(j).equals(Visibilite.cache)) {
                                code = "0";
                            } else {
                                code = carteMagie.getCode();
                            }
                            if (imageCarte.aImageCarte(code)) {
                                PanelImage image = new PanelImage(imageCarte.getImageCarteHeigth(code, height));
                                boutonCarte.add(image);
                            } else {
                                boutonCarte.add(new PanelDeTexteAffichage(code));
                            }
                            panel2.add(boutonCarte);
                        } else if (this.combat.getPlateauDeCartePerso1().isCartePiege(j)) {
                            Carte carteMagie = this.combat.getPlateauDeCartePerso1().getCartePiege(j);
                            JButton boutonCarte = new JButton();
                            boutonCarte.addActionListener(new boutonChoixCarteTerrain(j+6));
                            String code;
                            if (this.combat.getPlateauDeCartePerso1().getVisibiliteCarteMagie(j).equals(Visibilite.cache)) {
                                code = "0";
                            } else {
                                code = carteMagie.getCode();
                            }
                            if (imageCarte.aImageCarte(code)) {
                                PanelImage image = new PanelImage(imageCarte.getImageCarteHeigth(code, height));
                                boutonCarte.add(image);
                            } else {
                                boutonCarte.add(new PanelDeTexteAffichage(code));
                            }
                            panel2.add(boutonCarte);
                        } else {
                            JPanel panelFond = new JPanel ();
                            panelFond.setPreferredSize(new Dimension(width, height));
                            panelFond.setBackground(affichageTypeTerrain);
                            panel2.add(panelFond);
                        }
                        break;
                    case (5):
                        if (j==1 && perso1 != null) {
                            if (imagePerso.aImagePerso(perso1.getNom(), ImagePersonnage.combat)) {
                               PanelImage image = new PanelImage(imagePerso.getImagePersoHeigth(perso1.getNom(), height, ImagePersonnage.combat));
                                panel2.add(image);
                            } else {
                                panel2.add(new PanelDeTexteAffichage(perso1.getNom()));
                            }
                        } else if (j==2 && perso1 != null) {
                            this.afficherPvPerso();
                            panel2.add(this.vuePvPerso);
                        } else {
                            JPanel panelFond = new JPanel ();
                            panelFond.setPreferredSize(new Dimension(width, height));
                            panelFond.setBackground(affichageTypeTerrain);
                            panel2.add(panelFond);
                        }
                        break;
                    default:
                        JPanel panelDefault = new JPanel ();
                        panelDefault.setPreferredSize(new Dimension(width, height));
                        panelDefault.setBackground(affichageTypeTerrain);
                        panel2.add(panelDefault);
                        break;
                }
            }
        }
        Ecran.panel.ajouterCentre(panel2);
    }
    
    private void afficherPvPerso () {
        Dueliste perso = this.combat.getPerso1();
        Box vuePv = Box.createVerticalBox();
        vuePv.setPreferredSize(new Dimension(200, 100));
        Box boxPkmn = Box.createHorizontalBox();
        Box boxPvNum = Box.createHorizontalBox();
        boxPkmn.add(new PanelDeTexteAffichage(perso.getNom()));
        boxPvNum.add(new PanelDeTexteAffichage(this.combat.getPvPerso1()));
        this.vuePvPerso.removeAll();
        this.vuePvPerso.add(boxPkmn);
        this.vuePvPerso.add(boxPvNum);
        this.vuePvPerso.repaint();
        this.vuePvPerso.validate();
    }
    
    private void afficherPvAdv () {
        Dueliste perso = this.combat.getPerso2();
        Box boxPkmn = Box.createHorizontalBox();
        Box boxPvNum = Box.createHorizontalBox();
        boxPkmn.add(new PanelDeTexteAffichage(perso.getNom()));
        boxPvNum.add(new PanelDeTexteAffichage(this.combat.getPvPerso2()));
        this.vuePvAdv.removeAll();
        this.vuePvAdv.add(boxPkmn);
        this.vuePvAdv.add(boxPvNum);
        this.vuePvAdv.repaint();
        this.vuePvAdv.validate();
    }
    
    private void afficherCarte () {
        Carte carte = null;
        Box vueCarte = Box.createVerticalBox();
        if (carte instanceof CarteMonstre) {
            CarteMonstre carteMonstre = (CarteMonstre) carte;
            Box nom = Box.createHorizontalBox();
            nom.add(new PanelDeTexteAffichage(carteMonstre.getNom(), 8, 350, 15));
            nom.add(new PanelDeTexteAffichage(carteMonstre.getType().name(), 8, 350, 15));
            vueCarte.add(nom);
            vueCarte.add(new PanelDeTexteAffichage(carteMonstre.getEtoiles(), 8, 350, 15));
            vueCarte.add(new PanelDeTexteAffichage("[" + carteMonstre.getCategorie().name() + "]", 8, 350, 15));
            vueCarte.add(new PanelDeTexteAffichage(carteMonstre.getDescription(), 8, 350, 15));
            Box att_def = Box.createHorizontalBox();
            att_def.add(new PanelDeTexteAffichage("ATK/"+carteMonstre.getForce(), 8, 350, 15));
            att_def.add(new PanelDeTexteAffichage("DEF/"+carteMonstre.getDef(), 8, 350, 15));
            vueCarte.add(att_def);
        }
    }

    private void afficherCarteJoueur() {
        Deck main = this.combat.getMainPerso1();
        JPanel panel2 = new JPanel (new GridLayout(1, main.nbCarte()));
        for (int i = 0 ; i < main.nbCarte() ; i++) {
            Box vueCarte = Box.createVerticalBox();
            if (main.getCarte(i) instanceof CarteMonstre) {
                vueCarte.setBackground(Color.ORANGE);
                CarteMonstre carte = (CarteMonstre) main.getCarte(i);
                String nomMonstre = carte.getNom();
                if (nomMonstre.length() > 20) {
                    nomMonstre = nomMonstre.substring(0, 20);
                }
                vueCarte.add(new PanelDeTexteAffichage(nomMonstre, 9));
                ImageCarte imageCarte = new ImageCarte();
                if (imageCarte.aImageCarte(carte.getCode())) {
                    PanelImage image = new PanelImage(imageCarte.getImageCarteHeigth(carte.getCode(), 50));
                    vueCarte.add(image);
                } else {
                    vueCarte.add(new PanelDeTexteAffichage(carte.getCode()));
                }
            } else if (main.getCarte(i) instanceof CarteMagie) {
                vueCarte.setBackground(Color.GREEN);
                CarteMagie carte = (CarteMagie) main.getCarte(i);
                String nomCarte = carte.getNom();
                if (nomCarte.length() > 20) {
                    nomCarte = nomCarte.substring(0, 20);
                }
                vueCarte.add(new PanelDeTexteAffichage(nomCarte, 9));
                ImageCarte imageCarte = new ImageCarte();
                if (imageCarte.aImageCarte(carte.getCode())) {
                    PanelImage image = new PanelImage(imageCarte.getImageCarteHeigth(carte.getCode(), 50));
                    vueCarte.add(image);
                } else {
                    vueCarte.add(new PanelDeTexteAffichage(carte.getCode()));
                }
            } else if (main.getCarte(i) instanceof CartePiege) {
                vueCarte.setBackground(Color.red);
                CartePiege carte = (CartePiege) main.getCarte(i);
                String nomCarte = carte.getNom();
                if (nomCarte.length() > 20) {
                    nomCarte = nomCarte.substring(0, 20);
                }
                vueCarte.add(new PanelDeTexteAffichage(nomCarte, 9));
                ImageCarte imageCarte = new ImageCarte();
                if (imageCarte.aImageCarte(carte.getCode())) {
                    PanelImage image = new PanelImage(imageCarte.getImageCarteHeigth(carte.getCode(), 50));
                    vueCarte.add(image);
                } else {
                    vueCarte.add(new PanelDeTexteAffichage(carte.getCode()));
                }
            }
            JButton boutonAttaquer = new JButton();
            boutonAttaquer.add(vueCarte);
            boutonAttaquer.addActionListener(new boutonChoix(i+1));
            panel2.add(boutonAttaquer);
        }
        JButton boutonFinDeTour = new JButton("Fin de tour");
        boutonFinDeTour.addActionListener(new boutonChoix(0));
        panel2.add(boutonFinDeTour);
        Ecran.panel.ajouterSud(panel2);
    }

    private void choixMode() {
        JPanel panel2 = new JPanel (new GridLayout(1, Mode.values().length));
        for (int i = 0 ; i < Mode.values().length ; i++) {
            JButton boutonMode = new JButton(Mode.values()[i].name());
            boutonMode.addActionListener(new boutonChoix(i+1));
            panel2.add(boutonMode);
        }
        JButton boutonRetour = new JButton("retour");
        boutonRetour.addActionListener(new boutonChoix(0));
        panel2.add(boutonRetour);
        Ecran.panel.ajouterSud(panel2);
    }

    private void choixVisibilite() {
        JPanel panel2 = new JPanel (new GridLayout(1, Visibilite.values().length));
        for (int i = 0 ; i < Visibilite.values().length ; i++) {
            JButton boutonMode = new JButton(Visibilite.values()[i].name());
            boutonMode.addActionListener(new boutonChoix(i+1));
            panel2.add(boutonMode);
        }
        JButton boutonRetour = new JButton("retour");
        boutonRetour.addActionListener(new boutonChoix(0));
        panel2.add(boutonRetour);
        Ecran.panel.ajouterSud(panel2);
    }

    private void afficherMenuJoueur() {
        JPanel panel2 = new JPanel (new GridLayout(2, 3));
        JButton boutonAttaquer = new JButton(this.textes.attaquer);
        boutonAttaquer.addActionListener(new VueSwing.boutonChoix(1));
        panel2.add(boutonAttaquer);
        Ecran.panel.ajouterSud(panel2);
    }

    private void afficherMenuCarteTerrain() {
        JPanel panel2 = new JPanel (new GridLayout(2, 3));
        if (this.combat.getChoixCarteTerrain() < 5) {
            JButton boutonAttaquer = new JButton(this.textes.attaquer);
            boutonAttaquer.addActionListener(new VueSwing.boutonChoix(1));
            JButton boutonMode;
            if (this.combat.getPlateauDeCartePerso1().getModeCarteMonsre(this.combat.getChoixCarteTerrain()-1).equals(Mode.attaque)) {
                boutonMode = new JButton("Mode défense");
                boutonMode.addActionListener(new VueSwing.boutonChoix(2));
            } else {
                boutonMode = new JButton("Mode attaque");
                boutonMode.addActionListener(new VueSwing.boutonChoix(2));
            }
            JButton boutonSacrifice = new JButton("Sacrifier");
            boutonSacrifice.addActionListener(new VueSwing.boutonChoix(3));
            panel2.add(boutonAttaquer);
            panel2.add(boutonMode);
            panel2.add(boutonSacrifice);
            if (this.combat.getPlateauDeCartePerso1().getVisibiliteCarteMonsre(this.combat.getChoixCarteTerrain()-1).equals(Visibilite.cache)) {
                JButton boutonVisibilite = new JButton("rendre visible");
                boutonVisibilite.addActionListener(new VueSwing.boutonChoix(4));
                panel2.add(boutonVisibilite);
            }
        } else {
            JButton boutonAfficher = new JButton("afficher carte");
            boutonAfficher.addActionListener(new VueSwing.boutonChoix(1));
            panel2.add(boutonAfficher);
            if (this.combat.getPlateauDeCartePerso1().getVisibiliteCarteMagie(this.combat.getChoixCarteTerrain()-6).equals(Visibilite.cache)) {
                JButton boutonActiver = new JButton("activer");
                boutonActiver.addActionListener(new VueSwing.boutonChoix(2));
                panel2.add(boutonActiver);
            }
        }
        Ecran.panel.ajouterSud(panel2);
    }

    private void setAffichageCombat() {
        JPanel panel2 = new JPanel ();
        PanelDeTexteSuivant panelDeTexteSuivant = new PanelDeTexteSuivant(this);
        panelDeTexteSuivant.ajouterTexte(this.combat.getTexte());
        panel2.add(panelDeTexteSuivant);
        panelDeTexteSuivant.lancerAffichage();
        Ecran.panel.ajouterSud(panel2);
    }

    private void choixActivationEffet(Carte carte) {
        JPanel panel2 = new JPanel ();
        panel2.add(new PanelDeTexteAffichage("Voulez-vous activer l'effet de " + carte.getNom()));
        JButton boutonOui = new JButton("Oui");
        boutonOui.addActionListener(new boutonConfirmation(true));
        panel2.add(boutonOui);
        JButton boutonNon = new JButton("Non");
        boutonNon.addActionListener(new boutonConfirmation(false));
        panel2.add(boutonNon);
        Ecran.panel.ajouterSud(panel2);
    }

    private void attenteAdv() {
        Ecran.panel.ajouterSud(new PanelDeTexteAffichage(this.textes.attenteAdv));
    }
    
    private class boutonChoixCarteTerrain implements ActionListener {
        private int choix;
        
        public boutonChoixCarteTerrain (int choix) {
            this.choix = choix;
        }
        
        @Override
        public void actionPerformed (ActionEvent event) {
            combat.setChoixCarteTerrain(this.choix);
            reprendre();
        }
    }
    
    private class boutonChoixCarteTerrainAdv implements ActionListener {
        private int choix;
        
        public boutonChoixCarteTerrainAdv (int choix) {
            this.choix = choix;
        }
        
        @Override
        public void actionPerformed (ActionEvent event) {
            combat.setChoixCarteTerrainAdv(this.choix);
            reprendre();
        }
    }
    
    private class boutonConfirmation implements ActionListener {
        private boolean choix;
        
        public boutonConfirmation (boolean choix) {
            this.choix = choix;
        }
        
        @Override
        public void actionPerformed (ActionEvent event) {
            combat.setConfirmation(this.choix);
            reprendre();
        }
    }
    
}