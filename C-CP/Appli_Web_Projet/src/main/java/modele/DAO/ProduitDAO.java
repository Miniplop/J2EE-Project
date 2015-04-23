package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import modele.Produit;
import modele.Producteur;

public class ProduitDAO extends AbstractDAO {
	private static final String INSERT_PRODUIT = "INSERT INTO Produit (nom,unite,quantite,duree,producteur_id) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_PRODUIT="";
	private static final String SELECT_PRODUIT = "SELECT * FROM Produit WHERE id = ?";

    public ProduitDAO(DataSource ds) {
        super(ds, INSERT_PRODUIT, null, UPDATE_PRODUIT);
    }

    public Produit addProduit(final String nom, final String unite, final int quantite, final int duree, final Producteur producteur) throws DAOException {
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws DAOException {
                try {
                    statement.setString(1, nom);
                    statement.setString(2, unite);
                    statement.setInt(3, quantite);
                    //statement.setInt(4, duree);
                    statement.setInt(5, producteur.getId());
                } catch (SQLException ex) {
                    throw new DAOException(ex.getMessage(), ex);
                }
            }
        };
        super.add(setter);

        Statement statement = null;
        ResultSet generatedKeys = null;
        int id = 0;
        try {
            Connection conn = getConnection();
            statement = conn.createStatement();
            generatedKeys = statement.executeQuery("SELECT seq_produit.currval from dual");
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating object failed, no generated key obtained.");
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
        if(id == 0)
            return null;
        return new Produit(id, nom, unite, quantite, duree, producteur);
    }

    public void modifyProduit(Produit produit, String nom, String unite, int quantite, int duree) {
            throw new UnsupportedOperationException();
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

    List<Produit> getProduitsByProducteur(Producteur producteur) throws DAOException {
        Connection conn = null;
        PreparedStatement pSt;
        ResultSet rs;
        List<Produit> result = new ArrayList<>();
        try {
            conn = getConnection();
            pSt = conn.prepareStatement("SELECT * FROM Produit WHERE producteur_id = ?");
            pSt.setShort(1, producteur.getId());
            rs = pSt.executeQuery();

            while (rs.next()) {
                Produit produit = new Produit(rs.getInt("id"), rs.getString("nom"), rs.getString("unite"), rs.getInt("quantite"), rs.getInt("duree"), producteur);
                result.add(produit);
            }
            pSt.close();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public Produit getProduit(final int id) throws DAOException {
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
        final ProducteurDAO producteurDAO = new ProducteurDAO(dataSource);
         DAOModeleBuilder<Produit> builder;
            builder = new DAOModeleBuilder<Produit>() {
                @Override
                public Produit build(ResultSet rs) throws DAOException {
                    try {
                        
                        return new Produit(rs.getInt("id"), rs.getString("nom"), rs.getString("unite"), rs.getInt("quantite"), rs.getInt("duree"), producteurDAO.getProducteur(rs.getInt("producteur_id")));
                    } catch (SQLException ex) {
                        throw new DAOException(ex.getMessage(), ex);
                    }
                }
            };
        
        return (Produit) super.getSingle(builder, setter, ProduitDAO.SELECT_PRODUIT);
    }
}