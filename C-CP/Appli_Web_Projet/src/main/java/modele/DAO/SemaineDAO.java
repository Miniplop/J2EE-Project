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
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1,numero);
            }
        };
        super.add(setter);
        int id = super.getLastId("Semaine");
        return getSemaine(id);
    }

    public void modifySemaine(Semaine semaine, int numero, Consommateur permanent1, Consommateur permanent2) {
            throw new UnsupportedOperationException();
    }

    public Semaine getSemaine(final Mois mois, final int id) throws DAOException {
        final ConsommateurDAO consommateurDAO = new ConsommateurDAO(dataSource);
        DAOModeleBuilder<Semaine> builder = new DAOModeleBuilder<Semaine>() {     
            @Override
            public Semaine build(ResultSet rs) throws DAOException, SQLException {
                Consommateur permanent1 = consommateurDAO.getConsommateur(rs.getInt("consommateur_1_id"));
                Consommateur permanent2 = consommateurDAO.getConsommateur(rs.getInt("consommateur_2_id"));
                return new Semaine((short) rs.getInt("id"), rs.getInt("numero"), permanent1, permanent2, mois);
            }
        };
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, id);
            }
        };
        return (Semaine) super.getSingle(builder, setter, SemaineDAO.SELECT_SEMAINE);
    }

    public Semaine getSemaine(final int id) throws DAOException {
        final MoisDAO moisDAO = new MoisDAO(dataSource);
        final ConsommateurDAO consommateurDAO = new ConsommateurDAO(dataSource);
                
        DAOModeleBuilder<Semaine> builder = new DAOModeleBuilder<Semaine>() {     
            @Override
            public Semaine build(ResultSet rs) throws DAOException, SQLException {
                Mois mois = moisDAO.getMoisBySemaineId(rs.getInt("id"));
                Consommateur permanent1 = consommateurDAO.getConsommateur(rs.getInt("consommateur_1_id"));
                Consommateur permanent2 = consommateurDAO.getConsommateur(rs.getInt("consommateur_2_id"));
                return new Semaine((short) rs.getInt("id"), rs.getInt("numero"), permanent1, permanent2, mois);
            }
        };
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, id);
            }
        };
        return (Semaine) this.getSingle(builder, setter, SemaineDAO.SELECT_SEMAINE);
    }

    Semaine getLastSemaineAdded() throws DAOException {
        int id = super.getLastId("Semaine");
        return this.getSemaine(id);
    }
}