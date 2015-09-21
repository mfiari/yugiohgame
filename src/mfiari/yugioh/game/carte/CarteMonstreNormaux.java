/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.carte;

import mfiari.yugioh.game.carte.CarteMonstre;

/**
 *
 * @author mike
 */
public class CarteMonstreNormaux extends CarteMonstre {
    
    public CarteMonstreNormaux (String nom, int etoiles, Type type, Categorie categorie, String description, int force, int def, int argent, String code, 
            String nomAttaque) {
        super(nom, etoiles, type, categorie, description, force, def, argent, code, nomAttaque);
    }
}