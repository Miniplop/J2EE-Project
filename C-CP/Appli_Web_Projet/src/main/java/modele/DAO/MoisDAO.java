package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import modele.Mois;
import modele.Producteur;
import modele.Semaine;
import modele.Utilisateur;

public class MoisDAO extends AbstractDAO {
	private static final String INSERT_MOIS="";
	private static final String SELECT_MOIS="SELECT * FROM mois ";
	private static final String UPDATE_MOIS="UPDATE Mois SET";
	private static final String SELECT_MOIS_BY_ID="SELECT * FROM mois WHERE id = ? ";

    public MoisDAO(DataSource ds) {
        super(ds, INSERT_MOIS, SELECT_MOIS, UPDATE_MOIS);
    }

    public Mois addMois(int annee, String nom, Semaine semaine1, Semaine semaine2, Semaine semaine3, Semaine semaine4) {
            throw new UnsupportedOperationException();
    }

    public void modifyMois(Mois mois, int annee, String nom) {
            throw new UnsupportedOperationException();
    }

    public List<Mois> getMois() throws DAOException {
        final SemaineDAO semaineDAO = new SemaineDAO(dataSource);
        DAOModeleBuilder<Mois> builder = new DAOModeleBuilder<Mois>() {
            @Override
            public Mois build(ResultSet rs) throws DAOException {
                Mois mois = null;
                try {
                    mois = new Mois(rs.getString("annee"), rs.getString("nom"));
                    semaineDAO.getSemaines(mois,rs.getShort("semaine_1_id"));
                    semaineDAO.getSemaines(mois,rs.getShort("semaine_2_id"));
                    semaineDAO.getSemaines(mois,rs.getShort("semaine_3_id"));
                    semaineDAO.getSemaines(mois,rs.getShort("semaine_4_id"));
                } catch (SQLException ex) {
                    throw new DAOException(ex.getMessage(), ex);
                }
                return mois;
            }
        };
        return super.gets(builder);
    }
}