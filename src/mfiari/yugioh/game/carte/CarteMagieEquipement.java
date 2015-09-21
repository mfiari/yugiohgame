/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.carte;

/**
 *
 * @author mike
 */
public class CarteMagieEquipement extends CarteMagie {
    
    private int bonusAtk;
    private int bonusDef;
    private Categorie categorie;
    private Type type;
    
    public CarteMagieEquipement (String nom, String description, int argent, String code, int bonusAtk, int bonusDef, Categorie categorie, Type type, Affectation affectation) {
        super(nom, TypeMagie.equipement, description, argent, code, affectation);
        this.bonusAtk = bonusAtk;
        this.bonusDef = bonusDef;
        this.categorie = categorie;
        this.type = type;
    }

    public int getBonusAtk() {
        return bonusAtk;
    }

    public void setBonusAtk(int bonusAtk) {
        this.bonusAtk = bonusAtk;
    }

    public int getBonusDef() {
        return bonusDef;
    }

    public void setBonusDef(int bonusDef) {
        this.bonusDef = bonusDef;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
}