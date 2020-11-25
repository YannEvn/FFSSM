/**
 * @(#) Plongee.java
 */
package FFSSM;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Plongee {
    
    public Site lieu;
    
    public Moniteur chefDePalanquee;
    
    public LocalDate date;
    
    public int profondeur;
    
    public int duree;
    
    public ArrayList<Plongeur> participants = new ArrayList<>();
    
    public Plongee(Site lieu, Moniteur chefDePalanquee, LocalDate date, int profondeur, int duree) {
        this.lieu = lieu;
        this.chefDePalanquee = chefDePalanquee;
        this.date = date;
        this.profondeur = profondeur;
        this.duree = duree;
    }
    
    public void ajouteParticipant(Plongeur participant) {
        
        if (!participants.contains(participant) && participant.getLicence().estValide(LocalDate.now())) {
            participants.add(participant);
        }
    }
    
    public LocalDate getDate() {
        return date;
    }

    /**
     * Détermine si la plongée est conforme. Une plongée est conforme si tous
     * les plongeurs de la palanquée ont une licence valide à la date de la
     * plongée
     *
     * @return vrai si la plongée est conforme
     */
    public boolean estConforme() {
        // TODO: Implémenter cette méthode
        throw new UnsupportedOperationException("Pas encore implémenté");
    }
    
}
