/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FFSSM;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.BeforeEach;

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
    Plongeur p1;
    Moniteur m1;

    Plongee plongee1;
    Site site1;

    @BeforeEach
    public void setUp() throws Exception {
        p1 = new Plongeur("154785", "ALBERT", "Paul", "12 avenue de la Mer", "0101010101", LocalDate.of(1970, Month.MARCH, 3), 0, GroupeSanguin.BMOINS);
        m1 = new Moniteur("154785", "EVENO", "Yann", "15 avenue de la Mer", "0707070707", LocalDate.of(1975, Month.FEBRUARY, 7), 15487, GroupeSanguin.APLUS);
        c1 = new Club(m1, "SudPlongee", "0563504895");

        site1 = new Site("Port de Toulon", "Initiation Adultes");
        plongee1 = new Plongee(site1, m1, LocalDate.now(), 25, 15);
    }
    

}
