package modele.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import modele.Consommateur;
import modele.Producteur;
import modele.Utilisateur;

public class ConsommateurDAO extends UtilisateurDAO {
    
    private static final String SELECT_CONSOMMATEURS = "SELECT * FROM Consommateur";
    private static final String SELECT_CONSOMMATEUR = "SELECT * FROM Consommateur WHERE id = ?";

    public ConsommateurDAO(DataSource ds) {
        super(ds, null, SELECT_CONSOMMATEURS, null, SELECT_CONSOMMATEUR);
    }

    public Consommateur addConsommateur(String nom, String prenom, String email, String adresse) {
            throw new UnsupportedOperationException();
    }
 
    public void modifyConsommateur(Consommateur consommateur, String nom, String prenom, String email, String adresse) {
            throw new UnsupportedOperationException();
    }

    public List<Consommateur> getConsommateurs() {
            throw new UnsupportedOperationException();
    }

    public Consommateur getConsommateur(final int id) throws DAOException {
        
        final ContratDAO contratDAO = new ContratDAO(super.dataSource);
        final ConsommateurDAO utilisateurDAO = new ConsommateurDAO(super.dataSource);
        System.out.println("1");
        DAOModeleBuilder<Consommateur> builder = new DAOModeleBuilder<Consommateur>() {
            
            @Override
            public Consommateur build(ResultSet rs) throws DAOException {
                try {
                    Utilisateur utilisateur = utilisateurDAO.getUtilisateur(rs.getInt("id"));
                    Consommateur conso = new Consommateur(rs.getShort("id"), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getAdresse(), null);
                    
        System.out.println("2");contratDAO.getContratsByConsommateur(conso);
                    return conso;
                } catch (SQLException ex) {
                    throw new DAOException(ex.getMessage(), ex);
                }
            }
        };
        System.out.println("1.2");
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
        
        return (Consommateur) super.get(builder, setter);
    }
}