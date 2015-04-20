package modele.DAO;

import modele.Permanence;
import modele.Semaine;
import modele.Consommateur;

public class PermanenceDAO extends AbstractDAO {
	private static final String INSERT_PERMANENCE;
	private static final String SELECT_PERMANENCES;
	private static final String UPDATE_PERMANENCE;

	public Permanence addPermanence(Semaine semaine, Consommateur permanent1, Consommateur permanent2) {
		throw new UnsupportedOperationException();
	}

	public void modifyPermanence(Consommateur permanent1, Consommateur permanent2) {
		throw new UnsupportedOperationException();
	}

	public List<Permanence> getPermanences() {
		throw new UnsupportedOperationException();
	}

	public List<Permanence> getPermanences(int consommateurId, int annee, String mois) {
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