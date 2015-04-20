package java.modele.DAO;

// import java.modele.Produit;
import java.modele.Producteur;
// import Diagramme_de_classe.modele.Produit;

public class ProduitDAO extends AbstractDAO {
	private static final String INSERT_PRODUIT;
	private static final String SELECT_PRODUITS;
	private static final String UPDATE_PRODUIT;

	protected java.modele.Produit add(DAOQueryParameter setter) {
		throw new UnsupportedOperationException();
	}

	protected List get(DAOQueryParameter setter) {
		throw new UnsupportedOperationException();
	}

	protected void modify(DAOQueryParameter setter) {
		throw new UnsupportedOperationException();
	}

	public java.modele.Produit addProduit(String nom, String unite, int quantite, int duree, Producteur producteur) {
		throw new UnsupportedOperationException();
	}

	public void modifyProduit(java.modele.Produit produit, String nom, String unite, int quantite, int duree) {
		throw new UnsupportedOperationException();
	}

	public List<Diagramme_de_classe.modele.Produit> getProduits() {
		throw new UnsupportedOperationException();
	}
}