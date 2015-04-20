package modele.DAO;

// import modele.Contrat;
import modele.Produit;
import modele.Semaine;
// import Diagramme_de_classe.modele.Contrat;

public class ContratDAO extends AbstractDAO {
	private static final String INSERT_CONTRAT;
	private static final String SELECT_CONTRATS;
	private static final String UPDATE_CONTRAT;

	public modele.Contrat addContrat(int quantite, boolean valide, Produit produit, int consommateur_id) {
		throw new UnsupportedOperationException();
	}

	public void modifyContrat(modele.Contrat contrat, boolean valide, Semaine semaineDebut) {
		throw new UnsupportedOperationException();
	}

	public List<Diagramme_de_classe.modele.Contrat> getContrats() {
		throw new UnsupportedOperationException();
	}

	public List<modele.Contrat> getContrat(int produit_id, int consommateurId) {
		throw new UnsupportedOperationException();
	}

	public modele.Contrat getContratById(int id) {
		throw new UnsupportedOperationException();
	}

	public List<modele.Contrat> getContrats(int produit_id) {
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