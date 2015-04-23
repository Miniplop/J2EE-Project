package modele.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import modele.Consommateur;

public class ConsommateurDAO extends UtilisateurDAO {
    
    private static final String SELECT_CONSOMMATEURS = "SELECT * FROM Consommateur";
    private static final String SELECT_CONSOMMATEUR_BY_ID = "SELECT * FROM Consommateur WHERE id = ?";

    public ConsommateurDAO(DataSource ds) {
        super(ds, null, SELECT_CONSOMMATEURS, null);
    }

    public List<Consommateur> getConsommateurs() throws DAOException {
        DAOModeleBuilder<Consommateur> builder = new DAOModeleBuilder<Consommateur>() {
            @Override
            public Consommateur build(ResultSet rs) throws DAOException,SQLException {
                return new Consommateur(rs.getShort("id"));
            }
        };
        
        List<Consommateur> consommateurs = super.gets(builder);
        for(Consommateur consommateur : consommateurs)
            this.getUtilisateur(consommateur);
        return consommateurs;
    }

    public Consommateur getConsommateur(final int id) throws DAOException {
        DAOModeleBuilder<Consommateur> builder = new DAOModeleBuilder<Consommateur>() {
            @Override
            public Consommateur build(ResultSet rs) throws DAOException {
                try {
                    Consommateur conso = new Consommateur((short) rs.getInt("id"));
                    return conso;
                } catch (SQLException ex) {
                    throw new DAOException(ex.getMessage(), ex);
                }
            }
        };
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, id);
            }
        };
        
        Consommateur consommateur = (Consommateur) super.getSingle(builder, setter, this.SELECT_CONSOMMATEUR_BY_ID);
        return (Consommateur)this.getUtilisateur(consommateur);
    }
}