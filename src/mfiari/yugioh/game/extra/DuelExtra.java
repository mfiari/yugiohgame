/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.extra;

import mfiari.lib.game.controlleur.ControlleurVue;
import mfiari.lib.game.position.Orientation;
import mfiari.yugioh.game.combat.Combat;
import mfiari.yugioh.game.combat.Regles;
import mfiari.yugioh.game.demarrage.CreationCarte;
import mfiari.yugioh.game.demarrage.CreationPerso;
import mfiari.yugioh.game.liste.ListeDeDueliste;
import mfiari.yugioh.game.perso.Dueliste;

/**
 *
 * @author mike
 */
public class DuelExtra extends ControlleurVue {
    

    private boolean valider;
    private ListeDeDueliste personnages;
    private String affichage;
    private Dueliste perso;
    private Dueliste adv;
    
    public DuelExtra () {
        super(true);
        //CreationCapacite creationCapacite = new CreationCapacite();
        CreationPerso creationPerso = new CreationPerso();
        CreationCarte creationCarte = new CreationCarte();
        this.perso = null;
        this.adv = null;
        this.personnages = creationPerso.getPerso();
    }
    
    public String getAffichage () {
        return this.affichage;
    }
    
    public Dueliste getPersonnageEnCour () {
        if (this.adv == null && this.perso != null) {
            return this.perso;
        } else if (this.adv != null) {
            return this.adv;
        }
        return null;
    }
    
    public ListeDeDueliste getParticipant () {
        return this.personnages;
    }
    
    public void menu () {
        do {
            this.pcsControlleurVue.firePropertyChange("choixPerso", null, null);
            if (this.perso == null) {
                if (this.choix >= 1 && this.choix <= this.personnages.size()) {
                    this.perso = this.personnages.getPersonnage(this.choix -1);
                    this.pcsControlleurVue.firePropertyChange("confirmerChoixPerso", null, null);
                    if (!this.valider) {
                        this.perso = null;
                    }
                } else if (this.choix == this.personnages.size()+1 ) {
                    this.perso = new Dueliste("J1", 0, 0, null, Orientation.droite);
                    this.pcsControlleurVue.firePropertyChange("choixCarte", null, null);
                } else if (this.choix == 0 ) {
                    this.perso = null;
                }
            } else {
                if (this.choix >= 1 && this.choix <= this.personnages.size()) {
                    this.adv = this.personnages.getPersonnage(this.choix -1);
                    this.pcsControlleurVue.firePropertyChange("confirmerChoixPerso", null, null);
                    if (!this.valider) {
                        this.adv = null;
                    }
                } else if (this.choix == 0 ) {
                    this.adv = null;
                }
            }
        } while ((this.perso == null || this.adv == null));
        if (this.perso != null && this.adv != null) {
            this.terminer();
        }
    }
    
    public void setValider (boolean choix) {
        this.valider = choix;
    }
    
    public void terminer () {
        Combat combat = new Combat();
        combat.combatSimple(this.perso, this.adv, new Regles[] {Regles.invocation_direct, Regles.attaque_direct_interdit});
    }
    
}