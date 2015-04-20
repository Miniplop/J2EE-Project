package modele.DAO;

// import modele.Utilisateur;
// import Diagramme_de_classe.modele.Utilisateur;

public class UtilisateurDAO extends AbstractDAO {
	private static final String SELECT_UTILISATEURS;
	private static final String UPDATE_UTILISATEUR;

	protected void modifyUtilisateur(modele.Utilisateur utilisateur, String nom, String prenom, String email, String adresse) {
		throw new UnsupportedOperationException();
	}

	protected List<Diagramme_de_classe.modele.Utilisateur> getUtilisateurs() {
		throw new UnsupportedOperationException();
	}

	public modele.Utilisateur getUtilisateur(String email, String mdp) {
		throw new UnsupportedOperationException();
	}

	protected Object add(DAOQueryParameter setter) {
		throw new UnsupportedOperationException();
	}

	protected void modify(DAOQueryParameter setter) {
		throw new UnsupportedOperationException();
	}

	protected ResultSet get(DAOQueryParameter setter) {
		throw new UnsupportedOperationException();
	}
}