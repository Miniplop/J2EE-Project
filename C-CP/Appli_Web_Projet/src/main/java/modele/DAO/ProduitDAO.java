package modele.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import modele.Produit;
import modele.Producteur;

public class ProduitDAO extends AbstractDAO {
	private static final String INSERT_PRODUIT = "INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES (?, ?, ?, ?, ?)";
	private static final String SELECT_PRODUIT = "SELECT * FROM Produit WHERE id = ?";

    public ProduitDAO(DataSource ds) {
        super(ds, INSERT_PRODUIT, null, null);
    }

    public Produit addProduit(final String nom, final String unite, final int quantite, final int duree, final Producteur producteur) throws DAOException {
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                    statement.setString(1, nom);
                    statement.setString(2, unite);
                    statement.setInt(3, quantite);
                    statement.setInt(4, duree);
                    statement.setInt(5, producteur.getId());
            }
        };
        super.add(setter);
        
        int id = getLastId("Produit");
        return new Produit(id, nom, unite, quantite, duree, producteur);
    }

    public List<Produit> getProduits() throws DAOException {
       ProducteurDAO producteurDAO = new ProducteurDAO(super.dataSource);
       List<Producteur> producteurs =  producteurDAO.getProducteurs();
       List<Produit> produits = new ArrayList<>();
       for(Producteur prod : producteurs) {
           produits.addAll(prod.getProduits());
       }
       return produits;       
     }

    List<Produit> getProduitsByProducteur(final Producteur producteur) throws DAOException {
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setShort(1, producteur.getId());
            }
        };
        DAOModeleBuilder<Produit> builder = new DAOModeleBuilder<Produit>() {

            @Override
            public Produit build(ResultSet rs) throws SQLException, DAOException {
                return new Produit(rs.getInt("id"), rs.getString("nom"), rs.getString("unite"), rs.getInt("quantite"), rs.getInt("duree"), producteur);
            }
        };
        return this.getMultiple(builder, setter, "SELECT * FROM Produit WHERE producteur_id = ?");
    }

    public Produit getProduit(final int id) throws DAOException {
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, id);
            }
        };
        final ProducteurDAO producteurDAO = new ProducteurDAO(dataSource);
         DAOModeleBuilder<Produit> builder;
            builder = new DAOModeleBuilder<Produit>() {
                @Override
                public Produit build(ResultSet rs) throws DAOException, SQLException {
                    return new Produit(rs.getInt("id"), rs.getString("nom"), rs.getString("unite"), rs.getInt("quantite"), rs.getInt("duree"), producteurDAO.getProducteur(rs.getInt("producteur_id")));
                }
            };
        
        return (Produit) super.getSingle(builder, setter, ProduitDAO.SELECT_PRODUIT);
    }
}