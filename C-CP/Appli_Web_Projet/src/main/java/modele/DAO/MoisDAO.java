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
import modele.Produit;
import modele.Semaine;
import modele.Utilisateur;

public class MoisDAO extends AbstractDAO {
	private static final String INSERT_MOIS="";
	private static final String SELECT_MOIS="SELECT * FROM mois ";
	private static final String UPDATE_MOIS="UPDATE Mois SET";
	private static final String SELECT_MOIS_BY_ID="SELECT * FROM mois WHERE id = ? ";
	private static final String SELECT_MOIS_BY_SEMAINE_ID="SELECT * FROM mois WHERE semaine_1_id = ? OR semaine_2_id = ? OR semaine_3_id = ? OR semaine_4_id = ?";

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
                    semaineDAO.getSemaine(mois, rs.getShort("semaine_1_id"));
                    semaineDAO.getSemaine(mois, rs.getShort("semaine_2_id"));
                    semaineDAO.getSemaine(mois, rs.getShort("semaine_3_id"));
                    semaineDAO.getSemaine(mois, rs.getShort("semaine_4_id"));
                } catch (SQLException ex) {
                    throw new DAOException(ex.getMessage(), ex);
                }
                return mois;
            }
        };
        return super.gets(builder);
    }

    Mois getMoisBySemaineId(final int semaine_id) throws DAOException {
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws DAOException {
                try {
                    statement.setInt(1, semaine_id);
                    statement.setInt(2, semaine_id);
                    statement.setInt(3, semaine_id);
                    statement.setInt(4, semaine_id);
                } catch (SQLException ex) {
                    throw new DAOException(ex.getMessage(), ex);
                }
            }
        };
         DAOModeleBuilder<Mois> builder = new DAOModeleBuilder<Mois>() {
                @Override
                public Mois build(ResultSet rs) throws DAOException {
                    try {
                        return new Mois(rs.getString("annee"), rs.getString("nom"));
                    } catch (SQLException ex) {
                        throw new DAOException(ex.getMessage(), ex);
                    }
                }
            };
        
        return (Mois) super.getSingle(builder, setter, MoisDAO.SELECT_MOIS_BY_SEMAINE_ID);
    }
}