/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.evenement;

import mfiari.lib.game.evenements.EvenementDialogue;
import mfiari.lib.game.evenements.EvenementQuete;
import mfiari.lib.game.position.Position;
import mfiari.yugioh.game.ville.Endroits;

/**
 *
 * @author mike
 */
public class Evenements {
    
    public static EvenementQuete jeu = new EvenementQuete("jeu");
    public static EvenementQuete chapitre1 = new EvenementQuete("chapitre1");
    public static EvenementQuete depart = new EvenementQuete(false, new Position(Endroits.ecole_quartierEcole_villeDebut), null, null, "le depart");
    public static EvenementDialogue debut = new EvenementDialogue(new Position(Endroits.salleCours_ecole_quartierEcole_villeDebut), null, 
            null, "le depart_1");
    public static EvenementCombat combat_regle1 = new EvenementCombat(new Position(Endroits.salleCours_ecole_quartierEcole_villeDebut), null, 
            null, "le depart_2", null);
    public static EvenementDialogue rencontre_avec_kaiba = new EvenementDialogue(new Position(Endroits.bas_maisonYugi_quartierHabitant_villeDebut), null, 
            null, "le depart_3");
    public static EvenementCombat combat_regle2 = new EvenementCombat(new Position(Endroits.salleCours_ecole_quartierEcole_villeDebut), null, 
            null, "le depart_4", null);
    public static EvenementDialogue invitation_kaiba = new EvenementDialogue(new Position(Endroits.bas_maisonYugi_quartierHabitant_villeDebut), null, 
            null, "le depart_5");
    public static EvenementDialogue dialogue_avant_combat_kaiba = new EvenementDialogue(new Position(Endroits.bas_kaibaCorps_quartierKaibaCorp_villeDebut), 
            null, null, "le depart_6");
    public static EvenementCombat combat_regle3 = new EvenementCombat(new Position(Endroits.bas_kaibaCorps_quartierKaibaCorp_villeDebut), null, 
            null, "le depart_7", null);
    
}
