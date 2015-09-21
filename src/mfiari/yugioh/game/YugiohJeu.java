/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game;

import mfiari.lib.game.evenements.EvenementQuete;
import mfiari.lib.game.jeu.Jeu;
import mfiari.lib.game.objet.ObjetEndroitPassage;
import mfiari.lib.game.objet.Sac;
import mfiari.lib.game.position.Position;
import mfiari.yugioh.game.perso.Dueliste;

/**
 *
 * @author mike
 */
public class YugiohJeu extends Jeu {
    
    public YugiohJeu () {
        super();
    }
    
    public void jouer (EvenementQuete jeu, Dueliste perso) {
        super.jouer(jeu, perso);
        this.continuer();
    }

    @Override
    public void setChoixAction(int choix) {
        this.choix = choix;
        int positionX;
        int positionY;
        switch (this.choix) {
            case (1):
                positionY = this.perso.getPosition().getPositionY();
                positionX = this.perso.getPosition().getPositionX() - 1;
                this.bouger(positionX, positionY);
                break;
            case (2):
                positionY = this.perso.getPosition().getPositionY();
                positionX = this.perso.getPosition().getPositionX() + 1;
                this.bouger(positionX, positionY);
                break;
            case (3):
                positionY = this.perso.getPosition().getPositionY() + 1;
                positionX = this.perso.getPosition().getPositionX();
                this.bouger(positionX, positionY);
                break;
            case (4):
                positionY = this.perso.getPosition().getPositionY() - 1;
                positionX = this.perso.getPosition().getPositionX();
                this.bouger(positionX, positionY);
                break;
            case (5):
                this.action();
                break;
            case (6):
                /*Menu menu = new Menu(this.equipe, this.sac, this);
                Vues.createVue(menu);
                menu.menu();*/
                break;
        }
        //this.perso.setPosition(this.equipe.getPersonnage(0).getPosition());
    }

    @Override
    public void bouger(Position p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void bouger(int positionX, int positionY) {
        System.out.println("bouger");
        if (!(this.endroit.aObjetEndroit(this.perso.getPosition()) instanceof ObjetEndroitPassage)) {
            System.out.println("pas endroit pasage");
            if (positionX >= 0 && positionX < this.endroit.getLargeur() && positionY >= 0 && positionY < this.endroit.getLongueur()) {
                System.out.println("dans cadre");
                if (this.endroit.aObjetEndroit(new Position(positionX, positionY)) != null) {
                    System.out.println("obje endroit pas null");
                    if (this.endroit.aObjetEndroit(new Position(positionX, positionY)) instanceof ObjetEndroitPassage) {
                        System.out.println("dest endroit pasage");
                        ObjetEndroitPassage obj = (ObjetEndroitPassage) this.endroit.aObjetEndroit(new Position(positionX, positionY));
                        if (true/*!obj.getType().equals(TypeObjet.chemin)*/) {
                            System.out.println("prendre");
                            obj.prendre(this.perso);
                            this.endroit = this.perso.getPosition().getEndroit();
                        } else {
                            System.out.println("chemin");
                            this.perso.getPosition().setPositionX(positionX);
                            this.perso.getPosition().setPositionY(positionY);
                        }
                    }
                } else if (this.endroit.aGens(new Position(positionX, positionY)) != null) {
                     System.out.println("gens");
                    this.setAffichage("imposible");
                } else {
                     System.out.println("voie libre");
                    if (this.endroit.aEndroit(new Position(positionX, positionY)) != null) {
                        this.endroit.aEndroit(new Position(positionX, positionY)).entrer(this.perso);
                        this.endroit = this.perso.getPosition().getEndroit();
                    } else {
                        this.perso.getPosition().setPositionX(positionX);
                        this.perso.getPosition().setPositionY(positionY);
                    }
                }
            }
        } else {
             System.out.println("endroit pasage");
            ObjetEndroitPassage obj = (ObjetEndroitPassage) this.endroit.aObjetEndroit(this.perso.getPosition());
            if (positionX < 0 || positionX == this.endroit.getLargeur() || positionY < 0
                    || positionY == this.endroit.getLongueur()) {
                if (!this.quete.verifieEndroitAccessible(obj.getEndroitDarive())) {
                    obj.prendre(this.perso);
                    this.endroit = this.perso.getPosition().getEndroit();
                } else {
                }
            } else {
                this.perso.getPosition().setPositionX(positionX);
                this.perso.getPosition().setPositionY(positionY);
            }
        }
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean gameOver() {
        return false;
    }

    @Override
    public int getArgent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getExp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sac getSac() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
