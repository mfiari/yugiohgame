/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.reseau;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mfiari.lib.game.reseau.EnvoiObjet;
import mfiari.lib.game.reseau.ReceveurObjet;
import mfiari.yugioh.game.combat.AbstractCombat;
import mfiari.yugioh.game.carte.Affectation;
import mfiari.yugioh.game.carte.Carte;
import mfiari.yugioh.game.carte.CarteMagie;
import mfiari.yugioh.game.carte.CarteMagieEquipement;
import mfiari.yugioh.game.carte.CarteMonstre;
import mfiari.yugioh.game.carte.Categorie;
import mfiari.yugioh.game.perso.Dueliste;
import mfiari.yugioh.game.carte.Mode;
import mfiari.yugioh.game.carte.Type;
import mfiari.yugioh.game.carte.Visibilite;
import mfiari.yugioh.game.evenement.EvenementCombat;

/**
 *
 * @author mike
 */
public abstract class CombatReseau extends AbstractCombat {
    
    protected ReceveurObjet receveur;
    protected EnvoiObjet envoi;
    
    public CombatReseau (Socket socket) {
        this.envoi = new EnvoiObjet(socket);
        this.receveur = new ReceveurObjet(socket);
    }
    
    @Override
    protected void choixPerso (Dueliste perso1, Dueliste perso2, EvenementCombat evenementCombat) {
        try {
        this.mainPerso1.ajouterCarte(this.perso1.getDeck().prendreCarte());
        boolean finDeTour = false;
        boolean carteMonstrePose = false;
        while (!finDeTour) {
            this.choix = -1;
            this.choixCarteTerrain = -1;
            this.choixCarteTerrainAdv = -1;
            this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
            this.pcsControlleurVue.firePropertyChange("afficherCarteJoueur", null, null);
            if (this.choix != -1) {
                if (this.choix == 0 ) {
                    finDeTour = true;
                    this.afficheTexte(perso1.getNom() + " : Je fini mon tour");
                    this.envoi.writeObject(this.choix);
                } else if (this.choix > 0 && this.choix <= this.mainPerso1.nbCarte()) {
                    int choixCarte = this.choix;
                    if (this.mainPerso1.getCarte(choixCarte -1) instanceof CarteMonstre) {
                        if (!carteMonstrePose) {
                            this.pcsControlleurVue.firePropertyChange("choixMode", null, null);
                            if (this.choix > 0 && this.choix <= Mode.values().length) {
                                Mode mode = Mode.values()[this.choix -1];
                                this.pcsControlleurVue.firePropertyChange("choixVisibilite", null, null);
                                if (this.choix > 0 && this.choix <= Visibilite.values().length) {
                                    Visibilite visibilite = Visibilite.values()[this.choix -1];
                                    CarteMonstre carte = (CarteMonstre)this.mainPerso1.prendreCarte(choixCarte -1);
                                    this.plateauCartePerso1.poserCarteMonstre(carte, visibilite, mode);
                                    carteMonstrePose = true;
                                    this.envoi.writeObject(choixCarte);
                                    this.envoi.writeObject(carte);
                                    this.envoi.writeObject(mode);
                                    this.envoi.writeObject(visibilite);
                                    if (visibilite.equals(Visibilite.visible)) {
                                        this.afficheTexte(perso1.getNom() + " : Je pose " + carte.getNom() + " en mode " + mode.name());
                                    } else {
                                        this.afficheTexte(perso1.getNom() + " : Je pose un monstre face caché en mode " + mode.name());
                                    }
                                }
                            }
                        } else {
                            this.afficheTexte("Vous avez déjà poser un monstre.");
                        }
                    } else {
                        this.pcsControlleurVue.firePropertyChange("choixVisibilite", null, null);
                        if (this.choix > 0 && this.choix <= Visibilite.values().length) {
                            Visibilite visibilite = Visibilite.values()[this.choix -1];
                            CarteMagie carte = (CarteMagie)this.mainPerso1.prendreCarte(choixCarte -1);
                            this.plateauCartePerso1.poserCarteMagique(carte, visibilite);
                            this.envoi.writeObject(choixCarte);
                            this.envoi.writeObject(carte);
                            this.envoi.writeObject(visibilite);
                            if (visibilite.equals(Visibilite.visible)) {
                                this.afficheTexte(perso1.getNom() + " : Je pose " + carte.getNom());
                                this.activeCarteMagie(carte, true);
                            } else {
                                this.afficheTexte(perso1.getNom() + " : Je pose une carte magie face caché.");
                            }
                        }
                    }
                }
            } else if (this.choixCarteTerrain != -1) {
                this.pcsControlleurVue.firePropertyChange("afficherMenuCarteTerrain", null, null);
                CarteMonstre carte1 = this.plateauCartePerso1.getCarteMonsre(this.choixCarteTerrain -1);
                switch (this.choix) {
                    case 1:
                        this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
                        this.pcsControlleurVue.firePropertyChange("choixCarteAdv", null, null);
                        System.out.println("carte adv : " + this.choixCarteTerrainAdv);
                        if (this.choixCarteTerrainAdv != -1) {
                            this.envoi.writeObject(-1);
                            this.envoi.writeObject(this.choix);
                            this.envoi.writeObject(this.choixCarteTerrain);
                            this.envoi.writeObject(this.choixCarteTerrainAdv);
                            CarteMonstre carte2 = this.plateauCartePerso2.getCarteMonsre(this.choixCarteTerrainAdv -1);
                            this.afficheTexte(perso1.getNom() + " : J'attaque " + carte2.getNom() + " avec " + carte1.getNom());
                            this.afficheTexte(perso1.getNom() + " : " + carte1.getNom() + " attaque " + carte1.getNomAttaque());
                            this.attaque(carte1, carte2, this.plateauCartePerso2.getModeCarteMonsre(this.choixCarteTerrainAdv -1), true);
                        }
                        break;
                    case 2:
                        this.envoi.writeObject(-1);
                        this.envoi.writeObject(this.choix);
                        this.envoi.writeObject(this.choixCarteTerrain);
                        if (this.plateauCartePerso1.getModeCarteMonsre(this.choixCarteTerrain-1).equals(Mode.attaque)) {
                            this.plateauCartePerso1.changerModeCarteMonsre(this.choixCarteTerrain-1, Mode.defense);
                            this.afficheTexte(perso1.getNom() + " : je place " + carte1.getNom() + " en mode defense");
                        } else {
                            this.plateauCartePerso1.changerModeCarteMonsre(this.choixCarteTerrain-1, Mode.attaque);
                            this.afficheTexte(perso1.getNom() + " : je place " + carte1.getNom() + " en mode attaque");
                        }
                        break;
                    case 3:
                        break;
                    case 4:
                        this.envoi.writeObject(-1);
                        this.envoi.writeObject(this.choix);
                        this.envoi.writeObject(this.choixCarteTerrain);
                        this.plateauCartePerso1.changerVisibiliteCarteMonsre(this.choixCarteTerrain-1, Visibilite.visible);
                        break;
                }
            }
        }
        } catch (IOException ex) {
            Logger.getLogger(CombatReseau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void choixEnnemie (Dueliste perso1, Dueliste perso2) {
        try {
        this.mainPerso2.ajouterCarte(this.perso2.getDeck().prendreCarte());
        boolean finTour = false;
        while (!finTour) {
            this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
            this.pcsControlleurVue.firePropertyChange("attenteAdv", null, null);
            int choixEnnemie = (Integer) this.receveur.readObject();
            if (choixEnnemie != -1) {
                if (choixEnnemie == 0) {
                    finTour = true;
                    this.afficheTexte(perso2.getNom() + " : Je fini mon tour");
                } else {
                    Carte carte = (Carte) this.receveur.readObject();
                    if (carte instanceof CarteMonstre) {
                        CarteMonstre carteMonstre = (CarteMonstre)this.mainPerso2.prendreCarte(choixEnnemie -1);
                        Mode mode = (Mode) this.receveur.readObject();
                        Visibilite visibilite = (Visibilite) this.receveur.readObject();
                        this.plateauCartePerso2.poserCarteMonstre(carteMonstre, visibilite, mode);
                        if (visibilite.equals(Visibilite.visible)) {
                            this.afficheTexte(perso2.getNom() + " : Je pose " + carte.getNom() + " en mode " + mode.name());
                            System.out.println("carte adv : " + carteMonstre);
                            System.out.println("carte adv : " + carteMonstre.getNom());
                        } else {
                            this.afficheTexte(perso2.getNom() + " : Je pose un monstre face caché en mode " + mode.name());
                        }
                    } else {
                        CarteMagie carteMagie = (CarteMagie)this.mainPerso2.prendreCarte(choixEnnemie -1);
                        Visibilite visibilite = (Visibilite) this.receveur.readObject();
                        this.plateauCartePerso2.poserCarteMagique(carteMagie, visibilite);
                        if (visibilite.equals(Visibilite.visible)) {
                            this.afficheTexte(perso2.getNom() + " : Je pose " + carte.getNom());
                            this.activeCarteMagie(carteMagie, false);
                        } else {
                            
                            this.afficheTexte(perso2.getNom() + " : Je pose une carte magie face caché.");
                        }
                    }
                }
            } else {
                int choixActionEnnemie = (Integer) this.receveur.readObject();
                if (choixActionEnnemie == 1) {
                    this.choixCarteTerrain = (Integer) this.receveur.readObject();
                    this.choixCarteTerrainAdv = (Integer) this.receveur.readObject();
                    CarteMonstre carte2 = this.plateauCartePerso1.getCarteMonsre(this.choixCarteTerrainAdv-1);
                    CarteMonstre carte1 = this.plateauCartePerso2.getCarteMonsre(this.choixCarteTerrain-1);
                    this.afficheTexte(perso2.getNom() + " : J'attaque " + carte2.getNom() + " avec " + carte1.getNom());
                    this.afficheTexte(perso2.getNom() + " : " + carte1.getNom() + " attaque " + carte1.getNomAttaque());
                    this.attaque(carte1, carte2, this.plateauCartePerso1.getModeCarteMonsre(this.choixCarteTerrainAdv-1), false);
                } else if (choixActionEnnemie == 2) {
                    int choixCarte = (Integer) this.receveur.readObject();
                    if (this.plateauCartePerso2.getModeCarteMonsre(choixCarte).equals(Mode.attaque)) {
                        this.plateauCartePerso2.changerModeCarteMonsre(choixCarte, Mode.defense);
                        this.afficheTexte(perso2.getNom() + " : je place " + this.plateauCartePerso1.getCarteMonsre(choixCarte).getNom() + " en mode defense");
                    } else {
                        this.plateauCartePerso2.changerModeCarteMonsre(choixCarte, Mode.attaque);
                        this.afficheTexte(perso2.getNom() + " : je place " + this.plateauCartePerso1.getCarteMonsre(choixCarte).getNom() + " en mode attaque");
                    }
                } else if (choixActionEnnemie == 4) {
                    int choixCarte = (Integer) this.receveur.readObject();
                    this.plateauCartePerso2.changerVisibiliteCarteMonsre(choixCarte, Visibilite.visible);
                }
            }
        }
        } catch (IOException ex) {
            Logger.getLogger(CombatReseau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void activeCarteMagie (CarteMagie carte, boolean attaquePerso) {
        try {
        if (attaquePerso) {
            if (carte.getAffectation().equals(Affectation.monstreAllie)) {
                this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
                this.pcsControlleurVue.firePropertyChange("choixCarteAdv", null, null);
                if (this.choixCarteTerrain != -1) {
                    this.envoi.writeObject(this.choixCarteTerrain);
                    CarteMonstre carte2 = this.plateauCartePerso1.getCarteMonsre(this.choixCarteTerrain -1);
                    this.afficheTexte(perso1.getNom() + " : J'utilise " + carte.getNom() + " sur " + carte2.getNom());
                    if (carte instanceof CarteMagieEquipement) {
                        CarteMagieEquipement carteMagieEquipement = (CarteMagieEquipement) carte;
                        if ((carteMagieEquipement.getCategorie().equals(Categorie.tous) || carte2.getCategorie().equals(carteMagieEquipement.getCategorie()))
                                && (carteMagieEquipement.getType().equals(Type.tous) || carte2.getType().equals(carteMagieEquipement.getType()))) {
                            carte2.setForce(carte2.getForce() + carteMagieEquipement.getBonusAtk());
                            carte2.setDef(carte2.getDef() + carteMagieEquipement.getBonusDef());
                            this.afficheTexte(perso1.getNom() + " : " + carte2.getNom() + " est maintenant à " + carte2.getForce() + " atk et " + 
                                    carte2.getDef() + " défense.");
                        }
                    }
                }
            }
        } else {
            int choixCarte = (Integer) this.receveur.readObject();
            CarteMonstre carte2 = this.plateauCartePerso2.getCarteMonsre(choixCarte -1);
            this.afficheTexte(perso2.getNom() + " : J'utilise " + carte.getNom() + " sur " + carte2.getNom());
            if (carte instanceof CarteMagieEquipement) {
                CarteMagieEquipement carteMagieEquipement = (CarteMagieEquipement) carte;
                if ((carteMagieEquipement.getCategorie().equals(Categorie.tous) || carte2.getCategorie().equals(carteMagieEquipement.getCategorie()))
                        && (carteMagieEquipement.getType().equals(Type.tous) || carte2.getType().equals(carteMagieEquipement.getType()))) {
                    carte2.setForce(carte2.getForce() + carteMagieEquipement.getBonusAtk());
                    carte2.setDef(carte2.getDef() + carteMagieEquipement.getBonusDef());
                    this.afficheTexte(perso2.getNom() + " : " + carte2.getNom() + " est maintenant à " + carte2.getForce() + " atk et " + 
                            carte2.getDef() + " défense.");
                }
            }
        }
        } catch (IOException ex) {
            Logger.getLogger(CombatReseau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
