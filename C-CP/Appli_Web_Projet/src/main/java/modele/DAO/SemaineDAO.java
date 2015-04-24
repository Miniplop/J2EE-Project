package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import modele.Semaine;
import modele.Consommateur;
import modele.Mois;

public class SemaineDAO extends AbstractDAO<Semaine> {
	private static final String INSERT_SEMAINE="INSERT INTO Semaine(numero,consommateur_1_id,consommateur_2_id) VALUES (?,NULL,NULL)";
        private static final String SELECT_SEMAINES="SELECT * FROM Semaine ";
	private static final String SELECT_SEMAINE="SELECT * FROM Semaine WHERE id = ? ";

    public SemaineDAO(DataSource ds) {
        super(ds, INSERT_SEMAINE, SELECT_SEMAINES, null);
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

    public void modifySemaine(final Semaine semaine, final Consommateur permanent, int numero) throws DAOException {
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, permanent.getId());
                statement.setInt(2, semaine.getId());
            }
        };
        super.modify(setter, "UPDATE Semaine SET consommateur_"+numero+"_id = ? WHERE id = ?");
    }
    public void modifySemainePermanent1(Semaine semaine, Consommateur permanent1) throws DAOException {
        modifySemaine(semaine, permanent1, 1);
    }

    public void modifySemainePermanent2(Semaine semaine, Consommateur permanent2) throws DAOException { 
        modifySemaine(semaine, permanent2, 2);
    }

    public Semaine getSemaine(final Mois mois, final int id) throws DAOException {
        DAOModeleBuilder<Semaine> builder = new DAOModeleBuilder<Semaine>() {     
            @Override
            public Semaine build(ResultSet rs) throws DAOException, SQLException {
                Semaine semaine = new Semaine((short) rs.getInt("id"), rs.getInt("numero"), rs.getInt("consommateur_1_id"), rs.getInt("consommateur_2_id"));
                semaine.setMois(mois);
                return semaine;
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
    
    protected int getCount(int id) throws DAOException {
        
        Statement statement = null;
        ResultSet generatedKeys = null;
        try {
            Connection conn = getConnection();
            statement = conn.createStatement();
            generatedKeys = statement.executeQuery("SELECT COUNT (id) FROM (SELECT * FROM Semaine WHERE consommateur_1_id = "+id+" OR consommateur_2_id="+id+" ) ");
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating object failed, no generated key obtained.");
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
        return id;
    }
    
    public int getNombreSemaineByConsommateur(int id ) throws DAOException{
        return this.getCount(id);
    }
    
    public Semaine getSemaine(final int id) throws DAOException {
        DAOModeleBuilder<Semaine> builder = new DAOModeleBuilder<Semaine>() {     
            @Override
            public Semaine build(ResultSet rs) throws DAOException, SQLException {
                return new Semaine((short) rs.getInt("id"), rs.getInt("numero"), rs.getInt("consommateur_1_id"), rs.getInt("consommateur_2_id"));
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