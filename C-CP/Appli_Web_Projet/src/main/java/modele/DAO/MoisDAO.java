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

    public Mois addMois(final String annee, final String nom, final Semaine semaine1, final Semaine semaine2, final Semaine semaine3, final Semaine semaine4) throws DAOException  {

        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1,Integer.parseInt(annee));
                statement.setString(2, nom);
                statement.setInt(3, semaine1.getId());
                statement.setInt(4, semaine2.getId());
                statement.setInt(5, semaine2.getId());
                statement.setInt(6, semaine4.getId());
            }
        };
        super.add(setter);
        return new Mois(annee, nom);
    }

    public List<Mois> getMois() throws DAOException {
        DAOModeleBuilder<Mois> builder = new DAOModeleBuilder<Mois>() {
            @Override
            public Mois build(ResultSet rs) throws SQLException, DAOException {
                Mois mois = new Mois(rs.getString("annee"), rs.getString("nom"), rs.getInt("semaine_1_id"),
                            rs.getInt("semaine_2_id"), rs.getInt("semaine_3_id"), rs.getInt("semaine_4_id"));
                return mois;
            }
        };
        List<Mois> mois = super.gets(builder);
        SemaineDAO semaineDAO = new SemaineDAO(dataSource);
        for(Mois moi : mois) {
            moi.addSemaine(semaineDAO.getSemaine(moi.getSemaine_1_id()));
            moi.addSemaine(semaineDAO.getSemaine(moi.getSemaine_2_id()));
            moi.addSemaine(semaineDAO.getSemaine(moi.getSemaine_3_id()));
            moi.addSemaine(semaineDAO.getSemaine(moi.getSemaine_4_id()));
        }
        Collections.sort(mois);
        return mois;
    }

    public Mois getMoisBySemaineId(final int semaine_id) throws DAOException {
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
                    Mois mois = new Mois(rs.getString("annee"), rs.getString("nom"), rs.getInt("semaine_1_id"),
                            rs.getInt("semaine_2_id"), rs.getInt("semaine_3_id"), rs.getInt("semaine_4_id"));
                    return mois;
                }
            };
        
        return super.getSingle(builder, setter, MoisDAO.SELECT_MOIS_BY_SEMAINE_ID);
    }

    public Mois getLastMois() throws DAOException {
        SemaineDAO semaineDAO = new SemaineDAO(dataSource);
        Semaine semaine = semaineDAO.getLastSemaineAdded();
        Mois mois = this.getMoisBySemaineId(semaine.getId());
        return mois;
    }
}