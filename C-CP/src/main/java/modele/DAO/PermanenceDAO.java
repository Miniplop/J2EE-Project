package modele.DAO;

import java.util.List;
import javax.sql.DataSource;
import modele.Permanence;
import modele.Semaine;
import modele.Consommateur;

public class PermanenceDAO extends AbstractDAO {
    
    private static final String INSERT_PERMANENCE="";
    private static final String SELECT_PERMANENCES="";
    private static final String UPDATE_PERMANENCE="";
    private static final String SELECT_PERMANENCE="";

    public PermanenceDAO(DataSource ds) {
        super(ds, INSERT_PERMANENCE, SELECT_PERMANENCES, UPDATE_PERMANENCE, SELECT_PERMANENCE);
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
}