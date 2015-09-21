/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.perso;

import mfiari.lib.game.personnage.Personnage;
import mfiari.lib.game.position.Orientation;
import mfiari.lib.game.ville.Endroit;
import mfiari.yugioh.game.liste.Deck;
import mfiari.yugioh.game.liste.ListeDeCarte;

/**
 *
 * @author mike
 */
public class Dueliste extends Personnage {
    
    private int argent;
    private ListeDeCarte cartes;
    private Deck deck;
    
    public Dueliste (String nom, int positionX, int positionY, Endroit e, Orientation orientation) {
        super(nom, positionX, positionY, e, orientation);
        this.argent = 0;
        this.cartes = new ListeDeCarte();
        this.deck = new Deck(40);
    }

    public int getArgent() {
        return argent;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public ListeDeCarte getCartes() {
        return cartes;
    }

    public void setCartes(ListeDeCarte cartes) {
        this.cartes = cartes;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    
}
