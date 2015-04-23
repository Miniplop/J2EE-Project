package modele.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import modele.Producteur;
import modele.Utilisateur;

public abstract class UtilisateurDAO extends AbstractDAO {
    
    private static final String SELECT_UTILISATEUR = "SELECT * FROM Utilisateur WHERE id = ?";

    public UtilisateurDAO(DataSource ds, String insert_query, String select_query, String update_query) {
        super(ds, insert_query, select_query, update_query);
    }
    
    public UtilisateurDAO(DataSource ds) {
        super(ds, null, null, null);
    }

    public Utilisateur getUtilisateur(final Utilisateur utilisateur) throws DAOException {
        DAOModeleBuilder<Utilisateur> builder = new DAOModeleBuilder<Utilisateur>() {
            @Override
            public Utilisateur build(ResultSet rs) throws DAOException, SQLException {
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setPrenom(rs.getString("prenom"));
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setAdresse(rs.getString("adresse"));
                return utilisateur;
            }
        };
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, utilisateur.getId());
            }
        };
        
        return (Utilisateur) super.getSingle(builder, setter, UtilisateurDAO.SELECT_UTILISATEUR);
    }

    public Utilisateur getUtilisateur(final String email, final String nom, final String type) throws DAOException {
        final DataSource ds = super.dataSource;
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setString(1, nom.toLowerCase());
                statement.setString(2, email.toLowerCase());
            }
        };
        DAOModeleBuilder<Utilisateur> builder = new DAOModeleBuilder<Utilisateur>() {
            @Override
            public Utilisateur build(ResultSet rs) throws SQLException, DAOException {
                Utilisateur utilisateur = null;
                if("producteur".equals(type)) {
                    ProducteurDAO producteurDAO = new ProducteurDAO(ds);
                    utilisateur = producteurDAO.getProducteur(rs.getInt("id"));
                } else if ("consommateur".equals(type)) {
                    ConsommateurDAO consommateurDAO = new ConsommateurDAO(ds);
                    utilisateur = consommateurDAO.getConsommateur(rs.getInt("id"));
                } else {
                    throw new DAOException("Demande d'utilisateur non typé");
                }
                return utilisateur;
            }
        };
        return (Utilisateur) this.getSingle(builder, setter, "SELECT * FROM Utilisateur WHERE LOWER(nom) = ? AND LOWER(email) = ?");
    }
}