package modele.DAO;

import modele.Producteur;
import modele.Utilisateur;

public class ProducteurDAO extends UtilisateurDAO {
	private static final String SELECT_PRODUCTEURS;
	private static final String UPDATE_PRODUCTEUR;

	public void modifyProducteur(Producteur producteur, String nom, String prenom, String adresse, String email) {
		throw new UnsupportedOperationException();
	}

	public List<Producteur> getProducteurs() {
		throw new UnsupportedOperationException();
	}

	public Utilisateur getUtilisateur(String email, String mdp) {
		throw new UnsupportedOperationException();
	}
}