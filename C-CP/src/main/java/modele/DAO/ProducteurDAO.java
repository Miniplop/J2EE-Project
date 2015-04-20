package modele.DAO;

import java.util.List;
import modele.Producteur;

public class ProducteurDAO extends UtilisateurDAO {
	private static final String INSERT_PRODUCTEUR="";
	private static final String SELECT_PRODUCTEURS="";
	private static final String UPDATE_PRODUCTEUR="";

    public ProducteurDAO(Object ds, String insert_query, String select_query, String update_query) {
        super(ds, insert_query, select_query, update_query);
    }

    public Producteur addProducteur(String nom, String prenom, String email, String adresse) {
            throw new UnsupportedOperationException();
    }

    public void modifyProducteur(Producteur producteur, String nom, String prenom, String adresse, String email) {
            throw new UnsupportedOperationException();
    }

    public List<Producteur> getProducteurs() {
            throw new UnsupportedOperationException();
    }
}