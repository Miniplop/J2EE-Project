package modele.DAO;

// import modele.Mois;
import modele.Semaine;
// import Diagramme_de_classe.modele.Mois;

public class MoisDAO extends AbstractDAO {
	private static final String INSERT_MOIS;
	private static final String SELECT_MOIS;
	private static final String UPDATE_MOIS;

	public modele.Mois addMois(int annee, String nom, Semaine semaine1, Semaine semaine2, Semaine semaine3, Semaine semaine4) {
		throw new UnsupportedOperationException();
	}

	public void modifyMois(modele.Mois mois, int annee, String nom) {
		throw new UnsupportedOperationException();
	}

	public List<Diagramme_de_classe.modele.Mois> getMois() {
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