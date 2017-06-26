/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.combat;

import mfiari.lib.game.condition.Condition;
import mfiari.lib.game.controlleur.ControlleurVue;
import mfiari.lib.game.liste.ListeDeGens;
import mfiari.lib.game.personnage.Gens;
import mfiari.yugioh.game.carte.Action;
import mfiari.yugioh.game.liste.Deck;
import mfiari.yugioh.game.liste.ListeDeCarte;
import mfiari.yugioh.game.liste.PlateauDeCarte;
import mfiari.yugioh.game.carte.CarteMagie;
import mfiari.yugioh.game.carte.CarteMonstre;
import mfiari.yugioh.game.perso.Dueliste;
import mfiari.yugioh.game.carte.Mode;
import mfiari.yugioh.game.carte.Resultat;
import mfiari.yugioh.game.carte.Visibilite;
import mfiari.yugioh.game.condition.ConditionMelange;
import mfiari.yugioh.game.evenement.EvenementCombat;
import mfiari.yugioh.game.ville.Terrain;
import mfiari.yugioh.game.ville.Terrains;

/**
 *
 * @author mike
 */
public abstract class AbstractCombat extends ControlleurVue {
    
    protected Dueliste perso1;
    protected int pvPerso1;
    protected Deck mainPerso1;
    protected ListeDeCarte cimetierePerso1;
    protected PlateauDeCarte plateauCartePerso1;
    protected Dueliste perso2;
    protected int pvPerso2;
    protected Deck mainPerso2;
    protected ListeDeCarte cimetierePerso2;
    protected PlateauDeCarte plateauCartePerso2;
    protected int choixCarteTerrain;
    protected int choixCarteTerrainAdv;
    protected CarteMagie magieActive;
    protected CarteMonstre monstreAttaquant;
    protected boolean turn;
    protected boolean activation;
    protected Terrain terrain;
    protected Regles[] regles;
    protected int nbSacrifice;
    
    public AbstractCombat () {
        super(true);
        this.mainPerso1 = new Deck(5);
        this.cimetierePerso1 = new ListeDeCarte();
        this.pvPerso1 = 2000;
        this.plateauCartePerso1 = new PlateauDeCarte();
        this.mainPerso2 = new Deck(5);
        this.cimetierePerso2 = new ListeDeCarte();
        this.pvPerso2 = 2000;
        this.plateauCartePerso2 = new PlateauDeCarte();
        this.terrain = Terrains.terrain_normal;
    }
    
    public boolean tourJ1 () {
        return this.turn;
    }
    
    public Dueliste getPerso1 () {
        return this.perso1;
    }
    
    public int getPvPerso1 () {
        return this.pvPerso1;
    }
    
    public Deck getMainPerso1 () {
        return this.mainPerso1;
    }
    
    public PlateauDeCarte getPlateauDeCartePerso1 () {
        return this.plateauCartePerso1;
    }
    
    public ListeDeCarte getCimetierePerso1 () {
        return this.cimetierePerso1;
    }
    
    public Dueliste getPerso2 () {
        return this.perso2;
    }
    
    public int getPvPerso2 () {
        return this.pvPerso2;
    }
    
    public Deck getMainPerso2 () {
        return this.mainPerso2;
    }
    
    public PlateauDeCarte getPlateauDeCartePerso2 () {
        return this.plateauCartePerso2;
    }
    
    public ListeDeCarte getCimetierePerso2 () {
        return this.cimetierePerso2;
    }
    
    public CarteMagie getMagieActive () {
        return this.magieActive;
    }
    
    public CarteMonstre getMonstreActif () {
        return this.monstreAttaquant;
    }
    
    public Terrain getTerrain () {
        return this.terrain;
    }
    
    public String getTexte () {
        return this.texte;
    }
    
    public void setChoixCarteTerrain (int choix) {
        this.choixCarteTerrain = choix;
    }
    
    public int getChoixCarteTerrain () {
        return this.choixCarteTerrain;
    }
    
    public void setChoixCarteTerrainAdv (int choix) {
        this.choixCarteTerrainAdv = choix;
    }
    
    public int getChoixCarteTerrainAdv () {
        return this.choixCarteTerrainAdv;
    }
    
    public void setConfirmation (boolean choix) {
        this.activation = choix;
    }
    
    public Regles[] getRegles () {
        return this.regles;
    }
    
    protected void affichePv () {
        this.pcsControlleurVue.firePropertyChange("afficherPvPerso", null, null);
        this.pcsControlleurVue.firePropertyChange("afficherPvAdv", null, null);
    }

    public abstract void combatSimple(Dueliste perso1, Dueliste perso2, Regles[] regles);
    
    protected abstract void choixPerso (Dueliste perso1, Dueliste perso2, EvenementCombat evenementCombat);
    
    protected abstract void choixEnnemie (Dueliste perso1, Dueliste perso2);
    
    protected void combat (Dueliste perso1, Dueliste perso2) {
        //this.attaque(perso1, perso2);
    }

    public void combat2(Dueliste perso1, Dueliste perso2, Regles[] regles, EvenementCombat evenementCombat) {
        this.perso1 = perso1;
        this.perso2 = perso2;
        this.regles = regles;
        this.pcsControlleurVue.firePropertyChange("afficherRegles", null, null);
        this.pcsControlleurVue.firePropertyChange("debutCombat", null, null);
        Condition derniereConditionEffectuer;
        if (evenementCombat.getDialogue() != null) {
            evenementCombat.getDialogue().activeEvenement(this);
        }
        derniereConditionEffectuer = evenementCombat.getCondition();
        if (!(derniereConditionEffectuer instanceof ConditionMelange)) {
            this.perso1.getDeck().melanger();
            this.perso2.getDeck().melanger();
        } else {
            evenementCombat.conditionReussi();
        }
        for (int i = 0 ; i < 4 ; i++) {
            this.mainPerso1.ajouterCarte(this.perso1.getDeck().prendreCarte());
            this.mainPerso2.ajouterCarte(this.perso2.getDeck().prendreCarte());
        }
        this.pcsControlleurVue.firePropertyChange("afficherTerrainCombat", null, null);
        while (this.pvPerso1 > 0 && this.pvPerso2 > 0) {
            if (evenementCombat.getDialogue() != null) {
                evenementCombat.getDialogue().activeEvenement(this);
            }
            this.turn = true;
            this.activeEffet(false, Action.tour_perso);
            this.choixPerso(this.perso1, this.perso2, evenementCombat);
            this.turn = false;
            if (this.pvPerso1 > 0 && this.pvPerso2 > 0) {
                this.activeEffet(false, Action.tour_adv);
                this.choixEnnemie(this.perso1, this.perso2);
            }
            this.choixCarteTerrain = 0;
        }
        if (this.pvPerso1 > 0) {
            this.argent(this.perso1, this.perso2);
        } else {
            this.defaite();
        }
    }
    
    // cette fonction permet de savoir lequel de vos personnage l'ennemi va attaquer
    protected void attaque(CarteMonstre carte1, CarteMonstre carte2, Mode mode, boolean attaquePerso) {
        this.verifAttaque(carte1, carte2, mode, attaquePerso);
    }
    
    protected void verifAttaque (CarteMonstre carte1, CarteMonstre carte2, Mode mode, boolean attaquePerso) {
        if (this.attaqueReussi(carte1, carte2) == 0) {
            int degat;
            degat = this.degatAtt(carte1, carte2, mode);
            System.out.println("degat : " + degat);
            if (degat > 0) {
                CarteMonstre carte;
                if (attaquePerso) {
                    if (mode.equals(Mode.attaque)) {
                        this.pvPerso2 -= degat;
                    }
                    carte = plateauCartePerso2.retirerCarteMonstre(this.choixCarteTerrainAdv -1);
                } else {
                    if (mode.equals(Mode.attaque)) {
                        this.pvPerso1 -= degat;
                    }
                    carte = plateauCartePerso1.retirerCarteMonstre(this.choixCarteTerrainAdv -1);
                }
                this.afficheTexte(carte.getNom() + " est détruit");
            } else if (degat < 0) {
                CarteMonstre carte;
                if (attaquePerso) {
                    this.pvPerso1 += degat;
                    if (mode.equals(Mode.attaque)) {
                        carte = plateauCartePerso1.retirerCarteMonstre(this.choixCarteTerrain-1);
                        this.afficheTexte(carte.getNom() + " est détruit");
                    }
                    plateauCartePerso2.changerVisibiliteCarteMonsre(this.choixCarteTerrainAdv -1, Visibilite.visible);
                } else {
                    this.pvPerso2 += degat;
                    if (mode.equals(Mode.attaque)) {
                        carte = plateauCartePerso2.retirerCarteMonstre(this.choixCarteTerrain-1);
                        this.afficheTexte(carte.getNom() + " est détruit");
                    }
                    plateauCartePerso1.changerVisibiliteCarteMonsre(this.choixCarteTerrainAdv -1, Visibilite.visible);
                }
            } else {
                if (attaquePerso) {
                    CarteMonstre cartePerso = plateauCartePerso1.retirerCarteMonstre(this.choixCarteTerrain-1);
                    this.afficheTexte(cartePerso.getNom() + " est détruit");
                    CarteMonstre carteAdv = plateauCartePerso2.retirerCarteMonstre(this.choixCarteTerrainAdv -1);
                    this.afficheTexte(carteAdv.getNom() + " est détruit");
                } else {
                    CarteMonstre cartePerso = plateauCartePerso1.retirerCarteMonstre(this.choixCarteTerrainAdv-1);
                    this.afficheTexte(cartePerso.getNom() + " est détruit");
                    CarteMonstre carteAdv = plateauCartePerso2.retirerCarteMonstre(this.choixCarteTerrain -1);
                    this.afficheTexte(carteAdv.getNom() + " est détruit");
                }
            }
            this.affichePv();
        }
    }

    protected int attaqueReussi(CarteMonstre carte1, CarteMonstre carte2) {
        return 0;
    }
    
    protected int degatAtt(CarteMonstre carte1, CarteMonstre carte2, Mode mode) {
        int degat;
        if (mode.equals(Mode.attaque)) {
            degat = carte1.getForce() - carte2.getForce();
        } else {
            degat = carte1.getForce() - carte2.getDef();
        }
        return degat;
    }

    protected double arrondiDegat(Dueliste attaquer, double degat) {
        if (degat < 1) {
            degat = 1;
        }
        return degat;
    }
    
    protected abstract void activeCarteMagie (CarteMagie carte, boolean attaquePerso);
    
    protected abstract Resultat activeEffet (boolean attaquePerso, Action action);
    
    protected void argent (Dueliste perso1, Dueliste perso2) {
        
    }
    
    protected void defaite () {
        
    }
    
    protected boolean aRegle (Regles regle) {
        for (int i = 0 ; i < this.regles.length ; i++) {
            if (this.regles[i] == regle) {
                return true;
            }
        }
        return false;
    }
    
}