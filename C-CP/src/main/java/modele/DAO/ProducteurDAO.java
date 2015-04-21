package modele.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import modele.Producteur;
import modele.Utilisateur;

public class ProducteurDAO extends UtilisateurDAO {
    
    private static final String SELECT_PRODUCTEURS = "SELECT * FROM Producteur";
    private static final String SELECT_PRODUCTEUR_BY_ID = "SELECT * FROM Producteur WHERE id = ?";

    public ProducteurDAO(DataSource dataSource) {
        super(dataSource, null, SELECT_PRODUCTEURS, null, SELECT_PRODUCTEUR_BY_ID);
    }

    public Producteur addProducteur(String nom, String prenom, String email, String adresse) {
            throw new UnsupportedOperationException();
    }

    public void modifyProducteur(Producteur producteur, String nom, String prenom, String adresse, String email) {
            throw new UnsupportedOperationException();
    }

    public List<Producteur> getProducteurs() throws DAOException {
        final ProduitDAO produitDAO = new ProduitDAO(super.dataSource);
        final UtilisateurDAO utilisateurDAO = new UtilisateurDAO(super.dataSource);
        
        DAOModeleBuilder<Producteur> builder;
        builder = new DAOModeleBuilder<Producteur>() {
            @Override
            public Producteur build(ResultSet rs) throws DAOException {
                Utilisateur utilisateur;
                Producteur prod;
                
                try {
                    utilisateur = utilisateurDAO.getUtilisateur(rs.getInt("id"));
                    prod = new Producteur(rs.getShort("id"), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getAdresse());
                } catch (SQLException ex) {
                    throw new DAOException(ex.getMessage(), ex);
                }
                produitDAO.getProduitsByProducteur(prod);
                return prod;
            }
        };
        return super.gets(builder);
    }

    public Producteur getProducteur(final int id) throws DAOException {
        
        final ProduitDAO produitDAO = new ProduitDAO(super.dataSource);
        final UtilisateurDAO utilisateurDAO = new UtilisateurDAO(super.dataSource);
        
        DAOModeleBuilder<Producteur> builder = new DAOModeleBuilder<Producteur>() {
            
            @Override
            public Producteur build(ResultSet rs) throws DAOException {
                try {
                    Utilisateur utilisateur = utilisateurDAO.getUtilisateur(rs.getInt("id"));
                    Producteur prod = new Producteur(rs.getShort("id"), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getAdresse());
                    produitDAO.getProduitsByProducteur(prod);
                    return prod;
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
        
        return (Producteur) super.get(builder, setter);
    }
}