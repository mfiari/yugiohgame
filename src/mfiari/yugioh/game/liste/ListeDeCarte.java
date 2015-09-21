/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.liste;

import java.io.Serializable;
import java.util.ArrayList;
import mfiari.yugioh.game.carte.Carte;

/**
 *
 * @author mike
 */
public class ListeDeCarte implements Serializable {
    
    private ArrayList<Carte> cartes;
    
    public ListeDeCarte () {
        this.cartes = new ArrayList<>();
    }
    
    public void ajouterCarte (Carte carte) {
        this.cartes.add(carte);
    }
    
    public Carte getCarte (int i) {
        return this.cartes.get(i);
    }
    
    public boolean aCarte (Carte carte) {
        return this.cartes.contains(carte);
    }
    
    public int getNbCarte (Carte carte) {
        return 0;
    }
    
    public int size () {
        return this.cartes.size();
    }
    
}
