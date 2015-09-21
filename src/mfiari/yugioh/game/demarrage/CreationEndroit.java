/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.demarrage;

import mfiari.yugioh.game.ville.Endroits;

/**
 *
 * @author mike
 */
public class CreationEndroit {

    public CreationEndroit() {
        this.modificationSalle();
        this.modificationBatiment();
        this.modificationQuartier();
        this.modificationQuartierRoute();
        this.modificationQuartierForet();
        this.modificationVille();
        this.modificationSousZone();
        this.modificationZone();
        this.modificationPays();
    }

    private void modificationSalle() {
        
    }

    private void modificationBatiment() {
        Endroits.maisonYugi_quartierHabitant_villeDebut.ajouterSalle(Endroits.bas_maisonYugi_quartierHabitant_villeDebut, 0);
        Endroits.maisonYugi_quartierHabitant_villeDebut.ajouterSalle(Endroits.chambre_maisonYugi_quartierHabitant_villeDebut, 1);
        Endroits.ecole_quartierEcole_villeDebut.ajouterSalle(Endroits.salleCours_ecole_quartierEcole_villeDebut, 0);
    }

    private void modificationQuartier() {
        Endroits.quartierEcole_villeDebut.ajouterBatiment(Endroits.ecole_quartierEcole_villeDebut);
        Endroits.quartierHabitant_villeDebut.ajouterBatiment(Endroits.maisonYugi_quartierHabitant_villeDebut);
    }

    private void modificationQuartierRoute() {
    }

    private void modificationQuartierForet() {
    }

    private void modificationVille() {
        Endroits.villeDebut.ajouterQuartier(Endroits.quartierEcole_villeDebut);
        Endroits.villeDebut.ajouterQuartier(Endroits.quartierHabitant_villeDebut);
        Endroits.villeDebut.ajouterQuartier(Endroits.quartierKaibaCorp_villeDebut);
        Endroits.villeDebut.ajouterQuartier(Endroits.quartierTournoi_villeDebut);
    }

    private void modificationSousZone() {
        Endroits.zoneVilleDebut_zoneDebut_japon.ajouterVille(Endroits.villeDebut);
    }

    private void modificationZone() {
        Endroits.zoneDebut_japon.ajouterSousZone(Endroits.zoneVilleDebut_zoneDebut_japon);
    }

    private void modificationPays() {
        Endroits.japon.ajouterZone(Endroits.zoneDebut_japon);
    }
    
}
