/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.demarrage;

import mfiari.lib.game.condition.ConditionAttaque;
import mfiari.lib.game.condition.ConditionFinDeTour;
import mfiari.lib.game.evenements.EvenementDialogue;
import mfiari.lib.game.position.Orientation;
import mfiari.lib.game.position.Position;
import mfiari.yugioh.game.carte.Cartes;
import mfiari.yugioh.game.carte.Mode;
import mfiari.yugioh.game.carte.Visibilite;
import mfiari.yugioh.game.condition.ConditionChoixCarte;
import mfiari.yugioh.game.condition.ConditionMelange;
import mfiari.yugioh.game.evenement.Evenements;
import mfiari.yugioh.game.perso.Duelistes;

/**
 *
 * @author mike
 */
public class CreationEvenement {
    
    public CreationEvenement() {
        this.creationEvenementDialogue();
        this.creationEvenementCombat();
        this.creationEvenementChoix();
        this.creationEvenementSpecial();
        this.creationEvenementQueteAnnexe();
        this.creationEvenementQuete();
    }

    private void creationEvenementQuete() {
        Evenements.depart.ajouterQuete(Evenements.debut);
        Evenements.depart.ajouterQuete(Evenements.combat_regle1);
        Evenements.depart.ajouterQuete(Evenements.rencontre_avec_kaiba);
        Evenements.depart.ajouterQuete(Evenements.combat_regle2);
        Evenements.depart.ajouterQuete(Evenements.invitation_kaiba);
        Evenements.depart.ajouterQuete(Evenements.dialogue_avant_combat_kaiba);
        Evenements.depart.ajouterQuete(Evenements.combat_regle3);
        Evenements.chapitre1.ajouterQuete(Evenements.depart);
        Evenements.jeu.ajouterQuete(Evenements.chapitre1);
    }

    private void creationEvenementQueteAnnexe() {
    }

    private void creationEvenementDialogue() {
        /* Evenement Chapitre 1 : Quete depart : evenement debut */
        Duelistes.yugi.addListeDialogueEvenement(Evenements.debut, "Bien "+Duelistes.joey.getNom()+", jouons à duel monster.", 1);
        Duelistes.joey.addListeDialogueEvenement(Evenements.debut, "ok", 2);
        Evenements.debut.ajouterGens(Duelistes.yugi, new Position(0, 0, Orientation.droite));
        Evenements.debut.ajouterGens(Duelistes.joey, new Position(0, 2, Orientation.gauche));
        Evenements.debut.ajouterGens(Duelistes.ando, new Position(2, 0, Orientation.dos));
        Evenements.debut.ajouterGens(Duelistes.tea, new Position(2, 2, Orientation.dos));
        /* FIN Evenement Chapitre 1 : Quete depart : evenement debut */
        /* Evenement Chapitre 1 : Quete depart : evenement debut */
        Duelistes.kaiba.addListeDialogueEvenement(Evenements.rencontre_avec_kaiba, "Je suis venu prendre le dragon blanc aux yeux bleu.", 1);
        Duelistes.yugi.addListeDialogueEvenement(Evenements.rencontre_avec_kaiba, "non.", 2);
        Evenements.rencontre_avec_kaiba.ajouterGens(Duelistes.yugi);
        Evenements.rencontre_avec_kaiba.ajouterGens(Duelistes.kaiba);
        /* FIN Evenement Chapitre 1 : Quete depart : evenement debut */
        /* Evenement Chapitre 1 : Quete depart : evenement debut */
        Duelistes.kaiba.addListeDialogueEvenement(Evenements.invitation_kaiba, "Vien m'affronter.", 1);
        Duelistes.yugi.addListeDialogueEvenement(Evenements.invitation_kaiba, "ok.", 2);
        Evenements.invitation_kaiba.ajouterGens(Duelistes.yugi);
        Evenements.invitation_kaiba.ajouterGens(Duelistes.kaiba);
        /* FIN Evenement Chapitre 1 : Quete depart : evenement debut */
        /* Evenement Chapitre 1 : Quete depart : evenement debut */
        Duelistes.kaiba.addListeDialogueEvenement(Evenements.dialogue_avant_combat_kaiba, "Faisont un duel.", 1);
        Duelistes.yugi.addListeDialogueEvenement(Evenements.dialogue_avant_combat_kaiba, "ok.", 2);
        Evenements.dialogue_avant_combat_kaiba.ajouterGens(Duelistes.yugi);
        Evenements.dialogue_avant_combat_kaiba.ajouterGens(Duelistes.kaiba);
        /* FIN Evenement Chapitre 1 : Quete depart : evenement debut */
    }

    private void creationEvenementCombat() {
        EvenementDialogue e1 = new EvenementDialogue();
        Duelistes.joey.addListeDialogueEvenement(e1, "Aujourd'hui je vais gagner", 1);
        e1.ajouterGens(Duelistes.joey);
        
        EvenementDialogue e2d = new EvenementDialogue();
        Duelistes.yugi.addListeDialogueEvenement(e2d, "Au début de son tour, le dueliste pioche 1 carte dans son deck", 1);
        Duelistes.yugi.addListeDialogueEvenement(e2d, "Les cartes sont divisé en 3 catégories : les cartes monstres, les cartes magies et les cartes pièges.", 2);
        Duelistes.yugi.addListeDialogueEvenement(e2d, "Pour commencer je vais jouer la carte " + Cartes.dragon_koumori.getNom() + " en mode defense.", 3);
        e2d.ajouterGens(Duelistes.yugi);
        
        EvenementDialogue e2w = new EvenementDialogue();
        Duelistes.yugi.addListeDialogueEvenement(e2w, "Je vais utiliser " + Cartes.dragon_koumori.getNom() + " en mode defense.", 1);
        e2w.ajouterGens(Duelistes.yugi);
        
        EvenementDialogue e3d = new EvenementDialogue();
        Duelistes.yugi.addListeDialogueEvenement(e3d, "Lorsque tu attaques un monstre en mode défense qui a plus de défense que ton monstre,", 1);
        Duelistes.yugi.addListeDialogueEvenement(e3d, "Tu pert des points de vie équivalent à la différence entre ton attaque et la défense de son monstre.", 2);
        Duelistes.yugi.addListeDialogueEvenement(e3d, "Je vais à présent attaquer son monstre avec mon " + Cartes.dragon_koumori.getNom(), 3);
        Duelistes.yugi.addListeDialogueEvenement(e3d, "Je vais ensuite poser mon " + Cartes.crochet_argent.getNom() + " en mode défense", 4);
        e3d.ajouterGens(Duelistes.yugi);
        
        EvenementDialogue e3w = new EvenementDialogue();
        Duelistes.yugi.addListeDialogueEvenement(e3w, "Je vais à présent attaquer son monstre avec mon " + Cartes.dragon_koumori.getNom(), 1);
        e3w.ajouterGens(Duelistes.yugi);
        
        EvenementDialogue e4d = new EvenementDialogue();
        Duelistes.yugi.addListeDialogueEvenement(e4d, "Lorsque 2 monstre ayant une même force s'affronte, alors les 2 sont envoyés au cimetières.", 1);
        Duelistes.yugi.addListeDialogueEvenement(e4d, "Bien a moi de jouer.", 2);
        e4d.ajouterGens(Duelistes.yugi);
        
        EvenementDialogue e5d = new EvenementDialogue();
        Duelistes.yugi.addListeDialogueEvenement(e5d, "Lorsque un monstre détruit un autre monstre en mode défense, celui-ci ne pert pas de point de vie.", 1);
        Duelistes.yugi.addListeDialogueEvenement(e5d, "Bien il est temps de finir ce duel.", 2);
        e5d.ajouterGens(Duelistes.yugi);
        
        Evenements.combat_regle1.ajouterAdversaire(Duelistes.joey);
        Evenements.combat_regle1.ajouterEvenementDialogue(e1, new ConditionMelange(), null);
        Evenements.combat_regle1.ajouterEvenementDialogue(e2d, new ConditionChoixCarte(Cartes.dragon_koumori, Mode.defense, Visibilite.cache), e2w);
        Evenements.combat_regle1.ajouterEvenementDialogue(null, new ConditionFinDeTour(), null);
        Evenements.combat_regle1.ajouterEvenementDialogue(e3d, new ConditionAttaque(), e3w);
        Evenements.combat_regle1.ajouterEvenementDialogue(e4d, null, null);
        Evenements.combat_regle1.ajouterEvenementDialogue(e5d, null, null);
        
        
        Evenements.combat_regle2.ajouterAdversaire(Duelistes.tea);
        Evenements.combat_regle2.ajouterEvenementDialogue(null, new ConditionAttaque(), null);
        Evenements.combat_regle3.ajouterAdversaire(Duelistes.kaiba);
        Evenements.combat_regle3.ajouterEvenementDialogue(null, new ConditionAttaque(), null);
    }

    private void creationEvenementChoix() {
    }

    private void creationEvenementSpecial() {
    }
    
}
