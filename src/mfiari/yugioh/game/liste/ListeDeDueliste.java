/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.liste;

import java.util.ArrayList;
import mfiari.yugioh.game.perso.Dueliste;

/**
 *
 * @author mike
 */
public class ListeDeDueliste {
    
    private ArrayList<Dueliste> personnages;
    
    public ListeDeDueliste () {
        this.personnages = new ArrayList<>();
    }
    
    public void ajouterPersonnage (Dueliste perso) {
        this.personnages.add(perso);
    }
    
    public Dueliste getPersonnage (int i) {
        return this.personnages.get(i);
    }
    
    public int size () {
        return this.personnages.size();
    }
    
}