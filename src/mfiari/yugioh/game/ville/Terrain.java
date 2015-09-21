/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.ville;

import java.util.ArrayList;
import mfiari.yugioh.game.carte.Affectation;
import mfiari.yugioh.game.carte.CarteMonstre;
import mfiari.yugioh.game.carte.Categorie;
import mfiari.yugioh.game.carte.TypeMagie;

/**
 *
 * @author mike
 */
public class Terrain {
    
    private String nom;
    private int augmente;
    private int diminue;
    private ArrayList<Categorie> CategoriesAugmente;
    private ArrayList<Categorie> Categoriesdiminues;
    
    public Terrain (String nom, int augmente, int diminue) {
        this.nom = nom;
        this.augmente = augmente;
        this.diminue = diminue;
        this.CategoriesAugmente = new ArrayList<>();
        this.Categoriesdiminues = new ArrayList<>();
    }
    
    public void ajouterCategorieAugmente (Categorie categorie) {
        this.CategoriesAugmente.add(categorie);
    }
    
    public void ajouterCategorieDiminue (Categorie categorie) {
        this.Categoriesdiminues.add(categorie);
    }
    
    public boolean isAugmented (CarteMonstre carte) {
        return this.CategoriesAugmente.contains(carte.getCategorie());
    }
    
    public boolean isDiminued (CarteMonstre carte) {
        return this.CategoriesAugmente.contains(carte.getCategorie());
    }
    
    public void affecte (CarteMonstre carte) {
        if (this.isAugmented(carte)) {
            carte.setForce(carte.getForce() + this.augmente);
            carte.setDef(carte.getDef() + this.augmente);
        } else if (this.isDiminued(carte)) {
            carte.setForce(carte.getForce() - this.augmente);
            carte.setDef(carte.getDef() - this.augmente);
        }
    }
    
    public void annule (CarteMonstre carte) {
        if (this.isAugmented(carte)) {
            carte.setForce(carte.getForce() - this.augmente);
            carte.setDef(carte.getDef() - this.augmente);
        } else if (this.isDiminued(carte)) {
            carte.setForce(carte.getForce() + this.augmente);
            carte.setDef(carte.getDef() + this.augmente);
        }
    }
    
    public int getDiminue () {
        return this.diminue;
    }
    
    public int getAugmente () {
        return this.augmente;
    }
    
    public int nbCategoriesAugmente () {
        return this.CategoriesAugmente.size();
    }
    
    public Categorie getCategoriesAugmente (int i) {
        return this.CategoriesAugmente.get(i);
    }
    
    public int nbCategoriesdiminues () {
        return this.Categoriesdiminues.size();
    }
    
    public Categorie getCategoriesdiminues (int i) {
        return this.Categoriesdiminues.get(i);
    }
    
}
