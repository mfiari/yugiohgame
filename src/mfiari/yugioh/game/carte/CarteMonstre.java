/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.carte;

/**
 *
 * @author mike
 */
abstract public class CarteMonstre extends Carte {
    
    private int force;
    private int def;
    private int etoiles;
    private Categorie categorie;
    private String nomAttaque;
    private Type type;
    
    public CarteMonstre (String nom, int etoiles, Type type, Categorie categorie, String description, int force, int def, int argent, String code, 
            String nomAttaque) {
        super(nom, description, argent, code);
        this.force = force;
        this.def = def;
        this.etoiles = etoiles;
        this.categorie = categorie;
        this.nomAttaque = nomAttaque;
        this.type = type;
    }

    public int getForce() {
        return this.force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getDef() {
        return this.def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getEtoiles() {
        return this.etoiles;
    }

    public void setEtoiles(int etoiles) {
        this.etoiles = etoiles;
    }

    public Categorie getCategorie() {
        return this.categorie;
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

    public String getNomAttaque() {
        return this.nomAttaque;
    }
    
}
