package modele.DAO;

import java.util.List;
import javax.sql.DataSource;
import modele.Semaine;
import modele.Consommateur;

public class SemaineDAO extends AbstractDAO {
	private static final String INSERT_SEMAINE="";
	private static final String SELECT_SEMAINES="";
	private static final String UPDATE_SEMAINE="";
	private static final String SELECT_SEMAINE="";

    public SemaineDAO(DataSource ds) {
        super(ds, INSERT_SEMAINE, SELECT_SEMAINES, UPDATE_SEMAINE, SELECT_SEMAINE);
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

    Semaine getSemaine(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}