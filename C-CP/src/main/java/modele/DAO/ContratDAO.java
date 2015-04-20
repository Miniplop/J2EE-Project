package java.modele.DAO;

// import java.modele.Contrat;
import java.modele.Produit;
import java.modele.Consommateur;
import java.modele.Semaine;
// import Diagramme_de_classe.modele.Contrat;

public class ContratDAO extends AbstractDAO {
	private static final String INSERT_CONTRAT;
	private static final String SELECT_CONTRATS;
	private static final String UPDATE_CONTRAT;

	public java.modele.Contrat addContrat(int quantite, boolean valide, Produit produit, Consommateur consommateur) {
		throw new UnsupportedOperationException();
	}

	public void modifyContrat(java.modele.Contrat contrat, boolean valide, Semaine semaineDebut) {
		throw new UnsupportedOperationException();
	}

	public List<Diagramme_de_classe.modele.Contrat> getContrats() {
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