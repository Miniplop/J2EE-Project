package modele.DAO;

// import modele.Semaine;
import modele.Consommateur;
// import Diagramme_de_classe.modele.Semaine;

public class SemaineDAO extends AbstractDAO {
	private static final String INSERT_SEMAINE;
	private static final String SELECT_SEMAINES;
	private static final String UPDATE_SEMAINE;

	public modele.Semaine addSemaine(int numero) {
		throw new UnsupportedOperationException();
	}

	public void modifySemaine(modele.Semaine semaine, int numero, Consommateur permanent1, Consommateur permanent2) {
		throw new UnsupportedOperationException();
	}

	public List<Diagramme_de_classe.modele.Semaine> getSemaines() {
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