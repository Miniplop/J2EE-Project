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
        super(dataSource, null, SELECT_PRODUCTEURS, null);
    }

    public List<Producteur> getProducteurs() throws DAOException {
        final ProduitDAO produitDAO = new ProduitDAO(super.dataSource);
        final UtilisateurDAO utilisateurDAO = this;
        
        DAOModeleBuilder<Producteur> builder = new DAOModeleBuilder<Producteur>() {
            @Override
            public Producteur build(ResultSet rs) throws DAOException, SQLException {
                Utilisateur utilisateur = utilisateurDAO.getUtilisateur(rs.getInt("id"));
                Producteur prod = new Producteur(rs.getShort("id"), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getAdresse());
                produitDAO.getProduitsByProducteur(prod);
                return prod;
            }
        };
        return super.gets(builder);
    }

    public Producteur getProducteur(final int id) throws DAOException {
        final ProduitDAO produitDAO = new ProduitDAO(super.dataSource);
        final UtilisateurDAO utilisateurDAO = this;
        
        DAOModeleBuilder<Producteur> builder = new DAOModeleBuilder<Producteur>() {
            
            @Override
            public Producteur build(ResultSet rs) throws DAOException, SQLException {
                Utilisateur utilisateur = utilisateurDAO.getUtilisateur(rs.getInt("id"));
                Producteur prod = new Producteur(rs.getShort("id"), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getAdresse());
                produitDAO.getProduitsByProducteur(prod);
                return prod;
            }
        };
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, id);
            }
        };
        Object prod = super.getSingle(builder, setter, this.SELECT_PRODUCTEUR_BY_ID);
        return (Producteur) prod;
    }
}