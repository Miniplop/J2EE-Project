package java.modele.DAO;

// import java.modele.Utilisateur;
// import Diagramme_de_classe.modele.Utilisateur;

public class UtilisateurDAO extends AbstractDAO {
	private static final String INSERT_UTILISATEUR;
	private static final String SELECT_UTILISATEURS;
	private static final String UPDATE_UTILISATEUR;

	protected java.modele.Utilisateur addUtilisateur(String nom, String prenom, String email, String adresse) {
		throw new UnsupportedOperationException();
	}

	protected void modifyUtilisateur(java.modele.Utilisateur utilisateur, String nom, String prenom, String email, String adresse) {
		throw new UnsupportedOperationException();
	}

	protected List<Diagramme_de_classe.modele.Utilisateur> getUtilisateurs() {
		throw new UnsupportedOperationException();
	}

	protected Object add(DAOQueryParameter setter) {
		throw new UnsupportedOperationException();
	}

	protected void modify(DAOQueryParameter setter) {
		throw new UnsupportedOperationException();
	}

	protected List get(DAOQueryParameter setter) {
		throw new UnsupportedOperationException();
	}
}