package java.modele.DAO;

// import java.modele.Permanence;
import java.modele.Semaine;
import java.modele.Consommateur;
// import Diagramme_de_classe.modele.Permanence;

public class PermanenceDAO extends AbstractDAO {
	private static final String INSERT_PERMANENCE;
	private static final String SELECT_PERMANENCES;
	private static final String UPDATE_PERMANENCE;

	public java.modele.Permanence addPermanence(Semaine semaine, Consommateur permanent1, Consommateur permanent2) {
		throw new UnsupportedOperationException();
	}

	public void modifyPermanence(Consommateur permanent1, Consommateur permanent2) {
		throw new UnsupportedOperationException();
	}

	public List<Diagramme_de_classe.modele.Permanence> getPermanences() {
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