/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.evenement;

import mfiari.lib.game.jeu.Jeu;
import mfiari.lib.game.objet.Objet;
import mfiari.lib.game.personnage.Gens;
import mfiari.lib.game.position.Position;
import mfiari.lib.game.ville.Terrain;
import mfiari.yugioh.game.YugiohJeu;
import mfiari.yugioh.game.combat.Combat;
import mfiari.yugioh.game.combat.Regles;
import mfiari.yugioh.game.perso.Dueliste;

/**
 *
 * @author mike
 */
public class EvenementCombat extends mfiari.lib.game.evenements.EvenementCombat {
    
    private Regles[] regles;
    
    {
        this.regles = new Regles[] {Regles.invocation_direct, Regles.attaque_direct_interdit};
    }

    public EvenementCombat (Position endroitDeLevenement, Gens personneAquiParler, Objet objet, String titre, Terrain t) {
        super(endroitDeLevenement, personneAquiParler, objet, titre, t);
    }

    @Override
    public void activeEvenement(Jeu jeu) {
        YugiohJeu yugiohJeu = (YugiohJeu) jeu;
        Combat combat = new Combat();
        combat.combat2((Dueliste)yugiohJeu.getPerso(), (Dueliste)this.dresseur, this.regles, this);
    }
    
}
