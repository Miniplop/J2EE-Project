package modele.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void modifySemaine(Semaine semaine, Consommateur permanent, int numero) throws DAOException {
        String UPDATE_SEMAINE = "UPDATE Semaine SET consommateur_"+numero+"_id == "+permanent.getId()+" WHERE ";
        this.modify(null, UPDATE_SEMAINE);
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
                Semaine semaine = new Semaine((short) rs.getInt("id"), rs.getInt("numero"));
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

    public Semaine getSemaine(final int id) throws DAOException {
        DAOModeleBuilder<Semaine> builder = new DAOModeleBuilder<Semaine>() {     
            @Override
            public Semaine build(ResultSet rs) throws DAOException, SQLException {
                return new Semaine((short) rs.getInt("id"), rs.getInt("numero"));
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