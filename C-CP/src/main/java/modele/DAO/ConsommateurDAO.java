package java.modele.DAO;

import java.modele.Consommateur;
import Diagramme_de_classe.modele.Consommateu;

public class ConsommateurDAO extends UtilisateurDAO {
	private static final String INSERT_CONSOMMATEUR;
	private static final String SELECT_CONSOMMATEURS;
	private static final String UPDATE_CONSOMMATEUR;

	public Consommateur addConsommateur(String nom, String prenom, String email, String adresse) {
		throw new UnsupportedOperationException();
	}

	public void modifyConsommateur(Consommateur consommateur, String nom, String prenom, String email, String adresse) {
		throw new UnsupportedOperationException();
	}

	public List<Diagramme_de_classe.modele.Consommateu> getConsommateurs() {
		throw new UnsupportedOperationException();
	}
}