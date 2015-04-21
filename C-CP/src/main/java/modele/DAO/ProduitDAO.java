package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import modele.Produit;
import modele.Producteur;

public class ProduitDAO extends AbstractDAO {
	private static final String INSERT_PRODUIT = "";
	private static final String UPDATE_PRODUIT="";
	private static final String SELECT_PRODUIT = "SELECT * FROM Produit WHERE id = ?";

    public ProduitDAO(DataSource ds) {
        super(ds, INSERT_PRODUIT, null, UPDATE_PRODUIT, SELECT_PRODUIT);
    }

    public Produit addProduit(String nom, String unite, int quantite, int duree, Producteur producteur) {
            throw new UnsupportedOperationException();
    }

    public void modifyProduit(Produit produit, String nom, String unite, int quantite, int duree) {
            throw new UnsupportedOperationException();
    }

    public List<Produit> getProduits() throws DAOException {
       ProducteurDAO producteurDAO = new ProducteurDAO(super.dataSource);
       List<Producteur> producteurs =  producteurDAO.getProducteurs();
       List<Produit> produits = new ArrayList<Produit>();
       for(Producteur prod : producteurs) {
           produits.addAll(prod.getProduits());
       }
       return produits;       
    }

    List<Produit> getProduitsByProducteur(Producteur producteur) throws DAOException {
    
        Connection conn = null;
        PreparedStatement pSt;
        ResultSet rs;
        List<Produit> result = new ArrayList<Produit>();
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
}