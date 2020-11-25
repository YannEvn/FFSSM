package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;

public class Plongeur extends Personne {
	
    public int niveau;
    public ArrayList<Licence> licences = new ArrayList<>();
    
    public Plongeur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, int niveau) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
        this.niveau = niveau;
    }
    
    public void ajouteLicence(String numero, LocalDate delivrance, Club club) {
        
        licences.add(new Licence(this, numero, delivrance, niveau, club));      
    }
    
    public Licence getLicence() {
        
        return licences.get(licences.size()-1);
        
    }
}
