package modele.DAO;

import java.util.List;
import modele.Mois;
import modele.Semaine;

public class MoisDAO extends AbstractDAO {
	private static final String INSERT_MOIS="";
	private static final String SELECT_MOIS="";
	private static final String UPDATE_MOIS="";

    public MoisDAO(Object ds, String insert_query, String select_query, String update_query) {
        super(ds, insert_query, select_query, update_query);
    }

    public Mois addMois(int annee, String nom, Semaine semaine1, Semaine semaine2, Semaine semaine3, Semaine semaine4) {
            throw new UnsupportedOperationException();
    }

    public void modifyMois(Mois mois, int annee, String nom) {
            throw new UnsupportedOperationException();
    }

    public List<Mois> getMois() {
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