/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.carte;

import mfiari.yugioh.game.ville.Terrain;

/**
 *
 * @author mike
 */
public class CarteMagieTerrain extends CarteMagie {
    
    private Terrain terrain;
    
    public CarteMagieTerrain (String nom, String description, int argent, String code, Terrain terrain) {
        super(nom, TypeMagie.terrain, description, argent, code, Affectation.tousMonstre);
        this.terrain = terrain;
    }
    
    public Terrain getTerrain () {
        return this.terrain;
    }
    
}