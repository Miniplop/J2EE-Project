package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import modele.Semaine;
import modele.Consommateur;
import modele.Mois;

public class SemaineDAO extends AbstractDAO {
	private static final String INSERT_SEMAINE="INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES (?,NULL,NULL)";
        private static final String SELECT_SEMAINES="SELECT * FROM semaine ";
	private static final String UPDATE_SEMAINE="";
	private static final String SELECT_SEMAINE="SELECT * FROM semaine WHERE id = ? ";

    public SemaineDAO(DataSource ds) {
        super(ds, INSERT_SEMAINE, SELECT_SEMAINES, UPDATE_SEMAINE);
    }

    public Semaine addSemaine(final int numero) throws DAOException {
            DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws DAOException {
                try {
                    statement.setInt(1,numero);
                } catch (SQLException ex) {
                    throw new DAOException(ex.getMessage(), ex);
                }
            }
        };
        super.add(setter);
        Statement statement = null;
        ResultSet generatedKeys = null;
        int id = 0;
        try {
            Connection conn = getConnection();
            statement = conn.createStatement();
            generatedKeys = statement.executeQuery("SELECT seq_semaine.currval from dual");
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating object failed, no generated key obtained.");
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
        if(id == 0)
            return null;
        return getSemaine(id);
    }

    public void modifySemaine(Semaine semaine, int numero, Consommateur permanent1, Consommateur permanent2) {
            throw new UnsupportedOperationException();
    }

    public Semaine getSemaine(final Mois mois, final int id) throws DAOException {
        final ConsommateurDAO consommateurDAO = new ConsommateurDAO(dataSource);
        DAOModeleBuilder<Semaine> builder = new DAOModeleBuilder<Semaine>() {     
                @Override
                public Semaine build(ResultSet rs) throws DAOException {
                    try {
                        Consommateur permanent1 = consommateurDAO.getConsommateur(rs.getInt("consommateur_1_id"));
                        Consommateur permanent2 = consommateurDAO.getConsommateur(rs.getInt("consommateur_2_id"));
                        return new Semaine((short) rs.getInt("id"), rs.getInt("numero"), permanent1, permanent2, mois);
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
        final ConsommateurDAO consommateurDAO = new ConsommateurDAO(dataSource);
                
        DAOModeleBuilder<Semaine> builder = new DAOModeleBuilder<Semaine>() {     
                @Override
                public Semaine build(ResultSet rs) throws DAOException {
                    try {
                        Mois mois = moisDAO.getMoisBySemaineId(rs.getInt("id"));
                        Consommateur permanent1 = consommateurDAO.getConsommateur(rs.getInt("consommateur_1_id"));
                        Consommateur permanent2 = consommateurDAO.getConsommateur(rs.getInt("consommateur_2_id"));
                        return new Semaine((short) rs.getInt("id"), rs.getInt("numero"), permanent1, permanent2, mois);
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

    Semaine getLastSemaineAdded() throws DAOException {
        Statement statement;
        ResultSet generatedKeys;
        int id = 0;
        try {
            Connection conn = getConnection();
            statement = conn.createStatement();
            generatedKeys = statement.executeQuery("SELECT max(id) from semaine");
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating object failed, no generated key obtained.");
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
        return this.getSemaine(id);
    }
}