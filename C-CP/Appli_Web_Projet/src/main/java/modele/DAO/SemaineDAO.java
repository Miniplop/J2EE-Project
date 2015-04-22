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
	private static final String INSERT_SEMAINE="INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES (?,?,?)";
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
                    statement.setNull(2, java.sql.Types.INTEGER);
                    statement.setNull(3,java.sql.Types.INTEGER);
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
        return new Semaine((short)id,0,0,0,null);
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

    Semaine getSemaine(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}