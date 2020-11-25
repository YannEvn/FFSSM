/**
 * @(#) Plongee.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Plongee {

    public Site lieu;

    public Moniteur chefDePalanquee;

    public LocalDate date;

    public int profondeur;

    public int duree;

    public Set<Plongeur> participants = new HashSet<>();

    public Plongee(Site lieu, Moniteur chefDePalanquee, LocalDate date, int profondeur, int duree) {
        this.lieu = lieu;
        this.chefDePalanquee = chefDePalanquee;
        this.date = date;
        this.profondeur = profondeur;
        this.duree = duree;
    }

    public void ajouteParticipant(Plongeur participant) {

        if (!participants.contains(participant)) {
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
    /*public boolean estConforme() throws Exception {

        Boolean conforme = false;

        if (participants.isEmpty()) {
            throw new Exception("Aucun plongeur dans la palanquée");
        }
        for (Plongeur p : participants) {

            conforme = p.getLicence().estValide(date);
            break;

        }

        return conforme;
    }*/
    public boolean estConforme() {
        boolean conforme = true;
        for (Plongeur p : participants) {
            if (!p.licences.get(p.licences.size()-1).estValide(this.getDate())) {
                conforme = false;
            }
        }
        return conforme;

    }

}
