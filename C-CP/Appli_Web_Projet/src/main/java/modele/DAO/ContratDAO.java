package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import modele.Contrat;
import modele.Produit;
import modele.Consommateur;
import modele.Semaine;

public class ContratDAO extends AbstractDAO {
	private static final String INSERT_CONTRAT ="INSERT INTO Contrat (quantite, produit_id, consommateur_id) VALUES (?,?,?)";
	private static final String SELECT_CONTRATS = "SELECT * FROM Contrat";
	private static final String UPDATE_CONTRAT = "UPDATE Contrat SET valide=?, consommateur_id=? WHERE id=?";
	private static final String SELECT_CONTRAT = "SELECT * FROM Contrat WHERE id = ?";

    public ContratDAO(DataSource ds) {
        super(ds, INSERT_CONTRAT, SELECT_CONTRATS, UPDATE_CONTRAT);
    }

    public Contrat addContrat(final int quantite, final Produit produit, final Consommateur consommateur) throws DAOException {
        DAOQueryParameter setter = new DAOQueryParameter() {

            @Override
            public void set(PreparedStatement statement) throws DAOException {
                try {
                    statement.setInt(1, quantite);
                    statement.setInt(2, produit.getId());
                    statement.setInt(3, consommateur.getId());
                } catch (SQLException ex) {
                        throw new DAOException(ex.getMessage(), ex);
                }
            }
        };
        this.add(setter);
        
        Statement statement = null;
        ResultSet generatedKeys = null;
        int id = 0;
        try {
            Connection conn = getConnection();
            statement = conn.createStatement();
            generatedKeys = statement.executeQuery("SELECT seq_contrat.currval from dual");
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
        return new Contrat(id, quantite, produit, consommateur);
    }

    public void modifyContrat(Contrat contrat, int valide, Semaine semaineDebut) {
        
    }

    public List<Contrat> getContrats() throws DAOException {
        final ProduitDAO produitDAO = new ProduitDAO(dataSource);
        final ConsommateurDAO consommateurDAO = new ConsommateurDAO(dataSource);
        final SemaineDAO semaineDAO = new SemaineDAO(dataSource);
        DAOModeleBuilder<Contrat> builder = new DAOModeleBuilder<Contrat>() {
            @Override
            public Contrat build(ResultSet rs) throws DAOException {
                Contrat contrat;
                try {
                    Produit produit = produitDAO.getProduit(rs.getShort("produit_id"));
                    Consommateur consommateur = consommateurDAO.getConsommateur(rs.getShort("consommateur_id"));
                    Semaine semaine_debut = semaineDAO.getSemaine(rs.getShort("debut_semaine_id"));
                    contrat = new Contrat(rs.getShort("id"), rs.getInt("quantite"), rs.getShort("valide"), consommateur, produit, semaine_debut);
                } catch (SQLException ex) {
                    throw new DAOException(ex.getMessage(), ex);
                }
                return contrat;
            }
        };
        return this.gets(builder);
    }

    public Contrat getContratsByConsommateur(Consommateur conso) throws DAOException {
        Connection conn = null;
        PreparedStatement pSt;
        ResultSet rs;
        Contrat contrat = null;
        ProduitDAO produitDAO = new ProduitDAO(super.dataSource);
        SemaineDAO semaineDAO = new SemaineDAO(super.dataSource);
        try {
            conn = getConnection();
            pSt = conn.prepareStatement("SELECT * FROM Contrat WHERE consommateur_id = ?");
            pSt.setInt(1, conso.getId());
            rs = pSt.executeQuery();
            if(rs.next()) {
                contrat = new Contrat(rs.getInt("id"), rs.getInt("quantite"),
                        rs.getShort("valide"),
                        conso, 
                        produitDAO.getProduit(rs.getInt("produit_id")),
                        semaineDAO.getSemaine(rs.getInt("debut_semaine_id")));
            }
            pSt.close();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return contrat;
    }
    
    public Map<Integer, List<Contrat>> getContratEnAttente() throws DAOException {
        ConsommateurDAO consommateurDAO = new ConsommateurDAO(dataSource);
        ProduitDAO produitDAO = new ProduitDAO(dataSource);
        Map<Integer, List<Contrat>> contrats = new HashMap<>();
        Connection conn = null;
        PreparedStatement pSt;
        ResultSet rs;
        List<Produit> result = new ArrayList<>();
        try {
            conn = getConnection();
            pSt = conn.prepareStatement("SELECT * FROM Contrat WHERE valide = 2");
            rs = pSt.executeQuery();

            while (rs.next()) {
                Contrat contrat = new Contrat(rs.getInt("id"), rs.getInt("quantite"), produitDAO.getProduit(rs.getInt("produit_id")), consommateurDAO.getConsommateur(rs.getInt("consommateur_id")));
                if(contrats.get(contrat.getProduit().getId()) == null)
                    contrats.put(contrat.getProduit().getId(), new ArrayList<Contrat>());
                contrats.get(contrat.getProduit().getId()).add(contrat);
            }
            pSt.close();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return contrats;
    }

    public Contrat getContrat(final int id) throws DAOException {
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
        final ProduitDAO produitDAO = new ProduitDAO(dataSource);
        final ConsommateurDAO consommateurDAO = new ConsommateurDAO(dataSource);
         DAOModeleBuilder<Contrat> builder;
            builder = new DAOModeleBuilder<Contrat>() {
                @Override
                public Contrat build(ResultSet rs) throws DAOException {
                    try {
                        return new Contrat(rs.getInt("id"), rs.getInt("quantite"), produitDAO.getProduit(rs.getInt("produit_id")), consommateurDAO.getConsommateur(rs.getInt("consommateur_id")));
                    } catch (SQLException ex) {
                        throw new DAOException(ex.getMessage(), ex);
                    }
                }
            };
        
        return (Contrat) super.getSingle(builder, setter, ContratDAO.SELECT_CONTRAT);
    }
}