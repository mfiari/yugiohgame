/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.ville;

import mfiari.yugioh.game.carte.Categorie;

/**
 *
 * @author mike
 */
public class Terrains {
    
    
    public static Terrain terrain_normal = new Terrain("normal", 0, 0);
    public static Terrain terrain_desert = new Terrain("desert", 200, 0);
    public static Terrain terrain_montagne = new Terrain("montagne", 200, 0);
    public static Terrain terrain_foret = new Terrain("foret", 200, 0);
    public static Terrain terrain_mer = new Terrain("mer", 200, 200);
    public static Terrain terrain_prairie = new Terrain("prairie", 200, 0);
    public static Terrain terrain_tenebres = new Terrain("tenebres", 200, 200);
    
    static {
        terrain_desert.ajouterCategorieAugmente(Categorie.dinosaure);
        terrain_desert.ajouterCategorieAugmente(Categorie.zombie);
        terrain_desert.ajouterCategorieAugmente(Categorie.rocher);
        
        terrain_foret.ajouterCategorieAugmente(Categorie.insecte);
        terrain_foret.ajouterCategorieAugmente(Categorie.plante);
        terrain_foret.ajouterCategorieAugmente(Categorie.bete);
        terrain_foret.ajouterCategorieAugmente(Categorie.bete_guerrier);
        
        terrain_mer.ajouterCategorieAugmente(Categorie.poisson);
        terrain_mer.ajouterCategorieAugmente(Categorie.serpent_mer);
        terrain_mer.ajouterCategorieAugmente(Categorie.aqua);
        terrain_mer.ajouterCategorieDiminue(Categorie.feu);
        terrain_mer.ajouterCategorieDiminue(Categorie.machine);
        
        terrain_montagne.ajouterCategorieAugmente(Categorie.dragon);
        terrain_montagne.ajouterCategorieAugmente(Categorie.bete_aile);
        terrain_montagne.ajouterCategorieAugmente(Categorie.tonnerre);
        
        terrain_prairie.ajouterCategorieAugmente(Categorie.guerrier);
        terrain_prairie.ajouterCategorieAugmente(Categorie.bete_guerrier);
        
        terrain_tenebres.ajouterCategorieAugmente(Categorie.magicien);
        terrain_tenebres.ajouterCategorieAugmente(Categorie.demon);
        terrain_tenebres.ajouterCategorieDiminue(Categorie.fee);
    }
    
}
