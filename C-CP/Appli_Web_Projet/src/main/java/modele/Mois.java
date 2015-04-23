package modele;

import java.util.ArrayList;

public class Mois {
	private final String annee;
	private final String nom;
	private final ArrayList<Semaine> semaines = new ArrayList<>();

	public Mois(String annee, String nom) {
		this.annee = annee;
                this.nom = nom;
	}
        
        public void addSemaine(Semaine semaine){
            this.semaines.add(semaine);
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