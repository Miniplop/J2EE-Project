package modele.DAO;

import modele.Consommateur;
import Diagramme_de_classe.modele.Consommateu;
import modele.Utilisateur;

public class ConsommateurDAO extends UtilisateurDAO {
	private static final String SELECT_CONSOMMATEURS;
	private static final String UPDATE_CONSOMMATEUR;

	public void modifyConsommateur(Consommateur consommateur, String nom, String prenom, String email, String adresse) {
		throw new UnsupportedOperationException();
	}

	public List<Diagramme_de_classe.modele.Consommateu> getConsommateurs() {
		throw new UnsupportedOperationException();
	}

	public Utilisateur getUtilisateur(String email, String mdp) {
		throw new UnsupportedOperationException();
	}
}