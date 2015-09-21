/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mfiari.lib.game.connexionBD.Methode;
import mfiari.lib.game.evenements.Evenement;
import mfiari.lib.game.evenements.EvenementQuete;
import mfiari.lib.game.liste.ListeDEndroit;
import mfiari.lib.game.ville.Endroit;

/**
 *
 * @author mike
 */
public class Connexion extends Methode {
    
    private int partie;
    private int idJoueur;

    public Connexion() {
        super(null);
    }

    public Connexion(Connection conn) {
        super(conn);
    }
    
    public boolean isConnected () {
        return this.estConnecter();
    }
    
    public boolean partieChoisit () {
        return this.partie != 0;
    }

    public void parties() {
        String query = "SELECT p.id AS partie, p.temps_heure AS temps_heure, p.temps_min AS temps_minute "
                + "FROM partie p ORDER BY partie";
        ResultSet result = this.executeQuery(query);
        this.resultSet = result;
        this.pcsControlleurVue.firePropertyChange("afficherParties", null, null);
        this.partie = this.choix;
        this.idJoueur = this.partie;
        this.closeResultSet(result);
    }

    public void CreerPartie() {
        PreparedStatement prepare = this.prepareStatement("SELECT COUNT(*) AS rowcount "
                + "FROM partie p "
                + "JOIN joueur j ON j.id = p.id "
                + "WHERE p.id = ?");
        this.setParameterInt(prepare, this.partie, 1);
        ResultSet result = this.executePreparedStatement(prepare);
        if (this.aResult(result)) {
            if (this.getIntResultByString(result, "rowcount") > 0) {
                System.out.println("suppression de la partie cree");

                this.deleteAllEvenement();

                PreparedStatement prepare7 = this.prepareStatement("DELETE FROM dresseur_vaincu where id_joueur=?");
                this.setParameterInt(prepare7, this.idJoueur, 1);
                this.executeUpdatePreparedStatement(prepare7);

                PreparedStatement prepare14 = this.prepareStatement("DELETE FROM pokemon_pokedex where id_joueur=?");
                this.setParameterInt(prepare14, this.idJoueur, 1);
                this.executeUpdatePreparedStatement(prepare14);

                PreparedStatement prepare17 = this.prepareStatement("DELETE FROM joueur where id=?");
                this.setParameterInt(prepare17, this.partie, 1);
                this.executeUpdatePreparedStatement(prepare17);

                PreparedStatement prepare18 = this.prepareStatement("DELETE FROM rivaux where id_partie=?");
                this.setParameterInt(prepare18, this.partie, 1);
                this.executeUpdatePreparedStatement(prepare18);

                PreparedStatement prepare19 = this.prepareStatement("DELETE FROM endroit_visite where id_partie=?");
                this.setParameterInt(prepare19, this.partie, 1);
                this.executeUpdatePreparedStatement(prepare19);

                this.initFolioEndroitVisite();

                PreparedStatement prepare20 = this.prepareStatement("DELETE FROM partie where id=?");
                this.setParameterInt(prepare20, this.partie, 1);
                this.executeUpdatePreparedStatement(prepare20);

                PreparedStatement prepare21 = this.prepareStatement("insert into partie values(?,?,?,?)");
                this.setParameterInt(prepare21, this.partie, 1);
                this.setParameterInt(prepare21, 0, 2);
                this.setParameterInt(prepare21, 0, 3);
                this.setParameterInt(prepare21, 0, 4);
                this.executeUpdatePreparedStatement(prepare21);
            } else {
                this.sauvegarderPartie(0, 0, 0);
            }
        } else {
            this.sauvegarderPartie(0, 0, 0);
        }
        this.closeResultSet(result);
    }

    public void sauvegarderPartie(int temps_jeu_heure, int temps_jeu_minute, int temps_jeu_seconde) {
        this.pcsControlleurVue.firePropertyChange("sauvegarderPartie", null, null);
        PreparedStatement prepare = this.prepareStatement("UPDATE partie SET temps_heure = ?, temps_min = ?, temps_seconde = ? WHERE id=?");
        this.setParameterInt(prepare, temps_jeu_heure, 1);
        this.setParameterInt(prepare, temps_jeu_minute, 2);
        this.setParameterInt(prepare, temps_jeu_seconde, 3);
        this.setParameterInt(prepare, this.partie, 4);
        this.executeUpdatePreparedStatement(prepare);
    }

    public void finSauvegarderPartie() {
        this.pcsControlleurVue.firePropertyChange("sauvegarderPartieTerminer", null, null);
    }
    
    public int[] chargerPartie () {
        int[] time = new int[3];
        PreparedStatement prepare = this.prepareStatement("SELECT temps_heure, temps_min, temps_seconde FROM partie where id=?");
        this.setParameterInt(prepare, this.partie, 1);
        ResultSet result = this.executePreparedStatement(prepare);
        if (this.aResult(result)) {
            time[0] = this.getIntResultByString(result, "temps_heure");
            time[1] = this.getIntResultByString(result, "temps_min");
            time[2] = this.getIntResultByString(result, "temps_seconde");
        }
        this.closeResultSet(result);
        return time;
    }

    private void initFolioEndroitVisite() {
        PreparedStatement prepare = this.prepareStatement("UPDATE folio_endroit_visite SET folio_endroit_visite WHERE id = ?");
        this.setParameterInt(prepare, this.partie, 1);
        this.executeUpdatePreparedStatement(prepare);
    }

    private int getFolioEndroitVisite() {
        int folio = 0;
        PreparedStatement prepare = this.prepareStatement("Select folio_endroit_visite AS folio FROM folio_endroit_visite where id=?");
        this.setParameterInt(prepare, this.partie, 1);
        ResultSet result = this.executePreparedStatement(prepare);
        while (this.aResult(result)) {
            folio = this.getIntResultByString(result, "folio");
        }
        this.closeResultSet(result);
        folio++;
        PreparedStatement prepare2 = this.prepareStatement("UPDATE folio_endroit_visite SET folio_endroit_visite = ? WHERE id = ?");
        this.setParameterInt(prepare2, folio, 1);
        this.setParameterInt(prepare2, this.partie, 2);
        this.executeUpdatePreparedStatement(prepare2);
        return folio;
    }

    private void enregistrerEndroit(Endroit e) {
        /*on enregistre les villes déja visité*/
        PreparedStatement prepare = this.prepareStatement("INSERT INTO endroit_visite values(?,?,?)");
        this.setParameterInt(prepare, this.getFolioEndroitVisite(), 1);
        this.setParameterInt(prepare, this.partie, 2);
        this.setParameterString(prepare, e.getNom(), 3);
        this.executeUpdatePreparedStatement(prepare);
    }

    public void sauvegarderEndroit(ListeDEndroit carte_pokemon) {
        /*on sauvegarde les villes déja visité*/
        for (int i = 0; i < carte_pokemon.size(); i++) {
            System.out.println((i+1) + " : " + carte_pokemon.getEndroit(i));
            System.out.println("nom : " + carte_pokemon.getEndroit(i).getNom());
            System.out.println("getIdEndroit : " + this.getIdEndroit(carte_pokemon.getEndroit(i).getNom()));
            if (this.getIdEndroit(carte_pokemon.getEndroit(i).getNom()) == 0) {
                this.enregistrerEndroit((Endroit)carte_pokemon.getEndroit(i));
            }
        }
    }

    private int getIdEndroit (String nom) {
        int idEndroit = 0;
        PreparedStatement prepare = this.prepareStatement("SELECT * FROM endroit_visite where libelle_endroit=? and id_partie=?");
        this.setParameterString(prepare, nom, 1);
        this.setParameterInt(prepare, this.partie, 2);
        ResultSet result = this.executePreparedStatement(prepare);
        if (this.aResult(result)) {
            idEndroit = this.getIntResultByString(result, "id");
        }
        this.closeResultSet(result);
        return idEndroit;
    }
    
    private Endroit getEndroit (int id, ListeDEndroit villes) {
        Endroit e = null;
        PreparedStatement prepare = this.prepareStatement("SELECT libelle_endroit FROM endroit_visite where id_partie=? AND id=?");
        this.setParameterInt(prepare, this.partie, 1);
        this.setParameterInt(prepare, id, 2);
        ResultSet result = this.executePreparedStatement(prepare);
        if (this.aResult(result)) {
            e = (Endroit) villes.getEndroit(this.getStringResultByString(result, "libelle_endroit"));
        }
        System.out.println(e);
        this.closeResultSet(result);
        return e;
    }

    public ListeDEndroit chargerEndroits(ListeDEndroit villes) {
        for (int i = 0 ; i  < villes.size() ; i++) {
            System.out.println(villes.getEndroit(i).getNom());
        }
        ListeDEndroit endroits = new ListeDEndroit();
        PreparedStatement prepare = this.prepareStatement("SELECT libelle_endroit FROM endroit_visite where id_partie=?");
        this.setParameterInt(prepare, this.partie, 1);
        ResultSet result = this.executePreparedStatement(prepare);
        while (this.aResult(result)) {
            System.out.println("chargerEndroits -> nom : " + this.getStringResultByString(result, "libelle_endroit"));
            endroits.ajouterEndroit(villes.getEndroit(this.getStringResultByString(result, "libelle_endroit")));
            System.out.println("chargerEndroits -> endroit : " + endroits.getEndroit(endroits.size()-1));
        }
        this.closeResultSet(result);
        return endroits;
    }
    
    private void deleteAllEvenement () {
        PreparedStatement prepare = this.prepareStatement("SELECT * FROM evenements_chapitre where id_partie=?");
        this.setParameterInt(prepare, this.partie, 1);
        ResultSet result = this.executePreparedStatement(prepare);
        while (this.aResult(result)) {
            PreparedStatement prepare1 = this.prepareStatement("DELETE FROM quetes_annexe where id_evenement_chapitre=?");
            this.setParameterInt(prepare1, this.getIntResultByString(result, "id_evenement"), 1);
            this.executeUpdatePreparedStatement(prepare1);

            PreparedStatement prepare2 = this.prepareStatement("SELECT * FROM evenements_quete where id_evenement_chapitre=?");
            this.setParameterInt(prepare2, this.getIntResultByString(result, "id_evenement"), 1);
            ResultSet result1 = this.executePreparedStatement(prepare2);
            while (this.aResult(result1)) {
                PreparedStatement prepare3 = this.prepareStatement("DELETE FROM quetes where id_evenement_quete=?");
                this.setParameterInt(prepare3, this.getIntResultByString(result1, "id_evenement"), 1);
                this.executeUpdatePreparedStatement(prepare3);
            }
            this.closeResultSet(result1);
            PreparedStatement prepare4 = this.prepareStatement("DELETE FROM evenements_quete where id_evenement_chapitre=?");
            this.setParameterInt(prepare4, this.getIntResultByString(result, "id_evenement"), 1);
            this.executeUpdatePreparedStatement(prepare4);
        }
        this.closeResultSet(result);

        PreparedStatement prepare5 = this.prepareStatement("DELETE FROM evenements_chapitre where id_partie=?");
        this.setParameterInt(prepare5, this.partie, 1);
        this.executeUpdatePreparedStatement(prepare5);
    }

    public void sauvegarderEvenement(EvenementQuete e) {
        int nbEvenement_chapitre;
        int i=0;
        int j=0;
        EvenementQuete even_chapitre = null;
        EvenementQuete even_quetes;
        PreparedStatement prepare = this.prepareStatement("SELECT count(*) as rowcount, id_evenement  "
                    + "FROM evenements_chapitre where id_partie=? group by id_evenement");
        this.setParameterInt(prepare, this.partie, 1);
        ResultSet result = this.executePreparedStatement(prepare);
        if (this.aResult(result)) {
            nbEvenement_chapitre = this.getIntResultByString(result, "rowcount") -1;
        } else {
            PreparedStatement prepare1 = this.prepareStatement("insert into evenements_chapitre values(?,?,?,?)");
            this.setParameterInt(prepare1, 1, 1);
            this.setParameterString(prepare1, e.getEvenement(0).toString(), 2);
            this.setParameterBoolean(prepare1, e.estFini(), 3);
            this.setParameterInt(prepare1, this.partie, 4);
            this.executeUpdatePreparedStatement(prepare1);
            nbEvenement_chapitre = 0;
        }
        this.closeResultSet(result);
        System.out.println("nbEvenement_chapitre : " + nbEvenement_chapitre);
        System.out.println("even1 : " + e.getEvenement(nbEvenement_chapitre).toString() + " ; " + e.getEvenement(nbEvenement_chapitre).estFini());
        while (e.getEvenement(nbEvenement_chapitre).estFini()) { /* evenement chapitre 1 */
            System.out.println("even2 : " + e.getEvenement(nbEvenement_chapitre).toString() + " ; " + e.getEvenement(nbEvenement_chapitre).estFini());
            this.sauvegarderEvenementChapitre(nbEvenement_chapitre +1, e);
            even_chapitre = (EvenementQuete ) e.getEvenement(nbEvenement_chapitre);
            while (even_chapitre.getEvenement(i) != null) {
                System.out.println("even3 : " + even_chapitre.toString() + " ; " + even_chapitre.estFini());
                this.sauvegarderEvenementQuete(i+1, nbEvenement_chapitre +1, even_chapitre);
                if (even_chapitre.getQueteAnnexe().estFini()) {
                    this.sauvegarderQueteAnnexe(i+1, nbEvenement_chapitre +1, even_chapitre);
                }
                even_quetes = (EvenementQuete) even_chapitre.getEvenement(i);
                while (even_quetes.getEvenement(j) != null) {
                    System.out.println("even4 : " + even_quetes.toString() + " ; " + even_quetes.estFini());
                    this.sauvegarderQuetes(j+1, i+1, nbEvenement_chapitre +1, even_quetes.getEvenement(j));
                    j++;
                }
                i++;
            }
            nbEvenement_chapitre++;
        }
        this.sauvegarderEvenementChapitre(nbEvenement_chapitre +1, e);
        if (even_chapitre == null) {
            even_chapitre = (EvenementQuete ) e.getEvenement(nbEvenement_chapitre);
            System.out.println("even5 : " + even_chapitre.toString() + " ; " + even_chapitre.estFini());
            while (even_chapitre.getEvenement(i).estFini()) {
                System.out.println("even6 : " + even_chapitre.getEvenement(i).toString() + " ; " + even_chapitre.getEvenement(i).estFini());
                this.sauvegarderEvenementQuete(i+1, nbEvenement_chapitre +1, even_chapitre);
                even_quetes = (EvenementQuete ) even_chapitre.getEvenement(i);
                if (even_quetes.aQueteAnexe() && even_quetes.getQueteAnnexe().estFini()) {
                    this.sauvegarderQueteAnnexe(i+1, nbEvenement_chapitre +1, even_quetes);
                }
                while (even_quetes.getEvenement(j) != null) {
                    System.out.println("even7 : " + even_quetes.toString() + " ; " + even_quetes.estFini());
                    this.sauvegarderQuetes(j+1, i+1, nbEvenement_chapitre +1, even_quetes.getEvenement(j));
                    j++;
                }
                i++;
            }
            this.sauvegarderEvenementQuete(i+1, nbEvenement_chapitre +1, even_chapitre);
            even_quetes = (EvenementQuete ) even_chapitre.getEvenement(i);
            System.out.println("even8 : " + even_quetes.toString() + " ; " + even_quetes.estFini());
            j=0;
            while (even_quetes.getEvenement(j).estFini()) {
                System.out.println("even9 : " + even_quetes.toString() + " ; " + even_quetes.estFini());
                this.sauvegarderQuetes(j+1, i+1, nbEvenement_chapitre +1, even_quetes.getEvenement(j));
                j++;
            }
            this.sauvegarderQuetes(j+1, i+1, nbEvenement_chapitre +1, even_quetes.getEvenement(j));
        }
    }

    private void sauvegarderEvenementChapitre (int id, EvenementQuete e) {
        System.out.println("sauvegarde even chapitre : " + e.getEvenement(id-1).toString() + " ; " + e.getEvenement(id-1).estFini());
        PreparedStatement prepare = this.prepareStatement("SELECT * FROM evenements_chapitre where id_evenement=?");
        this.setParameterInt(prepare, id, 1);
        ResultSet result = this.executePreparedStatement(prepare);
        if (!this.aResult(result)) {
            PreparedStatement prepare1 = this.prepareStatement("insert into evenements_chapitre values(?,?,?,?)");
            this.setParameterInt(prepare1, id, 1);
            this.setParameterString(prepare1, e.getEvenement(id-1).toString(), 2);
            this.setParameterBoolean(prepare1, e.estFini(), 3);
            this.setParameterInt(prepare1, this.partie, 4);
            this.executeUpdatePreparedStatement(prepare1);
        } else {
            if (!this.getBooleanResultByString(result, "fini") && e.estFini()) {
                PreparedStatement prepare1 = this.prepareStatement("update evenements_chapitre set fini=? where id_evenement=?");
                this.setParameterBoolean(prepare1, true, 1);
                this.setParameterInt(prepare1, id, 2);
                this.executeUpdatePreparedStatement(prepare1);
            }
        }
        this.closeResultSet(result);
    }

    private void sauvegarderEvenementQuete (int id, int id_chapitre, EvenementQuete e) {
        System.out.println("sauvegarde even quete : " + e.getEvenement(id-1).toString() + " ; " + e.getEvenement(id-1).estFini());
        PreparedStatement prepare = this.prepareStatement("SELECT * FROM evenements_quete where id_evenement=? and id_evenement_chapitre=? "
                + "and id_partie=?");
        this.setParameterInt(prepare, id, 1);
        this.setParameterInt(prepare, id_chapitre, 2);
        this.setParameterInt(prepare, this.partie, 3);
        ResultSet result = this.executePreparedStatement(prepare);
        if (!this.aResult(result)) {
            PreparedStatement prepare1 = this.prepareStatement("insert into evenements_quete values(?,?,?,?,?)");
            this.setParameterInt(prepare1, id, 1);
            this.setParameterString(prepare1, e.getEvenement(id-1).toString(), 2);
            this.setParameterBoolean(prepare1, e.getEvenement(id-1).estFini(), 3);
            this.setParameterInt(prepare1, id_chapitre, 4);
            this.setParameterInt(prepare1, this.partie, 5);
            this.executeUpdatePreparedStatement(prepare1);
        } else {
            if (!this.getBooleanResultByString(result, "fini") && e.getEvenement(id-1).estFini()) {
                PreparedStatement prepare1 = this.prepareStatement("update evenements_quete set fini=? where id_evenement=? "
                            + "and id_evenement_chapitre=? and id_partie=?");
                this.setParameterBoolean(prepare1, true, 1);
                this.setParameterInt(prepare1, id, 2);
                this.setParameterInt(prepare1, id_chapitre, 3);
                this.setParameterInt(prepare1, this.partie, 4);
                this.executeUpdatePreparedStatement(prepare1);
            }
        }
        this.closeResultSet(result);
    }

    private void sauvegarderQueteAnnexe (int id, int id_chapitre, EvenementQuete e) {
        System.out.println("sauvegarde even quete annexe : " + e.getEvenement(id-1).toString() + " ; " + e.getEvenement(id-1).estFini());
        PreparedStatement prepare = this.prepareStatement("SELECT * FROM quetes_annexe where id_evenement=? and id_chapitre=? and"
                    + " id_partie=?");
        this.setParameterInt(prepare, id, 1);
        this.setParameterInt(prepare, id_chapitre, 2);
        this.setParameterInt(prepare, this.partie, 3);
        ResultSet result = this.executePreparedStatement(prepare);
        if (!this.aResult(result)) {
            PreparedStatement prepare1 = this.prepareStatement("insert into quetes_annexe values(?,?,?,?,?,?)");
            this.setParameterInt(prepare1, id, 1);
            this.setParameterString(prepare1, e.getQueteAnnexe().toString(), 2);
            this.setParameterBoolean(prepare1, e.estActiver(), 3);
            this.setParameterBoolean(prepare1, e.estFini(), 4);
            this.setParameterInt(prepare1, id_chapitre, 5);
            this.setParameterInt(prepare1, this.partie, 6);
            this.executeUpdatePreparedStatement(prepare1);
        } else {
            if ((!this.getBooleanResultByString(result, "fini") && e.estFini()) || (!this.getBooleanResultByString(result, "actif") 
                    && e.estActiver())) {
                PreparedStatement prepare1 = this.prepareStatement("update quetes_annexe set fini=?, actif=? where id_evenement=? and "
                        + "id_chapitre=? and id_partie=?");
                this.setParameterBoolean(prepare1, e.estFini(), 1);
                this.setParameterBoolean(prepare1, e.estActiver(), 2);
                this.setParameterInt(prepare1, id, 3);
                this.setParameterInt(prepare1, id_chapitre, 4);
                this.setParameterInt(prepare1, this.partie, 5);
                this.executeUpdatePreparedStatement(prepare1);
            }
        }
        this.closeResultSet(result);
    }

    private void sauvegarderQuetes (int id, int id_quete, int id_chapitre, Evenement e) {
        System.out.println("sauvegarde quete : " + e.toString() + " ; " + e.estFini());
        PreparedStatement prepare = this.prepareStatement("SELECT * FROM quetes where id_evenement=? and id_evenement_quete=? and "
                    + "id_evenement_chapitre=? and id_partie=?");
        this.setParameterInt(prepare, id, 1);
        this.setParameterInt(prepare, id_quete, 2);
        this.setParameterInt(prepare, id_chapitre, 3);
        this.setParameterInt(prepare, this.partie, 4);
        ResultSet result = this.executePreparedStatement(prepare);
        if (!this.aResult(result)) {
            PreparedStatement prepare1 = this.prepareStatement("insert into quetes values(?,?,?,?,?,?)");
            this.setParameterInt(prepare1, id, 1);
            this.setParameterString(prepare1, e.toString(), 2);
            this.setParameterBoolean(prepare1, e.estFini(), 3);
            this.setParameterInt(prepare1, id_quete, 4);
            this.setParameterInt(prepare1, id_chapitre, 5);
            this.setParameterInt(prepare1, this.partie, 6);
            this.executeUpdatePreparedStatement(prepare1);
        } else {
            if ((!this.getBooleanResultByString(result, "fini") && e.estFini())) {
                PreparedStatement prepare1 = this.prepareStatement("update quetes set fini=? where id_evenement=? and "
                            + "id_evenement_quete=? and id_evenement_chapitre=? and id_partie=?");
                this.setParameterBoolean(prepare1, e.estFini(), 1);
                this.setParameterInt(prepare1, id, 2);
                this.setParameterInt(prepare1, id_quete, 3);
                this.setParameterInt(prepare1, id_chapitre, 4);
                this.setParameterInt(prepare1, this.partie, 5);
                this.executeUpdatePreparedStatement(prepare1);
            }
        }
        this.closeResultSet(result);
    }

    public int chargerEvenementChapitre() {
        PreparedStatement prepare = this.prepareStatement("select * from evenements_chapitre where id_partie=? and fini=?");
        this.setParameterInt(prepare, this.partie, 1);
        this.setParameterBoolean(prepare, false, 2);
        ResultSet result = this.executePreparedStatement(prepare);
        if (this.aResult(result)) {
            System.out.println("charger even chapitre : " + this.getIntResultByString(result, "id_evenement"));
            return this.getIntResultByString(result, "id_evenement");
        }
        this.closeResultSet(result);
        return 0;
    }

    public int chargerEvenementQuete(int id_chapitre) {
        PreparedStatement prepare = this.prepareStatement("select * from evenements_quete where id_evenement_chapitre=? and id_partie=? "
                    + "and fini=?");
        this.setParameterInt(prepare, id_chapitre, 1);
        this.setParameterInt(prepare, this.partie, 2);
        this.setParameterBoolean(prepare, false, 3);
        ResultSet result = this.executePreparedStatement(prepare);
        
        if (this.aResult(result)) {
            System.out.println("charger even quete : " + this.getStringResultByString(result, "nom"));
            return this.getIntResultByString(result, "id_evenement");
        }
        this.closeResultSet(result);
        return 0;
    }

    public int chargerQuete(int id_quete, int id_chapitre) {
        PreparedStatement prepare = this.prepareStatement("select * from quetes where id_evenement_quete =? and id_evenement_chapitre=? "
                    + "and id_partie=? and fini=?");
        this.setParameterInt(prepare, id_quete, 1);
        this.setParameterInt(prepare, id_chapitre, 2);
        this.setParameterInt(prepare, this.partie, 3);
        this.setParameterBoolean(prepare, false, 4);
        ResultSet result = this.executePreparedStatement(prepare);
        if (this.aResult(result)) {
            System.out.println("charger quete : " + this.getStringResultByString(result, "nom"));
            return this.getIntResultByString(result, "id_evenement");
        }
        this.closeResultSet(result);
        return 0;
    }

    public int chargerQueteAnnexe(int id_chapitre) {
        PreparedStatement prepare = this.prepareStatement("select * from quetes_annexe where id_evenement_chapitre =? and id_partie=? "
                    + "and fini=? and actif=?");
        this.setParameterInt(prepare, id_chapitre, 1);
        this.setParameterInt(prepare, this.partie, 2);
        this.setParameterBoolean(prepare, false, 3);
        this.setParameterBoolean(prepare, true, 4);
        ResultSet result = this.executePreparedStatement(prepare);
        if (this.aResult(result)) {
            System.out.println("charger quete annexe : " + result);
            return this.getIntResultByString(result, "id_evenement");
        }
        this.closeResultSet(result);
        return 0;
    }
    
}
