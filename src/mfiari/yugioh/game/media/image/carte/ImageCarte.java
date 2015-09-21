/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.media.image.carte;

import java.awt.image.BufferedImage;
import java.net.URL;
import mfiari.lib.game.image.Image;

/**
 *
 * @author mike
 */
public class ImageCarte extends Image {
    
    public final static String defaut = "defaut/";
    public final static String combat = "combat/";
    
    public ImageCarte () {
        
    }
    
    public URL getUrlImageCarte (String nomCarte) {
        return this.getUrlImageCarte(nomCarte, defaut);
    }
    
    public URL getUrlImageCarte (String nomCarte, String type) {
        nomCarte = this.formatString(nomCarte);
        URL url = getClass().getResource(type+nomCarte+".png");
        if (url == null) {
            url = getClass().getResource(type+nomCarte+".jpg");
            if (url == null) {
                url = getClass().getResource(type+nomCarte+".gif");
            }
        }
        if (url == null && !defaut.equals(type)) {
            return this.getUrlImageCarte(nomCarte, defaut);
        }
        return url;
    }
    
    public boolean aImageCarte (String nomCarte) {
        return this.getUrlImageCarte(nomCarte) != null;
    }
    
    public boolean aImageCarte (String nomCarte, String type) {
        return this.getUrlImageCarte(nomCarte) != null;
    }
    
    public BufferedImage getImageCarte (String nomCarte) {
        return this.getImage(this.getUrlImageCarte(nomCarte));
    }
    
    public BufferedImage getImageCarte (String nomCarte, String type) {
        return this.getImage(this.getUrlImageCarte(nomCarte));
    }
    
    public BufferedImage getImageCarte (String nomCarte, int width, int height) {
        return this.getImage(this.getUrlImageCarte(nomCarte), width, height);
    }
    
    public BufferedImage getImageCarte (String nomCarte, int width, int height, String type) {
        return this.getImage(this.getUrlImageCarte(nomCarte), width, height);
    }
    
    public BufferedImage getImageCarteWidth(String nomCarte, int width) {
        return this.getImageWidth(this.getUrlImageCarte(nomCarte), width);
    }
    
    public BufferedImage getImageCarteWidth(String nomCarte, int width, String type) {
        return this.getImageWidth(this.getUrlImageCarte(nomCarte), width);
    }
    
    public BufferedImage getImageCarteHeigth(String nomCarte, int height) {
        return this.getImageHeight(this.getUrlImageCarte(nomCarte), height);
    }
    
    public BufferedImage getImageCarteHeigth(String nomCarte, int height, String type) {
        return this.getImageHeight(this.getUrlImageCarte(nomCarte), height);
    }
    
    public BufferedImage getImageCarteWidthOrHeigth(String nomCarte, int width, int height) {
        return this.getImageWidthOrHeight(this.getUrlImageCarte(nomCarte), width, height);
    }
    
    public BufferedImage getImageCarteWidthOrHeigth(String nomCarte, int width, int height, String type) {
        return this.getImageWidthOrHeight(this.getUrlImageCarte(nomCarte), width, height);
    }
    
}