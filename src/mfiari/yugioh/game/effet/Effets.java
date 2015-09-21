/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.effet;

import java.util.ArrayList;
import mfiari.yugioh.game.carte.Carte;
import mfiari.yugioh.game.carte.CarteMonstre;
import mfiari.yugioh.game.carte.CartePiege;
import mfiari.yugioh.game.carte.Cartes;
import mfiari.yugioh.game.carte.Categorie;
import mfiari.yugioh.game.carte.Mode;
import mfiari.yugioh.game.carte.Resultat;
import mfiari.yugioh.game.carte.Visibilite;
import mfiari.yugioh.game.combat.AbstractCombat;
import mfiari.yugioh.game.liste.Deck;
import mfiari.yugioh.game.liste.ListeDeCarte;
import mfiari.yugioh.game.liste.PlateauDeCarte;

/**
 *
 * @author mike
 */
public class Effets {
    
    /* Carte monstre */
    
    public static Effet abandonne = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet acid_crawler = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet air_eater = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            for (int i = 0; i < 5; i++) {
                CarteMonstre monstre = combat.getPlateauDeCartePerso1().getCarteMonsre(i);
                if (monstre != null && !monstre.equals(Cartes.air_eater)) {
                    combat.getPlateauDeCartePerso1().retirerCarteMonstre(i);
                }
                monstre = combat.getPlateauDeCartePerso2().getCarteMonsre(i);
                if (monstre != null && !monstre.equals(Cartes.air_eater)) {
                    combat.getPlateauDeCartePerso2().retirerCarteMonstre(i);
                }
            }
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet aligator_sword_dragon = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet ameba = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet amphibious_bugroth_mk3 = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet arlownay = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet armed_ninja = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet banisher_of_the_light = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet barrel_dragon = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet beastking_of_the_swamps = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet big_eye = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet binding_chain = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet bladefly = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet blast_juggler = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet blast_sphere = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet boar_soldier = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet bolt_escargot = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet bouclier = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet buster_blader = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet candle_of_fate = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet cannon_soldier = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet castle_of_dark_illusions = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet catapult_turtle = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet ceremonial_bell = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet charmeur_de_dragon = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            boolean jarreTrouve = false;
            for (int i = 0; i < 5; i++) {
                CartePiege piege = combat.getPlateauDeCartePerso1().getCartePiege(i);
                if (piege.equals(Cartes.jarre_capture_de_dragon)) {
                    jarreTrouve = true;
                    combat.getPlateauDeCartePerso1().retirerCarteMagique(i);
                }
                piege = combat.getPlateauDeCartePerso2().getCartePiege(i);
                if (piege.equals(Cartes.jarre_capture_de_dragon)) {
                    jarreTrouve = true;
                    combat.getPlateauDeCartePerso2().retirerCarteMagique(i);
                }
            }
            if (jarreTrouve) {
                for (int i = 0; i < 5; i++) {
                    CarteMonstre monstre = combat.getPlateauDeCartePerso1().getCarteMonsre(i);
                    if (monstre.getCategorie() == Categorie.dragon && combat.getPlateauDeCartePerso1().getVisibiliteCarteMagie(i) == Visibilite.visible) {
                        combat.getPlateauDeCartePerso1().changerModeCarteMonsre(i, Mode.attaque);
                    }
                    monstre = combat.getPlateauDeCartePerso2().getCarteMonsre(i);
                    if (monstre.getCategorie() == Categorie.dragon && combat.getPlateauDeCartePerso2().getVisibiliteCarteMagie(i) == Visibilite.visible) {
                        combat.getPlateauDeCartePerso2().changerModeCarteMonsre(i, Mode.attaque);
                    }
                }
            }
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet cockroach_knight = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet cocon_de_evolution = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet crass_clown = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet cyber_jar = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet cyber_stein = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet dark_magician_girl_dragon_rider = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet fille_malheureuse = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet great_moth = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet kuriboh = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet larvae_moth = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet magicienne_des_tenebres = new Effet("") {
        private int nbMagicien = 0;
        @Override
        public Resultat run(AbstractCombat combat) {
            this.annule(combat);
            this.nbMagicien = 0;
            ListeDeCarte cimetiere = combat.getCimetierePerso1();
            for (int i = 0 ; i < cimetiere.size() ; i++) {
                if (cimetiere.getCarte(i) instanceof CarteMonstre) {
                    CarteMonstre monstre = (CarteMonstre) cimetiere.getCarte(i);
                    if (monstre.equals(Cartes.magicien_des_tenebres)) {
                        this.nbMagicien++;
                    }
                }
            }
            cimetiere = combat.getCimetierePerso2();
            for (int i = 0 ; i < cimetiere.size() ; i++) {
                if (cimetiere.getCarte(i) instanceof CarteMonstre) {
                    CarteMonstre monstre = (CarteMonstre) cimetiere.getCarte(i);
                    if (monstre.equals(Cartes.magicien_des_tenebres)) {
                        this.nbMagicien++;
                    }
                }
            }
            CarteMonstre magicienne_des_tenebres = (CarteMonstre) this.getCarte();
            magicienne_des_tenebres.setForce(magicienne_des_tenebres.getForce()+(this.nbMagicien*300));
            magicienne_des_tenebres.setDef(magicienne_des_tenebres.getDef()+(this.nbMagicien*300));
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
            CarteMonstre magicienne_des_tenebres = (CarteMonstre) this.getCarte();
            magicienne_des_tenebres.setForce(magicienne_des_tenebres.getForce()-(this.nbMagicien*300));
            magicienne_des_tenebres.setDef(magicienne_des_tenebres.getDef()-(this.nbMagicien*300));
        }
    };
    
    public static Effet perfectly_ultimate_great_moth = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet sangan = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet thousand_eyes_restrict = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet time_magic = new Effet("time magic") {
        @Override
        public Resultat run(AbstractCombat combat) {
            int pileFace = (int) (Math.random() * 2 + 1);
            PlateauDeCarte p1;
            PlateauDeCarte p2;
            if (pileFace == 1) {
                if (combat.tourJ1()) {
                    p1 = combat.getPlateauDeCartePerso2();
                    p2 = combat.getPlateauDeCartePerso1();
                } else {
                    p1 = combat.getPlateauDeCartePerso1();
                    p2 = combat.getPlateauDeCartePerso2();
                }
                for (int i = 0; i < 5; i++) {
                    p1.retirerCarteMonstre(i);
                }
                for (int i = 0; i < 5; i++) {
                    CarteMonstre monstre = p2.getCarteMonsre(i);
                    if (monstre != null && monstre.equals(Cartes.bebe_dragon)) {
                        p2.retirerCarteMonstre(i);
                        p2.poserCarteMonstre(Cartes.dragon_milenaire, Visibilite.visible, Mode.attaque);
                    }
                }
            } else {
                if (combat.tourJ1()) {
                    p1 = combat.getPlateauDeCartePerso1();
                } else {
                    p1 = combat.getPlateauDeCartePerso2();
                }
                for (int i = 0; i < 5; i++) {
                    p1.retirerCarteMonstre(i);
                }
            }
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet toon_dark_magician_girl_dragon_rider = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet valkyrion_the_magna_warrior = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    /* Carte magie */
  
    public static Effet axe_of_despair = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
  
    public static Effet black_illusion_ritual = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
  
    public static Effet black_pendant = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
  
    public static Effet block_attack = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet bouclier_et_epee = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            for (int i = 0; i < 5; i++) {
                CarteMonstre monstre = combat.getPlateauDeCartePerso1().getCarteMonsre(i);
                if (monstre != null) {
                    int def = monstre.getDef();
                    monstre.setDef(monstre.getForce());
                    monstre.setForce(def);
                }
                monstre = combat.getPlateauDeCartePerso2().getCarteMonsre(i);
                if (monstre != null) {
                    int def = monstre.getDef();
                    monstre.setDef(monstre.getForce());
                    monstre.setForce(def);
                }
            }
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
            for (int i = 0; i < 5; i++) {
                CarteMonstre monstre = combat.getPlateauDeCartePerso1().getCarteMonsre(i);
                if (monstre != null) {
                    int def = monstre.getDef();
                    monstre.setDef(monstre.getForce());
                    monstre.setForce(def);
                }
                monstre = combat.getPlateauDeCartePerso2().getCarteMonsre(i);
                if (monstre != null) {
                    int def = monstre.getDef();
                    monstre.setDef(monstre.getForce());
                    monstre.setForce(def);
                }
            }
        }
    };
    
    public static Effet breath_of_light = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet chain_energy = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet chorus_of_sanctuary = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet completed = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet confiscation = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet curse_of_fiend = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    
    public static Effet destroy_all_monstre = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            for (int i = 0; i < 5; i++) {
                combat.getPlateauDeCartePerso1().retirerCarteMonstre(i);
                combat.getPlateauDeCartePerso2().retirerCarteMonstre(i);
            }
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet destroy_oponent_attack_monstre = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            if (combat.tourJ1()) {
                for (int i = 0; i < 5; i++) {
                    CarteMonstre monstre = combat.getPlateauDeCartePerso1().getCarteMonsre(i);
                    if (monstre != null && combat.getPlateauDeCartePerso1().getModeCarteMonsre(i) == Mode.attaque) {
                        combat.getPlateauDeCartePerso1().retirerCarteMonstre(i);
                        combat.afficheTexte(monstre.getNom() + " est détruit.");
                    }
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    CarteMonstre monstre = combat.getPlateauDeCartePerso2().getCarteMonsre(i);
                    if (monstre != null && combat.getPlateauDeCartePerso2().getModeCarteMonsre(i) == Mode.attaque) {
                        combat.getPlateauDeCartePerso2().retirerCarteMonstre(i);
                        combat.afficheTexte(monstre.getNom() + " est détruit.");
                    }
                }
            }
            return Resultat.bloque_att;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet de_de_ange = new Effet("") {
        private int augmentation;
        @Override
        public Resultat run(AbstractCombat combat) {
            int faceDe = (int) (Math.random() * 6 + 1);
            this.augmentation = faceDe*100;
            if (combat.tourJ1()) {
                for (int i = 0; i < 5; i++) {
                    CarteMonstre monstre = combat.getPlateauDeCartePerso1().getCarteMonsre(i);
                    if (monstre != null) {
                        monstre.setForce(monstre.getForce()+augmentation);
                        monstre.setDef(monstre.getDef()+augmentation);
                    }
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    CarteMonstre monstre = combat.getPlateauDeCartePerso2().getCarteMonsre(i);
                    if (monstre != null) {
                        monstre.setForce(monstre.getForce()+augmentation);
                        monstre.setDef(monstre.getDef()+augmentation);
                    }
                }
            }
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
            if (combat.tourJ1()) {
                for (int i = 0; i < 5; i++) {
                    CarteMonstre monstre = combat.getPlateauDeCartePerso1().getCarteMonsre(i);
                    if (monstre != null) {
                        monstre.setForce(monstre.getForce()-augmentation);
                        monstre.setDef(monstre.getDef()-augmentation);
                    }
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    CarteMonstre monstre = combat.getPlateauDeCartePerso2().getCarteMonsre(i);
                    if (monstre != null) {
                        monstre.setForce(monstre.getForce()-augmentation);
                        monstre.setDef(monstre.getDef()-augmentation);
                    }
                }
            }
        }
    };
    
    public static Effet destroy_all_oponent_monstre = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            if (combat.tourJ1()) {
                for (int i = 0; i < 5; i++) {
                    combat.getPlateauDeCartePerso2().retirerCarteMonstre(i);
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    combat.getPlateauDeCartePerso1().retirerCarteMonstre(i);
                }
            }
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet echange_de_coeur = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet epee_de_lumiere_revelatrice = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet generateur_energie_negative = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet makiu = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet polymerisation = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet resurection_du_monstre = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            if (combat.tourJ1()) {
                ListeDeCarte cimetiere = combat.getCimetierePerso1();
                for (int i = cimetiere.size()-1 ; i >= 0 ; i--) {
                    if (cimetiere.getCarte(i) instanceof CarteMonstre) {
                        CarteMonstre monstre = (CarteMonstre) cimetiere.getCarte(i);
                        combat.getPlateauDeCartePerso1().poserCarteMonstre(monstre, Visibilite.visible, Mode.attaque);
                    }
                }
            } else {
                ListeDeCarte cimetiere = combat.getCimetierePerso2();
                for (int i = cimetiere.size()-1 ; i >= 0 ; i--) {
                    if (cimetiere.getCarte(i) instanceof CarteMonstre) {
                        CarteMonstre monstre = (CarteMonstre) cimetiere.getCarte(i);
                        combat.getPlateauDeCartePerso2().poserCarteMonstre(monstre, Visibilite.visible, Mode.attaque);
                    }
                }
            }
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet stop_defense = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet telescope = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            if (combat.tourJ1()) {
                Deck deck = combat.getPerso2().getDeck();
                if (deck.size() > 5) {
                    for (int i = 0 ; i < 5 ; i++) {
                        Carte c = deck.getCarte(i);
                    }
                } else {
                    for (int i = 0 ; i < deck.size() ; i++) {
                        Carte c = deck.getCarte(i);
                    }
                }
            } else {
            }
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet the_seal_oh_orichalcos = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet toon_table_of_contents = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet toon_world = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    /* Carte piège */
    
    public static Effet acid_trap_hole = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet anti_eclair = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            if (combat.getMagieActive() != null && combat.getMagieActive().equals(Cartes.eclair)) {
                if (combat.tourJ1()) {
                    for (int i = 0; i < 5; i++) {
                        CarteMonstre monstre = combat.getPlateauDeCartePerso2().getCarteMonsre(i);
                        if (monstre != null) {
                            combat.getPlateauDeCartePerso2().retirerCarteMonstre(i);
                        }
                    }
                } else {
                    for (int i = 0; i < 5; i++) {
                        CarteMonstre monstre = combat.getPlateauDeCartePerso1().getCarteMonsre(i);
                        if (monstre != null) {
                            combat.getPlateauDeCartePerso1().retirerCarteMonstre(i);
                        }
                    }
                }
            }
            return Resultat.bloque_magie;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet anti_spell_fragrance = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet appropriate = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet aqua_chorus = new Effet("") {
        private ArrayList<CarteMonstre> monstreDejaAffecte = new ArrayList<>();
        @Override
        public Resultat run(AbstractCombat combat) {
            this.annule(combat);
            for (int i = 0; i < 5; i++) {
                CarteMonstre monstre = combat.getPlateauDeCartePerso1().getCarteMonsre(i);
                if (monstre != null) {
                    for (int j = i+1 ; j < 5 ; j++) {
                        CarteMonstre monstre2 = combat.getPlateauDeCartePerso1().getCarteMonsre(j);
                        if (monstre2 != null && monstre.getNom().equals(monstre2.getNom())) {
                            if (!monstreDejaAffecte.contains(monstre)) {
                                monstre.setForce(monstre.getForce()+500);
                                monstre.setDef(monstre.getDef()+500);
                                this.monstreDejaAffecte.add(monstre);
                            }
                            if (!monstreDejaAffecte.contains(monstre2)) {
                                monstre2.setForce(monstre2.getForce()+500);
                                monstre2.setDef(monstre2.getDef()+500);
                                this.monstreDejaAffecte.add(monstre2);
                            }
                        }
                    }
                    for (int j = 0 ; j < 5 ; j++) {
                        CarteMonstre monstre2 = combat.getPlateauDeCartePerso2().getCarteMonsre(j);
                        if (monstre2 != null && monstre.getNom().equals(monstre2.getNom())) {
                            if (!monstreDejaAffecte.contains(monstre)) {
                                monstre.setForce(monstre.getForce()+500);
                                monstre.setDef(monstre.getDef()+500);
                                this.monstreDejaAffecte.add(monstre);
                            }
                            if (!monstreDejaAffecte.contains(monstre2)) {
                                monstre2.setForce(monstre2.getForce()+500);
                                monstre2.setDef(monstre2.getDef()+500);
                                this.monstreDejaAffecte.add(monstre2);
                            }
                        }
                    }
                }
                monstre = combat.getPlateauDeCartePerso2().getCarteMonsre(i);
                if (monstre != null) {
                    for (int j = i+1 ; j < 5 ; j++) {
                        CarteMonstre monstre2 = combat.getPlateauDeCartePerso2().getCarteMonsre(j);
                        if (monstre2 != null && monstre.getNom().equals(monstre2.getNom())) {
                            if (!monstreDejaAffecte.contains(monstre)) {
                                monstre.setForce(monstre.getForce()+500);
                                monstre.setDef(monstre.getDef()+500);
                                this.monstreDejaAffecte.add(monstre);
                            }
                            if (!monstreDejaAffecte.contains(monstre2)) {
                                monstre2.setForce(monstre2.getForce()+500);
                                monstre2.setDef(monstre2.getDef()+500);
                                this.monstreDejaAffecte.add(monstre2);
                            }
                        }
                    }
                }
            }
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
            for (int i = 0 ; i < this.monstreDejaAffecte.size() ; i++) {
                CarteMonstre monstre = this.monstreDejaAffecte.get(i);
                monstre.setForce(monstre.getForce()-500);
                monstre.setDef(monstre.getDef()-500);
            }
        }
    };
    
    public static Effet armored_glass = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet backup_soldier = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet call_of_darkness = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet call_of_the_haunted = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet call_of_the_grave = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet castle_walls = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet ceasefire = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet chain_destruction = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet chapeau_magique = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet crush_card_virus = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet de_du_demon = new Effet("") {
        private int diminution;
        @Override
        public Resultat run(AbstractCombat combat) {
            int faceDe = (int) (Math.random() * 6 + 1);
            this.diminution = faceDe*100;
            if (!combat.tourJ1()) {
                for (int i = 0; i < 5; i++) {
                    CarteMonstre monstre = combat.getPlateauDeCartePerso2().getCarteMonsre(i);
                    if (monstre != null && combat.getPlateauDeCartePerso2().getVisibiliteCarteMonsre(i) == Visibilite.visible) {
                        monstre.setForce(monstre.getForce()-diminution);
                        monstre.setDef(monstre.getDef()-diminution);
                    }
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    CarteMonstre monstre = combat.getPlateauDeCartePerso1().getCarteMonsre(i);
                    if (monstre != null && combat.getPlateauDeCartePerso1().getVisibiliteCarteMonsre(i) == Visibilite.visible) {
                        monstre.setForce(monstre.getForce()-diminution);
                        monstre.setDef(monstre.getDef()-diminution);
                    }
                }
            }
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
            if (combat.tourJ1()) {
                for (int i = 0; i < 5; i++) {
                    CarteMonstre monstre = combat.getPlateauDeCartePerso2().getCarteMonsre(i);
                    if (monstre != null && combat.getPlateauDeCartePerso2().getVisibiliteCarteMonsre(i) == Visibilite.visible) {
                        monstre.setForce(monstre.getForce()+diminution);
                        monstre.setDef(monstre.getDef()+diminution);
                    }
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    CarteMonstre monstre = combat.getPlateauDeCartePerso1().getCarteMonsre(i);
                    if (monstre != null && combat.getPlateauDeCartePerso1().getVisibiliteCarteMonsre(i) == Visibilite.visible) {
                        monstre.setForce(monstre.getForce()+diminution);
                        monstre.setDef(monstre.getDef()+diminution);
                    }
                }
            }
        }
    };
    
    public static Effet jarre_capture_de_dragon = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            for (int i = 0; i < 5; i++) {
                CarteMonstre monstre = combat.getPlateauDeCartePerso1().getCarteMonsre(i);
                if (monstre.getCategorie() == Categorie.dragon) {
                    combat.getPlateauDeCartePerso1().changerModeCarteMonsre(i, Mode.defense);
                }
                monstre = combat.getPlateauDeCartePerso2().getCarteMonsre(i);
                if (monstre.getCategorie() == Categorie.dragon) {
                    combat.getPlateauDeCartePerso2().changerModeCarteMonsre(i, Mode.defense);
                }
            }
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet kunai_with_chaine = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
    
    public static Effet toon_defense = new Effet("") {
        @Override
        public Resultat run(AbstractCombat combat) {
            return Resultat.aucun;
        }

        @Override
        public void annule(AbstractCombat combat) {
        }
    };
}
