package modele.DAO;

import java.util.List;
import modele.Semaine;
import modele.Consommateur;

public class SemaineDAO extends AbstractDAO {
	private static final String INSERT_SEMAINE="";
	private static final String SELECT_SEMAINES="";
	private static final String UPDATE_SEMAINE="";

    public SemaineDAO(Object ds, String insert_query, String select_query, String update_query) {
        super(ds, insert_query, select_query, update_query);
    }

    public Semaine addSemaine(int numero) {
            throw new UnsupportedOperationException();
    }

    public void modifySemaine(Semaine semaine, int numero, Consommateur permanent1, Consommateur permanent2) {
            throw new UnsupportedOperationException();
    }

    public List<Semaine> getSemaines() {
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