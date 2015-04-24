package modele.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import modele.Contrat;
import modele.Produit;
import modele.Consommateur;
import modele.Semaine;

public class ContratDAO extends AbstractDAO<Contrat> {
	private static final String INSERT_CONTRAT ="INSERT INTO Contrat (quantite, produit_id, consommateur_id) VALUES (?,?,?)";
	private static final String SELECT_CONTRATS = "SELECT * FROM Contrat";
	private static final String UPDATE_CONTRAT = "UPDATE Contrat SET valide=?, debut_semaine_id=? WHERE id=?";
	private static final String SELECT_CONTRAT = "SELECT * FROM Contrat WHERE id = ?";

    public ContratDAO(DataSource ds) {
        super(ds, INSERT_CONTRAT, SELECT_CONTRATS, UPDATE_CONTRAT);
    }

    public Contrat addContrat(final int quantite, final int produit_id, final Consommateur consommateur) throws DAOException {
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, quantite);
                statement.setInt(2, produit_id);
                statement.setInt(3, consommateur.getId());
            }
        };
        this.add(setter);
        return new Contrat(this.getLastId("Contrat"), quantite, consommateur, produit_id, null);
    }
    
    public int getCountContrat( final int user_id) throws DAOException{
         DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, user_id);
            }
        };
        final String SELECT_COUNT ="SELECT COUNT (id) FROM (SELECT * Contrat WHERE consommateur_id= ? ) ";
        return  super.getCount(setter,SELECT_COUNT);
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
        DAOModeleBuilder<Contrat> builder = new DAOModeleBuilder<Contrat>() {
            @Override
            public Contrat build(ResultSet rs) throws DAOException {
                Contrat contrat;
                try {
                    contrat = new Contrat(rs.getShort("id"), rs.getInt("quantite"), 
                            rs.getShort("valide"), rs.getInt("consommateur_id"), rs.getInt("produit_id"), rs.getInt("debut_semaine_id"));
                } catch (SQLException ex) {
                    throw new DAOException(ex.getMessage(), ex);
                }
                return contrat;
            }
        };
        return this.gets(builder);
    }

    public List<Contrat> getContratsByConsommateur(final Consommateur conso) throws DAOException {
        
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, conso.getId());
            }
        };
        DAOModeleBuilder<Contrat> builder = new DAOModeleBuilder<Contrat>() {

            @Override
            public Contrat build(ResultSet rs) throws SQLException, DAOException {
                return new Contrat(rs.getInt("id"), rs.getInt("quantite"), rs.getInt("valide"), conso, 
                        rs.getInt("produit_id"), rs.getInt("debut_semaine_id"));
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
         DAOModeleBuilder<Contrat> builder = new DAOModeleBuilder<Contrat>() {
                @Override
                public Contrat build(ResultSet rs) throws SQLException, DAOException {
                    return new Contrat(rs.getInt("id"), rs.getInt("quantite"), rs.getInt("valide"), rs.getInt("consommateur_id"),
                            rs.getInt("produit_id"), rs.getInt("debut_semaine_id"));
                }
            };
        
        return (Contrat) super.getSingle(builder, setter, ContratDAO.SELECT_CONTRAT);
    }

    public List<Contrat> getContratByProduit(final Produit prod) throws DAOException {
        DAOQueryParameter setter = new DAOQueryParameter() {
            @Override
            public void set(PreparedStatement statement) throws SQLException {
                statement.setInt(1, prod.getId());
            }
        };
        DAOModeleBuilder<Contrat> builder;
            builder = new DAOModeleBuilder<Contrat>() {
                @Override
                public Contrat build(ResultSet rs) throws SQLException, DAOException {
                    return new Contrat(rs.getInt("id"), rs.getInt("quantite"), rs.getInt("valide"), 
                            prod, rs.getInt("consommateur_id"), rs.getInt("debut_semaine_id"));
                }
            };
            
        return this.getMultiple(builder, setter, "SELECT * FROM Contrat WHERE produit_id=?");
    }
}