package modele.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import modele.Producteur;
import modele.Produit;

public class ProducteurDAO extends UtilisateurDAO {
    
    private static final String SELECT_PRODUCTEURS = "SELECT * FROM Producteur";
    private static final String SELECT_PRODUCTEUR_BY_ID = "SELECT * FROM Producteur WHERE id = ?";

    public ProducteurDAO(DataSource dataSource) {
        super(dataSource, null, SELECT_PRODUCTEURS, null);
    }

    public List<Producteur> getProducteurs() throws DAOException {
        
        DAOModeleBuilder<Producteur> builder = new DAOModeleBuilder<Producteur>() {
            @Override
            public Producteur build(ResultSet rs) throws DAOException, SQLException {
                Producteur prod = new Producteur(rs.getShort("id"));
                return prod;
            }
        };
        List<Producteur> producteurs = super.gets(builder);
        for(Producteur producteur : producteurs)
            this.getUtilisateur(producteur);
        return producteurs;
    }

    public Producteur getProducteur(final int id) throws DAOException {
        
        DAOModeleBuilder<Producteur> builder = new DAOModeleBuilder<Producteur>() {
            
            @Override
            public Producteur build(ResultSet rs) throws DAOException, SQLException {
                return new Producteur(rs.getShort("id"));
            }
        };
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, id);
            }
        };
        Producteur producteur = (Producteur) this.getSingle(builder, setter, SELECT_PRODUCTEUR_BY_ID);
        return (Producteur) this.getUtilisateur(producteur);
    }

    public Producteur getProducteurOfProduit(final Produit produit) throws DAOException {
        DAOModeleBuilder<Producteur> builder = new DAOModeleBuilder<Producteur>() {
            @Override
            public Producteur build(ResultSet rs) throws DAOException, SQLException {
                return new Producteur(rs.getShort("id"));
            }
        };
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, produit.getId());
            }
        };
        Producteur producteur = (Producteur) this.getSingle(builder, setter, "SELECT * FROM Producteur WHERE id = (SELECT producteur_id FROM Produit WHERE id = ?)");
        return (Producteur) this.getUtilisateur(producteur);
    }
}