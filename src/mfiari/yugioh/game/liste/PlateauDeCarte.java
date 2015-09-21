/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.liste;

import mfiari.yugioh.game.carte.Carte;
import mfiari.yugioh.game.carte.CarteMagie;
import mfiari.yugioh.game.carte.CarteMonstre;
import mfiari.yugioh.game.carte.CartePiege;
import mfiari.yugioh.game.carte.Mode;
import mfiari.yugioh.game.carte.Visibilite;

/**
 *
 * @author mike
 */
public class PlateauDeCarte {
    
    private CarteMonstre[] cartesMonstre;
    private Visibilite[] visibliteMonstre;
    private Mode[] mode;
    private Carte[] cartesMagie;
    private Visibilite[] visibliteMagie;
    
    public PlateauDeCarte () {
        this.cartesMonstre = new CarteMonstre[5];
        this.visibliteMonstre = new Visibilite[5];
        this.mode = new Mode[5];
        this.cartesMagie = new Carte[5];
        this.visibliteMagie = new Visibilite[5];
    }
    
    public int poserCarteMonstre (CarteMonstre carte, Visibilite visibilite, Mode mode) {
        for (int i = 0 ; i < this.cartesMonstre.length ; i++) {
            if (this.cartesMonstre[i] == null) {
                this.cartesMonstre[i] = carte;
                this.visibliteMonstre[i] = visibilite;
                this.mode[i] = mode;
                return i;
            }
        }
        return -1;
    }
    
    public int poserCarteMagique (CarteMagie carte, Visibilite visibilite) {
        for (int i = 0 ; i < this.cartesMagie.length ; i++) {
            if (this.cartesMagie[i] == null) {
                this.cartesMagie[i] = carte;
                this.visibliteMagie[i] = visibilite;
                return i;
            }
        }
        return -1;
    }
    
    public int poserCarteMagique (CartePiege carte, Visibilite visibilite) {
        for (int i = 0 ; i < this.cartesMagie.length ; i++) {
            if (this.cartesMagie[i] == null) {
                this.cartesMagie[i] = carte;
                this.visibliteMagie[i] = visibilite;
                return i;
            }
        }
        return -1;
    }
    
    public CarteMonstre retirerCarteMonstre (int position) {
        CarteMonstre carte = this.cartesMonstre[position];
        this.cartesMonstre[position] = null;
        this.visibliteMonstre[position] = null;
        this.mode[position] = null;
        return carte;
    }
    
    public void retirerCarteMagique (int position) {
        this.cartesMagie[position] = null;
        this.visibliteMagie[position] = null;
    }
    
    public void retirerCarteMagique (CarteMagie carte) {
        for (int i = 0 ; i < this.cartesMagie.length ; i++) {
            if (carte.equals(this.cartesMagie[i])) {
                this.cartesMagie[i] = null;
                this.visibliteMagie[i] = null;
                break;
            }
        }
    }
    
    public CarteMonstre getCarteMonsre (int position) {
        return this.cartesMonstre[position];
    }
    
    public Visibilite getVisibiliteCarteMonsre (int position) {
        return this.visibliteMonstre[position];
    }
    
    public void changerVisibiliteCarteMonsre (int position, Visibilite visibilite) {
        this.visibliteMonstre[position] = visibilite;
    }
    
    public Mode getModeCarteMonsre (int position) {
        return this.mode[position];
    }
    
    public void changerModeCarteMonsre (int position, Mode mode) {
        this.mode[position] = mode;
    }
    
    public boolean isCarteMagie (int position) {
        return this.cartesMagie[position] != null && this.cartesMagie[position] instanceof CarteMagie;
    }
    
    public CarteMagie getCarteMagie (int position) {
        if (this.cartesMagie[position] instanceof CarteMagie) {
            return (CarteMagie)this.cartesMagie[position];
        }
        return null;
    }
    
    public boolean isCartePiege (int position) {
        return this.cartesMagie[position] != null && this.cartesMagie[position] instanceof CartePiege;
    }
    
    public CartePiege getCartePiege (int position) {
        if (this.cartesMagie[position] instanceof CartePiege) {
            return (CartePiege)this.cartesMagie[position];
        }
        return null;
    }
    
    public Visibilite getVisibiliteCarteMagie (int position) {
        return this.visibliteMagie[position];
    }
    
    public void changerVisibiliteCarteMagie (int position, Visibilite visibilite) {
        this.visibliteMagie[position] = visibilite;
    }
    
}
