package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import modele.Contrat;
import modele.Produit;
import modele.Consommateur;
import modele.Semaine;

public class ContratDAO extends AbstractDAO {
    //TODO
	private static final String INSERT_CONTRAT ="INSERT INTO Contrat ( quantite,produit_id,consommateur_id,debut_semaine_id) VALUES (?,?,?,?)";
	private static final String SELECT_CONTRATS="SELECT * FROM Contrat";
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

    public Contrat getContratsByConsommateur(Consommateur conso) throws DAOException {
        Connection conn = null;
        PreparedStatement pSt;
        ResultSet rs;
        Contrat contrat;
        ProduitDAO produitDAO = new ProduitDAO(super.dataSource);
        SemaineDAO semaineDAO = new SemaineDAO(super.dataSource);
        try {
            conn = getConnection();
            pSt = conn.prepareStatement("SELECT * FROM Contrat WHERE consommateur_id = ?");
            pSt.setInt(1, conso.getId());
            rs = pSt.executeQuery();
            contrat = new Contrat(rs.getInt("id"), rs.getInt("quantite"),
                    (rs.getByte("valide") != 0),
                    conso, 
                    produitDAO.getProduit(rs.getInt("produit_id")),
                    semaineDAO.getSemaine(rs.getInt("debut_semaine_id")));
            pSt.close();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return contrat;
    }
}