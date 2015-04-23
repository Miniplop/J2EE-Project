package modele.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import modele.Mois;
import modele.Semaine;


public class MoisDAO extends AbstractDAO {
	private static final String INSERT_MOIS="INSERT INTO Mois (annee, nom, semaine_1_id, semaine_2_id, semaine_3_id, semaine_4_id) VALUES (?,?,?,?,?,?)";
	private static final String SELECT_MOIS="SELECT * FROM mois ";
	private static final String UPDATE_MOIS="UPDATE Mois SET";
	private static final String SELECT_MOIS_BY_ID="SELECT * FROM mois WHERE id = ? ";
	private static final String SELECT_MOIS_BY_SEMAINE_ID="SELECT * FROM mois WHERE semaine_1_id = ? OR semaine_2_id = ? OR semaine_3_id = ? OR semaine_4_id = ?";

    public MoisDAO(DataSource ds) {
        super(ds, INSERT_MOIS, SELECT_MOIS, UPDATE_MOIS);
    }

    public Mois addMois(final String annee, final String nom) throws DAOException  {
        final SemaineDAO semaineDAO = new SemaineDAO(dataSource);
            DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws DAOException {
                try {
                    Semaine sem1 = semaineDAO.addSemaine(1);
                    Semaine sem2 = semaineDAO.addSemaine(2);
                    Semaine sem3 = semaineDAO.addSemaine(3);
                    Semaine sem4 = semaineDAO.addSemaine(4);
                    statement.setInt(1,Integer.parseInt(annee));
                    statement.setString(2, nom);
                    statement.setInt(3, sem1.getId());
                    statement.setInt(4, sem2.getId());
                    statement.setInt(5, sem3.getId());
                    statement.setInt(6, sem4.getId());
                } catch (SQLException ex) {
                    throw new DAOException(ex.getMessage(), ex);
                }
            }
        };
        super.add(setter);
        return new Mois(annee, nom);
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