package modele.DAO;

import java.util.List;
import javax.sql.DataSource;
import modele.Contrat;
import modele.Produit;
import modele.Consommateur;
import modele.Semaine;

public class ContratDAO extends AbstractDAO {
    //TODO
	private static final String INSERT_CONTRAT ="";
	private static final String SELECT_CONTRATS="";
	private static final String UPDATE_CONTRAT="";
	private static final String SELECT_CONTRAT="";

    public ContratDAO(DataSource ds) {
        super(ds, INSERT_CONTRAT, SELECT_CONTRATS, UPDATE_CONTRAT, SELECT_CONTRAT);
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
}