package modele.DAO;

// import modele.Produit;
// import Diagramme_de_classe.modele.Produit;

public class ProduitDAO extends AbstractDAO {
	private static final String INSERT_PRODUIT;
	private static final String SELECT_PRODUITS;
	private static final String UPDATE_PRODUIT;

	public modele.Produit addProduit(String nom, String unite, int quantite, int duree, int producteur_id) {
		throw new UnsupportedOperationException();
	}

	public void modifyProduit(modele.Produit produit, String nom, String unite, int quantite, int duree) {
		throw new UnsupportedOperationException();
	}

	public List<Diagramme_de_classe.modele.Produit> getProduits() {
		throw new UnsupportedOperationException();
	}

	public List<modele.Produit> getProduits(int producteur_id) {
		throw new UnsupportedOperationException();
	}

	protected Object add(DAOQueryParameter setter) {
		throw new UnsupportedOperationException();
	}

	protected void modify(DAOQueryParameter setter) {
		throw new UnsupportedOperationException();
	}

	protected ResultSet get(DAOQueryParameter setter) {
		throw new UnsupportedOperationException();
	}
}