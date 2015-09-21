/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.swing;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import mfiari.lib.game.clavier.ActionPerso;
import mfiari.lib.game.clavier.CodeBouton;
import mfiari.lib.game.clavier.KeyDispatcher;
import mfiari.lib.game.clavier.ListComponentNumber;
import mfiari.lib.game.clavier.ListKeyAction;
import mfiari.lib.game.connexionBD.Methode;
import mfiari.lib.game.swing.Ecran;
import mfiari.lib.game.swing.PanelDeTexteAffichage;
import mfiari.lib.game.swing.PanelDeTexteSuivant;
import mfiari.lib.game.swing.VueSwing;
import mfiari.yugioh.game.texte.TexteVueConnexion;

/**
 *
 * @author mike
 */
public class VueSwing_connexion extends VueSwing {
    
    private Methode methode;
    private TexteVueConnexion textes;
    
    public VueSwing_connexion (Methode c) {
        super(TexteVueConnexion.getInstance(), c);
        this.methode = c;
        this.textes = TexteVueConnexion.getInstance();
        this.methode.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("transfertEnCours")) {
                    transfertEnCours();
                } else if (evt.getPropertyName().equals("sauvegarderPartie")) {
                    sauvegarderPartie();
                } else {
                    if (evt.getPropertyName().equals("afficherParties")) {
                        afficherParties();
                    } else if (evt.getPropertyName().equals("transfertTerminer")) {
                        transfertTerminer();
                    } else if (evt.getPropertyName().equals("sauvegarderPartieTerminer")) {
                        sauvegarderPartieTerminer();
                    } else if (evt.getPropertyName().equals("problemeConnexion")) {
                        problemeConnexion();
                    }
                    attendre();
                }
            }
        });
    }
    
    public void afficherParties() {
        try {
            JPanel panel2 = new JPanel (new GridLayout(3, 1));
            int i = 0;
            ResultSet result = this.methode.getResultSet();
            ListComponentNumber componentNumber = new ListComponentNumber();
            while (result.next()) {
                i++;
                PanelDeTexteAffichage panelTexteAffichageSauvegarde = new PanelDeTexteAffichage();
                panelTexteAffichageSauvegarde.ajouterTexte("sauvegarde " + i);
                /*if (result.getString("nom") != null && !result.getString("nom").isEmpty()) {
                    JPanel panelPartie = new JPanel (new GridLayout(2, 2));
                    PanelDeTexteAffichage panelTexteAffichagePerso = new PanelDeTexteAffichage();
                    panelTexteAffichagePerso.ajouterTexte(result.getString("nom"));
                    PanelDeTexteAffichage panelTexteAffichageTempsJeu = new PanelDeTexteAffichage();
                    panelTexteAffichageTempsJeu.ajouterTexte("temps de jeu : " + result.getString("temps_heure") + ":" + result.getString("temps_minute"));
                    panelPartie.add(panelTexteAffichageSauvegarde);
                    panelPartie.add(panelTexteAffichagePerso);
                    panelPartie.add(panelTexteAffichageTempsJeu);
                    JButton bouton = new JButton();
                    bouton.add(panelPartie);
                    bouton.addActionListener(new boutonChoix(i));
                    panel2.add(bouton);
                    componentNumber.addKeyAction(bouton, i);
                } else {*/
                    JPanel panelPartie = new JPanel (new GridLayout(2, 1));
                    PanelDeTexteAffichage panelTexteAffichageVide = new PanelDeTexteAffichage();
                    panelTexteAffichageVide.ajouterTexte("vide");
                    panelPartie.add(panelTexteAffichageSauvegarde);
                    panelPartie.add(panelTexteAffichageVide);
                    JButton bouton = new JButton();
                    bouton.add(panelPartie);
                    bouton.addActionListener(new boutonChoix(i));
                    panel2.add(bouton);
                    componentNumber.addKeyAction(bouton, i);
                /*}*/
            }
            JButton boutonRetour = new JButton(this.textes.retour);
            boutonRetour.addActionListener(new boutonChoix(0));
            Ecran.panel.ajouterCentre(panel2);
            Ecran.panel.ajouterSud(boutonRetour);
            ListKeyAction actions = new ListKeyAction();
            actions.addKeyAction(CodeBouton.HAUT, ActionPerso.DECREMENTE);
            actions.addKeyAction(CodeBouton.BAS, ActionPerso.INCREMENTE);
            actions.addKeyAction(CodeBouton.ACTION, ActionPerso.ACTION);
            Ecran.fenetreDuJeu.addKeyBoardManager(new KeyDispatcher(new ActionPerso(this.methode, this, actions, componentNumber)));
        } catch (SQLException ex) {
            Logger.getLogger(VueSwing_connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void transfertEnCours() {
        PanelDeTexteAffichage panelTexte = new PanelDeTexteAffichage("Transfert en cours...");
        Ecran.panel.ajouterNord(panelTexte);
    }
    
    public void transfertTerminer() {
        PanelDeTexteSuivant panelTexte = new PanelDeTexteSuivant(this);
        panelTexte.ajouterTexte("Votre transfert à bien été effectuer.");
        panelTexte.lancerAffichage();
        Ecran.panel.ajouterNord(panelTexte);
    }
    
    public void sauvegarderPartie() {
        PanelDeTexteAffichage panelTexte = new PanelDeTexteAffichage("Sauvegarde en cour, ne pas eteindre...");
        Ecran.panel.ajouterCentre(panelTexte);
    }
    
    public void sauvegarderPartieTerminer() {
        PanelDeTexteSuivant panelTexte = new PanelDeTexteSuivant(this);
        panelTexte.ajouterTexte("Sauvegarde reussi.");
        panelTexte.lancerAffichage();
        Ecran.panel.ajouterCentre(panelTexte);
    }
    
    public void problemeConnexion() {
        PanelDeTexteSuivant panelTexte = new PanelDeTexteSuivant(this);
        panelTexte.ajouterTexte("Un problème est survenu lors du transfert de donnée. Le site n'est peut-être pas accessible."
                + "Veuillez réessayer ultérieurement");
        panelTexte.lancerAffichage();
        Ecran.panel.ajouterNord(panelTexte);
    }
    
    private class boutonChoix implements ActionListener {
        int indice;
        public boutonChoix (int indice) {
            this.indice = indice;
        }
        @Override
        public void actionPerformed (ActionEvent event) {
            methode.setChoix(this.indice);
            reprendre();
        }
    }
}
