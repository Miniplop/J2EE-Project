package modele.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import modele.Consommateur;
import modele.Utilisateur;

public class ConsommateurDAO extends UtilisateurDAO {
    
    private static final String SELECT_CONSOMMATEURS = "SELECT * FROM Consommateur";
    private static final String SELECT_CONSOMMATEUR_BY_ID = "SELECT * FROM Consommateur WHERE id = ?";

    public ConsommateurDAO(DataSource ds) {
        super(ds, null, SELECT_CONSOMMATEURS, null);
    }

    public Consommateur addConsommateur(String nom, String prenom, String email, String adresse) {
            throw new UnsupportedOperationException();
    }
 
    public void modifyConsommateur(Consommateur consommateur, String nom, String prenom, String email, String adresse) {
            throw new UnsupportedOperationException();
    }

    public List<Consommateur> getConsommateurs() throws DAOException {
         final SemaineDAO semaineDAO = new SemaineDAO(dataSource);
        DAOModeleBuilder<Consommateur> builder = new DAOModeleBuilder<Consommateur>() {
            @Override
            public Consommateur build(ResultSet rs) throws DAOException {
                Consommateur consommateur = null;
                try {
                    consommateur = new Consommateur(rs.getShort("id"),rs.getString("nom"), rs.getString("prenom"),rs.getString("email"), rs.getString("adresse"), null);
                } catch (SQLException ex) {
                    throw new DAOException(ex.getMessage(), ex);
                }
                return consommateur;
            }
        };
        return super.gets(builder);
    }

    public Consommateur getConsommateur(final int id) throws DAOException {
        
        final ContratDAO contratDAO = new ContratDAO(super.dataSource);
        final UtilisateurDAO utilisateurDAO = this;
        DAOModeleBuilder<Consommateur> builder = new DAOModeleBuilder<Consommateur>() {
            
            @Override
            public Consommateur build(ResultSet rs) throws DAOException {
                try {
                    Utilisateur utilisateur = utilisateurDAO.getUtilisateur(rs.getInt("id"));
                    Consommateur conso = new Consommateur((short) rs.getInt("id"), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getAdresse(), null);
                    contratDAO.getContratsByConsommateur(conso);
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
        
        return (Consommateur) super.getSingle(builder, setter, this.SELECT_CONSOMMATEUR_BY_ID);
    }
}