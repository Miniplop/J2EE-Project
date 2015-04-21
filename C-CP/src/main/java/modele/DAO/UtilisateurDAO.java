package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import modele.Produit;
import modele.Utilisateur;

public class UtilisateurDAO extends AbstractDAO {
    
    private static final String SELECT_UTILISATEURS = "SELECT * FROM Utilisateur";
    private static final String SELECT_UTILISATEUR = "SELECT * FROM Utilisateur WHERE id = ?";

    public UtilisateurDAO(DataSource ds, String insert_query, String select_query, String update_query, String select_by_id_query) {
        super(ds, insert_query, select_query, update_query, select_by_id_query);
    }
    
    public UtilisateurDAO(DataSource ds) {
        super(ds, null, SELECT_UTILISATEURS, null, SELECT_UTILISATEUR);
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

    Utilisateur getUtilisateur(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Utilisateur getUtilisateur(String email, String nom, String type) throws DAOException {
        
        Connection conn = null;
        PreparedStatement pSt;
        ResultSet rs;
        Utilisateur utilisateur;
        try {
            conn = getConnection();
            pSt = conn.prepareStatement("SELECT * FROM Utilisateur WHERE nom = ? AND email = ?");
            pSt.setString(1, nom);
            pSt.setString(1, email);
            rs = pSt.executeQuery();
            if(type == "producteur") {
                ProducteurDAO producteurDAO = new ProducteurDAO(super.dataSource);
                utilisateur = producteurDAO.getProducteur(rs.getInt("id"));
            } else if (type == "consommateur") {
                ConsommateurDAO consommateurDAO = new ConsommateurDAO(super.dataSource);
                utilisateur = consommateurDAO.getConsommateur(rs.getInt("id"));
            } else {
                utilisateur = null;
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