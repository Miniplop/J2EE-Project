package java.modele;

import java.util.ArrayList;
import java.modele.Semaine;

public class Mois {
	private final String annee;
	private final String nom;
	private ArrayList<Semaine> semaines = new ArrayList<Semaine>();

	public Mois(String annee, String nom) {
		throw new UnsupportedOperationException();
	}

	public String getAnnee() {
		return this.annee;
	}

	public String getNom() {
		return this.nom;
	}
}