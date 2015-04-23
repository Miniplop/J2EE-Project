package modele.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import modele.Mois;
import modele.Semaine;


public class MoisDAO extends AbstractDAO<Mois> {
	private static final String INSERT_MOIS="INSERT INTO Mois (annee, nom, semaine_1_id, semaine_2_id, semaine_3_id, semaine_4_id) VALUES (?,?,?,?,?,?)";
	private static final String SELECT_MOIS="SELECT * FROM mois ";
	private static final String SELECT_MOIS_BY_ID="SELECT * FROM mois WHERE id = ? ";
	private static final String SELECT_MOIS_BY_SEMAINE_ID="SELECT * FROM mois WHERE semaine_1_id = ? OR semaine_2_id = ? OR semaine_3_id = ? OR semaine_4_id = ?";

    public MoisDAO(DataSource ds) {
        super(ds, INSERT_MOIS, SELECT_MOIS, null);
    }

    public Mois addMois(final String annee, final String nom) throws DAOException  {
        SemaineDAO semaineDAO = new SemaineDAO(dataSource);
        final Semaine sem1 = semaineDAO.addSemaine(1);
        final Semaine sem2 = semaineDAO.addSemaine(2);
        final Semaine sem3 = semaineDAO.addSemaine(3);
        final Semaine sem4 = semaineDAO.addSemaine(4);
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1,Integer.parseInt(annee));
                statement.setString(2, nom);
                statement.setInt(3, sem1.getId());
                statement.setInt(4, sem2.getId());
                statement.setInt(5, sem3.getId());
                statement.setInt(6, sem4.getId());
            }
        };
        super.add(setter);
        return new Mois(annee, nom);
    }

    public List<Mois> getMois() throws DAOException {
        final SemaineDAO semaineDAO = new SemaineDAO(dataSource);
        DAOModeleBuilder<Mois> builder = new DAOModeleBuilder<Mois>() {
            @Override
            public Mois build(ResultSet rs) throws SQLException, DAOException {
                Mois mois = new Mois(rs.getString("annee"), rs.getString("nom"));
                semaineDAO.getSemaine(mois, rs.getShort("semaine_1_id"));
                semaineDAO.getSemaine(mois, rs.getShort("semaine_2_id"));
                semaineDAO.getSemaine(mois, rs.getShort("semaine_3_id"));
                semaineDAO.getSemaine(mois, rs.getShort("semaine_4_id"));
                return mois;
            }
        };
        Collections.sort(super.gets(builder));
        return super.gets(builder);
    }

    Mois getMoisBySemaineId(final int semaine_id) throws DAOException {
        final SemaineDAO semaineDAO = new SemaineDAO(dataSource);
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, semaine_id);
                statement.setInt(2, semaine_id);
                statement.setInt(3, semaine_id);
                statement.setInt(4, semaine_id);
            }
        };
         DAOModeleBuilder<Mois> builder = new DAOModeleBuilder<Mois>() {
                @Override
                public Mois build(ResultSet rs) throws DAOException, SQLException {
                    Mois mois = new Mois(rs.getString("annee"), rs.getString("nom"));
                    semaineDAO.getSemaine(mois, rs.getShort("semaine_1_id"));
                    semaineDAO.getSemaine(mois, rs.getShort("semaine_2_id"));
                    semaineDAO.getSemaine(mois, rs.getShort("semaine_3_id"));
                    semaineDAO.getSemaine(mois, rs.getShort("semaine_4_id"));
                    return mois;
                }
            };
        
        return super.getSingle(builder, setter, MoisDAO.SELECT_MOIS_BY_SEMAINE_ID);
    }

    public Mois getLastMois() throws DAOException {
        SemaineDAO semaineDAO = new SemaineDAO(dataSource);
        Semaine semaine = semaineDAO.getLastSemaineAdded();
        return this.getMoisBySemaineId(semaine.getId());
    }
}