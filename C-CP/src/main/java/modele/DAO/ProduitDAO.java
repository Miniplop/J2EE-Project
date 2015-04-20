package modele.DAO;

import java.util.List;
import javax.sql.DataSource;
import modele.Produit;
import modele.Producteur;

public class ProduitDAO extends AbstractDAO {
	private static final String INSERT_PRODUIT = "";
	private static final String SELECT_PRODUITS = "SELECT * FROM Produit";
	private static final String UPDATE_PRODUIT="";

    public ProduitDAO(DataSource ds) {
        super(ds, INSERT_PRODUIT, SELECT_PRODUITS, UPDATE_PRODUIT);
    }

    protected Produit add(DAOQueryParameter setter) {
            throw new UnsupportedOperationException();
    }

    protected List get(DAOQueryParameter setter) {
            throw new UnsupportedOperationException();
    }

    protected void modify(DAOQueryParameter setter) {
            throw new UnsupportedOperationException();
    }

    public Produit addProduit(String nom, String unite, int quantite, int duree, Producteur producteur) {
            throw new UnsupportedOperationException();
    }

    public void modifyProduit(Produit produit, String nom, String unite, int quantite, int duree) {
            throw new UnsupportedOperationException();
    }

    public List<Produit> getProduits() {
            throw new UnsupportedOperationException();
    }
}