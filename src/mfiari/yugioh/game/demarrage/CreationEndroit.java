/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mfiari.yugioh.game.demarrage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import mfiari.lib.game.liste.ListeDEndroit;
import mfiari.lib.game.objet.ObjetEndroit;
import mfiari.lib.game.personnage.Gens;
import mfiari.lib.game.position.Position;
import mfiari.lib.game.ville.Endroit;
import mfiari.yugioh.game.perso.Dueliste;
import mfiari.yugioh.game.ville.Endroits;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author mike
 */
public class CreationEndroit {

    public CreationEndroit() {
        this.modificationSalle();
        this.modificationBatiment();
        this.modificationQuartier();
        this.modificationQuartierRoute();
        this.modificationQuartierForet();
        this.modificationVille();
        this.modificationSousZone();
        this.modificationZone();
        this.modificationPays();
    }

    private void modificationSalle() {
        
    }

    private void modificationBatiment() {
        Endroits.maisonYugi_quartierHabitant_villeDebut.ajouterSalle(Endroits.bas_maisonYugi_quartierHabitant_villeDebut, 0);
        Endroits.maisonYugi_quartierHabitant_villeDebut.ajouterSalle(Endroits.chambre_maisonYugi_quartierHabitant_villeDebut, 1);
        Endroits.ecole_quartierEcole_villeDebut.ajouterSalle(Endroits.salleCours_ecole_quartierEcole_villeDebut, 0);
    }

    private void modificationQuartier() {
        Endroits.quartierEcole_villeDebut.ajouterBatiment(Endroits.ecole_quartierEcole_villeDebut);
        Endroits.quartierHabitant_villeDebut.ajouterBatiment(Endroits.maisonYugi_quartierHabitant_villeDebut);
    }

    private void modificationQuartierRoute() {
    }

    private void modificationQuartierForet() {
    }

    private void modificationVille() {
        Endroits.villeDebut.ajouterQuartier(Endroits.quartierEcole_villeDebut);
        Endroits.villeDebut.ajouterQuartier(Endroits.quartierHabitant_villeDebut);
        Endroits.villeDebut.ajouterQuartier(Endroits.quartierKaibaCorp_villeDebut);
        Endroits.villeDebut.ajouterQuartier(Endroits.quartierTournoi_villeDebut);
    }

    private void modificationSousZone() {
        Endroits.zoneVilleDebut_zoneDebut_japon.ajouterVille(Endroits.villeDebut);
    }

    private void modificationZone() {
        Endroits.zoneDebut_japon.ajouterSousZone(Endroits.zoneVilleDebut_zoneDebut_japon);
    }

    private void modificationPays() {
        Endroits.japon.ajouterZone(Endroits.zoneDebut_japon);
    }
    
    public void createXMLFile () {
        ListeDEndroit endroits = new ListeDEndroit();
        endroits.ajouterEndroit(Endroits.bas_maisonYugi_quartierHabitant_villeDebut);
        endroits.ajouterEndroit(Endroits.chambre_maisonYugi_quartierHabitant_villeDebut);
        endroits.ajouterEndroit(Endroits.salleCours_ecole_quartierEcole_villeDebut);
        endroits.ajouterEndroit(Endroits.quartierEcole_villeDebut);
        endroits.ajouterEndroit(Endroits.quartierHabitant_villeDebut);
        endroits.ajouterEndroit(Endroits.quartierKaibaCorp_villeDebut);
        endroits.ajouterEndroit(Endroits.quartierTournoi_villeDebut);
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            
            for (int k = 0 ; k < endroits.size() ; k++) {
                Endroit e = (Endroit) endroits.getEndroit(k);
                Document doc = db.newDocument();
                Element endroitElement = doc.createElement("endroit");
                endroitElement.setAttribute("nom", e.getNom());
                endroitElement.setAttribute("longueur", String.valueOf(e.getLongueur()));
                endroitElement.setAttribute("largeur", String.valueOf(e.getLongueur()));
                endroitElement.setAttribute("positionx", String.valueOf(e.getPosition().getPositionX()));
                endroitElement.setAttribute("positiony", String.valueOf(e.getPosition().getPositionY()));
                endroitElement.setAttribute("type", "route");
                /*endroitElement.setAttribute("terrain", e.getTerrain().getType().toString());*/
                
                Element solElement = doc.createElement("sol");
                solElement.setAttribute("type", "herbe");
                endroitElement.appendChild(solElement);
                
                Element objetEndroitsElement = doc.createElement("objet_endroits");
                Element batimentsElement = doc.createElement("batiments");
                Element gensElement = doc.createElement("gens");
                for (int i = 0; i < e.getLargeur(); i++) {
                    for (int j = 0; j < e.getLongueur(); j++) {
                        Position p = new Position(i, j);
                        if (e.aEndroit(p) != null) {
                            Endroit batiment = (Endroit)e.aEndroit(p);
                            Element batimentElement = doc.createElement("batiment");
                            /*batimentElement.setAttribute("type", this.getNomImageBatimentByClass(batiment));*/
                            batimentElement.setAttribute("positionx", String.valueOf(p.getPositionX()));
                            batimentElement.setAttribute("positiony", String.valueOf(p.getPositionY()));
                            batimentsElement.appendChild(batimentElement);
                        } else if (e.aGens(p) != null) {
                            Gens gens = e.aGens(p);
                            if (gens instanceof Dueliste) {
                                Element dresseurElement = doc.createElement("dueliste");
                                dresseurElement.setAttribute("nom", gens.getNom());
                                dresseurElement.setAttribute("positionx", String.valueOf(p.getPositionX()));
                                dresseurElement.setAttribute("positiony", String.valueOf(p.getPositionY()));
                                gensElement.appendChild(dresseurElement);
                            } else {
                                Element habitantElement = doc.createElement("habitant");
                                habitantElement.setAttribute("nom", gens.getNom());
                                habitantElement.setAttribute("positionx", String.valueOf(p.getPositionX()));
                                habitantElement.setAttribute("positiony", String.valueOf(p.getPositionY()));
                                gensElement.appendChild(habitantElement);
                            }
                        } else if (e.aObjetEndroit(p) != null) {
                            ObjetEndroit objet_endroit = e.aObjetEndroit(p);
                            Element objetEndroitElement = doc.createElement("objet_endroit");
                            objetEndroitElement.setAttribute("type", objet_endroit.getType().toString());
                            objetEndroitElement.setAttribute("positionx", String.valueOf(p.getPositionX()));
                            objetEndroitElement.setAttribute("positiony", String.valueOf(p.getPositionY()));
                            objetEndroitsElement.appendChild(objetEndroitElement);
                        }
                    }
                }
                endroitElement.appendChild(objetEndroitsElement);
                endroitElement.appendChild(batimentsElement);
                endroitElement.appendChild(gensElement);
                doc.appendChild(endroitElement);
                
                FileWriter fileWriter = new FileWriter(new File("media/endroits/"+k+".xml"));
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

                transformer.transform(new DOMSource(doc), new StreamResult(fileWriter));
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CreationEndroit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException | TransformerException ex) {
            throw new RuntimeException("Error converting to String", ex);
        } catch (IOException ex) {
            Logger.getLogger(CreationEndroit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
