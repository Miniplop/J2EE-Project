package modele.DAO;

import java.util.List;
import modele.Permanence;
import modele.Semaine;
import modele.Consommateur;

public class PermanenceDAO extends AbstractDAO {
    
    private static final String INSERT_PERMANENCE="";
    private static final String SELECT_PERMANENCES="";
    private static final String UPDATE_PERMANENCE="";

    public PermanenceDAO(Object ds, String insert_query, String select_query, String update_query) {
        super(ds, insert_query, select_query, update_query);
    }

    public Permanence addPermanence(Semaine semaine, Consommateur permanent1, Consommateur permanent2) {
            throw new UnsupportedOperationException();
    }

    public void modifyPermanence(Consommateur permanent1, Consommateur permanent2) {
            throw new UnsupportedOperationException();
    }

    public List<Permanence> getPermanences() {
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