/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FFSSM;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Yann
 */
public class FFSSMTest {

    /*
    Test Date - 1 an
    Test Ajout Participant Plongée
    
     */
    Club c1;
    Plongeur p1, p2;
    Moniteur m1, m2;

    Embauche e1;

    Plongee plongee1;
    Site site1;

    @BeforeEach
    public void setUp() throws Exception {

        p1 = new Plongeur("154785", "ALBERT", "Paul", "12 avenue de la Mer", "0101010101", LocalDate.of(1970, Month.MARCH, 3), 0, GroupeSanguin.BMOINS);
        p2 = new Plongeur("154786", "GUIRAUD", "Guy", "93 route du Port", "0101010102", LocalDate.of(1970, Month.MARCH, 2), 1, GroupeSanguin.AMOINS);

        m1 = new Moniteur("154784", "EVENO", "Yann", "15 avenue de la Mer", "0707070707", LocalDate.of(1975, Month.FEBRUARY, 7), 15487, GroupeSanguin.APLUS);
        m2 = new Moniteur("467864", "DURAND", "Pierre", "78 Chemin des Mouettes", "0808080808", LocalDate.of(1982, Month.AUGUST, 28), 24657, GroupeSanguin.BPLUS);

        c1 = new Club(m1, "SudPlongee", "0563504895");
    }

    @Test
    public void testEmployeurActuel() throws Exception {

        m2.nouvelleEmbauche(c1, LocalDate.now());
        assertEquals(c1, m2.employeurActuel().get(), "Pas le même employeur");

    }

    public void plongeurAjoutLicenceEtLicenceValide() {
        assertTrue(p1.licences.isEmpty());
        p1.ajouteLicence("0", LocalDate.now(), c1);
        assertFalse(p1.licences.isEmpty());
        assertTrue(p1.licences.get(0).estValide(LocalDate.now().plusMonths(11)));
        assertFalse(p1.licences.get(0).estValide(LocalDate.now().plusMonths(12)));
    }

    @Test
    public void ajoutPlongeeEtConforme() {

        Site s1 = new Site("Port de Toulon", "Initiation adultes");
        Plongee plongee1 = new Plongee(s1, m1, LocalDate.of(2021, 03, 12), 25, 20);

        //Les plongées du Club1 sont vides
        assertTrue(c1.plongees.isEmpty());
        //On ajoute une plongée
        c1.organisePlongee(plongee1);
        //Les plongées ne sont plus vides
        assertFalse(c1.plongees.isEmpty());

        //On ajoute une licence valide au Plongeur1
        p1.ajouteLicence("0", LocalDate.now(), c1);
        //On ajoute une licence invalide au Plongeur2
        p2.ajouteLicence("0", LocalDate.of(2019, 11, 25), c1);

        //Aucun plongeur dans la plongée
        assertTrue(plongee1.participants.isEmpty());

        //On ajoute le Plongeur1 (valide) à la plongée
        plongee1.ajouteParticipant(p1);

        //La plongée n'est plus vide
        assertTrue(!plongee1.participants.isEmpty());
        //Les plongeurs ont une licence valide
        assertTrue(plongee1.estConforme());

        //On ajoute le Plongeur2 (invalide) à la plongée
        plongee1.ajouteParticipant(p2);
        //La plongée n'est plus valide
        assertFalse(plongee1.estConforme()); // plongee1 n'est plus conforme
    }

    @Test
    public void emplois() {
        //Le moniteur1 n'a pas d'embauche
        assertTrue(m1.emplois.isEmpty());
        //On lui en ajoute une
        m1.nouvelleEmbauche(c1, LocalDate.now());
        //Il est embauché
        assertTrue(!m1.emplois.isEmpty());

        //On suppose qu'il a un employeur
        Optional<Club> employeur = Optional.of(m1.emplois.get(m1.emplois.size() - 1).getEmployeur());

        //Les deux employeurs sont égaux
        assertEquals(Optional.ofNullable(m1.emplois.get(m1.emplois.size() - 1).getEmployeur()), employeur);

        //La méthode emplois() renvoie la bonne liste
        assertTrue(m1.emplois() == m1.emplois);

        //On termine son emploi à la date actuelle
        m1.emplois.get(m1.emplois.size() - 1).terminer(LocalDate.now());

        //Vérification de fin d'emploi
        assertTrue(m1.emplois.get(m1.emplois.size() - 1).estTerminee());
    }

    @Test
    public void listePlongeesNonConforme() throws Exception {
        
        Site s1 = new Site("Calanques Marseille", "Sortie longue");
        
        Plongee plongee1 = new Plongee(s1, m1, LocalDate.of(2021, 01, 18), 15, 45);
        Plongee plongee2 = new Plongee(s1, m1, LocalDate.of(2020, 12, 24), 6, 2);
        
        //Licence Valide
        p1.ajouteLicence("0", LocalDate.now(), c1); 
        //Licence Non Valide
        p2.ajouteLicence("0", LocalDate.of(2019, 11, 25), c1); 
        
        //Ajoutdu Plongeur1 à la Plongée1
        plongee1.ajouteParticipant(p1);
        //Ajoutdu Plongeur2 à la Plongée2
        plongee2.ajouteParticipant(p2);
        
        //Le Club1 Organise les deux plongées
        c1.organisePlongee(plongee1);
        c1.organisePlongee(plongee2);
        
        assertTrue(c1.plongeesNonConformes().contains(plongee2)
                && !c1.plongeesNonConformes().contains(plongee1));
        // On vérifie que plongeesNonConformes() renvoit une HashSet contenant plongee2 mais pas plongee 1
    }

}
