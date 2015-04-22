package modele;

import java.util.ArrayList;
import java.util.Arrays;

public class Mois {
	private final String annee;
	private final String nom;
	private final ArrayList<Semaine> semaines = new ArrayList<Semaine>();

	public Mois(String annee, String nom) {
		this.annee = annee;
                this.nom = nom;
	}
        
        public void addSemaines( Semaine ... semaines ){
            this.semaines.addAll(Arrays.asList(semaines));
        }

	public String getAnnee() {
		return this.annee;
	}

	public String getNom() {
		return this.nom;
	}
        
        public ArrayList<Semaine> getSemaines(){
            return this.semaines;
        }
}