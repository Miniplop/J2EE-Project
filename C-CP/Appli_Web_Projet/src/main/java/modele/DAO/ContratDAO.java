package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import modele.Contrat;
import modele.Produit;
import modele.Consommateur;
import modele.Semaine;

public class ContratDAO extends AbstractDAO {
	private static final String INSERT_CONTRAT ="INSERT INTO Contrat (quantite, produit_id, consommateur_id) VALUES (?,?,?)";
	private static final String SELECT_CONTRATS = "SELECT * FROM Contrat";
	private static final String UPDATE_CONTRAT = "UPDATE Contrat SET valide=?, debut_semaine_id=? WHERE id=?";
	private static final String SELECT_CONTRAT = "SELECT * FROM Contrat WHERE id = ?";

    public ContratDAO(DataSource ds) {
        super(ds, INSERT_CONTRAT, SELECT_CONTRATS, UPDATE_CONTRAT);
    }

    public Contrat addContrat(final int quantite, final Produit produit, final Consommateur consommateur) throws DAOException {
        DAOQueryParameter setter = new DAOQueryParameter() {

            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, quantite);
                statement.setInt(2, produit.getId());
                statement.setInt(3, consommateur.getId());
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

    public void modifyContrat(final Contrat contrat, final int valide, final Semaine semaineDebut) throws DAOException {
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, valide);
                statement.setInt(2, semaineDebut.getId());
                statement.setInt(3, contrat.getId());
            }
        };
        this.modify(setter, UPDATE_CONTRAT);
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

    public List<Contrat> getContratsByConsommateur(final Consommateur conso) throws DAOException {
        
        final ProduitDAO produitDAO = new ProduitDAO(super.dataSource);
        final SemaineDAO semaineDAO = new SemaineDAO(super.dataSource);
        DAOQueryParameter setter = new DAOQueryParameter() {

            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, conso.getId());
            }
        };
        DAOModeleBuilder<Contrat> builder = new DAOModeleBuilder<Contrat>() {

            @Override
            public Contrat build(ResultSet rs) throws SQLException, DAOException {
                return new Contrat(rs.getInt("id"), rs.getInt("quantite"),
                        rs.getShort("valide"),
                        conso, 
                        produitDAO.getProduit(rs.getInt("produit_id")),
                        semaineDAO.getSemaine(rs.getInt("debut_semaine_id")));
            }
        };
        return this.getMultiple(builder, setter, "SELECT * FROM Contrat WHERE consommateur_id = ?");
    }

    public Contrat getContrat(final int id) throws DAOException {
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, id);
            }
        };
        final ProduitDAO produitDAO = new ProduitDAO(dataSource);
        final ConsommateurDAO consommateurDAO = new ConsommateurDAO(dataSource);
         DAOModeleBuilder<Contrat> builder = new DAOModeleBuilder<Contrat>() {
                @Override
                public Contrat build(ResultSet rs) throws SQLException, DAOException {
                    return new Contrat(rs.getInt("id"), rs.getInt("quantite"), produitDAO.getProduit(rs.getInt("produit_id")), consommateurDAO.getConsommateur(rs.getInt("consommateur_id")));
                }
            };
        
        return (Contrat) super.getSingle(builder, setter, ContratDAO.SELECT_CONTRAT);
    }

    public List<Contrat> getContratByProduit(final Produit prod) throws DAOException {
        final ConsommateurDAO consommateurDAO = new ConsommateurDAO(dataSource);
        final SemaineDAO semaineDAO = new SemaineDAO(dataSource);
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, prod.getId());
            }
        };
        DAOModeleBuilder<Contrat> builder = new DAOModeleBuilder<Contrat>() {
            @Override
            public Contrat build(ResultSet rs) throws SQLException, DAOException {
                return new Contrat(rs.getInt("id"), rs.getInt("quantite"), rs.getInt("valide"), 
                        consommateurDAO.getConsommateur(rs.getInt("consommateur_id")),prod, 
                        semaineDAO.getSemaine(rs.getInt("debut_semaine_id"))
                );
            }
        };
            
        return this.getMultiple(builder, setter, "SELECT * FROM Contrat WHERE produit_id=?");
    }
}