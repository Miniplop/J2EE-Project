package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import modele.Consommateur;
import modele.Producteur;
import modele.Produit;
import modele.Utilisateur;

public class UtilisateurDAO extends AbstractDAO {
    
    private static final String SELECT_UTILISATEURS = "SELECT * FROM Utilisateur";
    private static final String SELECT_UTILISATEUR = "SELECT * FROM Utilisateur WHERE id = ?";

    public UtilisateurDAO(DataSource ds, String insert_query, String select_query, String update_query) {
        super(ds, insert_query, select_query, update_query);
    }
    
    public UtilisateurDAO(DataSource ds) {
        super(ds, null, SELECT_UTILISATEURS, null);
    }

    protected Utilisateur addUtilisateur(String nom, String prenom, String email, String adresse) {
            throw new UnsupportedOperationException();
    }

    protected void modifyUtilisateur(Utilisateur utilisateur, String nom, String prenom, String email, String adresse) {
            throw new UnsupportedOperationException();
    }

    protected List<Utilisateur> getUtilisateurs() {
            throw new UnsupportedOperationException();
    }

    public Utilisateur getUtilisateur(final int id) throws DAOException {
        
        DAOModeleBuilder<Utilisateur> builder = new DAOModeleBuilder<Utilisateur>() {
            @Override
            public Utilisateur build(ResultSet rs) throws DAOException {
                try {
                    Utilisateur utilisateur = new Producteur((short)rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("adresse"));
                    
                    return utilisateur;
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
        
        return (Utilisateur) super.getSingle(builder, setter, this.SELECT_UTILISATEUR);
    }

    public Utilisateur getUtilisateur(String email, String nom, String type) throws DAOException {
        
        Connection conn = null;
        PreparedStatement pSt;
        ResultSet rs;
        Utilisateur utilisateur = null;
        try {
            conn = getConnection();
            pSt = conn.prepareStatement("SELECT * FROM Utilisateur WHERE LOWER(nom) = ? AND LOWER(email) = ?");
            pSt.setString(1, nom.toLowerCase());
            pSt.setString(2, email.toLowerCase());
            rs = pSt.executeQuery();
            if(rs.next()) {
                if("producteur".equals(type)) {
                    ProducteurDAO producteurDAO = new ProducteurDAO(super.dataSource);
                    utilisateur = producteurDAO.getProducteur(rs.getInt("id"));
                } else if ("consommateur".equals(type)) {
                    ConsommateurDAO consommateurDAO = new ConsommateurDAO(super.dataSource);
                    utilisateur = consommateurDAO.getConsommateur(rs.getInt("id"));
                } else {
                    throw new DAOException("Demande d'utilisateur non typé");
                }
            } else {
            }
            pSt.close();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return utilisateur;
    }
}