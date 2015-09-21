/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.combat;

import mfiari.lib.game.condition.ConditionFinDeTour;
import mfiari.lib.game.condition.ConditionPrimaire;
import mfiari.lib.game.liste.ListeDeGens;
import mfiari.lib.game.personnage.Gens;
import mfiari.yugioh.game.carte.Action;
import mfiari.yugioh.game.carte.Affectation;
import mfiari.yugioh.game.carte.Carte;
import mfiari.yugioh.game.carte.CarteMagie;
import mfiari.yugioh.game.carte.CarteMagieEquipement;
import mfiari.yugioh.game.carte.CarteMagieNormal;
import mfiari.yugioh.game.carte.CarteMagieTerrain;
import mfiari.yugioh.game.carte.CarteMonstre;
import mfiari.yugioh.game.carte.CarteMonstreEffet;
import mfiari.yugioh.game.carte.CartePiege;
import mfiari.yugioh.game.carte.CartePiegeNormal;
import mfiari.yugioh.game.carte.Categorie;
import mfiari.yugioh.game.perso.Dueliste;
import mfiari.yugioh.game.carte.Mode;
import mfiari.yugioh.game.carte.Resultat;
import mfiari.yugioh.game.carte.Type;
import mfiari.yugioh.game.carte.TypeMagie;
import mfiari.yugioh.game.carte.Visibilite;
import mfiari.yugioh.game.condition.ConditionChoixCarte;
import mfiari.yugioh.game.evenement.EvenementCombat;

/**
 *
 * @author mike
 */
public class Combat extends AbstractCombat {
    
    public Combat () {
        
    }

    @Override
    public void combatSimple(Dueliste perso1, Dueliste perso2, Regles[] regles) {
        this.perso1 = perso1;
        this.perso2 = perso2;
        this.regles = regles;
        this.perso1.getDeck().melanger();
        this.perso2.getDeck().melanger();
        this.pcsControlleurVue.firePropertyChange("afficherRegles", null, null);
        this.pcsControlleurVue.firePropertyChange("debutCombat", null, null);
        for (int i = 0 ; i < 4 ; i++) {
            this.mainPerso1.ajouterCarte(this.perso1.getDeck().prendreCarte());
            this.mainPerso2.ajouterCarte(this.perso2.getDeck().prendreCarte());
        }
        this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
        while (this.pvPerso1 > 0 && this.pvPerso2 > 0) {
            this.turn = true;
            this.activeEffet(false, Action.tour_perso);
            this.choixPerso(this.perso1, this.perso2, null);
            this.turn = false;
            if (this.pvPerso1 > 0 && this.pvPerso2 > 0) {
                this.activeEffet(false, Action.tour_adv);
                this.choixEnnemie(this.perso1, this.perso2);
            }
            //this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
            //this.combat(this.perso1, this.perso2);
            this.choixCarteTerrain = 0;
        }
        if (this.pvPerso1 > 0) {
            this.argent(this.perso1, this.perso2);
        } else {
            this.defaite();
        }
    }
    
    @Override
    protected void choixPerso (Dueliste perso1, Dueliste perso2, EvenementCombat evenementCombat) {
        boolean finDeTour = false;
        boolean carteMonstrePose = false;
        if (this.perso1.getDeck().nbCarte() <= 0) {
            finDeTour = true;
            this.pvPerso1 = 0;
        } else {
            this.mainPerso1.ajouterCarte(this.perso1.getDeck().prendreCarte());
            this.activeEffet(false, Action.pioche);
            this.nbSacrifice = 0;
        }
        while (!finDeTour && this.pvPerso1 > 0 && this.pvPerso2 > 0) {
            this.choix = -1;
            this.choixCarteTerrain = -1;
            this.choixCarteTerrainAdv = -1;
            this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
            this.pcsControlleurVue.firePropertyChange("afficherCarteJoueur", null, null);
            if (this.choix != -1) {
                if (this.choix == 0 ) {
                    if (evenementCombat!= null && evenementCombat.getCondition() instanceof ConditionPrimaire) {
                        if (evenementCombat.getCondition() instanceof ConditionChoixCarte) {
                            evenementCombat.getWarning().activeEvenement(this);
                        } else if (evenementCombat.getCondition() instanceof ConditionFinDeTour) {
                            evenementCombat.conditionReussi();
                            finDeTour = true;
                            this.afficheTexte(perso1.getNom() + " : Je fini mon tour");
                        }
                    } else {
                        finDeTour = true;
                        this.afficheTexte(perso1.getNom() + " : Je fini mon tour");
                    }
                } else if (this.choix > 0 && this.choix <= this.mainPerso1.nbCarte()) {
                    int choixCarte = this.choix;
                    if (this.mainPerso1.getCarte(choixCarte -1) instanceof CarteMonstre) {
                        if (!carteMonstrePose) {
                            CarteMonstre carteMonstre = (CarteMonstre)this.mainPerso1.getCarte(choixCarte -1);
                            if (evenementCombat!= null && evenementCombat.getCondition() instanceof ConditionChoixCarte && 
                                    !((ConditionChoixCarte)evenementCombat.getCondition()).equals(carteMonstre)) {
                                evenementCombat.getWarning().activeEvenement(this);
                            } else {
                                if (this.aRegle(Regles.invocation_direct) || (this.aRegle(Regles.sacrifice) && (carteMonstre.getEtoiles() <= 4 || 
                                        (carteMonstre.getEtoiles() <=6 && this.nbSacrifice >=1)) || (carteMonstre.getEtoiles() > 6 && this.nbSacrifice >1))) {
                                    this.pcsControlleurVue.firePropertyChange("choixMode", null, null);
                                    if (this.choix > 0 && this.choix <= Mode.values().length) {
                                        Mode mode = Mode.values()[this.choix -1];
                                        if (mode.equals(Mode.defense)) {
                                            this.pcsControlleurVue.firePropertyChange("choixVisibilite", null, null);
                                        } else {
                                            this.choix = 1;
                                        }
                                        if (this.choix > 0 && this.choix <= Visibilite.values().length) {
                                            Visibilite visibilite = Visibilite.values()[this.choix -1];
                                            if (evenementCombat!= null && evenementCombat.getCondition() instanceof ConditionChoixCarte && 
                                                    !((ConditionChoixCarte)evenementCombat.getCondition()).equals(carteMonstre)) {
                                                evenementCombat.getWarning().activeEvenement(this);
                                            } else {
                                                CarteMonstre carte = (CarteMonstre)this.mainPerso1.prendreCarte(choixCarte -1);
                                                this.plateauCartePerso1.poserCarteMonstre(carte, visibilite, mode);
                                                carteMonstrePose = true;
                                                if (visibilite.equals(Visibilite.visible)) {
                                                    this.afficheTexte(perso1.getNom() + " : Je pose " + carte.getNom() + " en mode " + mode.name());
                                                } else {
                                                    this.afficheTexte(perso1.getNom() + " : Je pose un monstre face caché en mode " + mode.name());
                                                }
                                                this.affecteTerrain(carte);
                                                this.activeEffet(false, Action.invocation);
                                                if (evenementCombat != null) {
                                                    evenementCombat.conditionReussi();
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (carteMonstre.getEtoiles() <=6) {
                                        this.afficheTexte("Il faut 1 sacrifice pour invoquer " + carteMonstre.getNom());
                                    } else {
                                        this.afficheTexte("Il faut 2 sacrifices pour invoquer " + carteMonstre.getNom());
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
                            Carte carte = this.mainPerso1.prendreCarte(choixCarte -1);
                            if (carte instanceof CarteMagie) {
                                CarteMagie carteMagie = (CarteMagie) carte;
                                this.plateauCartePerso1.poserCarteMagique((CarteMagie)carte, visibilite);
                                if (visibilite.equals(Visibilite.visible)) {
                                    this.afficheTexte(perso1.getNom() + " : Je pose " + carteMagie.getNom());
                                    this.activeCarteMagie(carteMagie, true);
                                } else {
                                    this.afficheTexte(perso1.getNom() + " : Je pose une carte magie face caché.");
                                }
                            } else {
                                this.plateauCartePerso1.poserCarteMagique((CartePiege)carte, visibilite);
                                if (visibilite.equals(Visibilite.visible)) {
                                    this.afficheTexte(perso1.getNom() + " : Je pose " + carte.getNom());
                                    ((CartePiege)carte).getEffet().run(this);
                                } else {
                                    this.afficheTexte(perso1.getNom() + " : Je pose une carte magie face caché.");
                                }
                            }
                        }
                    }
                }
            } else if (this.choixCarteTerrain != -1) {
                this.pcsControlleurVue.firePropertyChange("afficherMenuCarteTerrain", null, null);
                if (this.choixCarteTerrain > 0 && this.choixCarteTerrain <=5) {
                    CarteMonstre carte1 = this.plateauCartePerso1.getCarteMonsre(this.choixCarteTerrain -1);
                    switch (this.choix) {
                        case 1:
                            this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
                            this.pcsControlleurVue.firePropertyChange("choixCarteAdv", null, null);
                            System.out.println("carte adv : " + this.choixCarteTerrainAdv);
                            if (this.choixCarteTerrainAdv != -1) {
                                this.plateauCartePerso1.changerModeCarteMonsre(this.choixCarteTerrain-1, Mode.attaque);
                                if (this.choixCarteTerrainAdv == 0) {
                                    if (this.aRegle(Regles.attaque_direct)) {
                                        this.afficheTexte(perso1.getNom() + " : J'attaque directement tes points de vie avec " + carte1.getNom());
                                        this.afficheTexte(perso1.getNom() + " : " + carte1.getNom() + " attaque " + carte1.getNomAttaque());
                                        if (this.activeEffet(true, Action.attaque) != Resultat.bloque_att) {
                                            this.pvPerso2 -= carte1.getForce();
                                        }
                                    } else {
                                        this.afficheTexte("Vous n'êtes pas autorisé à attaquer directement les points de vie adverse.");
                                    }
                                } else if (this.choixCarteTerrainAdv <= 5) {
                                    CarteMonstre carte2 = this.plateauCartePerso2.getCarteMonsre(this.choixCarteTerrainAdv -1);
                                    this.afficheTexte(perso1.getNom() + " : J'attaque " + carte2.getNom() + " avec " + carte1.getNom());
                                    this.afficheTexte(perso1.getNom() + " : " + carte1.getNom() + " attaque " + carte1.getNomAttaque());
                                    if (this.activeEffet(true, Action.attaque) != Resultat.bloque_att) {
                                        this.attaque(carte1, carte2, this.plateauCartePerso2.getModeCarteMonsre(this.choixCarteTerrainAdv -1), true);
                                    }
                                }
                            }
                            break;
                        case 2:
                            if (this.plateauCartePerso1.getModeCarteMonsre(this.choixCarteTerrain-1).equals(Mode.attaque)) {
                                this.plateauCartePerso1.changerModeCarteMonsre(this.choixCarteTerrain-1, Mode.defense);
                                this.afficheTexte(perso1.getNom() + " : je place " + carte1.getNom() + " en mode defense");
                            } else {
                                this.plateauCartePerso1.changerModeCarteMonsre(this.choixCarteTerrain-1, Mode.attaque);
                                this.afficheTexte(perso1.getNom() + " : je place " + carte1.getNom() + " en mode attaque");
                            }
                            break;
                        case 3:
                            this.nbSacrifice++;
                            this.cimetierePerso1.ajouterCarte(this.plateauCartePerso1.retirerCarteMonstre(this.choixCarteTerrain-1));
                            break;
                        case 4:
                            this.plateauCartePerso1.changerVisibiliteCarteMonsre(this.choixCarteTerrain-1, Visibilite.visible);
                            break;
                    }
                } else {
                    CarteMagie carteMagie = this.plateauCartePerso1.getCarteMagie(this.choixCarteTerrain -6);
                    switch (this.choix) {
                        case 1:
                            break;
                        case 2:
                            this.activeCarteMagie(carteMagie, true);
                            break;
                    }
                }
            }
        }
    }
    
    @Override
    protected void choixEnnemie (Dueliste perso1, Dueliste perso2) {
        if (this.perso2.getDeck().nbCarte() <= 0) {
            this.pvPerso2 = 0;
        }
        if (this.pvPerso1 > 0 && this.pvPerso2 > 0) {
            this.mainPerso2.ajouterCarte(this.perso2.getDeck().prendreCarte());
            this.activeEffet(false, Action.pioche);
            Carte carte = this.mainPerso2.prendreCarte();
            if (carte instanceof CarteMonstre) {
                boolean cartePose = false;
                CarteMonstre carteMonstre = (CarteMonstre) carte;
                for (int i = 0 ; i < 5 ; i++) {
                    if (this.plateauCartePerso1.getCarteMonsre(i) != null) {
                        CarteMonstre carte2 = this.plateauCartePerso1.getCarteMonsre(i);
                        if (this.plateauCartePerso1.getVisibiliteCarteMonsre(i).equals(Visibilite.cache) || 
                                (this.plateauCartePerso1.getModeCarteMonsre(i).equals(Mode.attaque) && carte2.getForce() < carteMonstre.getForce()) ||
                                (this.plateauCartePerso1.getModeCarteMonsre(i).equals(Mode.defense) && carte2.getDef() < carteMonstre.getForce())) {
                            cartePose = true;
                            this.choixCarteTerrain = this.plateauCartePerso2.poserCarteMonstre(carteMonstre, Visibilite.visible, Mode.attaque)+1;
                            this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
                            this.afficheTexte(perso2.getNom() + " : Je pose " + carteMonstre.getNom() + " en mode attaque.");
                            this.affecteTerrain(carteMonstre);
                            this.activeEffet(false, Action.invocation);
                            this.choixCarteTerrainAdv = i+1;
                            if (this.plateauCartePerso1.getVisibiliteCarteMonsre(i).equals(Visibilite.cache)) {
                                this.afficheTexte(perso2.getNom() + " : J'attaque la carte face caché avec " + carteMonstre.getNom());
                            } else {
                                this.afficheTexte(perso2.getNom() + " : J'attaque " + carte2.getNom() + " avec " + carteMonstre.getNom());
                            }
                            this.afficheTexte(perso2.getNom() + " : " + carteMonstre.getNom() + " attaque " + carteMonstre.getNomAttaque());
                            if (this.activeEffet(false, Action.attaque) != Resultat.bloque_att) {
                                this.attaque(carteMonstre, carte2, this.plateauCartePerso1.getModeCarteMonsre(i), false);
                            }
                            break;
                        }
                    }
                }
                if (!cartePose) {
                    this.choixCarteTerrain = this.plateauCartePerso2.poserCarteMonstre(carteMonstre, Visibilite.cache, Mode.defense)+1;
                    this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
                    this.afficheTexte(perso2.getNom() + " : Je pose un monstre en mode defense.");
                    this.affecteTerrain(carteMonstre);
                    this.activeEffet(false, Action.invocation);
                }
            } else if (carte instanceof CarteMagie) {
                this.afficheTexte(perso2.getNom() + " : Je pose une carte face caché");
                this.plateauCartePerso2.poserCarteMagique(((CarteMagie)carte), Visibilite.cache);
            } else if (carte instanceof CartePiege) {
                this.afficheTexte(perso2.getNom() + " : Je pose une carte face caché");
                this.plateauCartePerso2.poserCarteMagique(((CartePiege)carte), Visibilite.cache);
            }
            for (int i = 0 ; i < 5 ; i++) {
                if (this.pvPerso1 <= 0 || this.pvPerso2 <= 0) {
                    break;
                }
                if (i != this.choixCarteTerrain-1) {
                    if (this.plateauCartePerso2.getCarteMonsre(i) != null) {
                        boolean cartePose = false;
                        CarteMonstre carteMonstre = (CarteMonstre) this.plateauCartePerso2.getCarteMonsre(i);
                        for (int j = 0 ; j < 5 ; j++) {
                            if (this.plateauCartePerso1.getCarteMonsre(j) != null) {
                                CarteMonstre carte2 = this.plateauCartePerso1.getCarteMonsre(j);
                                if (this.plateauCartePerso1.getVisibiliteCarteMonsre(j).equals(Visibilite.cache) || 
                                    (this.plateauCartePerso1.getModeCarteMonsre(j).equals(Mode.attaque) && carte2.getForce() < carteMonstre.getForce()) ||
                                    (this.plateauCartePerso1.getModeCarteMonsre(j).equals(Mode.defense) && carte2.getDef() < carteMonstre.getForce())) {
                                    cartePose = true;
                                    int tmpChoixCarteTerrain = this.choixCarteTerrain;
                                    this.choixCarteTerrain = i+1;
                                    this.choixCarteTerrainAdv = j+1;
                                    if (this.plateauCartePerso2.getModeCarteMonsre(i).equals(Mode.defense)) {
                                        this.plateauCartePerso2.changerModeCarteMonsre(i, Mode.attaque);
                                        this.afficheTexte(perso2.getNom() + " : je place " + carteMonstre.getNom() + " en mode attaque");
                                    }
                                    if (this.plateauCartePerso1.getVisibiliteCarteMonsre(j).equals(Visibilite.cache)) {
                                        this.afficheTexte(perso2.getNom() + " : J'attaque la carte face caché avec " + carteMonstre.getNom());
                                    } else {
                                        this.afficheTexte(perso2.getNom() + " : J'attaque " + carte2.getNom() + " avec " + carteMonstre.getNom());
                                    }
                                    this.afficheTexte(perso2.getNom() + " : " + carteMonstre.getNom() + " attaque " + carteMonstre.getNomAttaque());
                                    if (this.activeEffet(false, Action.attaque) != Resultat.bloque_att) {
                                        this.attaque(carteMonstre, carte2, this.plateauCartePerso1.getModeCarteMonsre(j), false);
                                    }
                                    this.choixCarteTerrain = tmpChoixCarteTerrain;
                                    break;
                                }
                            }
                        }
                        if (!cartePose) {
                            if (this.plateauCartePerso2.getModeCarteMonsre(i).equals(Mode.attaque)) {
                                this.plateauCartePerso2.changerModeCarteMonsre(i, Mode.defense);
                                this.afficheTexte(perso2.getNom() + " : je place " + carteMonstre.getNom() + " en mode defense");
                            }
                        }
                    }
                }
            }
            this.afficheTexte(perso2.getNom() + " : Je fini mon tour");
        }
    }
    
    @Override
    protected void activeCarteMagie (CarteMagie carte, boolean attaquePerso) {
        if (carte instanceof CarteMagieNormal) {
            CarteMagieNormal magieNormal = (CarteMagieNormal) carte;
            if (attaquePerso) {
                this.afficheTexte(perso1.getNom() + " : J'active l'effet de ma carte " + magieNormal.getNom() + ", " + magieNormal.getEffet().getNom());
            } else {
                this.afficheTexte(perso2.getNom() + " : J'active l'effet de ma carte " + magieNormal.getNom() + ", " + magieNormal.getEffet().getNom());
            }
            magieNormal.getEffet().run(this);
        } else if (carte.getAffectation().equals(Affectation.monstreAllie)) {
            this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
            this.pcsControlleurVue.firePropertyChange("choixCarteAdv", null, null);
            if (this.choixCarteTerrain != -1) {
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
        } else if (carte instanceof CarteMagieTerrain) {
            CarteMagieTerrain carteTerrain = (CarteMagieTerrain) carte;
            this.afficheTexte("le terrain est devenu " + carteTerrain.getNom());
            if (carteTerrain.getTerrain().nbCategoriesAugmente() > 0) {
                String texte = "";
                String separator = "";
                for (int i = 0 ; i < carteTerrain.getTerrain().nbCategoriesAugmente() ; i++) {
                    texte += separator + " " + carteTerrain.getTerrain().getCategoriesAugmente(i);
                    separator = ",";
                }
                this.afficheTexte("Monstre de type " + texte + " sont plus fort.");
            }
            if (carteTerrain.getTerrain().nbCategoriesdiminues() > 0) {
                String texte = "";
                String separator = "";
                for (int i = 0 ; i < carteTerrain.getTerrain().nbCategoriesdiminues() ; i++) {
                    texte += separator + " " + carteTerrain.getTerrain().getCategoriesdiminues(i);
                    separator = ",";
                }
                this.afficheTexte("Affaibli Monstre de type " + texte + ".");
            }
            for (int i = 0 ; i < 5 ; i++) {
                this.annuleTerrain(this.plateauCartePerso1.getCarteMonsre(i));
            }
            for (int i = 0 ; i < 5 ; i++) {
                this.annuleTerrain(this.plateauCartePerso1.getCarteMonsre(i));
            }
            this.terrain = carteTerrain.getTerrain();
            for (int i = 0 ; i < 5 ; i++) {
                this.affecteTerrain(this.plateauCartePerso1.getCarteMonsre(i));
            }
            for (int i = 0 ; i < 5 ; i++) {
                this.affecteTerrain(this.plateauCartePerso1.getCarteMonsre(i));
            }
            this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
        }
        if (!carte.estPermanent()) {
            if (attaquePerso) {
                this.plateauCartePerso1.retirerCarteMagique(carte);
            } else {
                this.plateauCartePerso2.retirerCarteMagique(carte);
            }
        }
    }
    
    private void affecteTerrain (CarteMonstre carte) {
        if (carte != null) {
            this.terrain.affecte(carte);
        }
    }
    
    private void annuleTerrain (CarteMonstre carte) {
        if (carte != null) {
            this.terrain.annule(carte);
        }
    }
    
    @Override
    protected Resultat activeEffet (boolean attaquePerso, Action action) {
        Resultat resultat = null;
        if (!attaquePerso) {
            for (int i = 0 ; i < 5 ; i++) {
                CarteMonstre monstre = this.plateauCartePerso1.getCarteMonsre(i);
                if (monstre != null && monstre instanceof CarteMonstreEffet) {
                    CarteMonstreEffet monstreEffet = (CarteMonstreEffet) monstre;
                    if (monstreEffet.getAction() == action) {
                        this.pcsControlleurVue.firePropertyChange("choixActivationEffet", monstreEffet, null);
                        if (this.activation) {
                            this.afficheTexte(perso1.getNom() + " : J'active l'effet de ma carte " + monstreEffet.getNom() + ", " + monstreEffet.getEffet().getNom());
                            resultat = monstreEffet.getEffet().run(this);
                            this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
                        }
                    }
                }
            }
            for (int i = 0 ; i < 5 ; i++) {
                CartePiege piege = this.plateauCartePerso1.getCartePiege(i);
                if (piege != null && piege instanceof CartePiegeNormal) {
                    CartePiegeNormal piegeNormal = (CartePiegeNormal) piege;
                    if (piegeNormal.getAction() == action) {
                        this.pcsControlleurVue.firePropertyChange("choixActivationEffet", piegeNormal, null);
                         if (this.activation) {
                            this.afficheTexte(perso1.getNom() + " : J'active l'effet de ma carte " + piegeNormal.getNom() + ", " + piegeNormal.getEffet().getNom());
                            resultat = piegeNormal.getEffet().run(this);
                            if (piegeNormal.getType() != TypeMagie.continu) {
                                this.plateauCartePerso1.retirerCarteMagique(i);
                            }
                            this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
                         }
                    }
                }
            }
        } else {
            for (int i = 0 ; i < 5 ; i++) {
                CarteMonstre monstre = this.plateauCartePerso2.getCarteMonsre(i);
                if (monstre != null && monstre instanceof CarteMonstreEffet) {
                    CarteMonstreEffet monstreEffet = (CarteMonstreEffet) monstre;
                    if (monstreEffet.getAction() == action) {
                        this.afficheTexte(perso2.getNom() + " : J'active l'effet de ma carte " + monstreEffet.getNom() + ", " + monstreEffet.getEffet().getNom());
                        resultat = monstreEffet.getEffet().run(this);
                        this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
                    }
                }
            }
            for (int i = 0 ; i < 5 ; i++) {
                CartePiege piege = this.plateauCartePerso2.getCartePiege(i);
                if (piege != null && piege instanceof CartePiegeNormal) {
                    CartePiegeNormal piegeNormal = (CartePiegeNormal) piege;
                    if (piegeNormal.getAction() == action) {
                        this.afficheTexte(perso2.getNom() + " : J'active l'effet de ma carte " + piegeNormal.getNom() + ", " + piegeNormal.getEffet().getNom());
                        resultat = piegeNormal.getEffet().run(this);
                        if (piegeNormal.getType() != TypeMagie.continu) {
                            this.plateauCartePerso2.retirerCarteMagique(i);
                        }
                        this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
                    }
                }
            }
        }
        return resultat;
    }
    
}