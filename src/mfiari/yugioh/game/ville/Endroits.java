/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.ville;

import mfiari.lib.game.position.Orientation;
import mfiari.lib.game.ville.Batiment;
import mfiari.lib.game.ville.Magasin;
import mfiari.lib.game.ville.Pays;
import mfiari.lib.game.ville.Quartier;
import mfiari.lib.game.ville.Salle;
import mfiari.lib.game.ville.SousZone;
import mfiari.lib.game.ville.Ville;
import mfiari.lib.game.ville.Zone;

/**
 *
 * @author mike
 */
public class Endroits {
    
    public static Pays japon = new Pays("kanto", 5, 3, 102, 100);
    public static Zone zoneDebut_japon = new Zone("zone de debut", 0, 0, 3, 2);
    public static SousZone zoneVilleDebut_zoneDebut_japon = new SousZone("zone de ville debut", 1, 1, 10, 6);
    
    public static Ville villeDebut = new Ville("ville debut", 10, 11, 1, 3);
    public static Quartier quartierKaibaCorp_villeDebut = new Quartier("quartier kaiba corp ville debut", 0, 0, 11, 8, null);
    public static Batiment kaibaCorps_quartierKaibaCorp_villeDebut = new Batiment("kaiba corp", 2, 3, 1, 1, 0, 1, Orientation.face, 
            Endroits.quartierKaibaCorp_villeDebut);
    public static Salle bas_kaibaCorps_quartierKaibaCorp_villeDebut = new Salle("bas kaiba corp", 0, 0, 10, 7, 
            Endroits.kaibaCorps_quartierKaibaCorp_villeDebut, 0);
    public static Quartier quartierHabitant_villeDebut = new Quartier("quartier habitant ville debut", 0, 0, 11, 8, null);
    public static Quartier quartierEcole_villeDebut = new Quartier("quartier ecole ville debut", 0, 0, 11, 8, null);
    public static Batiment ecole_quartierEcole_villeDebut = new Batiment("ecole", 2, 3, 1, 1, 0, 1, Orientation.face, 
            Endroits.quartierEcole_villeDebut);
    public static Salle salleCours_ecole_quartierEcole_villeDebut = new Salle("salle de cours", 0, 0, 10, 7, 
            Endroits.ecole_quartierEcole_villeDebut, 0);
    public static Quartier quartierTournoi_villeDebut = new Quartier("quartier tournoi ville debut", 0, 0, 11, 8, null);
    public static Magasin maisonYugi_quartierHabitant_villeDebut = new Magasin("maison de Yugi", 2, 3, 1, 1, 1, Orientation.face, 
            Endroits.quartierHabitant_villeDebut);
    public static Salle bas_maisonYugi_quartierHabitant_villeDebut = new Salle("rez de chausse maison Yugi", 0, 0, 10, 7, 
            Endroits.maisonYugi_quartierHabitant_villeDebut, 0);
    public static Salle chambre_maisonYugi_quartierHabitant_villeDebut = new Salle("rez de chausse maison Yugi", 0, 0, 10, 7, 
            Endroits.maisonYugi_quartierHabitant_villeDebut, 1);
    
}
