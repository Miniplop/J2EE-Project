package modele.DAO;

import java.util.List;
import javax.sql.DataSource;
import modele.Mois;
import modele.Semaine;

public class MoisDAO extends AbstractDAO {
	private static final String INSERT_MOIS="";
	private static final String SELECT_MOIS="SELECT * FROM mois ";
	private static final String UPDATE_MOIS="UPDATE Mois SET";
	private static final String SELECT_MOIS_BY_ID="SELECT * FROM mois WHERE id = ? ";

    public MoisDAO(DataSource ds) {
        super(ds, INSERT_MOIS, SELECT_MOIS, UPDATE_MOIS, SELECT_MOIS_BY_ID);
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
}