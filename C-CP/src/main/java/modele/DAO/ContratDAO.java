package modele.DAO;

import java.util.List;
import modele.Contrat;
import modele.Produit;
import modele.Consommateur;
import modele.Semaine;

public class ContratDAO extends AbstractDAO {
    //TODO
	private static final String INSERT_CONTRAT ="";
	private static final String SELECT_CONTRATS="";
	private static final String UPDATE_CONTRAT="";

    public ContratDAO(Object ds, String insert_query, String select_query, String update_query) {
        super(ds, insert_query, select_query, update_query);
    }

    public Contrat addContrat(int quantite, boolean valide, Produit produit, Consommateur consommateur) {
            throw new UnsupportedOperationException();
    }

    public void modifyContrat(Contrat contrat, boolean valide, Semaine semaineDebut) {
            throw new UnsupportedOperationException();
    }

    public List<Contrat> getContrats() {
            throw new UnsupportedOperationException();
    }

    protected Object add(DAOQueryParameter setter) {
            throw new UnsupportedOperationException();
    }

    protected void modify(DAOQueryParameter setter) {
            throw new UnsupportedOperationException();
    }

    protected List get(DAOQueryParameter setter) {
            throw new UnsupportedOperationException();
    }
}