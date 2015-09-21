/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.media.image.personnage;

import java.awt.image.BufferedImage;
import java.net.URL;
import mfiari.lib.game.image.Extension;
import mfiari.lib.game.image.Image;
import mfiari.lib.game.position.Orientation;

/**
 *
 * @author mike
 */
public class ImagePersonnage extends Image {
    
    public final static String combat = "combat/";
    public final static String dehors = "dehors/";
    
    public ImagePersonnage () {
        
    }
    
    public URL getUrlImagePerso (String nomPerso) {
        return this.getUrlImagePerso(nomPerso, defaut);
    }
    
    public URL getUrlImagePerso (String nomPerso, String type) {
        nomPerso = this.formatString(nomPerso);
        URL url = null;
        for (int i = 0 ; i < Extension.values().length ; i++) {
            url = getClass().getResource(type+nomPerso+"."+Extension.values()[i].name());
            if (url != null) {
                break;
            }
        }
        if (url == null && !defaut.equals(type)) {
            return this.getUrlImagePerso(nomPerso, defaut);
        }
        return url;
    }
    
    public URL getUrlImagePerso (String nomDresseur, String type, Orientation orientation, boolean walk) {
        String s = "";
        switch (orientation) {
            case dos :
                s = "_dos";
                break;
            case droite :
                s = "_droite";
                break;
            case face :
                s = "_face";
                break;
            case gauche :
                s = "_gauche";
                break;
        }
        if (walk) {
            s += "_inter";
        }
        URL url = this.getUrlImagePerso(nomDresseur+s, type);
        if (url == null) {
            url = this.getUrlImagePerso(nomDresseur, type);
        }
        return url;
    }
    
    public boolean aImagePerso (String nomPerso) {
        return this.getUrlImagePerso(nomPerso) != null;
    }
    
    public boolean aImagePerso (String nomPerso, String type) {
        return this.getUrlImagePerso(nomPerso, type) != null;
    }
    
    public boolean aImagePerso (String nomPerso, String type, Orientation orientation) {
        return this.getUrlImagePerso(nomPerso, type, orientation, false) != null;
    }
    
    public BufferedImage getImagePerso (String nomPerso) {
        return this.getImage(this.getUrlImagePerso(nomPerso));
    }
    
    public BufferedImage getImagePerso (String nomPerso, String type) {
        return this.getImage(this.getUrlImagePerso(nomPerso));
    }
    
    public BufferedImage getImagePerso (String nomPerso, int width, int height) {
        return this.getImage(this.getUrlImagePerso(nomPerso), width, height);
    }
    
    public BufferedImage getImagePerso (String nomPerso, int width, int height, String type) {
        return this.getImage(this.getUrlImagePerso(nomPerso), width, height);
    }
    
    public BufferedImage getImagePersoWidth(String nomPerso, int width) {
        return this.getImageWidth(this.getUrlImagePerso(nomPerso), width);
    }
    
    public BufferedImage getImagePersoWidth(String nomPerso, int width, String type) {
        return this.getImageWidth(this.getUrlImagePerso(nomPerso), width);
    }
    
    public BufferedImage getImagePersoHeigth(String nomPerso, int height) {
        return this.getImageHeight(this.getUrlImagePerso(nomPerso), height);
    }
    
    public BufferedImage getImagePersoHeigth(String nomPerso, int height, String type) {
        return this.getImageHeight(this.getUrlImagePerso(nomPerso, type), height);
    }
    
    public BufferedImage getImagePersoHeigth(String nomPerso, int height, String type, Orientation orientation) {
        return this.getImageHeight(this.getUrlImagePerso(nomPerso, type, orientation, false), height);
    }
    
    public BufferedImage getImagePersoWidthOrHeigth(String nomPerso, int width, int height) {
        return this.getImageWidthOrHeight(this.getUrlImagePerso(nomPerso), width, height);
    }
    
    public BufferedImage getImagePersoWidthOrHeigth(String nomPerso, int width, int height, String type) {
        return this.getImageWidthOrHeight(this.getUrlImagePerso(nomPerso), width, height);
    }
    
}