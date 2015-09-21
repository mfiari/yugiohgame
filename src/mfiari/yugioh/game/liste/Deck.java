/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.liste;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import mfiari.yugioh.game.carte.Carte;

/**
 *
 * @author mike
 */
public class Deck implements Serializable {
    
    private Carte[] cartes;
    
    public Deck () {
        this(20);
    }
    
    public Deck (int taille) {
        this.cartes = new Carte[taille];
    }
    
    public void melanger () {
        //System.out.println(this.cartes.length);
        List<Carte> list = Arrays.asList(this.cartes);
        Collections.shuffle(list);
        this.cartes = (Carte[]) list.toArray();
        //System.out.println(this.cartes.length);
    }
    
    public int size () {
        return this.cartes.length;
    }
    
    public int nbCarte () {
        int nbCarte = 0 ;
        int indice = 0;
        while (indice < this.cartes.length && this.cartes[indice] != null) {
            nbCarte++;
            indice++;
        }
        return nbCarte;
    }
    
    public boolean ajouterCarte (Carte carte) {
        if (this.nbCarte() < this.cartes.length) {
            this.cartes[this.nbCarte()] = carte;
            return true;
        }
        return false;
    }
    
    public Carte getCarte (int i) {
        return this.cartes[i];
    }
    
    public Carte prendreCarte () {
        return this.prendreCarte(0);
    }
    
    public Carte prendreCarte (int i) {
        Carte carte = this.cartes[i];
        int nbCarte = this.nbCarte();
        for (int j = i ; j < nbCarte -1 ; j++) {
            this.cartes[j] = this.cartes[j+1];
        }
        this.cartes[nbCarte-1] = null;
        return carte;
    }
    
}
