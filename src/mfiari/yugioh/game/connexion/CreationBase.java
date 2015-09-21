/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.connexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import mfiari.lib.game.connexionBD.Methode;

/**
 *
 * @author mike
 */
public class CreationBase extends Methode {
    
    private Connection connection;
    
    public CreationBase () {
        super(null);
    }
    
    public CreationBase (Connection connection) {
        super(connection);
        this.connection = connection;
        if (this.estConnecter()) {
            if (!this.tableExist() || (this.getDateTableMiseAJour().before(this.getDateDerniereMiseAJour()))) {
                System.out.println("reconstruction de la base");
                this.dropAllTable();
                this.creationTablePartie();
                this.creationTableFolioEndroitVisite();
                this.creationTableEndroitVisite();
                this.creationTableJoueurs();
                this.creationTableCartes();
                this.creationTableObjet();
                this.creationTableEvenementsChapitre();
                this.creationTableEvenementsQuete();
                this.creationTableQuetes();
                this.creationTableQuetesAnnexe();
                this.creationTableMiseAJour();
            }
        }
    }
    
    private void dropAllTable () {
        this.exexuteUpdate("DROP TABLE IF EXISTS mise_a_jour CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS quetes_annexe CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS quetes CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS evenements_quete CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS evenements_chapitre CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS objets CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS cartes CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS joueurs CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS endroit_visite CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS folio_endroit_visite CASCADE");
        this.exexuteUpdate("DROP TABLE IF EXISTS partie CASCADE");
    }
    
    private boolean tableExist () {
        boolean exist = true;
        try {
            Statement statement = this.connection.createStatement() ;
            statement.executeQuery("SELECT * FROM partie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            exist = false;
        }
        return exist;
    }
    
    private Date getDateTableMiseAJour () {
        Calendar dateMiseAJour = Calendar.getInstance();
        dateMiseAJour.set(2014, Calendar.APRIL, 8, 18, 20);
        return dateMiseAJour.getTime();
    }
    
    private Date getDateDerniereMiseAJour () {
        Calendar dateMiseAJour = Calendar.getInstance();
        dateMiseAJour.set(2014, Calendar.APRIL, 8, 7, 50);
        return dateMiseAJour.getTime();
    }
    
    private void creationTablePartie () {
        String requete = "CREATE TABLE partie ("
                + "id SMALLINT PRIMARY KEY,"
                + "temps_heure INT,"
                + "temps_min SMALLINT,"
                + "temps_seconde SMALLINT"
                + ")";
        if (this.exexuteUpdate(requete)) {
            this.insertionDonneePartie();
        }
    }
    
    private void insertionDonneePartie () {
        this.exexuteUpdate("INSERT INTO partie (id) VALUES (1)");
        this.exexuteUpdate("INSERT INTO partie (id) VALUES (2)");
        this.exexuteUpdate("INSERT INTO partie (id) VALUES (3)");
    }
    
    private void creationTableFolioEndroitVisite() {
        String requete = "CREATE TABLE folio_endroit_visite ("
                + "id SMALLINT,"
                + "folio_endroit_visite INT,"
                + "PRIMARY KEY (id),"
                + "FOREIGN KEY (id) REFERENCES partie(id)"
                + ")";
        if (this.exexuteUpdate(requete)) {
            this.insertionDonneeFolioEndroitVisite();
        }
    }
    
    private void insertionDonneeFolioEndroitVisite () {
        this.exexuteUpdate("INSERT INTO folio_endroit_visite (id, folio_endroit_visite) VALUES "
                + "(1, 0),"
                + "(2, 0),"
                + "(3, 0)");
    }
    
    private void creationTableEndroitVisite() {
        String requete = "CREATE TABLE endroit_visite ("
                + "id INT,"
                + "id_partie SMALLINT,"
                + "libelle_endroit VARCHAR(100),"
                + "PRIMARY KEY (id, id_partie),"
                + "FOREIGN KEY (id_partie) REFERENCES partie(id)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableJoueurs () {
        String requete = "CREATE TABLE joueurs ("
                + "id SMALLINT,"
                + "id_partie SMALLINT,"
                + "nom varchar(32),"
                + "argent INT,"
                + "positionx SMALLINT,"
                + "positiony SMALLINT,"
                + "orientation VARCHAR(10),"
                + "id_endroit INT,"
                + "PRIMARY KEY (id, id_partie),"
                + "FOREIGN KEY (id_partie) REFERENCES partie(id),"
                + "FOREIGN KEY (id_endroit, id_partie) REFERENCES endroit_visite(id, id_partie)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableObjet() {
        String requete = "CREATE TABLE objets ("
                + "id_joueur SMALLINT,"
                + "id_partie SMALLINT,"
                + "libelle_objet VARCHAR(32),"
                + "type_objet VARCHAR(32),"
                + "quantite SMALLINT,"
                + "PRIMARY KEY (id_joueur, id_partie, libelle_objet, type_objet),"
                + "FOREIGN KEY (id_joueur, id_partie) REFERENCES joueurs(id, id_partie)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableCartes() {
        String requete = "CREATE TABLE cartes ("
                + "id_joueur SMALLINT,"
                + "id_partie SMALLINT,"
                + "code VARCHAR(10),"
                + "deck BOOLEAN,"
                + "position SMALLINT,"
                + "quantite SMALLINT,"
                + "PRIMARY KEY (id_joueur, id_partie, code, position),"
                + "FOREIGN KEY (id_joueur, id_partie) REFERENCES joueurs(id, id_partie)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableEvenementsChapitre() {
        String requete = "CREATE TABLE evenements_chapitre ("
                + "id_evenement INT,"
                + "nom VARCHAR(32),"
                + "fini BOOLEAN,"
                + "id_partie INT,"
                + "PRIMARY KEY (id_evenement, id_partie),"
                + "FOREIGN KEY (id_partie) REFERENCES partie(id)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableEvenementsQuete() {
        String requete = "CREATE TABLE evenements_quete ("
                + "id_evenement INT,"
                + "nom VARCHAR(32),"
                + "fini BOOLEAN,"
                + "id_evenement_chapitre INT,"
                + "id_partie INT,"
                + "PRIMARY KEY (id_evenement, id_evenement_chapitre, id_partie),"
                + "FOREIGN KEY (id_partie) REFERENCES partie(id)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableQuetes() {
        String requete = "CREATE TABLE quetes ("
                + "id_evenement INT,"
                + "nom VARCHAR(32),"
                + "fini BOOLEAN,"
                + "id_evenement_quete INT,"
                + "id_evenement_chapitre INT,"
                + "id_partie INT,"
                + "PRIMARY KEY (id_evenement, id_evenement_quete, id_evenement_chapitre, id_partie),"
                + "FOREIGN KEY (id_partie) REFERENCES partie(id)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableQuetesAnnexe() {
        String requete = "CREATE TABLE quetes_annexe ("
                + "id_evenement INT,"
                + "nom VARCHAR(32),"
                + "actif BOOLEAN,"
                + "fini BOOLEAN,"
                + "id_evenement_chapitre INT,"
                + "id_partie INT,"
                + "PRIMARY KEY (id_evenement, id_evenement_chapitre, id_partie),"
                + "FOREIGN KEY (id_partie) REFERENCES partie(id)"
                + ")";
        this.exexuteUpdate(requete);
    }
    
    private void creationTableMiseAJour() {
        String requete = "CREATE TABLE mise_a_jour ("
                + "date_mise_a_jour TIMESTAMP"
                + ")";
        if (this.exexuteUpdate(requete)) {
            this.insertionDonneeTableMiseAJour();
        }
    }
    
    private void insertionDonneeTableMiseAJour () {
        this.exexuteUpdate("INSERT INTO mise_a_jour (date_mise_a_jour) VALUES ('2013-12-21 00:00:00')");
    }
}
