/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.demarrage;

import mfiari.yugioh.game.carte.Action;
import mfiari.yugioh.game.carte.Cartes;
import mfiari.yugioh.game.effet.Effets;

/**
 *
 * @author mike
 */
public class CreationCarte {
    
    public CreationCarte () {
        this.creationCarteMonstreEffet();
        this.creationCarteMonstreRituel();
        this.creationCarteMagie();
        this.creationCartePiege();
    }
    
    private void creationCarteMonstreEffet () {
        Cartes.acid_crawler.setEffet(Effets.acid_crawler);
        Cartes.air_eater.setEffet(Effets.air_eater);
        Cartes.air_eater.setAction(Action.invocation);
        Cartes.ameba.setEffet(Effets.ameba);
        Cartes.ameba.setAction(Action.controle);
        Cartes.amphibious_bugroth_mk3.setEffet(Effets.amphibious_bugroth_mk3);
        Cartes.amphibious_bugroth_mk3.setAction(Action.manuellement);
        Cartes.arlownay.setEffet(Effets.arlownay);
        Cartes.arlownay.setAction(Action.cimitiere_himself);
        Cartes.armed_ninja.setEffet(Effets.armed_ninja);
        Cartes.armed_ninja.setAction(Action.manuellement);
        Cartes.banisher_of_the_light.setEffet(Effets.banisher_of_the_light);
        Cartes.banisher_of_the_light.setAction(Action.cimetiere);
        Cartes.barrel_dragon.setEffet(Effets.barrel_dragon);
        Cartes.barrel_dragon.setAction(Action.tour_perso);
        Cartes.beastking_of_the_swamps.setEffet(Effets.beastking_of_the_swamps);
        Cartes.beastking_of_the_swamps.setAction(Action.manuellement);
        Cartes.big_eye.setAction(Action.manuellement);
        Cartes.big_eye.setEffet(Effets.big_eye);
        Cartes.binding_chain.setAction(Action.piege);
        Cartes.binding_chain.setEffet(Effets.binding_chain);
        Cartes.bladefly.setAction(Action.invocation);
        Cartes.bladefly.setEffet(Effets.bladefly);
        Cartes.blast_juggler.setEffet(Effets.blast_juggler);
        Cartes.blast_juggler.setAction(Action.manuellement);
        Cartes.blast_sphere.setAction(Action.attaque);
        Cartes.blast_sphere.setEffet(Effets.blast_sphere);
        Cartes.bolt_escargot.setEffet(Effets.bolt_escargot);
        Cartes.bolt_escargot.setAction(Action.manuellement);
        Cartes.boar_soldier.setAction(Action.invocation);
        Cartes.boar_soldier.setEffet(Effets.boar_soldier);
        Cartes.buster_blader.setAction(Action.invocation);
        Cartes.buster_blader.setEffet(Effets.buster_blader);
        Cartes.candle_of_fate.setEffet(Effets.candle_of_fate);
        Cartes.candle_of_fate.setAction(Action.manuellement);
        Cartes.cannon_soldier.setAction(Action.manuellement);
        Cartes.cannon_soldier.setEffet(Effets.cannon_soldier);
        Cartes.castle_of_dark_illusion.setAction(Action.manuellement);
        Cartes.castle_of_dark_illusion.setEffet(Effets.castle_of_dark_illusions);
        Cartes.catapult_turtle.setAction(Action.manuellement);
        Cartes.catapult_turtle.setEffet(Effets.catapult_turtle);
        Cartes.ceremonial_bell.setAction(Action.invocation);
        Cartes.ceremonial_bell.setEffet(Effets.ceremonial_bell);
        Cartes.charmeur_de_dragon.setEffet(Effets.charmeur_de_dragon);
        Cartes.charmeur_de_dragon.setAction(Action.manuellement);
        Cartes.cockroach_knight.setEffet(Effets.cockroach_knight);
        Cartes.cockroach_knight.setAction(Action.cimitiere_himself);
        Cartes.cocon_de_evolution.setEffet(Effets.cocon_de_evolution);
        Cartes.cocon_de_evolution.setAction(Action.manuellement);
        Cartes.crass_clown.setEffet(Effets.crass_clown);
        Cartes.crass_clown.setAction(Action.manuellement);
        Cartes.cyber_jar.setEffet(Effets.cyber_jar);
        Cartes.cyber_jar.setAction(Action.manuellement);
        Cartes.cyber_stein.setEffet(Effets.cyber_stein);
        Cartes.cyber_stein.setAction(Action.manuellement);
        Cartes.fille_malheureuse.setEffet(Effets.fille_malheureuse);
        Cartes.fille_malheureuse.setAction(Action.cimitiere_himself);
        Cartes.greath_moth.setEffet(Effets.great_moth);
        Cartes.greath_moth.setAction(Action.invocation);
        Cartes.kuriboh.setEffet(Effets.kuriboh);
        Cartes.kuriboh.setAction(Action.attaque);
        Cartes.larve_de_mite.setEffet(Effets.larvae_moth);
        Cartes.larve_de_mite.setAction(Action.invocation);
        Cartes.le_bouclier.setEffet(Effets.bouclier);
        Cartes.le_bouclier.setAction(Action.magie);
        Cartes.magicien_du_temps.setEffet(Effets.time_magic);
        Cartes.magicien_du_temps.setAction(Action.manuellement);
        Cartes.magicienne_des_tenebres.setEffet(Effets.magicienne_des_tenebres);
        Cartes.magicienne_des_tenebres.setAction(Action.invocation);
        Cartes.perfectly_ultimate_great_moth.setEffet(Effets.perfectly_ultimate_great_moth);
        Cartes.perfectly_ultimate_great_moth.setAction(Action.invocation);
        Cartes.sangan.setEffet(Effets.sangan);
        Cartes.sangan.setAction(Action.cimitiere_himself);
        Cartes.valkyrion_guerrier_magnetique.setEffet(Effets.valkyrion_the_magna_warrior);
        Cartes.valkyrion_guerrier_magnetique.setAction(Action.invocation);
    }
    
    private void creationCarteMonstreRituel () {
        /*Cartes.abandonne.setEffet(Effets.acid_crawler);
        Cartes.abandonne.setAction(Action.manuellement);*/
    }
    
    private void creationCarteMagie () {
        Cartes.ancient_telescope.ajouterEffet(Effets.telescope);
        Cartes.black_illusion_ritual.ajouterEffet(Effets.black_illusion_ritual);
        Cartes.black_pendant.ajouterEffet(Effets.black_pendant);
        Cartes.block_attack.ajouterEffet(Effets.block_attack);
        Cartes.bouclier_et_epee.ajouterEffet(Effets.bouclier_et_epee);
        Cartes.breath_of_light.ajouterEffet(Effets.breath_of_light);
        Cartes.chain_energy.ajouterEffet(Effets.chain_energy);
        Cartes.confiscation.ajouterEffet(Effets.confiscation);
        Cartes.curse_of_fiend.ajouterEffet(Effets.curse_of_fiend);
        Cartes.de_de_ange.ajouterEffet(Effets.de_de_ange);
        Cartes.echange_de_coeur.ajouterEffet(Effets.echange_de_coeur);
        Cartes.eclair.ajouterEffet(Effets.destroy_all_oponent_monstre);
        Cartes.epee_de_lumiere_revelatrice.ajouterEffet(Effets.epee_de_lumiere_revelatrice);
        Cartes.makiu_brume_mystique.ajouterEffet(Effets.makiu);
        Cartes.polymerisation.ajouterEffet(Effets.polymerisation);
        Cartes.resurection_du_monstre.ajouterEffet(Effets.resurection_du_monstre);
        Cartes.stop_defense.ajouterEffet(Effets.stop_defense);
        Cartes.the_seal_of_orichalcos.ajouterEffet(Effets.the_seal_oh_orichalcos);
        Cartes.toon_table_of_contents.ajouterEffet(Effets.toon_table_of_contents);
        Cartes.toon_world.ajouterEffet(Effets.toon_world);
        Cartes.trou_noir.ajouterEffet(Effets.destroy_all_monstre);
    }
    
    private void creationCartePiege () {
        Cartes.acid_trap_hole.setEffet(Effets.acid_trap_hole);
        Cartes.acid_trap_hole.setAction(Action.manuellement);
        Cartes.anti_raigeki.setEffet(Effets.anti_eclair);
        Cartes.anti_raigeki.setAction(Action.magie);
        Cartes.anti_spell_fragrance.setEffet(Effets.anti_spell_fragrance);
        Cartes.anti_spell_fragrance.setAction(Action.manuellement);
        Cartes.appropriate.setEffet(Effets.appropriate);
        Cartes.appropriate.setAction(Action.pioche);
        Cartes.aqua_chorus.setEffet(Effets.aqua_chorus);
        Cartes.aqua_chorus.setAction(Action.manuellement);
        Cartes.armored_glass.setEffet(Effets.armored_glass);
        Cartes.armored_glass.setAction(Action.equippement);
        Cartes.backup_soldier.setEffet(Effets.backup_soldier);
        Cartes.backup_soldier.setAction(Action.manuellement);
        Cartes.call_of_darkness.setEffet(Effets.call_of_darkness);
        Cartes.call_of_darkness.setAction(Action.manuellement);
        Cartes.call_of_the_grave.setEffet(Effets.call_of_the_grave);
        Cartes.call_of_the_grave.setAction(Action.magie);
        Cartes.call_of_the_haunted.setEffet(Effets.call_of_the_haunted);
        Cartes.call_of_the_haunted.setAction(Action.manuellement);
        Cartes.castle_walls.setEffet(Effets.castle_walls);
        Cartes.castle_walls.setAction(Action.manuellement);
        Cartes.ceasefire.setEffet(Effets.ceasefire);
        Cartes.ceasefire.setAction(Action.manuellement);
        Cartes.chain_destruction.setEffet(Effets.chain_destruction);
        Cartes.chain_destruction.setAction(Action.invocation);
        Cartes.chapeau_magique.setEffet(Effets.chapeau_magique);
        Cartes.chapeau_magique.setAction(Action.attaque);
        Cartes.crush_card_virus.setEffet(Effets.crush_card_virus);
        Cartes.crush_card_virus.setAction(Action.manuellement);
        Cartes.de_du_demon.setEffet(Effets.de_du_demon);
        Cartes.de_du_demon.setAction(Action.attaque);
        Cartes.force_mirroire.setEffet(Effets.destroy_oponent_attack_monstre);
        Cartes.force_mirroire.setAction(Action.attaque);
        Cartes.jarre_capture_de_dragon.setEffet(Effets.jarre_capture_de_dragon);
        Cartes.jarre_capture_de_dragon.setAction(Action.manuellement);
        Cartes.kunai_with_chaine.setEffet(Effets.kunai_with_chaine);
        Cartes.kunai_with_chaine.setAction(Action.attaque);
        Cartes.toon_defense.setEffet(Effets.toon_defense);
        Cartes.toon_defense.setAction(Action.attaque);
    }
    
}
