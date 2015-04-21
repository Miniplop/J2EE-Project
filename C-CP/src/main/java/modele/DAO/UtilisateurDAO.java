package modele.DAO;

import java.util.List;
import javax.sql.DataSource;
import modele.Utilisateur;

public class UtilisateurDAO extends AbstractDAO {
    
    private static final String SELECT_UTILISATEURS = "SELECT * FROM Utilisateur";
    private static final String SELECT_UTILISATEUR = "SELECT * FROM Utilisateur WHERE id = ?";

    public UtilisateurDAO(DataSource ds, String insert_query, String select_query, String update_query, String select_by_id_query) {
        super(ds, insert_query, select_query, update_query, select_by_id_query);
    }
    
    public UtilisateurDAO(DataSource ds) {
        super(ds, null, SELECT_UTILISATEURS, null, SELECT_UTILISATEUR);
    }

    protected Utilisateur addUtilisateur(String nom, String prenom, String email, String adresse) {
            throw new UnsupportedOperationException();
    }

    protected void modifyUtilisateur(Utilisateur utilisateur, String nom, String prenom, String email, String adresse) {
            throw new UnsupportedOperationException();
    }

    protected List<Utilisateur> getUtilisateurs() {
            throw new UnsupportedOperationException();
    }

    Utilisateur getUtilisateur(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}