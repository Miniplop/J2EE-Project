package modele.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import modele.Semaine;
import modele.Consommateur;
import modele.Mois;

public class SemaineDAO extends AbstractDAO {
	private static final String INSERT_SEMAINE="";
        private static final String SELECT_SEMAINES="SELECT * FROM semaine ";
	private static final String UPDATE_SEMAINE="";
	private static final String SELECT_SEMAINE="";

    public SemaineDAO(DataSource ds) {
        super(ds, INSERT_SEMAINE, SELECT_SEMAINES, UPDATE_SEMAINE);
    }

    public Semaine addSemaine(int numero) {
            throw new UnsupportedOperationException();
    }

    public void modifySemaine(Semaine semaine, int numero, Consommateur permanent1, Consommateur permanent2) {
            throw new UnsupportedOperationException();
    }

    public List<Semaine> getSemaines(final Mois mois,int id) throws DAOException {
        DAOModeleBuilder<Semaine> builder;
            builder = new DAOModeleBuilder<Semaine>() {     
                @Override
                public Semaine build(ResultSet rs) throws DAOException {
                    try {
                        return new Semaine((short) rs.getInt("id"), rs.getInt("numero"), rs.getInt("consommateur_1_id"), rs.getInt("consommateur_2_id"), mois);
                    } catch (SQLException ex) {
                        throw new DAOException(ex.getMessage(), ex);
                    }
                }
            };
        return super.gets(builder);
    }

    Semaine getSemaine(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}