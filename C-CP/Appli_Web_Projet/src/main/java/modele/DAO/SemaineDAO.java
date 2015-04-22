package modele.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import modele.Semaine;
import modele.Consommateur;
import modele.Mois;

public class SemaineDAO extends AbstractDAO {
	private static final String INSERT_SEMAINE="";
        private static final String SELECT_SEMAINES="SELECT * FROM semaine ";
	private static final String UPDATE_SEMAINE="";
	private static final String SELECT_SEMAINE="SELECT * FROM semaine WHERE id = ? ";

    public SemaineDAO(DataSource ds) {
        super(ds, INSERT_SEMAINE, SELECT_SEMAINES, UPDATE_SEMAINE);
    }

    public Semaine addSemaine(int numero) {
            throw new UnsupportedOperationException();
    }

    public void modifySemaine(Semaine semaine, int numero, Consommateur permanent1, Consommateur permanent2) {
            throw new UnsupportedOperationException();
    }

    public Semaine getSemaine(final Mois mois, final int id) throws DAOException {
        DAOModeleBuilder<Semaine> builder = new DAOModeleBuilder<Semaine>() {     
                @Override
                public Semaine build(ResultSet rs) throws DAOException {
                    try {
                        return new Semaine((short) rs.getInt("id"), rs.getInt("numero"), rs.getInt("consommateur_1_id"), rs.getInt("consommateur_2_id"), mois);
                    } catch (SQLException ex) {
                        throw new DAOException(ex.getMessage(), ex);
                    }
                }
            };
        DAOQueryParameter setter = new DAOQueryParameter() {

            @Override
            public void set(PreparedStatement statement) throws DAOException {
                try {
                    statement.setInt(1, id);
                } catch (SQLException ex) {
                        throw new DAOException(ex.getMessage(), ex);
                }
            }
        };
        return (Semaine) super.getSingle(builder, setter, SemaineDAO.SELECT_SEMAINE);
    }

    public Semaine getSemaine(final int id) throws DAOException {
        final MoisDAO moisDAO = new MoisDAO(dataSource);
                
        DAOModeleBuilder<Semaine> builder = new DAOModeleBuilder<Semaine>() {     
                @Override
                public Semaine build(ResultSet rs) throws DAOException {
                    try {
                        Mois mois = moisDAO.getMoisBySemaineId(rs.getInt("id"));
                        return new Semaine((short) rs.getInt("id"), rs.getInt("numero"), rs.getInt("consommateur_1_id"), rs.getInt("consommateur_2_id"), mois);
                    } catch (SQLException ex) {
                        throw new DAOException(ex.getMessage(), ex);
                    }
                }
            };
        DAOQueryParameter setter = new DAOQueryParameter() {

            @Override
            public void set(PreparedStatement statement) throws DAOException {
                try {
                    statement.setInt(1, id);
                } catch (SQLException ex) {
                        throw new DAOException(ex.getMessage(), ex);
                }
            }
        };
        return (Semaine) this.getSingle(builder, setter, SemaineDAO.SELECT_SEMAINE);
    }
}